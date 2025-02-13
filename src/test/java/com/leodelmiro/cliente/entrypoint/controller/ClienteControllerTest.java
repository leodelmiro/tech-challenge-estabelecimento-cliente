package com.leodelmiro.cliente.entrypoint.controller;

import com.leodelmiro.cliente.core.domain.CPF;
import com.leodelmiro.cliente.core.domain.Cliente;
import com.leodelmiro.cliente.core.usecase.cliente.CadastraClienteUseCase;
import com.leodelmiro.cliente.core.usecase.cliente.IdentificaClienteUseCase;
import com.leodelmiro.cliente.entrypoint.api.mapper.ClienteMapper;
import com.leodelmiro.cliente.entrypoint.api.request.IdentificaClienteRequest;
import com.leodelmiro.cliente.entrypoint.api.response.ClienteResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ClienteControllerTest {

    @Test
    void deveCadastrar() {
        IdentificaClienteRequest identificaClienteRequest = new IdentificaClienteRequest("60770012019", "Teste", "test@test.com");
        Cliente cliente = new Cliente();
        ClienteResponse clienteResponse = new ClienteResponse("60770012019", "Teste", "test@test.com", LocalDateTime.now());

        CadastraClienteUseCase cadastraClienteUseCase = mock(CadastraClienteUseCase.class);
        ClienteMapper clienteMapper = mock(ClienteMapper.class);

        when(clienteMapper.toCLiente(identificaClienteRequest)).thenReturn(cliente);
        when(cadastraClienteUseCase.cadastrar(cliente)).thenReturn(cliente);
        when(clienteMapper.toClienteResponse(cliente)).thenReturn(clienteResponse);

        ClienteResponse result = ClienteController.cadastra(identificaClienteRequest, cadastraClienteUseCase, clienteMapper);

        assertEquals(clienteResponse, result);
        verify(clienteMapper).toCLiente(identificaClienteRequest);
        verify(cadastraClienteUseCase).cadastrar(cliente);
        verify(clienteMapper).toClienteResponse(cliente);
    }

    @Test
    void deveIdentificar() {
        String cpf = "60770012019";
        CPF cpfObj = new CPF(cpf);
        Cliente cliente = new Cliente();
        ClienteResponse clienteResponse = new ClienteResponse("60770012019", "Teste", "test@test.com", LocalDateTime.now());

        IdentificaClienteUseCase identificaClienteUseCase = mock(IdentificaClienteUseCase.class);
        ClienteMapper clienteMapper = mock(ClienteMapper.class);

        when(identificaClienteUseCase.identificar(cpfObj)).thenReturn(Optional.of(cliente));
        when(clienteMapper.toClienteResponse(cliente)).thenReturn(clienteResponse);

        ClienteResponse result = ClienteController.identificar(cpf, identificaClienteUseCase, clienteMapper);

        assertEquals(clienteResponse, result);
        verify(identificaClienteUseCase).identificar(cpfObj);
        verify(clienteMapper).toClienteResponse(cliente);
    }

    @Test
    void testIdentificarThrowsExceptionWhenClienteNotFound() {
        String cpf = "60770012019";
        CPF cpfObj = new CPF(cpf);

        IdentificaClienteUseCase identificaClienteUseCase = mock(IdentificaClienteUseCase.class);
        ClienteMapper clienteMapper = mock(ClienteMapper.class);

        when(identificaClienteUseCase.identificar(cpfObj)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> ClienteController.identificar(cpf, identificaClienteUseCase, clienteMapper));
        verify(identificaClienteUseCase).identificar(cpfObj);
        verifyNoInteractions(clienteMapper);
    }
}