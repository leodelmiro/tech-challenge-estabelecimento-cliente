package com.leodelmiro.cliente.dataprovider.repository.mapper;

import com.leodelmiro.cliente.core.domain.CPF;
import com.leodelmiro.cliente.dataprovider.repository.entity.ClienteEntity;
import com.leodelmiro.cliente.core.domain.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteEntityMapper {
    ClienteEntity toClienteEntity(Cliente cliente);

    default String map(CPF cpf) {
        return cpf != null ? cpf.getCpf() : null;
    }

    default CPF map(String cpf) {
        return cpf != null ? new CPF(cpf) : null;
    }

    Cliente toCliente(ClienteEntity clienteEntity);
}
