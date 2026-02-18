package com.example.holamundo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "animal")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id; 


    @Column(nullable = false)
    private String nombre;
    
    @Column(nullable = false, unique = true)
    private String especie;


    @Column(nullable = false)
    private String raza;

    @Column(nullable = false)
    private String edad;

    @Column(nullable = false)
    private String peso;

    @Column(nullable = false)
    private String fechaNacimiento;

    @Column(nullable = false)
    private String sexo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "acudiente_id", nullable = false)
    @ToString.Exclude
    private Acudiente acudiente;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<HistorialClinico> animales = new ArrayList<>();
    
}
