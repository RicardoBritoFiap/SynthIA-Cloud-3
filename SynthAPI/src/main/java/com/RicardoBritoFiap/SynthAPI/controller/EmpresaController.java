package com.RicardoBritoFiap.SynthAPI.controller;

import java.util.List;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.RicardoBritoFiap.SynthAPI.model.Empresa;
import com.RicardoBritoFiap.SynthAPI.repository.EmpresaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/Empresa")
public class EmpresaController {

    @Autowired
    private EmpresaRepository repository;

    @GetMapping("{id}")
    public ResponseEntity<Empresa> getEmpresa(@PathVariable Long id) {

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> getEmpresa() {
        List<Empresa> empresas = repository.findAll();
        return ResponseEntity.ok(empresas);
    }

    @PostMapping
    public ResponseEntity<Empresa> createEmpresa(@RequestBody @Valid Empresa empresas) {
        repository.save(empresas);
        return ResponseEntity.ok(empresas);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmpresa(@PathVariable Long id) {
        verificarseexisteempresa(id);
        repository.deleteById(id);

        return ResponseEntity.ok("Empresa deletada com sucesso");
    }

    @PutMapping("{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        verificarseexisteempresa(id);
        empresa.setId(id);
        repository.save(empresa);

        return ResponseEntity.ok(empresa);
    }

    private void verificarseexisteempresa(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "id da empresa não encontrado"));
    }
}