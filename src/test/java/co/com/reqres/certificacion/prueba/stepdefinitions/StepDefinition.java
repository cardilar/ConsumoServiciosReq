package co.com.reqres.certificacion.prueba.stepdefinitions;

import co.com.reqres.certificacion.prueba.questions.LastResponseStatusCode;
import co.com.reqres.certificacion.prueba.tasks.ObtenerInformacion;
import co.com.reqres.certificacion.prueba.tasks.Validar;
import co.com.reqres.certificacion.prueba.utils.Constantes;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Consequence;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.hamcrest.Matchers;

import java.util.regex.Matcher;

public class StepDefinition {

    @Before
    public void config(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("user");
        OnStage.theActorInTheSpotlight().whoCan(CallAnApi.at(Constantes.API_REQRES));
    }

    @Given("^Consumo el api con la peticion (.+) y endpoint (.+) y body(.+)$")
    public void consumoElApiConLaPeticion(String metodo,String endpoint, String body) {
        OnStage.theActorInTheSpotlight().wasAbleTo(
            ObtenerInformacion.usuario(metodo, endpoint, body)
        );
    }

    @Cuando("^verifico el estado de la peticion (\\d+)$")
    public void verificoElEstadoDeLaPeticionStatusCode(int statusCode) {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat("", LastResponseStatusCode.is(), Matchers.equalTo(statusCode)));
    }


    @Entonces("^valido la respuesta esperada (.+)$")
    public void validoLaRespuestaEsperadaMensaje(String mensaje) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                Validar.mensaje(mensaje)
        );
    }
}
