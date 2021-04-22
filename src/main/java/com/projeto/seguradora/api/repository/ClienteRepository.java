package com.projeto.seguradora.api.repository;

import com.projeto.seguradora.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Transactional
    public Cliente findByCpf(String cpf);
}
