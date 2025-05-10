package com.example.integrador3.service;

import com.example.integrador3.model.Carrera;
import com.example.integrador3.repository.CarreraRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarreraService {
    @Autowired
    private CarreraRespository carreraRespository;

    public Carrera findByCarrera(String carrera) {
        Carrera carreraDB = carreraRespository.findByCarrera(carrera);
        return carreraDB;
    }
}
