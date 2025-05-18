package com.example.integrador3.service;

import com.example.integrador3.model.Carrera;
import com.example.integrador3.model.Estudiante;
import com.example.integrador3.model.EstudianteCarrera;
import com.example.integrador3.repository.EstudianteCarreraRepository;
import com.example.integrador3.service.dto.estudiante.EstudianteCarreraRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class EstudianteCarreraService {
    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;
    @Autowired
    private EstudianteService estudianteService;
    @Autowired
    private CarreraService carreraService;

    @Transactional
    public EstudianteCarrera save(EstudianteCarreraRequestDTO estudianteCarreraDTO) {
        Estudiante estudiante = estudianteService.findById(estudianteCarreraDTO.getIdEstudiante());
        if (estudiante == null) {
            throw new RuntimeException("Estudiante no encontrado con DNI: " + estudianteCarreraDTO.getIdEstudiante());
        }
        Carrera carrera = carreraService.findByCarrera(estudianteCarreraDTO.getNombreCarrera());
        if (carrera == null) {
            throw new RuntimeException("Carrera no encontrada con nombre: " + estudianteCarreraDTO.getNombreCarrera());
        }
        if (estudianteCarreraRepository.existsByEstudianteAndCarrera(estudiante, carrera)) {
            throw new IllegalStateException("El estudiante con DNI " + estudiante.getDNI() +
                    " ya está matriculado en la carrera '" + carrera.getCarrera() + "'.");
        }
        EstudianteCarrera estudianteCarrera = new EstudianteCarrera();
        estudianteCarrera.setEstudiante(estudiante);
        estudianteCarrera.setCarrera(carrera);
        estudianteCarrera.setInscripcion(estudianteCarreraDTO.getInscripcion());
        estudianteCarrera.setAntiguedad(0);
        estudianteCarrera.setGraduacion(0);
        try {
            return estudianteCarreraRepository.save(estudianteCarrera);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar la matriculación del estudiante en la base de datos: " + e.getMessage(), e);
        }
    }
}
