package com.example.holamundo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.holamundo.model.Acudiente;
import com.example.holamundo.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {


    

    
}
