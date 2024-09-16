package com.fiap.synthia.empresa.password;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordRepository extends JpaRepository<PasswordModel, Long> {
    
}
