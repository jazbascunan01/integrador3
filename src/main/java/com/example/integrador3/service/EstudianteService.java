package com.example.integrador3.service;

import com.example.integrador3.model.Estudiante;
import com.example.integrador3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // TODO Usar DTO sin carreras
    public List<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }

    public List<Estudiante> findAllByDni() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        estudiantes.sort((e1, e2) -> e1.getDNI().compareTo(e2.getDNI()));
        return estudiantes;
    }

}
