package com.leodelmiro.cliente.core.dataprovider.cliente;

import com.leodelmiro.cliente.core.domain.CPF;
import com.leodelmiro.cliente.core.domain.Cliente;

import java.util.Optional;

public interface IdentificaClienteGateway {
    Optional<Cliente> identificar(CPF cpf);
}
