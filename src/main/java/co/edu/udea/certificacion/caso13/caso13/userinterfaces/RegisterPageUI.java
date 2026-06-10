package co.edu.udea.certificacion.caso13.caso13.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public final class RegisterPageUI {

    public static final Target NAME_INPUT = Target.the("registration name input")
            .locatedBy("css:input[type='text']");

    public static final Target EMAIL_INPUT = Target.the("registration email input")
            .locatedBy("css:input[type='email']");

    public static final Target PASSWORD_INPUT = Target.the("registration password input")
            .locatedBy("css:input[type='password']");

    public static final Target JOIN_BUTTON = Target.the("join button")
            .locatedBy("xpath://button[normalize-space()='Unirse']");

    public static final Target GO_TO_LOGIN_LINK = Target.the("go to login link from register")
            .locatedBy("xpath://a[contains(., 'Inicia Sesión')]");

    private RegisterPageUI() {
    }
}
