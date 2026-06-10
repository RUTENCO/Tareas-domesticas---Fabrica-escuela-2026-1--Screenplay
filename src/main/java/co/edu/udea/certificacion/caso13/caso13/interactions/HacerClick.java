package co.edu.udea.certificacion.caso13.caso13.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;

public class HacerClick implements Interaction {

    private final Target objetivo;

    public HacerClick(Target objetivo) {
        this.objetivo = objetivo;
    }

    public static HacerClick en(Target objetivo) {
        return Tasks.instrumented(HacerClick.class, objetivo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(objetivo));
    }
}
