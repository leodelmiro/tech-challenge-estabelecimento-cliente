package com.leodelmiro.cliente.core.dataprovider.cliente;

import com.leodelmiro.cliente.core.domain.Cliente;

public interface CadastraClienteGateway {
    Cliente cadastrar(Cliente cliente);
}
