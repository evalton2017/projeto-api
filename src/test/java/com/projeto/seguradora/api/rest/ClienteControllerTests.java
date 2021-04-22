package com.projeto.seguradora.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.seguradora.api.model.Cliente;
import com.projeto.seguradora.api.repository.ClienteRepository;
import com.projeto.seguradora.api.service.ClienteService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;


@WebMvcTest(ClienteController.class)
public class ClienteControllerTests {

    @Autowired
    private MockMvc clienteController;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;

    @Test
    public void deveSalvarCliente() throws Exception {
        Cliente cliente = new Cliente(null,"Jose da Silva","39853985004","Brasilia","DF");

        Mockito.when(clienteService.salvar(any(Cliente.class))).thenReturn(cliente);
        this.clienteController.perform(MockMvcRequestBuilders.post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(cliente)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    public void deveRetornarListaClientes() throws Exception {

        List<Cliente> clientes = new ArrayList<>();
        Cliente cliente = new Cliente(1L,"Jose da Silva","25665556666","Brasilia","DF");
        clientes.add(cliente);

        Mockito.when(clienteService.listar()).thenReturn(clientes);
        this.clienteController.perform(MockMvcRequestBuilders.get("/api/clientes"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveRetornarNotFound() throws Exception {
        this.clienteController.perform(MockMvcRequestBuilders.get("/api/clientes/{id}",1))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deveDeletarCliente() throws Exception {
        this.clienteController.perform(MockMvcRequestBuilders.delete("/api/clientes/{id}",1))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deveAtualizarCliente() throws Exception {
        Cliente cliente = new Cliente(1L,"Jose da Silva","39853985004","Brasilia","DF");
        this.clienteController.perform(MockMvcRequestBuilders.put("/api/clientes/{id}",1)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(cliente)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }


}
