package com.example.integrador3.repository;

import com.example.integrador3.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    Estudiante findByLU(int LU);
    List<Estudiante> findAllByGenero(String genero);
}
