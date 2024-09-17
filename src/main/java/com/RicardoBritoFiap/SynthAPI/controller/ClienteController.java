package com.RicardoBritoFiap.SynthAPI.controller;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.RicardoBritoFiap.SynthAPI.model.Cliente;
import com.RicardoBritoFiap.SynthAPI.repository.ClienteRepository;
import jakarta.validation.Valid;
import static org.springframework.http.HttpStatus.NOT_FOUND;

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
@RequestMapping(path = "/Cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping("{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id) {

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getCliente() {
        List<Cliente> clientes = repository.findAll();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody @Valid Cliente clientes) {
        repository.save(clientes);
        return ResponseEntity.ok(clientes);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        verificarseexistecliente(id);
        repository.deleteById(id);

        return ResponseEntity.ok("Cliente deletado com sucesso");
    }

    @PutMapping("{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        verificarseexistecliente(id);
        cliente.setId(id);
        repository.save(cliente);

        return ResponseEntity.ok(cliente);
    }

    private void verificarseexistecliente(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "id do cliente não encontrado"));
    }
}
