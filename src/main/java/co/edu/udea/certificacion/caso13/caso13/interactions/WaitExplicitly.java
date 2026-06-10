package co.edu.udea.certificacion.caso13.caso13.interactions;

import co.edu.udea.certificacion.caso13.caso13.utils.TimeWait;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;

public class WaitExplicitly implements Interaction {

    private final int seconds;

    public WaitExplicitly(int seconds) {
        this.seconds = seconds;
    }

    public static WaitExplicitly forSeconds(int seconds) {
        return Tasks.instrumented(WaitExplicitly.class, seconds);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        TimeWait.forSeconds(seconds);
    }
}