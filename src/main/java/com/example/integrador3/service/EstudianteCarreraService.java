package com.example.integrador3.service;

import com.example.integrador3.repository.EstudianteCarreraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstudianteCarreraService {
    private final EstudianteCarreraRepository estudianteCarreraRepository;
}
