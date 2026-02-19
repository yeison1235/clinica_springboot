package com.example.holamundo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.holamundo.model.HistorialClinico;

public interface HistorialClinicoRepository extends JpaRepository<HistorialClinico, Long> {

       Optional<HistorialClinico> findByDocumento(String documento);
}
