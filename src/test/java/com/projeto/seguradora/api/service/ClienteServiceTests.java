package com.projeto.seguradora.api.service;

import com.projeto.seguradora.api.exception.DataIntegrityException;
import com.projeto.seguradora.api.exception.ExceptionErros;
import com.projeto.seguradora.api.model.Cliente;
import com.projeto.seguradora.api.repository.ClienteRepository;
import com.projeto.seguradora.api.rest.ClienteController;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.MethodArgumentNotValidException;

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

    @Test
    public void testeCPFCadastrado() throws ExceptionErros {
        Cliente cliente = new Cliente(null,"Jose da Silva",null,"Brasilia","DF");
        Mockito.when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        final Exception exception = Assertions.assertThrows(ExceptionErros.class, ()->
                clienteService.salvar(cliente));
        Assertions.assertEquals("O campo CPF é obrigatório.",exception.getMessage());
    }
}
