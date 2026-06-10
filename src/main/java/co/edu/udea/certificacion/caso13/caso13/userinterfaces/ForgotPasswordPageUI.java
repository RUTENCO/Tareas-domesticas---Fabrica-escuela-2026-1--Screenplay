package co.edu.udea.certificacion.caso13.caso13.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public final class ForgotPasswordPageUI {

    public static final Target EMAIL_INPUT = Target.the("forgot password email input")
            .locatedBy("css:input[type='email']");

    public static final Target SEND_LINK_BUTTON = Target.the("send link button")
            .locatedBy("xpath://button[contains(., 'Enviar enlace')]");

    public static final Target GO_TO_LOGIN_LINK = Target.the("go back to login link")
            .locatedBy("xpath://a[contains(., 'Volver al inicio de sesión')]");

    private ForgotPasswordPageUI() {
    }
}
