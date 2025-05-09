package com.example.integrador3.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Estudiante {
    @Id
    private Integer DNI;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private int edad;

    @Column
    private String genero;

    @Column
    private String ciudad;

    @Column (nullable = false)
    private int LU;

    @OneToMany (mappedBy = "estudiante", fetch = FetchType.LAZY)
    private List<EstudianteCarrera> carreras;


    public Estudiante(Integer DNI, String nombre, String apellido,  int edad, String genero, String ciudad, int LU ) {
        this.LU = LU;
        this.ciudad = ciudad;
        this.genero = genero;
        this.edad = edad;
        this.apellido = apellido;
        this.nombre = nombre;
        this.DNI = DNI;
        this.carreras = new ArrayList<>();
    }
}
