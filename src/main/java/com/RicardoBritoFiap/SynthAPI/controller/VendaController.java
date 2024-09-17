package com.RicardoBritoFiap.SynthAPI.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.RicardoBritoFiap.SynthAPI.model.Venda;
import com.RicardoBritoFiap.SynthAPI.repository.VendaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/Venda")
public class VendaController {

    @Autowired
    private VendaRepository repository;

    @GetMapping("{id}")
    public ResponseEntity<Venda> getVenda(@PathVariable Long id) {

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Venda>> getVenda() {
        List<Venda> vendas = repository.findAll();
        return ResponseEntity.ok(vendas);
    }

    @PostMapping
    public ResponseEntity<Venda> createVenda(@RequestBody @Valid Venda vendas) {
        repository.save(vendas);
        return ResponseEntity.ok(vendas);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVenda(@PathVariable Long id) {
        verificarseexisteempresa(id);
        repository.deleteById(id);

        return ResponseEntity.ok("Venda deletada com sucesso");
    }

    @PutMapping("{id}")
    public ResponseEntity<Venda> updateVenda(@PathVariable Long id, @RequestBody Venda venda) {
        verificarseexisteempresa(id);
        venda.setId(id);
        repository.save(venda);

        return ResponseEntity.ok(venda);
    }

    private void verificarseexisteempresa(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "id da venda não encontrado"));
    }
}
