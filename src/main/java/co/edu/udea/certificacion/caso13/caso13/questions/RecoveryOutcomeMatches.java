package co.edu.udea.certificacion.caso13.caso13.questions;

import co.edu.udea.certificacion.caso13.caso13.utils.FieldTargetResolver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class RecoveryOutcomeMatches implements Question<Boolean> {

    private final String message;
    private final String validationState;

    private RecoveryOutcomeMatches(String message, String validationState) {
        this.message = message;
        this.validationState = validationState;
    }

    public static RecoveryOutcomeMatches withMessageAndState(String message, String validationState) {
        return new RecoveryOutcomeMatches(message, validationState);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        // If message is provided (not N/A), validate that the message is visible
        if (!"N/A".equalsIgnoreCase(message)) {
            return VisibleAuthMessage.withText(message).answeredBy(actor);
        }

        // Otherwise, validate the field's validation state
        return FieldValidationState.on(
                FieldTargetResolver.getRecoveryFieldTarget("email"),
                validationState
        ).answeredBy(actor);
    }

    @Override
    public String toString() {
        return "recovery outcome matches message '" + message + "' or validation state '" + validationState + "'";
    }
}
