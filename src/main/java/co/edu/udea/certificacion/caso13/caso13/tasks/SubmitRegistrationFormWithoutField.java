package co.edu.udea.certificacion.caso13.caso13.tasks;

import co.edu.udea.certificacion.caso13.caso13.interactions.HacerClick;
import co.edu.udea.certificacion.caso13.caso13.interactions.TipearTexto;
import co.edu.udea.certificacion.caso13.caso13.userinterfaces.RegisterPageUI;
import co.edu.udea.certificacion.caso13.caso13.utils.TestDataFactory;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

/**
 * Task orquestadora para enviar formulario de registro omitiendo un campo específico.
 * 
 * Patrón Screenplay (SRP):
 * - Los if statements aquí son PERMITIDOS porque orquestan lógica de NEGOCIO
 *   (cuál campo probar), no condicionales técnicas de Serenity
 * - TODAS las acciones de bajo nivel delegadas a Interactions:
 *   ✓ TipearTexto.enCampo() - no usa Enter.theValue() nativo
 *   ✓ HacerClick.en() - no usa Click.on() nativo
 *
 * Regla: IF statements para control de flujo de negocio = ACEPTABLE
 *         IF statements para condicionales técnicas = PROHIBIDO
 *         Llamadas directas a Serenity (Click.on, Enter.theValue) = PROHIBIDO
 */
public class SubmitRegistrationFormWithoutField implements Task {

    private final String fieldToOmit;

    public SubmitRegistrationFormWithoutField(String fieldToOmit) {
        this.fieldToOmit = fieldToOmit;
    }

    public static SubmitRegistrationFormWithoutField omittingField(String fieldToOmit) {
        return Tasks.instrumented(SubmitRegistrationFormWithoutField.class, fieldToOmit);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Llenar campo nombre si no es el omitido
        if (!"name".equalsIgnoreCase(fieldToOmit)) {
            actor.attemptsTo(
                    TipearTexto.enCampo(TestDataFactory.getDefaultUserName(), RegisterPageUI.NAME_INPUT)
            );
        }

        // Llenar campo email si no es el omitido
        if (!"email".equalsIgnoreCase(fieldToOmit)) {
            actor.attemptsTo(
                    TipearTexto.enCampo(TestDataFactory.uniqueEmail("required.register"), RegisterPageUI.EMAIL_INPUT)
            );
        }

        // Llenar campo contraseña si no es el omitido
        if (!"password".equalsIgnoreCase(fieldToOmit)) {
            actor.attemptsTo(
                    TipearTexto.enCampo(TestDataFactory.getDefaultPassword(), RegisterPageUI.PASSWORD_INPUT)
            );
        }

        // Enviar formulario
        actor.attemptsTo(HacerClick.en(RegisterPageUI.JOIN_BUTTON));
    }
}
