package com.example.integrador3.model;

import jakarta.persistence.*;
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
    private Integer dni;

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

    @Column(nullable = false)
    private int num_lu;

    @ManyToMany
    @JoinTable(
            name = "estudiante_carrera",
            joinColumns = @JoinColumn(name = "estudiante_dni"),
            inverseJoinColumns = @JoinColumn(name = "carrera_id")
    )
    private List<Carrera> carreras;
}
