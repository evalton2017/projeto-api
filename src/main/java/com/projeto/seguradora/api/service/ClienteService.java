package com.projeto.seguradora.api.service;

import com.projeto.seguradora.api.exception.DataIntegrityException;
import com.projeto.seguradora.api.exception.ExceptionErros;
import com.projeto.seguradora.api.model.Cliente;
import com.projeto.seguradora.api.repository.ClienteRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente salvar(Cliente cliente) throws ExceptionErros {
        if(validaDuplicidade(cliente.getCpf())){
            throw new DataIntegrityException("CPF já cadastrado.");
        }
        return clienteRepository.save(cliente);
    }


    public Optional<Cliente> consultar(Long id) {
        Optional<Cliente> cliente =  clienteRepository.findById(id);
        return cliente;
    }

    public Cliente buscarCliente(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não existe"));
    }

    public void deletar(Long id) {
        clienteRepository
                .findById(id)
                .map(cliente ->{
                    clienteRepository.delete(cliente);
                    return Void.TYPE;
                });
    }


    public void atualizar(Long id, Cliente cliente) {
        clienteRepository
                .findById(id)
                .map(clienteNew ->{
                    clienteNew.setNome(cliente.getNome());
                    clienteRepository.save(clienteNew);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private Boolean validaDuplicidade(String cpf){
        Cliente cliente = clienteRepository.findByCpf(cpf);
        if(Objects.nonNull(cliente)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}