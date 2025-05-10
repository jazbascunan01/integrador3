package com.example.integrador3.service;

import com.example.integrador3.model.Carrera;
import com.example.integrador3.repository.CarreraRespository;
import com.example.integrador3.service.dto.carrera.CarreraResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraService {
    @Autowired
    private CarreraRespository carreraRespository;

    public Carrera findByCarrera(String carrera) {
        Carrera carreraDB = carreraRespository.findByCarrera(carrera);
        return carreraDB;
    }

    public List<CarreraResponseDTO> findCarrerasWithStudentCountOrdered() {
        List<Carrera> carreras = carreraRespository.findCarrerasWithStudentCountOrdered();
        return carreras.stream()
                .map(c -> new CarreraResponseDTO(c.getCarrera(), c.getEstudiantes().size()))
                .toList();
    }
}
