package co.edu.udea.certificacion.caso13.caso13.tasks;

import co.edu.udea.certificacion.caso13.caso13.interactions.AbrirPagina;
import co.edu.udea.certificacion.caso13.caso13.utils.AuthRoutes;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

/**
 * Task orquestadora que abre la página de login.
 * Delegación semántica: OpenLoginPage es el "qué" (BusinessLevel),
 * AbrirPágina es el "cómo" (Technical implementation).
 *
 * SRP: Esta Task NO conoce detalles técnicos de navegación.
 * Esa responsabilidad pertenece exclusivamente a AbrirPágina (Interaction).
 */
public class OpenLoginPage implements Task {

    public static OpenLoginPage now() {
        return Tasks.instrumented(OpenLoginPage.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(AbrirPagina.en(AuthRoutes.LOGIN));
    }
}
