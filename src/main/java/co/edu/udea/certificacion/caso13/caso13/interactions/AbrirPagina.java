package co.edu.udea.certificacion.caso13.caso13.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Open;

/**
 * Interaction de bajo nivel para abrir una URL en el navegador.
 * Encapsula la acción técnica de Serenity Open.url() 
 * manteniendo la semántica de la capa de Interactions.
 *
 * Patrón Screenplay:
 * - Esta es una acción ATÓMICA de bajo nivel
 * - Responsabilidad única: navegar a una URL
 * - No pertenece en Tasks (aquí va)
 * - Tasks la orquestan sin conocer detalles técnicos
 */
public class AbrirPagina implements Interaction {

    private final String url;

    public AbrirPagina(String url) {
        this.url = url;
    }

    public static AbrirPagina en(String url) {
        return new AbrirPagina(url);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(url));
    }
}
