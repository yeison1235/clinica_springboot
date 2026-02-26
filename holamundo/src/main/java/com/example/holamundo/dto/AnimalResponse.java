package com.example.holamundo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalResponse {

    private long Id;
    
    private String nombre;

    private String especie;

    private String raza;

    private String edad;

    private String peso;

    private String fechaNacimiento;

    private String sexo;

    private long acudienteId;

}
