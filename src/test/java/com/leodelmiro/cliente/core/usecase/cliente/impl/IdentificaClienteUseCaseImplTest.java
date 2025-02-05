package com.leodelmiro.cliente.core.usecase.cliente.impl;

import com.leodelmiro.cliente.core.domain.CPF;
import com.leodelmiro.cliente.core.domain.Cliente;
import com.leodelmiro.cliente.core.dataprovider.cliente.IdentificaClienteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class IdentificaClienteUseCaseImplTest {

    @Mock
    private IdentificaClienteGateway identificaClienteGateway;

    private IdentificaClienteUseCaseImpl identificaClienteUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        identificaClienteUseCase = new IdentificaClienteUseCaseImpl(identificaClienteGateway);
    }

    @Test
    void deveIndetificarCliente() {
        CPF cpf = new CPF("60770012019");
        Cliente cliente = new Cliente(cpf, "John Doe" , "test@test.com", LocalDateTime.now());
        when(identificaClienteGateway.identificar(cpf)).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = identificaClienteUseCase.identificar(cpf);

        assertTrue(result.isPresent());
        assertEquals(cliente, result.get());
        verify(identificaClienteGateway, times(1)).identificar(cpf);
    }

    @Test
    void deveDevolverClienteNaoIdentificadoQuandoNaoIdentificar() {
        CPF cpf = new CPF("60770012019");
        when(identificaClienteGateway.identificar(cpf)).thenReturn(Optional.empty());

        Optional<Cliente> result = identificaClienteUseCase.identificar(cpf);

        assertTrue(result.isEmpty());
        verify(identificaClienteGateway, times(1)).identificar(cpf);
    }

    @Test
    void deveAceitarNullOuVazio() {
        CPF cpf = null;

        Optional<Cliente> result = identificaClienteUseCase.identificar(cpf);

        assertTrue(result.isEmpty());
        verify(identificaClienteGateway, times(1)).identificar(cpf);
    }
}