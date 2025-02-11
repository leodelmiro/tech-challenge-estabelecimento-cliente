package bdd.stepdefinitions;

import bdd.config.RestApiApplicationIT;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;

import static bdd.utils.ClienteHelper.gerarIdentificaClienteRequest;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.notNullValue;

public class ClienteStepDefinitions extends RestApiApplicationIT {
    private Response response;
    private String cpf;
    private String baseUrl = "http://localhost:8090/api/v1/clientes";

    @Dado("que eu tenho os dados de um novo cliente")
    public void queEuTenhoOsDadosDeUmNovoCliente() {
    }

    @Quando("eu envio uma requisição para cadastrar cliente")
    public void euEnvioUmaRequisicaoPOST() {
        response = given()
                .contentType("application/json")
                .body(gerarIdentificaClienteRequest("08877839023"))
                .when()
                .post(baseUrl);
    }

    @Então("o cliente deve ser cadastrado com sucesso status 201")
    public void produtoDeveSerCadastrado() {
        response.then().statusCode(201);
    }

    @Dado("que eu busco um cliente existente")
    public void queEuBuscoUmClienteExistente() {
        cpf = "73317735007";
        given()
                .contentType("application/json")
                .body(gerarIdentificaClienteRequest(cpf))
                .when()
                .post(baseUrl);
    }

    @Quando("eu envio uma requisição para identificar cliente existente")
    public void euEnvioUmaRequisicaoGETComIdExistente() {
        response = when().get(baseUrl + "/" + cpf);
    }

    @Então("o cliente deve ser retornado com sucesso status 200")
    public void produtoDeveSerRetornado() {
        response.then().statusCode(200).body("cpf", notNullValue());
    }

    @Dado("que eu busco um cliente inexistente")
    public void queEuBuscoUmClienteInexistente() {
    }

    @Quando("eu envio uma requisição para identificar cliente inexistente")
    public void euEnvioUmaRequisicaoGETComIdNaoExistente() {
        response = when().get(baseUrl + "/9999");
    }

    @Então("deve ser retornado 404")
    public void produtoNaoEncontrado() {
        response.then().statusCode(404);
    }
}
