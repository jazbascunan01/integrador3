package com.example.integrador3.service;

import com.example.integrador3.model.Estudiante;
import com.example.integrador3.repository.EstudianteRepository;
import com.example.integrador3.service.dto.estudiante.EstudianteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;

    public Estudiante save(Estudiante estudiante) throws Exception {
        try {
            return estudianteRepository.save(estudiante);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el estudiante: " + e.getMessage());
        }
    }

    public Estudiante findById(Integer id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    public Estudiante findByLU(Integer LU) {
        return estudianteRepository.findByLU(LU);
    }

    public List<EstudianteResponseDTO> findAll() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        List<EstudianteResponseDTO> estudiantesResponse = new ArrayList<>();
        for (Estudiante estudiante : estudiantes) {
            EstudianteResponseDTO estudianteResponse = new EstudianteResponseDTO();
            estudianteResponse.setDNI(estudiante.getDNI());
            estudianteResponse.setNombre(estudiante.getNombre());
            estudianteResponse.setApellido(estudiante.getApellido());
            estudianteResponse.setEdad(estudiante.getEdad());
            estudianteResponse.setGenero(estudiante.getGenero());
            estudianteResponse.setCiudad(estudiante.getCiudad());
            estudianteResponse.setLU(estudiante.getLU());
            estudiantesResponse.add(estudianteResponse);
        }
        return estudiantesResponse;
    }

    public List<EstudianteResponseDTO> findAllByLU() {
        List<EstudianteResponseDTO> estudiantes = this.findAll();
        estudiantes.sort((e1, e2) -> e1.getLU().compareTo(e2.getLU()));
        return estudiantes;
    }

}
