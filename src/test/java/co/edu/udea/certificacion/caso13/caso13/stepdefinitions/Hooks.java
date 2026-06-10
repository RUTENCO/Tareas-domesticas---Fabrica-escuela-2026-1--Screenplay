package co.edu.udea.certificacion.caso13.caso13.stepdefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Managed(driver = "chrome")
    WebDriver driver;

    @Before
    public void setUp() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("qa-user");

        driver.manage().deleteAllCookies();
        driver.navigate().to("https://project-tdwx8.vercel.app/login");

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.localStorage.clear(); window.sessionStorage.clear();");
        driver.navigate().refresh();
    }
}
