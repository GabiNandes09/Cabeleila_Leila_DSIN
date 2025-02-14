package com.gabrielFernandes.cabeleila_leila.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielFernandes.cabeleila_leila.entity.Scheduling;
import com.gabrielFernandes.cabeleila_leila.repository.SchedulingRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class SchedulingService {
    private final SchedulingRepository schedulingRepository;

    @Transactional
    public Scheduling save(Scheduling scheduling) {
        return schedulingRepository.save(scheduling);
    }

    @Transactional(readOnly = true)
    public Scheduling getById(Long id) {
        return schedulingRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Agendamento não encontrado")
        );
    }

    @Transactional(readOnly = true)
    public List<Scheduling> getAll(){
        return schedulingRepository.findAll();
    }
}
