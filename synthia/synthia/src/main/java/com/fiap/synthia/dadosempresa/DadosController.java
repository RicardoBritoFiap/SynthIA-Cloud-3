package com.fiap.synthia.dadosempresa;

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
import static org.springframework.http.HttpStatus.NOT_FOUND;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/user_data")
public class DadosController {
    @Autowired
    private DadosRepository repository;

    @GetMapping("{id}")
    public ResponseEntity<DadosModel> getDados(@PathVariable Long id) {

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user_data")
    public ResponseEntity<List<DadosModel>> getDados() {
        List<DadosModel> Dados = repository.findAll();
        return ResponseEntity.ok(Dados);
    }

    @PostMapping
    public ResponseEntity<DadosModel> createDados(@RequestBody @Valid DadosModel Dados) {
        repository.save(Dados);
        return ResponseEntity.ok(Dados);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDados(@PathVariable Long id) {
        verificarseexisteDados(id);
        repository.deleteById(id);

        return ResponseEntity.ok("Dados deletados com sucesso");
    }

    @PutMapping("{id}")
    public ResponseEntity<DadosModel> updateDados(@PathVariable Long id, @RequestBody DadosModel Dados) {
        verificarseexisteDados(id);
        Dados.setId(id);
        repository.save(Dados);

        return ResponseEntity.ok(Dados);
    }

    private void verificarseexisteDados(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "id dos Dados não encontrado"));
    }
}