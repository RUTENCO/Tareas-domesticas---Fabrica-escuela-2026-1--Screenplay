package co.edu.udea.certificacion.caso13.caso13.tasks;

import co.edu.udea.certificacion.caso13.caso13.models.AuthUserData;
import co.edu.udea.certificacion.caso13.caso13.interactions.HacerClick;
import co.edu.udea.certificacion.caso13.caso13.interactions.TipearTexto;
import co.edu.udea.certificacion.caso13.caso13.interactions.WaitExplicitly;
import co.edu.udea.certificacion.caso13.caso13.userinterfaces.RegisterPageUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

/**
 * Task orquestadora que registra un nuevo usuario completando el formulario de registro.
 *
 * Patrón Screenplay (SRP):
 * - Responsabilidad ÚNICA: orquestar el llenado y envío del formulario de registro
 * - ORQUESTACIÓN: Delega TODAS las acciones de bajo nivel a Interactions:
 *   ✓ TipearTexto.enCampo() - no usa Enter.theValue() nativo
 *   ✓ WaitExplicitly.forSeconds() - no usa Thread.sleep() nativo
 *   ✓ HacerClick.en() - no usa Click.on() nativo
 *
 * - No hay condicionales técnicas ni llamadas directas a métodos nativos de Serenity
 * - Todo es delegación semántica de bajo nivel a Interactions
 *
 * Factory Pattern:
 * - Aceptar AuthUserData como parámetro encapsulado (patron builder implícito)
 */
public class RegisterUser implements Task {

    private final AuthUserData userData;

    public RegisterUser(AuthUserData userData) {
        this.userData = userData;
    }

    public static RegisterUser withData(AuthUserData userData) {
        return Tasks.instrumented(RegisterUser.class, userData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                TipearTexto.enCampo(userData.getName(), RegisterPageUI.NAME_INPUT),
                WaitExplicitly.forSeconds(2),
                TipearTexto.enCampo(userData.getEmail(), RegisterPageUI.EMAIL_INPUT),
                WaitExplicitly.forSeconds(2),
                TipearTexto.enCampo(userData.getPassword(), RegisterPageUI.PASSWORD_INPUT),
                WaitExplicitly.forSeconds(2),
                HacerClick.en(RegisterPageUI.JOIN_BUTTON)
        );
    }
}
