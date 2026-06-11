package co.edu.udea.certificacion.caso13.caso13.stepdefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("qa-user");

        WebDriver driver = BrowseTheWeb.as(OnStage.theActorInTheSpotlight()).getDriver();
        driver.manage().deleteAllCookies();
        driver.navigate().to("https://project-tdwx8.vercel.app/login");

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.localStorage.clear(); window.sessionStorage.clear();");
        driver.navigate().refresh();
    }
}
