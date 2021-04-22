package com.projeto.seguradora.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(schema = "public", name="tb_apolice")
public class Apolice  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long numero;

    @Column(nullable = false, length = 9)
    @NotNull(message = "{campo.placa.obrigatorio}")
    private String placa;

    @Column(nullable = false)
    @NotNull(message = "{campo.valor.obrigatorio}")
    private BigDecimal valor;

    @Column(name="inicio_vigencia", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate inicioVigencia;

    @Column(name="fim_vigencia", updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate fimVigencia;



}