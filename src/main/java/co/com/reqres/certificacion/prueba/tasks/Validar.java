package co.com.reqres.certificacion.prueba.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.json.JSONObject;

public class Validar implements Task {
    String mensaje;

    public Validar(String mensaje) {
        this.mensaje = mensaje;
    }

    public static Validar mensaje(String mensaje) {
        return Instrumented.instanceOf(Validar.class).withProperties(mensaje);
    }

    @Override
    public <T extends Actor> void performAs(T t) {
        String respuesta = SerenityRest.lastResponse().getBody().asString();
        JSONObject jsonObject = new JSONObject(respuesta);
        String name = jsonObject.getString("name");
        Ensure.that(name).equals(mensaje);
    }
}
