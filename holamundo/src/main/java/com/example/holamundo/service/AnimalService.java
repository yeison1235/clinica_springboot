package com.example.holamundo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.holamundo.dto.AcudienteRequest;
import com.example.holamundo.dto.AcudienteResponse;
import com.example.holamundo.dto.AnimalRequest;
import com.example.holamundo.dto.AnimalResponse;
import com.example.holamundo.model.Acudiente;
import com.example.holamundo.model.Animal;
import com.example.holamundo.repository.AcudienteRepository;
import com.example.holamundo.repository.AnimalRepository;



@Service
@Transactional

public class AnimalService {

    private final AnimalRepository animalRepository;

    private final AcudienteRepository acudienteRepository;

    

    public AnimalService(AnimalRepository animalRepository, AcudienteRepository acudienteRepository) {
        this.animalRepository = animalRepository;
        this.acudienteRepository = acudienteRepository;
    }

    //crear
    public AnimalResponse crear(AnimalRequest request) {
        Acudiente acudiente = findAcudiente(request.getAcudienteId());
        Animal animal = new Animal();
        applyRequest(animal, request, acudiente);
        Animal saved = animalRepository.save(animal);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public Page<AnimalResponse> listar(Pageable pageable) {
        return animalRepository.findAll(pageable).map(this::toResponse);

    }

    @Transactional(readOnly = true)
    public AnimalResponse obtener(long id) {
        Animal animal = findAnimal(id);
        return toResponse(animal);
    }

    public AnimalResponse actualizar (long id, AnimalRequest request){
        Acudiente acudiente = findAcudiente(request.getAcudienteId());
        Animal animal = new Animal();
        applyRequest(animal, request, acudiente);
        return toResponse(animalRepository.save(animal));

    }

    public void eliminar(long id) {
        Acudiente acudiente = findAcudiente(id);
        acudienteRepository.delete(acudiente);
    }


    // private Acudiente findAcudiente(long id){
    // return acudienteRepository.findById(id).orElseThrow(()-> new
    // ResourceNotFoundException("acudiente no encontrado"));

         
    private Animal findAnimal(Long id ){
        return animalRepository.findById(id).orElseThrow(()->new RuntimeException("animal no encontrado por id"  + id ));

    }

    private Acudiente findAcudiente(Long id ){
        return acudienteRepository.findById(id).orElseThrow(()->new RuntimeException("animal no encontrado por id"  + id ));

    }

    private void applyRequest(Animal animal, AnimalRequest request, Acudiente acudiente) {
        animal.setNombre(request.getNombre());
        animal.setEspecie(request.getEspecie());
        animal.setRaza(request.getRaza());
        animal.setEdad(request.getEdad());
        animal.setPeso(request.getPeso());
        animal.setFechaNacimiento(request.getFechaNacimiento());
        animal.setSexo(request.getSexo());
        animal.setAcudiente(acudiente);
    }
    

    private AnimalResponse toResponse(Animal animal) {
        AnimalResponse response = new AnimalResponse();
        response.setId(animal.getId());
        response.setNombre(animal.getNombre());
        response.setEspecie(animal.getEspecie());
        response.setRaza(animal.getRaza());
        response.setEdad(animal.getEdad());
        response.setPeso(animal.getPeso());
        response.setFechaNacimiento(animal.getFechaNacimiento());
        response.setSexo(animal.getSexo());
        response.setAcudienteId(animal.getAcudiente().getId());
        return response;

    }
}
    
    
    

    

