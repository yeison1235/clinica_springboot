package com.example.holamundo.repository;

import java.util.Optional;

import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.holamundo.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
        
        Page<Animal> findByDocumento(Long acudienteId,  Pageable pageable);


}
