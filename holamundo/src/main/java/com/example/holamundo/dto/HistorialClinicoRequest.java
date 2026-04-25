package com.example.holamundo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistorialClinicoRequest {

    
    private String nombre;
    @NotBlank
    private String telefono;
    @NotBlank
    private String telefono2;
    @NotBlank
    private String direccion;
    @NotBlank
    private String correo;
    @NotBlank
    private String documento;

    
}
