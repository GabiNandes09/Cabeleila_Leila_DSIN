package com.gabrielFernandes.cabeleila_leila.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielFernandes.cabeleila_leila.entity.User;
import com.gabrielFernandes.cabeleila_leila.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
    
    private final UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Usuário não encontrado")
        );
    }

    @Transactional
    public User updatePassword(Long id, String password){
        User user = getById(id);
        user.setPassword(password);
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> getAll(){
        return userRepository.findAll();
    }
}
