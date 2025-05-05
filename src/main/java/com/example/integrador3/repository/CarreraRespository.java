package com.example.integrador3.repository;

import com.example.integrador3.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarreraRespository extends JpaRepository<Carrera, Integer> {
}
