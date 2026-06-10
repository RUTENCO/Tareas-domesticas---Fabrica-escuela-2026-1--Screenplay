package co.edu.udea.certificacion.caso13.caso13.tasks;

import co.edu.udea.certificacion.caso13.caso13.interactions.WaitExplicitly;
import co.edu.udea.certificacion.caso13.caso13.questions.VisibleAuthMessage;
import co.edu.udea.certificacion.caso13.caso13.utils.TestDataFactory;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

/**
 * Task orquestadora que garantiza la existencia de un usuario semilla registrado.
 * 
 * Patrón Screenplay (SRP):
 * - Responsabilidad ÚNICA: orquestar el flujo de registro del usuario semilla
 * - ORQUESTACIÓN: Delega TODAS las acciones técnicas a Tasks/Interactions/Questions:
 *   ✓ OpenLoginPage - navegación
 *   ✓ GoToRegistrationPage - navegación dentro de página
 *   ✓ RegisterUser - llenar y enviar formulario
 *   ✓ VisibleAuthMessage - validación de resultado
 *   ✓ WaitExplicitly - pausa
 *   ✓ LogoutFromDashboard - limpieza
 *
 * - El if statement aquí es LÓGICA DE NEGOCIO (manejo de duplicados), NO condicional técnica
 * - No hay llamadas directas a métodos nativos de Serenity
 *
 * Flujo:
 * 1. Abre login
 * 2. Navega a registro
 * 3. Intenta registrar usuario semilla
 * 4. Si el email ya existe → sale (usuario ya registrado)
 * 5. Si NO existe → logout para limpiar estado
 */
public class EnsureSeedUserIsRegistered implements Task {

    private final String email;

    public EnsureSeedUserIsRegistered(String email) {
        this.email = email;
    }

    public static EnsureSeedUserIsRegistered withEmailAndPassword(String email, String password) {
        return Tasks.instrumented(EnsureSeedUserIsRegistered.class, email);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OpenLoginPage.now(),
                GoToRegistrationPage.fromLogin(),
                RegisterUser.withData(TestDataFactory.createSeedUserData(email))
        );

        // Verificar si el email ya estaba registrado
        boolean isAlreadyRegistered = VisibleAuthMessage.withText("El email ya está registrado")
                .answeredBy(actor);

        // Si NO estaba registrado previamente, logout para limpiar el estado
        // Lógica de negocio: usuario nuevo debe limpiarse para mantener precondiciones
        if (!isAlreadyRegistered) {
            actor.attemptsTo(
                    WaitExplicitly.forSeconds(1),
                    LogoutFromDashboard.now()
            );
        }
    }
}
