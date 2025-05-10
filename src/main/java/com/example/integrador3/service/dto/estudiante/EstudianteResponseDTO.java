package com.example.integrador3.service.dto.estudiante;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EstudianteResponseDTO {
    private Integer DNI;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String ciudad;
    private Integer LU;
}
