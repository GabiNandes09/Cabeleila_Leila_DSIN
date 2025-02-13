package com.gabrielFernandes.cabeleila_leila.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gabrielFernandes.cabeleila_leila.entity.Service;
import com.gabrielFernandes.cabeleila_leila.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    @Transactional
    public Service save (Service service){
        return serviceRepository.save(service);
    }

    @Transactional(readOnly = true)
    public Service getById(Long id){
        return serviceRepository.findById(id).orElseThrow(
            () -> new RuntimeException("Serviço não encontrado")
        );
    }

    @Transactional
    public Service updateName(Long id, String name){
        Service service = getById(id);
        service.setName(name);
        return service;
    }

    @Transactional(readOnly = true)
    public List<Service> getAll (){
        return serviceRepository.findAll();
    }
}
