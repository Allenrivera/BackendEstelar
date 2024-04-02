package com.example.service;


import com.example.model.movimiento.datosMovimiento;
import com.example.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class movimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;

    public List<datosMovimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public datosMovimiento saveMovimiento(datosMovimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

}
