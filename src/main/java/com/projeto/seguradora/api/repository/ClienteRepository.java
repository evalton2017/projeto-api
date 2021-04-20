package com.projeto.seguradora.api.repository;

import com.projeto.seguradora.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
