package com.projeto.seguradora.api.service;

import com.projeto.seguradora.api.exception.ExceptionErros;
import com.projeto.seguradora.api.model.Cliente;
import com.projeto.seguradora.api.repository.ClienteRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class ClienteServiceTests {

    @MockBean
    private ClienteRepository clienteRepository;

    @SpyBean
    private ClienteService clienteService;

    @Test
    public void testeListar(){
        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente = new Cliente(1L,"Jose da Silva","25665556666","Brasilia","DF");
        clientes.add(cliente);
        Mockito.when(clienteRepository.findAll()).thenReturn(clientes);
        Assert.assertNotNull(clienteService.listar());
    }

    @Test
    public void testeSalvarCliente() throws ExceptionErros {
        Cliente cliente = new Cliente(null,"","25665556666","Brasilia","DF");
        Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        Assert.assertNotNull(clienteService.salvar(cliente));
    }

    @Test
    public void testeConsultarCliente() throws ExceptionErros {
        Cliente cliente = new Cliente(1L,"Jose da Silva","25665556666","Brasilia","DF");
        Mockito.when(clienteRepository.findById(any(Long.class))).thenReturn(Optional.of(cliente));
        Assert.assertNotNull(clienteService.consultar(1L));
    }

 }
