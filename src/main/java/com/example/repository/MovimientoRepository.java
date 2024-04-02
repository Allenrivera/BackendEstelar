package com.example.repository;


import com.example.model.movimiento.datosMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository extends JpaRepository<datosMovimiento, Integer> {

}
