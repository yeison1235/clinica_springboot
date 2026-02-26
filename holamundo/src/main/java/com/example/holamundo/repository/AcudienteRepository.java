package com.example.holamundo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.holamundo.model.Acudiente;

public interface AcudienteRepository  extends JpaRepository<Acudiente, Long> {
    Optional<Acudiente> findByDocumento(String documento);

    
    
    
}
