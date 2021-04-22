package com.projeto.seguradora.api.repository;

import com.projeto.seguradora.api.SeguradoraApiApplication;
import com.projeto.seguradora.api.model.Cliente;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SeguradoraApiApplication.class)
public class ClienteRepositoryTests {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testesave(){
        Cliente cliente = new Cliente(null,"Maria Betania","97356893088","Brasilia","DF");
        Assertions.assertNotNull(clienteRepository.save(cliente));

    }
}
