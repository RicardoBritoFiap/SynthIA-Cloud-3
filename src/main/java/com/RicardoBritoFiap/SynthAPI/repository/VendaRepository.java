package com.RicardoBritoFiap.SynthAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.RicardoBritoFiap.SynthAPI.model.Venda;
import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    public List<Venda> findByClienteNome(String nome);
}