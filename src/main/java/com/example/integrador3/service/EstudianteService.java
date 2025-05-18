package com.example.integrador3.service;

import com.example.integrador3.model.Estudiante;
import com.example.integrador3.repository.EstudianteRepository;
import com.example.integrador3.service.dto.estudiante.EstudianteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para gestionar las operaciones relacionadas con los estudiantes.
 */
@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;
    // Lista de campos válidos para ordenar (para seguridad y evitar errores)
    private static final List<String> VALID_SORT_FIELDS = Arrays.asList("DNI", "nombre", "apellido", "edad", "genero", "ciudad", "LU");

    /**
     * Guarda un nuevo estudiante en la base de datos.
     *
     * @param estudiante Objeto del estudiante a guardar.
     * @return El estudiante guardado.
     */
    public Estudiante save(Estudiante estudiante) {
        try {
            return estudianteRepository.save(estudiante);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el estudiante: " + e.getMessage());
        }
    }

    /**
     * Busca un estudiante por su ID.
     *
     * @param id ID del estudiante.
     * @return El estudiante encontrado o null si no existe.
     */
    public Estudiante findById(Integer id) {
        return estudianteRepository.findById(id).orElse(null);
    }

    /**
     * Busca un estudiante por su LU.
     *
     * @param LU Número de LU del estudiante.
     * @return DTO del estudiante encontrado.
     */
    public EstudianteResponseDTO findByLU(Integer LU) {
        Estudiante estudiante = estudianteRepository.findByLU(LU);
        return mapToDTO(estudiante);
    }

    /**
     * Obtiene la lista de todos los estudiantes.
     *
     * @return Lista de DTOs de estudiantes.
     */

    public List<EstudianteResponseDTO> findAll() {
        List<Estudiante> estudiantes = estudianteRepository.findAll();
        return estudiantes.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    /**
     * Obtiene la lista de todos los estudiantes, aplicando el ordenamiento especificado.
     * @param sortBy Campo por el cual ordenar (ej: "LU", "apellido"). No debe ser null ni vacío.
     * @param sortDirection Dirección del ordenamiento ("ASC" o "DESC").
     * @return Lista de DTOs de estudiantes ordenados.
     */
    public List<EstudianteResponseDTO> findAllWithParams(String sortBy, String sortDirection) {
        if (!VALID_SORT_FIELDS.contains(sortBy)) {
            throw new IllegalArgumentException("Campo de ordenamiento no válido: " + sortBy + ". Campos válidos: " + VALID_SORT_FIELDS);
        }

        Sort.Direction direction = sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);

        List<Estudiante> estudiantes = estudianteRepository.findAll(sort);
        return estudiantes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    /**
     * Obtiene la lista de estudiantes ordenados por LU.
     *
     * @return Lista de DTOs de estudiantes ordenados.
     */
    public List<EstudianteResponseDTO> findAllByLU() {
        // Llama al nuevo métdo del repositorio que ya ordena en la BD
        List<Estudiante> estudiantesOrdenados = estudianteRepository.findAllByOrderByLU();
        // Mapea a DTO
        return estudiantesOrdenados.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    /**
     * Obtiene la lista de estudiantes filtrados por género.
     *
     * @param genero Género de los estudiantes a buscar.
     * @return Lista de DTOs de estudiantes filtrados.
     */
    public List<EstudianteResponseDTO> findAllByGenero(String genero) {
        List<Estudiante> estudiantes = estudianteRepository.findAllByGenero(genero);
        List<EstudianteResponseDTO> estudiantesResponse = new ArrayList<>();
        for (Estudiante estudiante : estudiantes) {
            EstudianteResponseDTO estudianteResponse = mapToDTO(estudiante);
            estudiantesResponse.add(estudianteResponse);
        }
        return estudiantesResponse;
    }

    /**
     * Busca estudiantes por carrera y ciudad de residencia.
     *
     * @param nombreCarrera    Nombre de la carrera.
     * @param ciudadResidencia Ciudad de residencia de los estudiantes.
     * @return Lista de DTOs de estudiantes filtrados.
     */
    public List<EstudianteResponseDTO> findEstudiantesByCarreraAndCiudad(String nombreCarrera, String ciudadResidencia) {
        List<Estudiante> estudiantes = estudianteRepository.findEstudiantesByCarreraAndCiudad(nombreCarrera, ciudadResidencia);
        List<EstudianteResponseDTO> estudiantesResponse = new ArrayList<>();
        for (Estudiante estudiante : estudiantes) {
            EstudianteResponseDTO estudianteResponse = mapToDTO(estudiante);
            estudiantesResponse.add(estudianteResponse);
        }
        return estudiantesResponse;
    }
    /**
     * Mapea un objeto Estudiante a un DTO de Estudiante.
     *
     * @param estudiante Objeto del estudiante.
     * @return DTO del estudiante.
     */
    public EstudianteResponseDTO mapToDTO(Estudiante estudiante) {
        if (estudiante == null) {
            return null;
        }
        EstudianteResponseDTO estudianteResponse = new EstudianteResponseDTO();
        estudianteResponse.setDNI(estudiante.getDNI());
        estudianteResponse.setNombre(estudiante.getNombre());
        estudianteResponse.setApellido(estudiante.getApellido());
        estudianteResponse.setEdad(estudiante.getEdad());
        estudianteResponse.setGenero(estudiante.getGenero());
        estudianteResponse.setCiudad(estudiante.getCiudad());
        estudianteResponse.setLU(estudiante.getLU());
        return estudianteResponse;
    }


}
