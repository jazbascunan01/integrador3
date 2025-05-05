package com.example.integrador3.service;

import com.example.integrador3.repository.CarreraRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarreraService {
    private final CarreraRespository carreraRespository;

}
