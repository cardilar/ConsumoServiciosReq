package co.com.reqres.certificacion.prueba.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.abilities.CallAnApi.as;

public class Post extends RestInteraction {

    private final String resource;
    private final String body;

    public Post(String resource, String body) {
        this.resource = resource;
        this.body = body;
    }

    @Step("{0} executes a POST on the resource #resource")
    @Override
    public <T extends Actor> void performAs(T actor) {
        rest().log().all().body(body).post(as(actor).resolve(resource)).then().log().all();
    }

    public static Post to(String resource, String body) { return instrumented(Post.class, resource, body); }

}