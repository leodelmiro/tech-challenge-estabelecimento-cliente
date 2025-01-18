package com.leodelmiro.cliente.core.usecase.cliente.impl;

import com.leodelmiro.cliente.core.domain.Cliente;
import com.leodelmiro.cliente.core.dataprovider.cliente.CadastraClienteGateway;
import com.leodelmiro.cliente.core.usecase.cliente.CadastraClienteUseCase;
import com.leodelmiro.cliente.core.usecase.cliente.IdentificaClienteUseCase;

public class CadastraClienteUseCaseImpl implements CadastraClienteUseCase {

    private final CadastraClienteGateway cadastraClienteGateway;
    private final IdentificaClienteUseCase identificaClienteUseCase;

    public CadastraClienteUseCaseImpl(CadastraClienteGateway cadastraClienteGateway,
                                      IdentificaClienteUseCase identificaClienteUseCase) {
        this.cadastraClienteGateway = cadastraClienteGateway;
        this.identificaClienteUseCase = identificaClienteUseCase;
    }

    @Override
    public Cliente cadastrar(Cliente cliente) {
        var possivelCliente = identificaClienteUseCase.identificar(cliente.getCpf());
        return possivelCliente.orElseGet(() -> cadastraClienteGateway.cadastrar(cliente));
    }
}