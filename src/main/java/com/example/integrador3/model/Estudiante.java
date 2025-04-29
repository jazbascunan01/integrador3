package com.example.integrador3.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {
    @Id
    private int dni;
    @Column(nullable = false)
    private String nombre;
    @Column (nullable = false)
    private String apellido;
    @Column (nullable = false)
    private int edad;
    @Column
    private String genero;
    @Column
    private String ciudad;
    @Column (nullable = false)
    private int num_lu;
    private List<Carrera> carreras;
}
