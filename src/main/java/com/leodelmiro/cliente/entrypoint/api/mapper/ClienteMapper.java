package com.leodelmiro.cliente.entrypoint.api.mapper;

import com.leodelmiro.cliente.core.domain.CPF;
import com.leodelmiro.cliente.entrypoint.api.request.IdentificaClienteRequest;
import com.leodelmiro.cliente.entrypoint.api.response.ClienteResponse;
import com.leodelmiro.cliente.core.domain.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    default String map(CPF cpf) {
        return cpf != null ? cpf.getCpf() : null;
    }

    default CPF map(String cpf) {
        return cpf != null ? new CPF(cpf) : null;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(source = "cpf", target = "cpf")
    Cliente toCLiente(IdentificaClienteRequest identificaClienteRequest);

    @Mapping(source = "cpf", target = "cpf")
    ClienteResponse toClienteResponse(Cliente cliente);
}
