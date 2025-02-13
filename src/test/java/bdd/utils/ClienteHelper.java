package bdd.utils;

import com.leodelmiro.cliente.entrypoint.api.request.IdentificaClienteRequest;

public abstract class ClienteHelper {
    public static IdentificaClienteRequest gerarIdentificaClienteRequest(String cpf) {
        return new IdentificaClienteRequest(
                cpf,
                "Teste",
                "teste@test.com"
        );
    }
}
