package com.projeto.seguradora.api.service;

import com.projeto.seguradora.api.dto.ApoliceDTO;
import com.projeto.seguradora.api.exception.ExceptionErros;
import com.projeto.seguradora.api.model.Apolice;
import com.projeto.seguradora.api.model.Cliente;
import com.projeto.seguradora.api.repository.ApoliceRepository;
import com.projeto.seguradora.api.repository.ClienteRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
public class ApoliceServiceTests {

    @MockBean
    private ApoliceRepository apoliceRepository;

    @SpyBean
    private ApoliceService apoliceService;

    private LocalDate inicio = LocalDate.now();
    private LocalDate fim = LocalDate.now();

    @Test
    public void testeListar(){
        List<Apolice> apolices = new ArrayList<>();
        Apolice apolice = new Apolice(null, 23355L, "PAD2566", new BigDecimal(233), inicio, fim);
        apolices.add(apolice);
        Mockito.when(apoliceRepository.findAll()).thenReturn(apolices);
        Assert.assertNotNull(apoliceService.lista());
    }

    @Test
    public void testeSalvarCliente() throws ExceptionErros {
        ApoliceDTO apoliceDTO = new ApoliceDTO(null, 23355L, "PAD2566", new BigDecimal(233), inicio, fim);
        Apolice apolice = new Apolice(null, 23355L, "PAD2566", new BigDecimal(233), inicio, fim);
        Mockito.when(apoliceRepository.save(any(Apolice.class))).thenReturn(apolice);
        Assert.assertNotNull(apoliceService.salvar(apoliceDTO));
    }

    @Test
    public void testeConsultarCliente() throws ExceptionErros {
        Apolice apolice = new Apolice(null, 23355L, "PAD2566", new BigDecimal(233), inicio, fim);
        Mockito.when(apoliceRepository.findById(any(Long.class))).thenReturn(Optional.of(apolice));
        Assert.assertNotNull(apoliceService.consultar(1L));
    }

}