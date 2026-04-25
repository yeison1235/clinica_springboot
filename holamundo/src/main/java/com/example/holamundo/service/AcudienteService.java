package com.example.holamundo.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.holamundo.dto.AcudienteRequest;
import com.example.holamundo.dto.AcudienteResponse;

import com.example.holamundo.model.Acudiente;
import com.example.holamundo.repository.AcudienteRepository;

@Service
@Transactional
public class AcudienteService {

    private final AcudienteRepository acudienteRepository;

    public AcudienteService(AcudienteRepository acudienteRepository) {
        this.acudienteRepository = acudienteRepository;

    }

    public AcudienteResponse crear(AcudienteRequest request) {
        Acudiente acudiente = new Acudiente();
        applyRequest(acudiente, request);
        Acudiente saved = acudienteRepository.save(acudiente);// guardar el acudiente en la base de datos
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public Page<AcudienteResponse> listar(Pageable pageable) {
        return acudienteRepository.findAll(pageable).map(this::toResponse);

    }

    @Transactional(readOnly = true)
    public AcudienteResponse obtener(long id) {
        Acudiente acudiente = findAcudiente(id);
        return toResponse(acudiente);
    }

    public AcudienteResponse actualizar (long id, AcudienteRequest request){
        Acudiente acudiente = findAcudiente(id);
        applyRequest(acudiente, request);
        return toResponse(acudienteRepository.save(acudiente));

    }


    public void eliminar(long id) {
        Acudiente acudiente = findAcudiente(id);
        acudienteRepository.delete(acudiente);
    }

    @Transactional(readOnly = true)
    public AcudienteResponse obtenerPorId(Long id) {
        Acudiente acudiente = findAcudiente(id);
        acudiente = acudienteRepository.findById(id).orElseThrow();
        return toResponse(acudiente);

    }


 
    // private Acudiente findAcudiente(long id){
    // return acudienteRepository.findById(id).orElseThrow(()-> new
    // ResourceNotFoundException("acudiente no encontrado"));

         
    private Acudiente findAcudiente(Long id ){
        return acudienteRepository.findById(id).orElseThrow(()->new RuntimeException("acudiente no encontrado por id"  + id ));

    }

    private void applyRequest(Acudiente acudiente, AcudienteRequest request) {
        acudiente.setNombre(request.getNombre());
        acudiente.setDocumento(request.getDocumento());
        acudiente.setTelefono(request.getTelefono());
        acudiente.setTelefono2(request.getTelefono2());
        acudiente.setDireccion(request.getDireccion());
        acudiente.setEmail(request.getCorreo());
    }

    private AcudienteResponse toResponse(Acudiente acudiente) {
        AcudienteResponse response = new AcudienteResponse();
        response.setId(acudiente.getId());
        response.setNombre(acudiente.getNombre());
        response.setDocumento(acudiente.getDocumento());
        response.setTelefono(acudiente.getTelefono());
        response.setCorreo(acudiente.getEmail());
        response.setTelefono2(acudiente.getTelefono2());
        response.setDireccion(acudiente.getDireccion());
        return response;

    }
}
