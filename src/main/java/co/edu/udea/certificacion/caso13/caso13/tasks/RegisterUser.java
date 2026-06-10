package co.edu.udea.certificacion.caso13.caso13.tasks;

import co.edu.udea.certificacion.caso13.caso13.models.AuthUserData;
import co.edu.udea.certificacion.caso13.caso13.interactions.HacerClick;
import co.edu.udea.certificacion.caso13.caso13.interactions.TipearTexto;
import co.edu.udea.certificacion.caso13.caso13.interactions.WaitExplicitly;
import co.edu.udea.certificacion.caso13.caso13.userinterfaces.RegisterPageUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class RegisterUser implements Task {

    private final AuthUserData userData;

    public RegisterUser(AuthUserData userData) {
        this.userData = userData;
    }

    public static RegisterUser withData(AuthUserData userData) {
        return Tasks.instrumented(RegisterUser.class, userData);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                TipearTexto.enCampo(userData.getName(), RegisterPageUI.NAME_INPUT),
                WaitExplicitly.forSeconds(1),
                TipearTexto.enCampo(userData.getEmail(), RegisterPageUI.EMAIL_INPUT),
                WaitExplicitly.forSeconds(1),
                TipearTexto.enCampo(userData.getPassword(), RegisterPageUI.PASSWORD_INPUT),
                WaitExplicitly.forSeconds(1),
                HacerClick.en(RegisterPageUI.JOIN_BUTTON)
        );
    }
}
