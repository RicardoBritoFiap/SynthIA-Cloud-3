package com.RicardoBritoFiap.SynthAPI.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.RicardoBritoFiap.SynthAPI.model.Synthia;
import com.RicardoBritoFiap.SynthAPI.repository.SynthiaRepository;
import jakarta.validation.Valid;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "/Sinthia")
public class SynthiaController {

    @Autowired
    private SynthiaRepository repository;

    @GetMapping("{id}")
    public ResponseEntity<Synthia> getSynthia(@PathVariable Long id) {

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Synthia>> getSynthia() {
        List<Synthia> synthias = repository.findAll();
        return ResponseEntity.ok(synthias);
    }

    @PostMapping
    public ResponseEntity<Synthia> createSynthia(@RequestBody @Valid Synthia synthias) {
        repository.save(synthias);
        return ResponseEntity.ok(synthias);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteSynthia(@PathVariable Long id) {
        verificarseexistesynthia(id);
        repository.deleteById(id);

        return ResponseEntity.ok("Synthia deletada com sucesso");
    }

    @PutMapping("{id}")
    public ResponseEntity<Synthia> updateSynthia(@PathVariable Long id, @RequestBody Synthia synthia) {
        verificarseexistesynthia(id);
        synthia.setId(id);
        repository.save(synthia);

        return ResponseEntity.ok(synthia);
    }

    private void verificarseexistesynthia(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "id da Synthia não encontrado"));
    }
}
