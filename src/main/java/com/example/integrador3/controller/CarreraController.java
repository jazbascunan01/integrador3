package com.example.integrador3.controller;

import com.example.integrador3.service.CarreraService;
import com.example.integrador3.service.dto.carrera.CarreraResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController {
    @Autowired
    private CarreraService carreraService;

    @GetMapping("conEstudiantes")
    public ResponseEntity<?> getEstudiantes() {
        try {
            List<CarreraResponseDTO> carreras = carreraService.findCarrerasWithStudentCountOrdered();
            return ResponseEntity.ok(carreras);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"Error. No se pudo obtener la lista de carreras.\"}");
        }
    }

    @GetMapping("/reporte")
    public ResponseEntity<?> getCarreraReport() {
        try {
            return ResponseEntity.ok(carreraService.getCarreraReport());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"error\":\"Error al generar el reporte de carreras.\"}");
        }
    }
}
