package com.projeto.seguradora.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ClienteDTO {

    @NotNull
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String cpf;

    @NotBlank
    private String cidade;

    @NotBlank
    private String uf;

    //@JsonFormat(pattern = "dd/MM/yyyy")
    //private LocalDate dataCadastro;
}
