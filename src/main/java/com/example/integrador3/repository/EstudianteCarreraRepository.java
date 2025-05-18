package com.example.integrador3.repository;

import com.example.integrador3.model.Carrera;
import com.example.integrador3.model.Estudiante;
import com.example.integrador3.model.EstudianteCarrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteCarreraRepository extends JpaRepository<EstudianteCarrera, Integer> {
    /**
     * Verifica si ya existe una matriculación para un estudiante en una carrera específica.
     * @param estudiante El estudiante.
     * @param carrera La carrera.
     * @return true si ya existe la matriculación, false en caso contrario.
     */
    boolean existsByEstudianteAndCarrera(Estudiante estudiante, Carrera carrera);
}
