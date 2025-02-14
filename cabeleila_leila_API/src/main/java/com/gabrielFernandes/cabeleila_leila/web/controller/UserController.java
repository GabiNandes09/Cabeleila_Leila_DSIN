package com.gabrielFernandes.cabeleila_leila.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielFernandes.cabeleila_leila.entity.User;
import com.gabrielFernandes.cabeleila_leila.service.UserService;

import lombok.RequiredArgsConstructor;




@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        User userResponse = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/{id}") 
    public ResponseEntity<User> getById (@PathVariable Long id){
        User userResponse = userService.getById(id);
        return ResponseEntity.ok().body(userResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable Long id, @RequestBody User user){
        User userResponse = userService.updatePassword(id, user.getPassword());
        return ResponseEntity.ok().body(userResponse);
    }

    @GetMapping() 
    public ResponseEntity<List<User>> getAll (){
        List<User> userResponse = userService.getAll();
        return ResponseEntity.ok().body(userResponse);
    }

}
