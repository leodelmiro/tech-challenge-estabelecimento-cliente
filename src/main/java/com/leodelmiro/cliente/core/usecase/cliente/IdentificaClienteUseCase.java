package com.leodelmiro.cliente.core.usecase.cliente;

import com.leodelmiro.cliente.core.domain.CPF;
import com.leodelmiro.cliente.core.domain.Cliente;

import java.util.Optional;

public interface IdentificaClienteUseCase {
    Optional<Cliente> identificar(CPF cpf);
}
