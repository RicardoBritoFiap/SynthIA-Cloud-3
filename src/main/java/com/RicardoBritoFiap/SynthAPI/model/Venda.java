package com.RicardoBritoFiap.SynthAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Data
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String valor;

    @NotBlank
    private String descvenda;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Cliente cliente;
}