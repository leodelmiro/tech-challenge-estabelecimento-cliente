package com.leodelmiro.cliente.dataprovider.gateway.cliente;

import com.leodelmiro.cliente.core.domain.CPF;
import com.leodelmiro.cliente.dataprovider.repository.ClienteRepository;
import com.leodelmiro.cliente.dataprovider.repository.entity.CPFEntity;
import com.leodelmiro.cliente.dataprovider.repository.mapper.ClienteEntityMapper;
import com.leodelmiro.cliente.core.domain.Cliente;
import com.leodelmiro.cliente.core.dataprovider.cliente.IdentificaClienteGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class IdentificaClienteGatewayImpl implements IdentificaClienteGateway {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteEntityMapper clienteEntityMapper;

    @Override
    public Optional<Cliente> identificar(CPF cpf) {
        return clienteRepository.findClienteByCpf(new CPFEntity(cpf.getCpf()))
                .map(clienteEntityMapper::toCliente);
    }
}
