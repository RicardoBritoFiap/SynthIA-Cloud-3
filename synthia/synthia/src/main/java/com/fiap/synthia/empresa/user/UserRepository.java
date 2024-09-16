package com.fiap.synthia.empresa.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    public Optional<UserModel> findByUsername(String username);
}
