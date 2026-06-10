package co.edu.udea.certificacion.caso13.caso13.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public final class DashboardPageUI {

    public static final Target LOGOUT_BUTTON = Target.the("logout button")
            .locatedBy("xpath://button[normalize-space()='Salir']");

    private DashboardPageUI() {
    }
}
