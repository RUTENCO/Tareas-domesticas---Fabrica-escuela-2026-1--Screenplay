package co.edu.udea.certificacion.caso13.caso13.tasks;

import co.edu.udea.certificacion.caso13.caso13.interactions.PresionarTecla;
import co.edu.udea.certificacion.caso13.caso13.interactions.TipearTexto;
import co.edu.udea.certificacion.caso13.caso13.interactions.WaitExplicitly;
import co.edu.udea.certificacion.caso13.caso13.userinterfaces.ForgotPasswordPageUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import org.openqa.selenium.Keys;

/**
 * Task orquestadora que solicita recuperación de contraseña.
 *
 * Patrón Screenplay (SRP):
 * - Responsabilidad ÚNICA: orquestar el envío de solicitud de recuperación
 * - ORQUESTACIÓN: Delega TODAS las acciones técnicas a Interactions:
 *   ✓ TipearTexto.enCampo() - no usa Enter.theValue() nativo
 *   ✓ WaitExplicitly.forSeconds() - no usa Thread.sleep() nativo
 *   ✓ PresionarTecla.en() - no usa Hit.the() o SendKeys() nativo
 *
 * - No hay condicionales técnicas ni llamadas directas a Serenity
 * - Cadena de acciones atómica: Email → Esperar → Presionar ENTER
 *
 * Factory Pattern:
 * - Constructor privado, factory method 'forEmail(email)'
 * - Parámetro encapsulado: email
 */
public class RequestPasswordRecovery implements Task {

    private final String email;

    public RequestPasswordRecovery(String email) {
        this.email = email;
    }

    public static RequestPasswordRecovery forEmail(String email) {
        return Tasks.instrumented(RequestPasswordRecovery.class, email);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                TipearTexto.enCampo(email, ForgotPasswordPageUI.EMAIL_INPUT),
                WaitExplicitly.forSeconds(1),
                PresionarTecla.en(Keys.ENTER, ForgotPasswordPageUI.EMAIL_INPUT)
        );
    }
}