package com.RicardoBritoFiap.SynthAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Data
public class Oferta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String desconto;

    @NotBlank
    private String descoferta;

    @OneToOne
    private Venda venda;
}