package com.gabrielFernandes.cabeleila_leila.web.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielFernandes.cabeleila_leila.entity.Scheduling;
import com.gabrielFernandes.cabeleila_leila.service.SchedulingService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/scheduling")
public class SchedulingController {
    private final SchedulingService schedulingService;

    @PostMapping
    public ResponseEntity<Scheduling> create(@RequestBody Scheduling scheduling){
        Scheduling schedulingResponse = schedulingService.save(scheduling);
        return ResponseEntity.status(HttpStatus.CREATED).body(schedulingResponse);
    }

    @GetMapping("/{id}") 
    public ResponseEntity<Scheduling> getById (@PathVariable Long id){
        Scheduling schedulingResponse = schedulingService.getById(id);
        return ResponseEntity.ok().body(schedulingResponse);
    }

    @GetMapping() 
    public ResponseEntity<List<Scheduling>> getAll (){
        List<Scheduling> schedulingResponse = schedulingService.getAll();
        return ResponseEntity.ok().body(schedulingResponse);
    }
}
