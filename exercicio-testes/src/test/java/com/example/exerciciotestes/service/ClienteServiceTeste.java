package com.example.exerciciotestes.service;

import com.example.exerciciotestes.controller.request.ClienteRequest;
import com.example.exerciciotestes.model.Cliente;
import com.example.exerciciotestes.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTeste {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void buscaTodosClientes() {
    }

    @Test
    void salvarCliente() {
        Cliente clienteMock = new Cliente(1L,"Renato", 200.0 );

        when(clienteRepository.save(any())).thenReturn(clienteMock);

        Cliente clienteResposta = clienteService.salvarCliente(new ClienteRequest("Renato", 200.0 ));

        assertNotNull(clienteResposta);
        assertEquals(clienteMock.getNomeCliente(), clienteResposta.getNomeCliente());
        assertEquals(clienteMock.getSaldoCliente(), clienteResposta.getSaldoCliente());
        verify(clienteRepository).save(any());

    }

    @Test
    void atualizarCliente() {
        Cliente clienteMock = new Cliente(1l,"Renato", 200.0);

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(clienteMock));
        when(clienteRepository.save(any())).thenReturn(clienteMock);

        Cliente clienteResposta = clienteService.atualizarCliente(1L , new ClienteRequest("Renato", 200.0));

        assertNotNull(clienteResposta);
        assertEquals(clienteMock.getNomeCliente(), clienteResposta.getNomeCliente());
        verify(clienteRepository).findById(anyLong());
        verify(clienteRepository).save(any());
    }

    @Test
    void buscaClientePorId() {
        Cliente clienteMock = new Cliente( 1L,"renato", 200.0 );

        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(clienteMock));

        Cliente clienteRespostaId = clienteService.buscaClientePorId(anyLong());

        assertNotNull(clienteRespostaId);
        assertEquals(clienteMock.getId(), clienteRespostaId.getId());

        verify(clienteRepository).findById(anyLong());

    }

    @Test
    void deletarClientePorId() {
        Cliente clienteMock = new Cliente( 1L,"renato", 200.0 );
        Boolean clienteResposta = clienteService.deletarClientePorId(1L);
        assertNotNull(clienteResposta);
        assertTrue(clienteResposta);
    }
}