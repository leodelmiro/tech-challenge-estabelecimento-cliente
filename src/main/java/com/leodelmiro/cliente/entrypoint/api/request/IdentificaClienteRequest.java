package com.leodelmiro.cliente.entrypoint.api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record IdentificaClienteRequest(
        @CPF @NotBlank String cpf,
        @NotBlank String nome,
        @Email @NotBlank String email
) {
}
