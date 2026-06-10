package co.edu.udea.certificacion.caso13.caso13.questions;

import co.edu.udea.certificacion.caso13.caso13.userinterfaces.DashboardPageUI;
import co.edu.udea.certificacion.caso13.caso13.userinterfaces.LoginPageUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RedirectTo implements Question<Boolean> {

    private final String expectedPath;

    private RedirectTo(String expectedPath) {
        this.expectedPath = expectedPath;
    }

    public static RedirectTo path(String expectedPath) {
        return new RedirectTo(expectedPath);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        // Wait for the appropriate UI element to be visible based on the redirect path
        Target expectedElement = resolveElementForPath(expectedPath);
        actor.attemptsTo(
                WaitUntil.the(expectedElement, isVisible()).forNoMoreThan(10).seconds()
        );

        // Verify the URL contains the expected path
        return CurrentUrlContains.path(expectedPath).answeredBy(actor);
    }

    private Target resolveElementForPath(String path) {
        return switch (path) {
            case "/dashboard" -> DashboardPageUI.LOGOUT_BUTTON;
            case "/login" -> LoginPageUI.SIGN_IN_BUTTON;
            default -> throw new IllegalArgumentException("Unknown redirect path: " + path);
        };
    }

    @Override
    public String toString() {
        return "redirect to " + expectedPath;
    }
}
