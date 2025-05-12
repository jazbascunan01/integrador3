package com.example.integrador3.service;

import com.example.integrador3.model.Carrera;
import com.example.integrador3.repository.CarreraRepository;
import com.example.integrador3.service.dto.carrera.CarreraResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
