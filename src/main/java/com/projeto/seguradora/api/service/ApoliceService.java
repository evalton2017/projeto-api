package com.projeto.seguradora.api.service;

import com.projeto.seguradora.api.dto.ApoliceDTO;
import com.projeto.seguradora.api.dto.ConsultaApoliceDTO;
import com.projeto.seguradora.api.exception.DataIntegrityException;
import com.projeto.seguradora.api.exception.ExceptionErros;
import com.projeto.seguradora.api.model.Apolice;
import com.projeto.seguradora.api.repository.ApoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ApoliceService {
    @Autowired
    ApoliceRepository apoliceRepository;

    public List<Apolice> lista() {
        return apoliceRepository.findAll();
    }

    public Apolice salvar(ApoliceDTO apoliceDTO) throws ExceptionErros {
        Apolice apolice = new Apolice();
        apolice.setPlaca(apoliceDTO.getPlaca());
        apolice.setValor(apoliceDTO.getValor());
        apolice.setInicioVigencia(apoliceDTO.getInicioVigencia());
        apolice.setFimVigencia(apoliceDTO.getFimVigencia());
        apolice.setNumero(gerarNumero());
        return apoliceRepository.save(apolice);
    }

    private Long gerarNumero(){
        Random gerador = new Random();
        Long numero = null;
        do{
           int num_aux =  gerador.nextInt(9999999);
           Apolice apolice = pesquisaNumero(new Long(num_aux));
           if(Objects.isNull(apolice)){
               numero = new Long(num_aux);
           }
        }while(numero==null);

        return numero;
    }

    private Apolice pesquisaNumero(Long numero){
        Apolice apolice = new Apolice();
        apolice = apoliceRepository.findByNumero(numero);
        return apolice;
    }

    public Optional<Apolice> consultar(Long id) {
        Optional<Apolice> apolice = apoliceRepository.findById(id);
        return apolice;
    }

    public Apolice buscarApolice(Long id) {
        Optional<Apolice> apolice = apoliceRepository.findById(id);
        return apolice.orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Apolice não existe"));
    }

    public ConsultaApoliceDTO consultarApolice(Long numero) throws  ExceptionErros{
        Apolice apolice = apoliceRepository.findByNumero(numero);
        ConsultaApoliceDTO consulta = new ConsultaApoliceDTO();
        if(Objects.isNull(apolice)){
            throw new DataIntegrityException("Apolice não encontrada.");
        }
        montarInforApolice(consulta, apolice);
        return consulta;
    }

    private void montarInforApolice(ConsultaApoliceDTO consulta,  Apolice apolice){
            LocalDate dataHoje = LocalDate.now();
            consulta.setNumero(apolice.getNumero());
            consulta.setPlaca(apolice.getPlaca());
            consulta.setValor(apolice.getValor());
            consulta.setVencido(apolice.getFimVigencia().isBefore(dataHoje));
            long diferencaEmDias = ChronoUnit.DAYS.between(dataHoje, apolice.getFimVigencia());
            if(consulta.getVencido()){
                consulta.setInfoVencimento("Apolice vencia a "+diferencaEmDias*-1+" dias.");
            }else{
                consulta.setInfoVencimento("Apolice irá vencer em "+diferencaEmDias+" dias.");
            }

    }

    public void deletar(Long id) {
        apoliceRepository
                .findById(id)
                .map(apolice -> {
                    apoliceRepository.delete(apolice);
                    return Void.TYPE;
                });
    }


    public void atualizar(Long id, ApoliceDTO apolice) {
        apoliceRepository
                .findById(id)
                .map(apoliceNew -> {
                    apoliceNew.setPlaca(apolice.getPlaca());
                    apoliceNew.setValor(apolice.getValor());
                    apoliceNew.setInicioVigencia(apolice.getInicioVigencia());
                    apoliceNew.setFimVigencia(apolice.getFimVigencia());
                    apoliceRepository.save(apoliceNew);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
