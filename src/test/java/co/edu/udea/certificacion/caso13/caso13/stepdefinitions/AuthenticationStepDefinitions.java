package co.edu.udea.certificacion.caso13.caso13.stepdefinitions;

import co.edu.udea.certificacion.caso13.caso13.models.AuthUserData;
import co.edu.udea.certificacion.caso13.caso13.interactions.HacerClick;
import co.edu.udea.certificacion.caso13.caso13.interactions.TipearTexto;
import co.edu.udea.certificacion.caso13.caso13.interactions.WaitExplicitly;
import co.edu.udea.certificacion.caso13.caso13.questions.CurrentUrlContains;
import co.edu.udea.certificacion.caso13.caso13.questions.FieldValidationState;
import co.edu.udea.certificacion.caso13.caso13.questions.VisibleAuthMessage;
import co.edu.udea.certificacion.caso13.caso13.tasks.GoToForgotPasswordPage;
import co.edu.udea.certificacion.caso13.caso13.tasks.GoToRegistrationPage;
import co.edu.udea.certificacion.caso13.caso13.tasks.LoginWithCredentials;
import co.edu.udea.certificacion.caso13.caso13.tasks.LogoutFromDashboard;
import co.edu.udea.certificacion.caso13.caso13.tasks.OpenLoginPage;
import co.edu.udea.certificacion.caso13.caso13.tasks.RegisterUser;
import co.edu.udea.certificacion.caso13.caso13.tasks.RequestPasswordRecovery;
import co.edu.udea.certificacion.caso13.caso13.userinterfaces.DashboardPageUI;
import co.edu.udea.certificacion.caso13.caso13.userinterfaces.ForgotPasswordPageUI;
import co.edu.udea.certificacion.caso13.caso13.userinterfaces.LoginPageUI;
import co.edu.udea.certificacion.caso13.caso13.userinterfaces.RegisterPageUI;
import co.edu.udea.certificacion.caso13.caso13.utils.TestDataFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AuthenticationStepDefinitions {

    private static final String DEFAULT_NAME = "QA Automation";

    @Given("the actor opens the login page")
    public void theActorOpensTheLoginPage() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                OpenLoginPage.now(),
                WaitExplicitly.forSeconds(1)
        );
    }

    @When("the actor navigates to the registration page")
    public void theActorNavigatesToTheRegistrationPage() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                GoToRegistrationPage.fromLogin(),
                WaitExplicitly.forSeconds(1)
        );
    }

    @When("the actor navigates to the forgot password page")
    public void theActorNavigatesToTheForgotPasswordPage() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                GoToForgotPasswordPage.fromLogin(),
                WaitExplicitly.forSeconds(1)
        );
    }

    @When("the actor registers with a unique valid user")
    public void theActorRegistersWithAUniqueValidUser() {
        String email = TestDataFactory.uniqueEmail("register.valid");
        AuthUserData userData = new AuthUserData(DEFAULT_NAME, email, "Abcd1234!");
        OnStage.theActorInTheSpotlight().attemptsTo(RegisterUser.withData(userData));
    }

    @When("the actor attempts to register with name {string}, email {string} and password {string}")
    public void theActorAttemptsToRegisterWithNameEmailAndPassword(String name, String email, String password) {
        AuthUserData userData = new AuthUserData(name, email, password);
        OnStage.theActorInTheSpotlight().attemptsTo(RegisterUser.withData(userData));
    }

    @Given("an existing registered user with email {string} and password {string}")
    public void anExistingRegisteredUserWithEmailAndPassword(String email, String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                OpenLoginPage.now(),
                GoToRegistrationPage.fromLogin(),
                RegisterUser.withData(new AuthUserData("Seed User", email, password))
        );

        boolean isAlreadyRegistered = VisibleAuthMessage.withText("El email ya está registrado")
                .answeredBy(OnStage.theActorInTheSpotlight());

        if (!isAlreadyRegistered) {
            OnStage.theActorInTheSpotlight().attemptsTo(LogoutFromDashboard.now());
        }
    }

    @When("the actor logs in with email {string} and password {string}")
    public void theActorLogsInWithEmailAndPassword(String email, String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(LoginWithCredentials.using(email, password));
    }

    @When("the actor logs out from the dashboard")
    public void theActorLogsOutFromTheDashboard() {
        OnStage.theActorInTheSpotlight().attemptsTo(LogoutFromDashboard.now());
    }

    @When("the actor requests password recovery for email {string}")
    public void theActorRequestsPasswordRecoveryForEmail(String email) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                RequestPasswordRecovery.forEmail(email),
                WaitExplicitly.forSeconds(1)
        );
    }

    @When("the actor attempts to submit the registration form with an empty {string}")
    public void theActorAttemptsToSubmitTheRegistrationFormWithAnEmptyField(String field) {
        if (!"name".equalsIgnoreCase(field)) {
            OnStage.theActorInTheSpotlight().attemptsTo(TipearTexto.enCampo(DEFAULT_NAME, RegisterPageUI.NAME_INPUT));
        }

        if (!"email".equalsIgnoreCase(field)) {
            OnStage.theActorInTheSpotlight().attemptsTo(
                    TipearTexto.enCampo(TestDataFactory.uniqueEmail("required.register"), RegisterPageUI.EMAIL_INPUT)
            );
        }

        if (!"password".equalsIgnoreCase(field)) {
            OnStage.theActorInTheSpotlight().attemptsTo(TipearTexto.enCampo("Abcd1234!", RegisterPageUI.PASSWORD_INPUT));
        }

        OnStage.theActorInTheSpotlight().attemptsTo(HacerClick.en(RegisterPageUI.JOIN_BUTTON));
    }

    @When("the actor attempts to submit the login form with an empty {string}")
    public void theActorAttemptsToSubmitTheLoginFormWithAnEmptyField(String field) {
        if (!"email".equalsIgnoreCase(field)) {
            OnStage.theActorInTheSpotlight().attemptsTo(
                    TipearTexto.enCampo(TestDataFactory.uniqueEmail("required.login"), LoginPageUI.EMAIL_INPUT)
            );
        }

        if (!"password".equalsIgnoreCase(field)) {
            OnStage.theActorInTheSpotlight().attemptsTo(TipearTexto.enCampo("Abcd1234!", LoginPageUI.PASSWORD_INPUT));
        }

        OnStage.theActorInTheSpotlight().attemptsTo(HacerClick.en(LoginPageUI.SIGN_IN_BUTTON));
    }

    @When("the actor attempts to submit the recovery form with an empty email")
    public void theActorAttemptsToSubmitTheRecoveryFormWithAnEmptyEmail() {
        OnStage.theActorInTheSpotlight().attemptsTo(HacerClick.en(ForgotPasswordPageUI.SEND_LINK_BUTTON));
    }

    @Then("the actor should see the message {string}")
    public void theActorShouldSeeTheMessage(String expectedMessage) {
        OnStage.theActorInTheSpotlight().attemptsTo(WaitExplicitly.forSeconds(1));
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(VisibleAuthMessage.withText(expectedMessage))
        );
    }

    @Then("the recovery outcome should show message {string} and validation state {string}")
    public void theRecoveryOutcomeShouldShowMessageAndValidationState(String message, String validationState) {
        if (!"N/A".equalsIgnoreCase(message)) {
            theActorShouldSeeTheMessage(message);
            return;
        }

        theRecoveryEmailFieldShouldHaveValidationState(validationState);
    }

    @Then("the actor should be redirected to {string}")
    public void theActorShouldBeRedirectedTo(String expectedPath) {
        if ("/dashboard".equals(expectedPath)) {
            OnStage.theActorInTheSpotlight().attemptsTo(
                    WaitUntil.the(DashboardPageUI.LOGOUT_BUTTON, isVisible()).forNoMoreThan(10).seconds()
            );
        }

        if ("/login".equals(expectedPath)) {
            OnStage.theActorInTheSpotlight().attemptsTo(
                    WaitUntil.the(LoginPageUI.SIGN_IN_BUTTON, isVisible()).forNoMoreThan(10).seconds()
            );
        }

        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(CurrentUrlContains.path(expectedPath))
        );
    }

    @Then("the registration {word} field should have validation state {string}")
    public void theRegistrationFieldShouldHaveValidationState(String field, String state) {
        if ("name".equalsIgnoreCase(field)) {
            OnStage.theActorInTheSpotlight().should(
                    GivenWhenThen.seeThat(FieldValidationState.on(RegisterPageUI.NAME_INPUT, state))
            );
            return;
        }

        if ("email".equalsIgnoreCase(field)) {
            OnStage.theActorInTheSpotlight().should(
                    GivenWhenThen.seeThat(FieldValidationState.on(RegisterPageUI.EMAIL_INPUT, state))
            );
            return;
        }

        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(FieldValidationState.on(RegisterPageUI.PASSWORD_INPUT, state))
        );
    }

    @Then("the login {word} field should have validation state {string}")
    public void theLoginFieldShouldHaveValidationState(String field, String state) {
        if ("email".equalsIgnoreCase(field)) {
            OnStage.theActorInTheSpotlight().should(
                    GivenWhenThen.seeThat(FieldValidationState.on(LoginPageUI.EMAIL_INPUT, state))
            );
            return;
        }

        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(FieldValidationState.on(LoginPageUI.PASSWORD_INPUT, state))
        );
    }

    @Then("the recovery email field should have validation state {string}")
    public void theRecoveryEmailFieldShouldHaveValidationState(String state) {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(FieldValidationState.on(ForgotPasswordPageUI.EMAIL_INPUT, state))
        );
    }
}
