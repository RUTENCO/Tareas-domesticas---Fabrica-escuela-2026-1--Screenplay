package co.edu.udea.certificacion.caso13.caso13.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class FieldValidationState implements Question<Boolean> {

    private final Target field;
    private final String state;

    public FieldValidationState(Target field, String state) {
        this.field = field;
        this.state = state;
    }

    public static FieldValidationState on(Target field, String state) {
        return new FieldValidationState(field, state);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        WebElement element = field.resolveFor(actor);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();

        if ("valid".equalsIgnoreCase(state)) {
            Object result = javascriptExecutor.executeScript("return arguments[0].validity.valid;", element);
            return Boolean.TRUE.equals(result);
        }

        Object result = javascriptExecutor.executeScript("return arguments[0].validity[arguments[1]];", element, state);
        return Boolean.TRUE.equals(result);
    }
}
