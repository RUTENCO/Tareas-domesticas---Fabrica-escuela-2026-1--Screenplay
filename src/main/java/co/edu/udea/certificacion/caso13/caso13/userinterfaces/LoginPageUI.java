package co.edu.udea.certificacion.caso13.caso13.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public final class LoginPageUI {

    public static final Target EMAIL_INPUT = Target.the("login email input")
            .locatedBy("css:input[type='email']");

    public static final Target PASSWORD_INPUT = Target.the("login password input")
            .locatedBy("css:input[type='password']");

    public static final Target SIGN_IN_BUTTON = Target.the("sign in button")
            .locatedBy("xpath://button[normalize-space()='Entrar']");

    public static final Target GO_TO_REGISTER_LINK = Target.the("go to register link")
            .locatedBy("xpath://a[contains(., 'Regístrate')]");

    public static final Target GO_TO_FORGOT_PASSWORD_LINK = Target.the("go to forgot password link")
            .locatedBy("xpath://a[contains(., '¿Olvidaste tu contraseña?')]");

    private LoginPageUI() {
    }
}
