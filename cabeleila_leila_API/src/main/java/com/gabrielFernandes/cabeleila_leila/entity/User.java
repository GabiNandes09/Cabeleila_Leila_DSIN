package com.gabrielFernandes.cabeleila_leila.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;
    @Column(name = "password", nullable = false, length = 200)
    private String password;
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 7)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Scheduling> schedulings = new ArrayList<>();

    public enum Role{
        ADMIN, CLIENT
    }

}
