package com.example.integrador3.service;

import com.example.integrador3.model.Carrera;
import com.example.integrador3.repository.CarreraRepository;
import com.example.integrador3.service.dto.carrera.CarreraResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class CarreraService {
    @Autowired
    private CarreraRepository carreraRepository;

    public Carrera findByCarrera(String carrera) {
        Carrera carreraDB = carreraRepository.findByCarrera(carrera);
        return carreraDB;
    }

    public List<CarreraResponseDTO> findCarrerasWithStudentCountOrdered() {
        List<Carrera> carreras = carreraRepository.findCarrerasWithStudentCountOrdered();
        return carreras.stream()
                .map(c -> new CarreraResponseDTO(c.getCarrera(), c.getEstudiantes().size()))
                .toList();
    }

    public List<Map<String, Object>> getCarreraReport() {
        List<Object[]> results = carreraRepository.findCarreraReport();
        Map<String, Map<Integer, Map<String, Long>>> report = new TreeMap<>();

        for (Object[] row : results) {
            String carrera = (String) row[0];
            Integer inscripcion = (Integer) row[1];
            Integer graduacion = (Integer) row[2];
            Long count = (Long) row[3];

            report.putIfAbsent(carrera, new TreeMap<>());
            Map<Integer, Map<String, Long>> years = report.get(carrera);

            years.putIfAbsent(inscripcion, new TreeMap<>());
            years.get(inscripcion).put("inscriptos", count);

            if (graduacion != null) {
                years.putIfAbsent(graduacion, new TreeMap<>());
                years.get(graduacion).put("egresados", count);
            }
        }

        List<Map<String, Object>> formattedReport = new ArrayList<>();
        report.forEach((carrera, years) -> {
            Map<String, Object> carreraData = new TreeMap<>();
            carreraData.put("carrera", carrera);
            carreraData.put("años", years);
            formattedReport.add(carreraData);
        });

        return formattedReport;
    }
}
