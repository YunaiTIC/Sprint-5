package com.openwebinars.spring.service;


import com.openwebinars.spring.dto.UserRegisterDTO;
import com.openwebinars.spring.entity.UserAuthority;
import com.openwebinars.spring.entity.UserEntity;
import com.openwebinars.spring.repositorios.UserEntityRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserEntityService {

    private final UserEntityRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserEntityService(UserEntityRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<UserEntity> findByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    public UserEntity save(UserRegisterDTO userDTO) {
        UserEntity user = new UserEntity(
                null,
                userDTO.username(),
                passwordEncoder.encode(userDTO.password()),
                userDTO.email(),
                List.of(UserAuthority.READ)
        );
        return this.repository.save(user);
    }

}