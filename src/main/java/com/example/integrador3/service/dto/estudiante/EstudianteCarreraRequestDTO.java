package com.example.integrador3.service.dto.estudiante;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EstudianteCarreraRequestDTO {
    private Integer idEstudiante;
    private String nombreCarrera;
    private int inscripcion;
    private int graduacion;
    private int antiguedad;
}
