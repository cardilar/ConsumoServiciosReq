package co.com.reqres.certificacion.prueba.tasks;

import co.com.reqres.certificacion.prueba.interactions.Delete;
import co.com.reqres.certificacion.prueba.interactions.Get;
import co.com.reqres.certificacion.prueba.interactions.Post;
import co.com.reqres.certificacion.prueba.interactions.Put;
import co.com.reqres.certificacion.prueba.utils.Constantes;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class ObtenerInformacion implements Task {

    String metodo;
    String endpoint;
    String body;

    public ObtenerInformacion(String metodo, String endpoint,String body) {
        this.metodo = metodo;
        this.endpoint = endpoint;
        this.body = body;
    }

    public static ObtenerInformacion usuario(String metodo, String endpoint, String body) {
        return Instrumented.instanceOf(ObtenerInformacion.class).withProperties(metodo, endpoint, body);
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        switch (metodo){
            case "post":
                t.attemptsTo(
                        Post.to(endpoint, body)
                );
                break;
            case "put":
                t.attemptsTo(
                        Put.to(endpoint, body)
                );
                break;
            case "delete":
                t.attemptsTo(
                        Delete.resource(endpoint)
                );
                break;
            default:
                t.attemptsTo(
                        Get.resource(endpoint)
                );
                break;
        }

    }
}
