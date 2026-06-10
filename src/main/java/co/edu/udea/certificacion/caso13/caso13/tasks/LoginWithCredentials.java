package co.edu.udea.certificacion.caso13.caso13.tasks;

import co.edu.udea.certificacion.caso13.caso13.interactions.HacerClick;
import co.edu.udea.certificacion.caso13.caso13.interactions.TipearTexto;
import co.edu.udea.certificacion.caso13.caso13.interactions.WaitExplicitly;
import co.edu.udea.certificacion.caso13.caso13.userinterfaces.LoginPageUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class LoginWithCredentials implements Task {

    private final String email;
    private final String password;

    public LoginWithCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static LoginWithCredentials using(String email, String password) {
        return Tasks.instrumented(LoginWithCredentials.class, email, password);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                TipearTexto.enCampo(email, LoginPageUI.EMAIL_INPUT),
                WaitExplicitly.forSeconds(1),
                TipearTexto.enCampo(password, LoginPageUI.PASSWORD_INPUT),
                WaitExplicitly.forSeconds(1),
                HacerClick.en(LoginPageUI.SIGN_IN_BUTTON)
        );
    }
}
