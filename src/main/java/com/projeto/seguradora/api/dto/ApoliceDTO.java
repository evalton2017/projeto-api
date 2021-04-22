package com.projeto.seguradora.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApoliceDTO {

    private Long id;

    private Long numero;

    @NotBlank
    private String placa;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private LocalDate inicioVigencia;

    @NotNull
    private LocalDate fimVigencia;
}
