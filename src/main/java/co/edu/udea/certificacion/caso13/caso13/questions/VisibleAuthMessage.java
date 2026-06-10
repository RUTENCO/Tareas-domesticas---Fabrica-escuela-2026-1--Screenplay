package co.edu.udea.certificacion.caso13.caso13.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class VisibleAuthMessage implements Question<Boolean> {

    private static final String ALERT_SELECTOR = "div.bg-red-200, div.bg-green-200";

    private final String expectedMessage;

    public VisibleAuthMessage(String expectedMessage) {
        this.expectedMessage = expectedMessage;
    }

    public static VisibleAuthMessage withText(String expectedMessage) {
        return new VisibleAuthMessage(expectedMessage);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        List<WebElement> alerts = BrowseTheWeb.as(actor).getDriver().findElements(By.cssSelector(ALERT_SELECTOR));
        return alerts.stream()
                .map(WebElement::getText)
                .map(String::trim)
                .anyMatch(message -> message.equalsIgnoreCase(expectedMessage));
    }
}
