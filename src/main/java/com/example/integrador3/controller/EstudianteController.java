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

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private EstudianteCarreraService estudianteCarreraService;

    @Autowired
    private CarreraService carreraService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Estudiante estudiante) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.save(estudiante));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PostMapping("/matricular")
    public ResponseEntity<?> matricular(@RequestBody EstudianteCarreraRequestDTO estudianteCarrera) {
        try {
            estudianteCarreraService.save(estudianteCarrera);
            return ResponseEntity.status(HttpStatus.OK).body("{\"success\":\"Estudiante matriculado correctamente.\"}");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo matricular el estudiante, revise los campos e intente nuevamente.\"}");
        }
    }

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

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findAll());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo obtener la lista de estudiantes.\"}");
        }
    }

    @GetMapping("/lu")
    public ResponseEntity<?> findAllByLU() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findAllByLU());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo obtener la lista de estudiantes.\"}");
        }
    }

    @GetMapping("/lu/{LU}")
    public ResponseEntity<?> findByLU(@PathVariable Integer LU) {
        try {
            EstudianteResponseDTO estudiante = estudianteService.findByLU(LU);
            return ResponseEntity.status(HttpStatus.OK).body(estudiante);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo obtener el estudiante.\"}");
        }
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<?> findAllByGenero(@PathVariable String genero) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findAllByGenero(genero));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo obtener la lista de estudiantes.\"}");
        }
    }

}
