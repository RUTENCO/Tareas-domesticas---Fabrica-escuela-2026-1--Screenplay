package co.edu.udea.certificacion.caso13.caso13.tasks;

import co.edu.udea.certificacion.caso13.caso13.interactions.HacerClick;
import co.edu.udea.certificacion.caso13.caso13.userinterfaces.LoginPageUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

/**
 * Task orquestadora que navega a la página de registro.
 *
 * Patrón Screenplay (SRP):
 * - Responsabilidad ÚNICA: hacer clic en el enlace de registro
 * - ORQUESTACIÓN: Delega la acción técnica a Interaction:
 *   ✓ HacerClick.en() - no usa Click.on() nativo
 *
 * - No hay condicionales técnicas ni llamadas directas a Serenity
 * - Simple clic en GO_TO_REGISTER_LINK
 */
public class GoToRegistrationPage implements Task {

    public static GoToRegistrationPage fromLogin() {
        return Tasks.instrumented(GoToRegistrationPage.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(HacerClick.en(LoginPageUI.GO_TO_REGISTER_LINK));
    }
}
