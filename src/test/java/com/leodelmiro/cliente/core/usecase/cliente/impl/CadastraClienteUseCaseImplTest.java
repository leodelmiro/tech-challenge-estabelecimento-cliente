package com.leodelmiro.cliente.core.usecase.cliente.impl;

import com.leodelmiro.cliente.core.dataprovider.cliente.CadastraClienteGateway;
import com.leodelmiro.cliente.core.domain.CPF;
import com.leodelmiro.cliente.core.domain.Cliente;
import com.leodelmiro.cliente.core.usecase.cliente.IdentificaClienteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CadastraClienteUseCaseImplTest {

    private CadastraClienteGateway cadastraClienteGateway;
    private IdentificaClienteUseCase identificaClienteUseCase;
    private CadastraClienteUseCaseImpl cadastraClienteUseCase;

    @BeforeEach
    void setUp() {
        cadastraClienteGateway = mock(CadastraClienteGateway.class);
        identificaClienteUseCase = mock(IdentificaClienteUseCase.class);
        cadastraClienteUseCase = new CadastraClienteUseCaseImpl(cadastraClienteGateway, identificaClienteUseCase);
    }

    @Test
    void deveRetornarClienteExistenteQuandoIdentificado() {
        Cliente clienteExistente = new Cliente();
        clienteExistente.setCpf(new CPF("60770012019"));
        clienteExistente.setNome("Existing Client");

        when(identificaClienteUseCase.identificar(new CPF("60770012019"))).thenReturn(Optional.of(clienteExistente));

        Cliente result = cadastraClienteUseCase.cadastrar(clienteExistente);

        assertEquals(clienteExistente, result);
        verify(identificaClienteUseCase, times(1)).identificar(new CPF("60770012019"));
        verify(cadastraClienteGateway, never()).cadastrar(any());
    }

    @Test
    void deveRegistrarNovoClienteQuandoNaoIdentificar() {
        Cliente newCliente = new Cliente();
        newCliente.setCpf(new CPF("60770012019"));
        newCliente.setNome("New Client");

        when(identificaClienteUseCase.identificar(new CPF("60770012019"))).thenReturn(Optional.empty());
        when(cadastraClienteGateway.cadastrar(newCliente)).thenReturn(newCliente);

        Cliente result = cadastraClienteUseCase.cadastrar(newCliente);

        assertEquals(newCliente, result);
        verify(identificaClienteUseCase, times(1)).identificar(new CPF("60770012019"));
        verify(cadastraClienteGateway, times(1)).cadastrar(newCliente);
    }
}