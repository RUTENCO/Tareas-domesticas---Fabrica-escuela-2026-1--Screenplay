package co.edu.udea.certificacion.caso13.caso13.tasks;

import co.edu.udea.certificacion.caso13.caso13.interactions.HacerClick;
import co.edu.udea.certificacion.caso13.caso13.interactions.TipearTexto;
import co.edu.udea.certificacion.caso13.caso13.interactions.WaitExplicitly;
import co.edu.udea.certificacion.caso13.caso13.userinterfaces.LoginPageUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

/**
 * Task orquestadora que realiza login con credenciales específicas.
 *
 * Patrón Screenplay (SRP):
 * - Responsabilidad ÚNICA: orquestar el ingreso de credenciales y envío del formulario
 * - ORQUESTACIÓN: Delega TODAS las acciones técnicas a Interactions:
 *   ✓ TipearTexto.enCampo() - no usa Enter.theValue() nativo
 *   ✓ WaitExplicitly.forSeconds() - no usa Thread.sleep() nativo
 *   ✓ HacerClick.en() - no usa Click.on() nativo
 *
 * - No hay condicionales técnicas ni llamadas directas a Serenity
 * - Cadena de acciones atómica: Email → Esperar → Contraseña → Esperar → Clic
 *
 * Factory Pattern:
 * - Constructor privado, factory method 'using(email, password)'
 * - Parámetros encapsulados: email y password
 */
public class LoginWithCredentials implements Task {

    private final String email;
    private final String password;

    public LoginWithCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static LoginWithCredentials using(String email, String password) {
        return Tasks.instrumented(LoginWithCredentials.class, email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                TipearTexto.enCampo(email, LoginPageUI.EMAIL_INPUT),
                WaitExplicitly.forSeconds(2),
                TipearTexto.enCampo(password, LoginPageUI.PASSWORD_INPUT),
                WaitExplicitly.forSeconds(2),
                HacerClick.en(LoginPageUI.SIGN_IN_BUTTON)
        );
    }
}
