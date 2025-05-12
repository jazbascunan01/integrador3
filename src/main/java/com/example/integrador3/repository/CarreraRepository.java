package com.example.integrador3.repository;

import com.example.integrador3.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    Carrera findByCarrera(String carrera);

    @Query("SELECT c FROM Carrera c JOIN c.estudiantes e GROUP BY c ORDER BY COUNT(e) DESC")
    List<Carrera> findCarrerasWithStudentCountOrdered();

    @Query("SELECT c.carrera, ec.inscripcion, ec.graduacion, COUNT(ec.id) " +
            "FROM EstudianteCarrera ec " +
            "JOIN ec.carrera c " +
            "GROUP BY c.carrera, ec.inscripcion, ec.graduacion " +
            "ORDER BY c.carrera ASC, ec.inscripcion ASC, ec.graduacion ASC")
    List<Object[]> findCarreraReport();
}
