package co.edu.udea.certificacion.caso13.caso13.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

public class TipearTexto implements Interaction {

    private final String texto;
    private final Target objetivo;

    public TipearTexto(String texto, Target objetivo) {
        this.texto = texto;
        this.objetivo = objetivo;
    }

    public static TipearTexto enCampo(String texto, Target objetivo) {
        return Tasks.instrumented(TipearTexto.class, texto, objetivo);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(texto).into(objetivo));
    }
}
