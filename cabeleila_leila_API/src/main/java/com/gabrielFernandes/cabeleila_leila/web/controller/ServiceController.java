package com.gabrielFernandes.cabeleila_leila.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielFernandes.cabeleila_leila.entity.Service;
import com.gabrielFernandes.cabeleila_leila.service.ServiceService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/services")
public class ServiceController {

    private final ServiceService serviceService;

    @PostMapping
    public ResponseEntity<Service> create(@RequestBody Service service){
        Service serviceResponse = serviceService.save(service);
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getById(@PathVariable Long id){
        Service serviceResponse = serviceService.getById(id);
        return ResponseEntity.ok().body(serviceResponse);
    }

    @GetMapping()
    public ResponseEntity<List<Service>> getAll(){
        List<Service> serviceResponse = serviceService.getAll();
        return ResponseEntity.ok().body(serviceResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Service> updateName(@PathVariable Long id, @RequestBody Service service){
        Service serviceResponse = serviceService.updateName(id, service.getName());
        return ResponseEntity.ok().body(serviceResponse);
    }
}
