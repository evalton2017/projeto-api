package com.projeto.seguradora.api.repository;

import com.projeto.seguradora.api.SeguradoraApiApplication;
import com.projeto.seguradora.api.model.Apolice;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SeguradoraApiApplication.class)
public class ApoliceRepositoryTests {

    @Autowired
    private ApoliceRepository apoliceRepository;

    private LocalDate inicio = LocalDate.now();
    private LocalDate fim = LocalDate.now();

    @Test
    public void testesave(){
        Apolice apolice = new Apolice(null, 23355L, "PAD2566", new BigDecimal(233), inicio, fim);
        Assertions.assertNotNull(apoliceRepository.save(apolice));

    }
}
