package com.example.holamundo.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.holamundo.dto.AnimalRequest;
import com.example.holamundo.dto.AnimalResponse;
import com.example.holamundo.service.AnimalService; 

@RestController
@RequestMapping("/animales")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<AnimalResponse> crear(@Valid @RequestBody AnimalRequest request) {
        AnimalResponse response = animalService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AnimalResponse>> listar() {
        return ResponseEntity.ok(animalService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalResponse> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.obtener(id));
    }

    @GetMapping("/acudiente/documento/{documento}")
    public ResponseEntity<List<AnimalResponse>> listarPorAcudienteDocumento(@PathVariable Long idAcudiente) {
        return ResponseEntity.ok(animalService.listarPorAcudiente(idAcudiente));
    }


    @GetMapping("/acudiente/documento/{documento}")
    public ResponseEntity<List<AnimalResponse>> listarPorDocumentoAcudiente(@PathVariable String documento) {
        return ResponseEntity.ok(animalService.listarPorDocumentoAcudiente(documento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        animalService.eliminar(id);
        return ResponseEntity.noContent().build();
    }




    

}