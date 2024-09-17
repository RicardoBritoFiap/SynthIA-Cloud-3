package com.RicardoBritoFiap.SynthAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.RicardoBritoFiap.SynthAPI.model.Synthia;

public interface SynthiaRepository extends JpaRepository<Synthia, Long> {
    public Synthia findByCnpjAndEmail(String cnpj, String email);
}