package com.leodelmiro.cliente.entrypoint.api.response;

import java.time.LocalDateTime;

public record ClienteResponse(
        String cpf,
        String nome,
        String email,
        LocalDateTime criadoEm
) {
}
