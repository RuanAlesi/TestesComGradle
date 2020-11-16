package steps;

import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

import static io.restassured.RestAssured.given;

public class StepsRestricoes {

    @Quando("eu consulto restricao pelo {string}")
    public void euConsultoRestricaoPelo(String cpf) {
        Steps.endereco += cpf;
        Steps.response =
                given()
                .when()
                    .get(Steps.endereco)
                .then()
                    .extract()
                    .response();
    }

    @Entao("deve ser retornado {int}")
    public void deve_ser_retornado(Integer statusCode) {
        Steps.response.then().statusCode(statusCode);
    }
}
