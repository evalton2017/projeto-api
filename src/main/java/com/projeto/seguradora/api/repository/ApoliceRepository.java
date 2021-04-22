package com.projeto.seguradora.api.repository;

import com.projeto.seguradora.api.model.Apolice;
import com.projeto.seguradora.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ApoliceRepository extends JpaRepository<Apolice, Long> {

    @Transactional
    public Apolice findByNumero(Long numero);

}
