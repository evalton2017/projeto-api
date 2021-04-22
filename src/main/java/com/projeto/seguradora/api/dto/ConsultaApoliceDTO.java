package com.projeto.seguradora.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultaApoliceDTO {

    private Long numero;
    private Boolean vencido;
    private String infoVencimento;
    private String placa;
    private BigDecimal valor;


}
