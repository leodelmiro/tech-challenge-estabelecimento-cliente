package com.leodelmiro.cliente.dataprovider.gateway.cliente;

import com.leodelmiro.cliente.dataprovider.repository.ClienteRepository;
import com.leodelmiro.cliente.dataprovider.repository.entity.ClienteEntity;
import com.leodelmiro.cliente.dataprovider.repository.mapper.ClienteEntityMapper;
import com.leodelmiro.cliente.core.domain.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CadastraClienteGatewayImplTest {

    @InjectMocks
    private CadastraClienteGatewayImpl cadastraClienteGateway;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteEntityMapper clienteEntityMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarCliente() {
        Cliente inputCliente = new Cliente();
        Cliente expectedCliente = new Cliente();
        var clienteEntity = new ClienteEntity(); // Replace with the actual ClienteEntity class

        when(clienteEntityMapper.toClienteEntity(inputCliente)).thenReturn(clienteEntity);
        when(clienteRepository.save(clienteEntity)).thenReturn(clienteEntity);
        when(clienteEntityMapper.toCliente(clienteEntity)).thenReturn(expectedCliente);

        Cliente result = cadastraClienteGateway.cadastrar(inputCliente);

        assertEquals(expectedCliente, result);
        verify(clienteEntityMapper, times(1)).toClienteEntity(inputCliente);
        verify(clienteRepository, times(1)).save(clienteEntity);
        verify(clienteEntityMapper, times(1)).toCliente(clienteEntity);
    }
}