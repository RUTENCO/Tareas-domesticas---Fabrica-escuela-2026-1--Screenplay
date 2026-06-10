package co.edu.udea.certificacion.caso13.caso13.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class CurrentUrlContains implements Question<Boolean> {

    private final String expectedPath;

    public CurrentUrlContains(String expectedPath) {
        this.expectedPath = expectedPath;
    }

    public static CurrentUrlContains path(String expectedPath) {
        return new CurrentUrlContains(expectedPath);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return BrowseTheWeb.as(actor).getDriver().getCurrentUrl().contains(expectedPath);
    }
}
