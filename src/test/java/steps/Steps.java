package steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import java.util.Map;

import static io.restassured.RestAssured.given;

public class Steps {
    public static String endereco;
    public static Response response;
    public static RequestSpecification request;
    public static String body;
    public static String simulacaoId;

    @Dado("o end-point {string}")
    public void oEndPoint(String endereco){
        this.endereco = endereco;
    }


    @Quando("eu consulto restricao pelo <CPF>")
    public void euConsultoRestricaoPeloCPF(String cpf) {
        this.endereco += cpf;
        response =
            given()
            .when()
                .get(this.endereco)
            .then()
                .extract()
                .response();
    }

    @Entao("deve ser retornado o status code {string}")
    public void deveSerRetornadoOStatusCode(String statusCode) {
        response.then().statusCode(Integer.parseInt(statusCode));
    }

    @Dado("as seguintes informacoes da simulacao")
    public void asSeguintesInformacoes(Map<String, String> dados) {
        this.body = "{";
        body += "\"cpf\": \"" + dados.get("cpf") + "\", ";
        body += "\"nome\": \"" + dados.get("nome") + "\", ";
        body += "\"email\": \"" + dados.get("email") + "\", ";
        body += "\"valor\": " + dados.get("valor") + ", ";
        body += "\"parcelas\": " + dados.get("parcelas") + ", ";
        body += "\"seguro\": " + dados.get("seguro") + "";
        body += "}";
        request = given().body(body).contentType(ContentType.JSON);
    }

    @Quando("quando eu envio a requisicao para o endereco {string}")
    public void quandoEuEnvioARequisicaoParaOEndereco(String endereco) {
        response =
                request.
                when().
                    post(endereco).
                then().extract().response();
        if (response.statusCode() == 201) {
            //simulacaoId = response.jsonPath().getString("id").toString();
            simulacaoId = response.jsonPath().getString("id").toString();
        }
    }

    @E("a simulacao deve ser deletada")
    public void aSimulacaoDeveSerDeletada() {
        given().
        when().
            delete("http://localhost:8080/api/v1/simulacoes/" + simulacaoId).
        then().statusCode(200);
    }

    @Quando("quando eu envio a alteracao para o endereco {string}")
    public void quandoEuEnvioAAlteracaoParaOEndereco(String endereco) {
        response = request.
        when().
            put(endereco).
        then().
            extract().response();
    }

    @Quando("eu consulto as simulacoes")
    public void euConsultoAsSimulacoes() throws Exception {
        response = given().
            when().
                get(this.endereco).
            then().
                extract().response();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public RequestSpecification getRequest() {
        return request;
    }

    public void setRequest(RequestSpecification request) {
        this.request = request;
    }
}
