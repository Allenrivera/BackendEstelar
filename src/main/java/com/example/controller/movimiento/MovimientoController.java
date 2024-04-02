package com.example.controller.movimiento;

import com.example.model.movimiento.datosMovimiento;
import com.example.service.movimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class MovimientoController {

    @Autowired
    private movimientoService movimientoService;

    @GetMapping("/movimientos")
    public ResponseEntity<List<datosMovimiento>> getAllMovimientos(){
        List<datosMovimiento> movimientos = movimientoService.getAllMovimientos();
        return  ResponseEntity.ok(movimientos);
    }
}
