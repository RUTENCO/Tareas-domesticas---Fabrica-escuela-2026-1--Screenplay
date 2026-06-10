package co.edu.udea.certificacion.caso13.caso13.tasks;

import co.edu.udea.certificacion.caso13.caso13.interactions.HacerClick;
import co.edu.udea.certificacion.caso13.caso13.userinterfaces.LoginPageUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class GoToForgotPasswordPage implements Task {

    public static GoToForgotPasswordPage fromLogin() {
        return Tasks.instrumented(GoToForgotPasswordPage.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(HacerClick.en(LoginPageUI.GO_TO_FORGOT_PASSWORD_LINK));
    }
}
