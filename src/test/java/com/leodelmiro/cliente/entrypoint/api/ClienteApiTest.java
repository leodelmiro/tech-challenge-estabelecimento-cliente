package com.leodelmiro.cliente.entrypoint.api;

import com.leodelmiro.cliente.core.domain.CPF;
import com.leodelmiro.cliente.core.domain.Cliente;
import com.leodelmiro.cliente.core.usecase.cliente.CadastraClienteUseCase;
import com.leodelmiro.cliente.core.usecase.cliente.IdentificaClienteUseCase;
import com.leodelmiro.cliente.entrypoint.api.mapper.ClienteMapper;
import com.leodelmiro.cliente.entrypoint.api.request.IdentificaClienteRequest;
import com.leodelmiro.cliente.entrypoint.api.response.ClienteResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class ClienteApiTest {

    @InjectMocks
    private ClienteApi clienteApi;

    @Mock
    private IdentificaClienteUseCase identificaClienteUseCase;

    @Mock
    private CadastraClienteUseCase cadastraClienteUseCase;

    @Mock
    private ClienteMapper clienteMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(mockRequest));
    }

    @Test
    void deveTestarCadastroCliente() {
        IdentificaClienteRequest request = new IdentificaClienteRequest("60770012019", "Teste", "test@test.com");

        ClienteResponse response = new ClienteResponse("60770012019", "Teste", "test@test.com", LocalDateTime.now());
        Cliente cliente = new Cliente(new CPF("60770012019"), "Teste", "test@test.com", LocalDateTime.now());

        when(clienteMapper.toCLiente(request)).thenReturn(cliente);
        when(cadastraClienteUseCase.cadastrar(any())).thenReturn(cliente);
        when(clienteMapper.toClienteResponse(any())).thenReturn(response);

        ResponseEntity<ClienteResponse> result = clienteApi.cadastra(request);

        assertNotNull(result);
        assertEquals(201, result.getStatusCode().value());
        assertEquals("60770012019", result.getBody().cpf());
        assertEquals("Teste", result.getBody().nome());
    }

    @Test
    void deveTestarClienteIdentificado() {
        ClienteResponse response = new ClienteResponse("60770012019", "Teste", "test@test.com", LocalDateTime.now());
        Cliente cliente = new Cliente(new CPF("60770012019"), "Teste", "test@test.com", LocalDateTime.now());

        when(identificaClienteUseCase.identificar(new CPF("60770012019"))).thenReturn(Optional.of(cliente));
        when(clienteMapper.toClienteResponse(any())).thenReturn(response);

        ResponseEntity<ClienteResponse> result = clienteApi.identificar("60770012019");

        assertNotNull(result);
        assertEquals(200, result.getStatusCode().value());
        assertEquals("60770012019", result.getBody().cpf());
        assertEquals("Teste", result.getBody().nome());
    }

    @Test
    void deveTestarClienteNãoIdentificado() {
        String cpf = "60770012019";

        when(identificaClienteUseCase.identificar(new CPF(cpf))).thenThrow(new RuntimeException("Cliente não encontrado"));

        ResponseEntity<ClienteResponse> result = clienteApi.identificar(cpf);

        assertNotNull(result);
        assertEquals(404, result.getStatusCode().value());
        assertNull(result.getBody());
    }
}