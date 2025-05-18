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

    @Transactional // Asegura que todas las operaciones de BD sean una única transacción
    public EstudianteCarrera save(EstudianteCarreraRequestDTO estudianteCarreraDTO) {
        // 1. Buscar el Estudiante
        Estudiante estudiante = estudianteService.findById(estudianteCarreraDTO.getIdEstudiante());
        if (estudiante == null) {
            // Lanza una excepción clara si el estudiante no se encuentra
            throw new RuntimeException("Estudiante no encontrado con DNI: " + estudianteCarreraDTO.getIdEstudiante());
        }

        // 2. Buscar la Carrera
        Carrera carrera = carreraService.findByCarrera(estudianteCarreraDTO.getNombreCarrera());
        if (carrera == null) {
            // Lanza una excepción clara si la carrera no se encuentra
            throw new RuntimeException("Carrera no encontrada con nombre: " + estudianteCarreraDTO.getNombreCarrera());
        }

        // 3. Verificar si el estudiante ya está matriculado en esa carrera
        if (estudianteCarreraRepository.existsByEstudianteAndCarrera(estudiante, carrera)) {
            // Lanza una excepción específica si ya existe la matriculación
            throw new IllegalStateException("El estudiante con DNI " + estudiante.getDNI() +
                    " ya está matriculado en la carrera '" + carrera.getCarrera() + "'.");
        }

        // 4. Crear la nueva entidad EstudianteCarrera
        EstudianteCarrera estudianteCarrera = new EstudianteCarrera();
        estudianteCarrera.setEstudiante(estudiante);
        estudianteCarrera.setCarrera(carrera);
        estudianteCarrera.setInscripcion(estudianteCarreraDTO.getInscripcion());

        // 5. Establecer la antigüedad en 0, según lo solicitado
        estudianteCarrera.setAntiguedad(0);

        // 6. Establecer el estado de graduación como "no graduado" (0)
        estudianteCarrera.setGraduacion(0);
        // 7. Guardar la nueva inscripción
        try {
            // El try-catch aquí es específicamente para la operación de guardado.
            // Si save() lanza una DataAccessException (por ej. un problema de constraint
            // que no hayamos previsto), la capturamos y relanzamos.
            return estudianteCarreraRepository.save(estudianteCarrera);
        } catch (Exception e) {
            // Puedes loggear el error aquí si tienes un logger configurado
            // log.error("Error al intentar guardar la matriculación en la BD", e);
            throw new RuntimeException("Error al guardar la matriculación del estudiante en la base de datos: " + e.getMessage(), e);
        }
    }
}
