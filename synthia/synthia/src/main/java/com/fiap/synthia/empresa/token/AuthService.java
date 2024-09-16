package com.fiap.synthia.empresa.token;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.fiap.synthia.empresa.user.UserRepository;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public Token login (Credentials credentials) {
        var user = userRepository.findByUsername(credentials.username())
                .orElseThrow( () -> new RuntimeException("Access denied") );

        if ( ! passwordEncoder.matches(credentials.password(), user.getPassword()) )
            throw new RuntimeException("Access denied");

        return tokenService.createToken(credentials.username());
    }

}