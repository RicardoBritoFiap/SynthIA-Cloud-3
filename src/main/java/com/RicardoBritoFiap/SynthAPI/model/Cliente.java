package com.RicardoBritoFiap.SynthAPI.model;

import org.hibernate.validator.constraints.br.CPF;
import lombok.Data;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @CPF
    private Long cpf;

    @NotBlank
    private String nome;

    @Email
    private String email;

    @NotBlank
    private String algoritmo;

    @NotBlank
    private String modelo;

    @ManyToOne
    private Empresa empresa;
}