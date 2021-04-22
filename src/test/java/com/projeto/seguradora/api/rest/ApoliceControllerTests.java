package com.projeto.seguradora.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projeto.seguradora.api.dto.ApoliceDTO;
import com.projeto.seguradora.api.model.Apolice;
import com.projeto.seguradora.api.repository.ApoliceRepository;
import com.projeto.seguradora.api.service.ApoliceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(ApoliceController.class)
public class ApoliceControllerTests {

    @Autowired
    private MockMvc apoliceController;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ApoliceService apoliceService;

    @MockBean
    private ApoliceRepository apoliceRepository;

    private LocalDate inicio = LocalDate.now();
    private LocalDate fim = LocalDate.now();


    @Test
    public void deveSalvarApolice() throws Exception {
        ApoliceDTO apoliceDTO = new ApoliceDTO(null, 23355L, "PAD2566", new BigDecimal(233), inicio, fim);
        Apolice apolice = new Apolice(null, 23355L, "PAD2566", new BigDecimal(233), inicio, fim);

        Mockito.when(apoliceService.salvar(any(ApoliceDTO.class))).thenReturn(apolice);
        this.apoliceController.perform(MockMvcRequestBuilders.post("/api/apolices")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(apoliceDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }


    @Test
    public void deveRetornarListaapolices() throws Exception {

        List<Apolice> apolices = new ArrayList<>();
        Apolice apolice = new Apolice(null, 23355L, "PAD2566", new BigDecimal(233), inicio, fim);
        apolices.add(apolice);

        Mockito.when(apoliceService.lista()).thenReturn(apolices);
        this.apoliceController.perform(MockMvcRequestBuilders.get("/api/apolices"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deveRetornarNotFound() throws Exception {
        this.apoliceController.perform(MockMvcRequestBuilders.get("/api/apolices/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void deveDeletarApolice() throws Exception {
        this.apoliceController.perform(MockMvcRequestBuilders.delete("/api/apolices/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void deveAtualizarApolice() throws Exception {
        ApoliceDTO apoliceDTO = new ApoliceDTO(1L, 23355L, "PAD2566", new BigDecimal(233), inicio, fim);

        this.apoliceController.perform(MockMvcRequestBuilders.put("/api/apolices/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(apoliceDTO)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }


}