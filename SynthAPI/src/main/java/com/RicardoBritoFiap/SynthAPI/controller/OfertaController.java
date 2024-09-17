package com.RicardoBritoFiap.SynthAPI.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.RicardoBritoFiap.SynthAPI.model.Oferta;
import com.RicardoBritoFiap.SynthAPI.repository.OfertaRepository;
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
@RequestMapping(path = "/Oferta")
public class OfertaController {

    @Autowired
    private OfertaRepository repository;

    @GetMapping("{id}")
    public ResponseEntity<Oferta> getOferta(@PathVariable Long id) {

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Oferta>> getOferta() {
        List<Oferta> ofertas = repository.findAll();
        return ResponseEntity.ok(ofertas);
    }

    @PostMapping
    public ResponseEntity<Oferta> createOferta(@RequestBody @Valid Oferta ofertas) {
        repository.save(ofertas);
        return ResponseEntity.ok(ofertas);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOferta(@PathVariable Long id) {
        verificarseexisteoferta(id);
        repository.deleteById(id);

        return ResponseEntity.ok("Oferta deletada com sucesso");
    }

    @PutMapping("{id}")
    public ResponseEntity<Oferta> updateOferta(@PathVariable Long id, @RequestBody Oferta oferta) {
        verificarseexisteoferta(id);
        oferta.setId(id);
        repository.save(oferta);

        return ResponseEntity.ok(oferta);
    }

    private void verificarseexisteoferta(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "id da oferta não encontrado"));
    }
}
