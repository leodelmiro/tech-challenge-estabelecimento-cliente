package com.leodelmiro.cliente.dataprovider.repository.mapper;

import com.leodelmiro.cliente.dataprovider.repository.entity.ClienteEntity;
import com.leodelmiro.cliente.core.domain.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteEntityMapper {
    ClienteEntity toClienteEntity(Cliente cliente);

    Cliente toCliente(ClienteEntity clienteEntity);
}
