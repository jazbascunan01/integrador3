package com.example.integrador3.service.dto.carrera;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CarreraResponseDTO {
    private String carrera;
    private int estudiantesInscriptos;
}
