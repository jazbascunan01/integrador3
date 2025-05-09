package com.example.integrador3.utils;

import com.example.integrador3.model.Carrera;
import com.example.integrador3.model.Estudiante;
import com.example.integrador3.model.EstudianteCarrera;
import com.example.integrador3.repository.CarreraRespository;
import com.example.integrador3.repository.EstudianteCarreraRepository;
import com.example.integrador3.repository.EstudianteRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
public class CargaDeDatos {
    private final CarreraRespository carreraRespository;
    private final EstudianteRepository estudianteRepository;
    private final EstudianteCarreraRepository estudianteCarreraRepository;

    private final String RUTA_CSV = "src/main/java/com/example/integrador3/csv/";

    @Autowired
    public CargaDeDatos(CarreraRespository carreraRespository, EstudianteRepository estudianteRepository, EstudianteCarreraRepository estudianteCarreraRepository) {
        this.carreraRespository = carreraRespository;
        this.estudianteRepository = estudianteRepository;
        this.estudianteCarreraRepository = estudianteCarreraRepository;
    }

    public void cargarDatosDesdeCSV() throws IOException {
        cargarCarreras();
        cargarEstudiantes();
        cargarEstudianteCarrera();
    }

    public void cargarCarreras() throws IOException  {
        File carreraCSV = ResourceUtils.getFile(RUTA_CSV + "carreras.csv");
        try (FileReader reader = new FileReader(carreraCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                Carrera carrera = new Carrera();
                carrera.setCarrera(csvRecord.get("carrera"));
                carrera.setDuracion(Integer.parseInt(csvRecord.get("duracion")));
                carreraRespository.save(carrera);
            }
        }
    }

    public void cargarEstudiantes() throws IOException  {
        File estudianteCSV = ResourceUtils.getFile(RUTA_CSV + "estudiantes.csv");
        try (FileReader reader = new FileReader(estudianteCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                Estudiante estudiante = new Estudiante();
                estudiante.setDNI(Integer.parseInt(csvRecord.get("DNI")));
                estudiante.setNombre(csvRecord.get("nombre"));
                estudiante.setApellido(csvRecord.get("apellido"));
                estudiante.setEdad(Integer.parseInt(csvRecord.get("edad")));
                estudiante.setGenero(csvRecord.get("genero"));
                estudiante.setCiudad(csvRecord.get("ciudad"));
                estudiante.setLU(Integer.parseInt(csvRecord.get("LU")));
                estudianteRepository.save(estudiante);
            }
        }
    }

    public void cargarEstudianteCarrera() throws IOException  {

        File estudianteCarreraCSV = ResourceUtils.getFile(RUTA_CSV + "estudianteCarrera.csv");

        try (FileReader reader = new FileReader(estudianteCarreraCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                Estudiante estudiante = estudianteRepository.findById(Integer.parseInt(csvRecord.get("id_estudiante"))).orElse(null);
                Carrera carrera = carreraRespository.findById(Integer.parseInt(csvRecord.get("id_carrera"))).orElse(null);
                if (estudiante != null && carrera != null) {
                    EstudianteCarrera estudianteCarrera = new EstudianteCarrera();
                    estudianteCarrera.setEstudiante(estudiante);
                    estudianteCarrera.setCarrera(carrera);
                    estudianteCarrera.setInscripcion(Integer.parseInt(csvRecord.get("inscripcion")));
                    estudianteCarrera.setGraduacion(Integer.parseInt(csvRecord.get("graduacion")));
                    estudianteCarrera.setAntiguedad(Integer.parseInt(csvRecord.get("antiguedad")));
                    estudianteCarreraRepository.save(estudianteCarrera);
                }
            }
        }
    }

}
