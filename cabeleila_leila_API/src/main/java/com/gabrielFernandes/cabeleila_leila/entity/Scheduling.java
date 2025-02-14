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
@Table(name = "schedulings")
public class Scheduling implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "date", nullable = false)
    private String date;
    @Column(name = "hour", nullable = false)
    private String hour;

    @ManyToMany(mappedBy = "schedulings", cascade = CascadeType.MERGE)
    private List<Service> services = new ArrayList<>();
}
