package co.edu.udea.certificacion.caso13.caso13.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Hit;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.Keys;

public class PresionarTecla implements Interaction {

    private final Keys tecla;
    private final Target objetivo;

    public PresionarTecla(Keys tecla, Target objetivo) {
        this.tecla = tecla;
        this.objetivo = objetivo;
    }

    public static PresionarTecla en(Keys tecla, Target objetivo) {
        return Tasks.instrumented(PresionarTecla.class, tecla, objetivo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Hit.the(tecla).into(objetivo));
    }
}
