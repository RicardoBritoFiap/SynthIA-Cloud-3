package com.fiap.synthia.empresa.user;

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
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable Long id) {

        return repository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getUser() {
        List<UserModel> Users = repository.findAll();
        return ResponseEntity.ok(Users);
    }

    @PostMapping
    public ResponseEntity<UserModel> createUser(@RequestBody @Valid UserModel Users) {
        repository.save(Users);
        return ResponseEntity.ok(Users);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        verificarseexisteUser(id);
        repository.deleteById(id);

        return ResponseEntity.ok("User deletado com sucesso");
    }

    @PutMapping("{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserModel User) {
        verificarseexisteUser(id);
        User.setId(id);
        repository.save(User);

        return ResponseEntity.ok(User);
    }

    private void verificarseexisteUser(Long id) {
        repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        NOT_FOUND,
                        "id do User não encontrado"));
    }
}