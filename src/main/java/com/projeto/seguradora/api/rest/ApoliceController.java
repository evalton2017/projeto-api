package com.projeto.seguradora.api.rest;

import com.projeto.seguradora.api.dto.ApoliceDTO;
import com.projeto.seguradora.api.dto.ConsultaApoliceDTO;
import com.projeto.seguradora.api.exception.ExceptionErros;
import com.projeto.seguradora.api.model.Apolice;
import com.projeto.seguradora.api.service.ApoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/apolices")
public class ApoliceController {

    @Autowired
    ApoliceService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Apolice> salvar(@RequestBody @Valid ApoliceDTO apolice) throws ExceptionErros {
        service.salvar(apolice);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<Apolice> Apolices(){
        return service.lista();
    }

    @GetMapping("{id}")
    public Apolice buscar(@PathVariable("id")  Long id){
        return service.consultar(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Apolice não encontrado"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id")  Long id){
        service.deletar(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid ApoliceDTO apolice){
        service.atualizar(id,apolice);
    }


    @GetMapping("consulta/{numero}")
    public ConsultaApoliceDTO consultar(@PathVariable("numero")  Long numero) throws ExceptionErros {
        return service.consultarApolice(numero);
    }

}