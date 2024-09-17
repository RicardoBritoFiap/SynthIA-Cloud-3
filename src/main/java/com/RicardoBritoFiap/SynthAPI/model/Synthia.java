package com.RicardoBritoFiap.SynthAPI.model;

import org.hibernate.validator.constraints.br.CNPJ;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Data
public class Synthia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @CNPJ
    private String cnpj;

    @Email
    private String email;
}
