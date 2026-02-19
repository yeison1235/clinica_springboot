package com.example.holamundo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.holamundo.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
        Optional<Animal> findByDocumento(String nombre);

}
