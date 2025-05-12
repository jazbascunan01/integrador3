package com.example.integrador3.repository;

import com.example.integrador3.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    Estudiante findByLU(int LU);
    List<Estudiante> findAllByGenero(String genero);
    @Query("SELECT ec.estudiante FROM EstudianteCarrera ec WHERE ec.carrera.carrera = :carrera AND ec.estudiante.ciudad = :ciudad")
    List<Estudiante> findEstudiantesByCarreraAndCiudad(String carrera, String ciudad);

}
