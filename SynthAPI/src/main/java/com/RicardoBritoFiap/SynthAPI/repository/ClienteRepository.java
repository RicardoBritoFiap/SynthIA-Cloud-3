package com.RicardoBritoFiap.SynthAPI.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.RicardoBritoFiap.SynthAPI.model.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public List<Cliente> findByEmpresaNome(String nome);
}