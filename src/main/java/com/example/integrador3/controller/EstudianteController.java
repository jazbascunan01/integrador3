package com.example.integrador3.controller;

import com.example.integrador3.model.Estudiante;
import com.example.integrador3.service.CarreraService;
import com.example.integrador3.service.EstudianteCarreraService;
import com.example.integrador3.service.EstudianteService;
import com.example.integrador3.service.dto.estudiante.EstudianteCarreraRequestDTO;
import com.example.integrador3.service.dto.estudiante.EstudianteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controlador REST para gestionar las operaciones relacionadas con los estudiantes.
 */
@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private EstudianteCarreraService estudianteCarreraService;

    @Autowired
    private CarreraService carreraService;

    /**
     * Guarda un nuevo estudiante en la base de datos.
     *
     * @param estudiante Objeto del estudiante a guardar.
     * @return Respuesta HTTP con el estudiante guardado o un mensaje de error.
     */
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Estudiante estudiante) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.save(estudiante));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    /**
     * Matricula un estudiante en una carrera.
     *
     * @param estudianteCarrera DTO con los datos del estudiante y la carrera.
     * @return Respuesta HTTP indicando el éxito o error de la operación.
     */
    @PostMapping("/matricular")
    public ResponseEntity<?> matricular(@RequestBody EstudianteCarreraRequestDTO estudianteCarrera) {
        try {
            estudianteCarreraService.save(estudianteCarrera);
            return ResponseEntity.status(HttpStatus.OK).body("{\"success\":\"Estudiante matriculado correctamente.\"}");
        } catch (IllegalStateException ise) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"error\":\"" + ise.getMessage() + "\"}");
        } catch (RuntimeException re) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + re.getMessage() + "\"}");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error interno del servidor. No se pudo matricular el estudiante.\"}");
        }
    }

    /**
     * Busca un estudiante por su ID.
     *
     * @param id ID del estudiante.
     * @return Respuesta HTTP con el estudiante encontrado o un mensaje de error.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            Estudiante estudiante = estudianteService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(estudiante);
        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo obtener el estudiante.\"}");
        }
        return null;
    }

    /**
     * Obtiene la lista de todos los estudiantes.
     * Si se proveen parámetros de ordenamiento (`sortBy`), se aplica dicho ordenamiento.
     * Si no se provee `sortBy`, se devuelve la lista sin un ordenamiento explícito (orden por defecto de la BD).
     *
     * @param sortBy Campo por el cual ordenar (opcional, ej: "LU", "apellido").
     * @param sortDir Dirección del ordenamiento (opcional, "ASC" o "DESC", por defecto "ASC" si sortBy está presente).
     * @return Respuesta HTTP con la lista de estudiantes o un mensaje de error.
     */
    @GetMapping("")
    public ResponseEntity<?> findAll(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") String sortDir) {
        try {
            List<EstudianteResponseDTO> estudiantes;
            if (sortBy != null && !sortBy.isEmpty()) {
                estudiantes = estudianteService.findAllWithParams(sortBy, sortDir);
            } else {
                estudiantes = estudianteService.findAll();
            }
            return ResponseEntity.status(HttpStatus.OK).body(estudiantes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"" + e.getMessage() + "\"}");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"Error interno al obtener la lista de estudiantes.\"}");
        }
    }

    /**
     * Obtiene la lista de estudiantes ordenados por LU.
     *
     * @return Respuesta HTTP con la lista de estudiantes o un mensaje de error.
     */
    @GetMapping("/lu")
    public ResponseEntity<?> findAllByLU() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findAllByLU());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo obtener la lista de estudiantes.\"}");
        }
    }

    /**
     * Busca un estudiante por su LU.
     *
     * @param LU Número de LU del estudiante.
     * @return Respuesta HTTP con el estudiante encontrado o un mensaje de error.
     */
    @GetMapping("/lu/{LU}")
    public ResponseEntity<?> findByLU(@PathVariable Integer LU) {
        try {
            EstudianteResponseDTO estudiante = estudianteService.findByLU(LU);
            return ResponseEntity.status(HttpStatus.OK).body(estudiante);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo obtener el estudiante.\"}");
        }
    }

    /**
     * Obtiene la lista de estudiantes filtrados por género.
     *
     * @param genero Género de los estudiantes a buscar.
     * @return Respuesta HTTP con la lista de estudiantes o un mensaje de error.
     */
    @GetMapping("/genero/{genero}")
    public ResponseEntity<?> findAllByGenero(@PathVariable String genero) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findAllByGenero(genero));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo obtener la lista de estudiantes.\"}");
        }
    }

    /**
     * Busca estudiantes por carrera y ciudad de residencia.
     *
     * @param nombreCarrera     Nombre de la carrera.
     * @param ciudadResidencia  Ciudad de residencia de los estudiantes.
     * @return Respuesta HTTP con la lista de estudiantes o un mensaje de error.
     */
    @GetMapping("/carrera/{nombreCarrera}/ciudad/{ciudadResidencia}")
    public ResponseEntity<?> findEstudiantesByCarreraAndCiudad(
            @PathVariable String nombreCarrera,
            @PathVariable String ciudadResidencia) {
        try {
            List<EstudianteResponseDTO> estudiantes = estudianteService.findEstudiantesByCarreraAndCiudad(nombreCarrera, ciudadResidencia);
            return ResponseEntity.status(HttpStatus.OK).body(estudiantes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo obtener la lista de estudiantes para la carrera y ciudad especificadas.\"}");
        }
    }
}
