package co.edu.udea.certificacion.caso13.caso13.tasks;

import co.edu.udea.certificacion.caso13.caso13.interactions.HacerClick;
import co.edu.udea.certificacion.caso13.caso13.userinterfaces.DashboardPageUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

/**
 * Task orquestadora que realiza logout desde el dashboard.
 *
 * Patrón Screenplay (SRP):
 * - Responsabilidad ÚNICA: hacer clic en el botón de logout
 * - ORQUESTACIÓN: Delega la acción técnica a Interaction:
 *   ✓ HacerClick.en() - no usa Click.on() nativo
 *
 * - No hay condicionales técnicas ni llamadas directas a Serenity
 * - Simple clic en LOGOUT_BUTTON
 */
public class LogoutFromDashboard implements Task {

    public static LogoutFromDashboard now() {
        return Tasks.instrumented(LogoutFromDashboard.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(HacerClick.en(DashboardPageUI.LOGOUT_BUTTON));
    }
}