package com.example.integrador3.service;

import com.example.integrador3.model.Carrera;
import com.example.integrador3.model.Estudiante;
import com.example.integrador3.model.EstudianteCarrera;
import com.example.integrador3.repository.EstudianteCarreraRepository;
import com.example.integrador3.service.dto.estudiante.EstudianteCarreraRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteCarreraService {
    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;
    @Autowired
    private EstudianteService estudianteService;
    @Autowired
    private CarreraService carreraService;

    public EstudianteCarrera save(EstudianteCarreraRequestDTO estudianteCarreraDTO) {
        try {
            Estudiante estudiante = estudianteService.findById(estudianteCarreraDTO.getIdEstudiante());
            Carrera carrera = carreraService.findByCarrera(estudianteCarreraDTO.getNombreCarrera());
            EstudianteCarrera estudianteCarrera = new EstudianteCarrera();
            if( estudiante != null && carrera != null) {
                estudianteCarrera.setEstudiante(estudiante);
                estudianteCarrera.setCarrera(carrera);
                estudianteCarrera.setInscripcion(estudianteCarreraDTO.getInscripcion());
            }
            return estudianteCarreraRepository.save(estudianteCarrera);
        } catch (Exception e) {
            throw new RuntimeException("Error al matricular el estudiante: " + e.getMessage());
        }

    }
}
