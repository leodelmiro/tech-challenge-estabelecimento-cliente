package com.leodelmiro.cliente.dataprovider.gateway.cliente;

import com.leodelmiro.cliente.core.domain.CPF;
import com.leodelmiro.cliente.core.domain.Cliente;
import com.leodelmiro.cliente.dataprovider.repository.ClienteRepository;
import com.leodelmiro.cliente.dataprovider.repository.mapper.ClienteEntityMapper;
import com.leodelmiro.cliente.dataprovider.repository.entity.ClienteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class IdentificaClienteGatewayImplTest {

    @InjectMocks
    private IdentificaClienteGatewayImpl identificaClienteGateway;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteEntityMapper clienteEntityMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveIndentificarCliente() {
        String cpfValue = "60770012019";
        CPF cpf = new CPF(cpfValue);
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setCpf(cpfValue);
        Cliente cliente = new Cliente();
        cliente.setCpf(cpf);

        when(clienteRepository.findClienteByCpf(cpfValue)).thenReturn(Optional.of(clienteEntity));
        when(clienteEntityMapper.toCliente(clienteEntity)).thenReturn(cliente);

        Optional<Cliente> result = identificaClienteGateway.identificar(cpf);

        assertTrue(result.isPresent());
        assertEquals(cliente, result.get());
        verify(clienteRepository, times(1)).findClienteByCpf(cpfValue);
        verify(clienteEntityMapper, times(1)).toCliente(clienteEntity);
    }

    @Test
    void deveVoltarNaoEncontradoSeNaoIdentificarCliente() {
        String cpfValue = "60770012019";
        CPF cpf = new CPF(cpfValue);

        when(clienteRepository.findClienteByCpf(cpfValue)).thenReturn(Optional.empty());

        Optional<Cliente> result = identificaClienteGateway.identificar(cpf);

        assertTrue(result.isEmpty());
        verify(clienteRepository, times(1)).findClienteByCpf(cpfValue);
        verify(clienteEntityMapper, never()).toCliente(any());
    }
}