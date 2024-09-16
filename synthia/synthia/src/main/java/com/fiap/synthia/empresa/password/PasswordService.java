package com.fiap.synthia.empresa.password;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
    @Autowired
    private PasswordRepository repository;

    public List<PasswordModel> getAll(){
        return repository.findAll();
    }
}
