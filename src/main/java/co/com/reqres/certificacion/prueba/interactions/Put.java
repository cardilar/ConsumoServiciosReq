package co.com.reqres.certificacion.prueba.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.abilities.CallAnApi.as;

public class Put extends RestInteraction {

    String resource;
    String body;

    public Put(String resource, String body) {
        this.resource = resource;
        this.body = body;
    }

    @Step("{0} execute a PUT on the resource #resource")
    @Override
    public <T extends Actor> void performAs(T actor) {
        rest().log().all().body(body).put(as(actor).resolve(resource)).then().log().all();
    }

    public static Put to(String resource, String body) { return instrumented(Put.class, resource,body); }

}