package co.edu.udea.certificacion.caso13.caso13.utils;

import co.edu.udea.certificacion.caso13.caso13.userinterfaces.ForgotPasswordPageUI;
import co.edu.udea.certificacion.caso13.caso13.userinterfaces.LoginPageUI;
import co.edu.udea.certificacion.caso13.caso13.userinterfaces.RegisterPageUI;
import net.serenitybdd.screenplay.targets.Target;

public final class FieldTargetResolver {

    private FieldTargetResolver() {
    }

    /**
     * Resolves a field name to its corresponding Target in the registration form.
     *
     * @param fieldName the name of the field (e.g., "name", "email", "password")
     * @return the Target corresponding to the field
     * @throws IllegalArgumentException if the field name is not recognized
     */
    public static Target getRegistrationFieldTarget(String fieldName) {
        return switch (fieldName.toLowerCase()) {
            case "name" -> RegisterPageUI.NAME_INPUT;
            case "email" -> RegisterPageUI.EMAIL_INPUT;
            case "password" -> RegisterPageUI.PASSWORD_INPUT;
            default -> throw new IllegalArgumentException("Unknown registration field: " + fieldName);
        };
    }

    /**
     * Resolves a field name to its corresponding Target in the login form.
     *
     * @param fieldName the name of the field (e.g., "email", "password")
     * @return the Target corresponding to the field
     * @throws IllegalArgumentException if the field name is not recognized
     */
    public static Target getLoginFieldTarget(String fieldName) {
        return switch (fieldName.toLowerCase()) {
            case "email" -> LoginPageUI.EMAIL_INPUT;
            case "password" -> LoginPageUI.PASSWORD_INPUT;
            default -> throw new IllegalArgumentException("Unknown login field: " + fieldName);
        };
    }

    /**
     * Resolves a field name to its corresponding Target in the password recovery form.
     *
     * @param fieldName the name of the field (e.g., "email")
     * @return the Target corresponding to the field
     * @throws IllegalArgumentException if the field name is not recognized
     */
    public static Target getRecoveryFieldTarget(String fieldName) {
        return switch (fieldName.toLowerCase()) {
            case "email" -> ForgotPasswordPageUI.EMAIL_INPUT;
            default -> throw new IllegalArgumentException("Unknown recovery field: " + fieldName);
        };
    }
}
