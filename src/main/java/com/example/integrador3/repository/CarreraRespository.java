package com.example.integrador3.repository;

import com.example.integrador3.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRespository extends JpaRepository<Carrera, Integer> {

    Carrera findByCarrera(String carrera);

    @Query("SELECT c FROM Carrera c JOIN c.estudiantes e GROUP BY c ORDER BY COUNT(e) DESC")
    List<Carrera> findCarrerasWithStudentCountOrdered();

}
