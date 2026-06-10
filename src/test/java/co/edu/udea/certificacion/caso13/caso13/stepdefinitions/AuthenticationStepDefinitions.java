package co.edu.udea.certificacion.caso13.caso13.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import co.edu.udea.certificacion.caso13.caso13.interactions.WaitExplicitly;
import co.edu.udea.certificacion.caso13.caso13.questions.FieldValidationState;
import co.edu.udea.certificacion.caso13.caso13.questions.RecoveryOutcomeMatches;
import co.edu.udea.certificacion.caso13.caso13.questions.RedirectTo;
import co.edu.udea.certificacion.caso13.caso13.questions.VisibleAuthMessage;
import co.edu.udea.certificacion.caso13.caso13.tasks.EnsureSeedUserIsRegistered;
import co.edu.udea.certificacion.caso13.caso13.tasks.GoToForgotPasswordPage;
import co.edu.udea.certificacion.caso13.caso13.tasks.GoToRegistrationPage;
import co.edu.udea.certificacion.caso13.caso13.tasks.LoginWithCredentials;
import co.edu.udea.certificacion.caso13.caso13.tasks.LogoutFromDashboard;
import co.edu.udea.certificacion.caso13.caso13.tasks.OpenLoginPage;
import co.edu.udea.certificacion.caso13.caso13.tasks.RegisterUser;
import co.edu.udea.certificacion.caso13.caso13.tasks.RequestPasswordRecovery;
import co.edu.udea.certificacion.caso13.caso13.tasks.SubmitLoginFormWithoutField;
import co.edu.udea.certificacion.caso13.caso13.tasks.SubmitRegistrationFormWithoutField;
import co.edu.udea.certificacion.caso13.caso13.utils.FieldTargetResolver;
import co.edu.udea.certificacion.caso13.caso13.utils.TestDataFactory;

public class AuthenticationStepDefinitions {

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
        OnStage.theActorInTheSpotlight().attemptsTo(
                RegisterUser.withData(TestDataFactory.createDefaultUserData(email))
        );
    }

    @When("the actor attempts to register with name {string}, email {string} and password {string}")
    public void theActorAttemptsToRegisterWithNameEmailAndPassword(String name, String email, String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                RegisterUser.withData(TestDataFactory.createUserWithData(name, email, password))
        );
    }

    @Given("an existing registered user with email {string} and password {string}")
    public void anExistingRegisteredUserWithEmailAndPassword(String email, String password) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                EnsureSeedUserIsRegistered.withEmailAndPassword(email, password)
        );
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
        OnStage.theActorInTheSpotlight().attemptsTo(
                SubmitRegistrationFormWithoutField.omittingField(field)
        );
    }

    @When("the actor attempts to submit the login form with an empty {string}")
    public void theActorAttemptsToSubmitTheLoginFormWithAnEmptyField(String field) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                SubmitLoginFormWithoutField.omittingField(field)
        );
    }

    @When("the actor attempts to submit the recovery form with an empty email")
    public void theActorAttemptsToSubmitTheRecoveryFormWithAnEmptyEmail() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                RequestPasswordRecovery.forEmail("")
        );
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
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(RecoveryOutcomeMatches.withMessageAndState(message, validationState))
        );
    }

    @Then("the actor should be redirected to {string}")
    public void theActorShouldBeRedirectedTo(String expectedPath) {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(RedirectTo.path(expectedPath))
        );
    }

    @Then("the registration {word} field should have validation state {string}")
    public void theRegistrationFieldShouldHaveValidationState(String field, String state) {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(
                        FieldValidationState.on(FieldTargetResolver.getRegistrationFieldTarget(field), state)
                )
        );
    }

    @Then("the login {word} field should have validation state {string}")
    public void theLoginFieldShouldHaveValidationState(String field, String state) {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(
                        FieldValidationState.on(FieldTargetResolver.getLoginFieldTarget(field), state)
                )
        );
    }

    @Then("the recovery email field should have validation state {string}")
    public void theRecoveryEmailFieldShouldHaveValidationState(String state) {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(
                        FieldValidationState.on(FieldTargetResolver.getRecoveryFieldTarget("email"), state)
                )
        );
    }
}
