package com.example.repository;


import com.example.model.cuenta.datosCuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<datosCuenta, String> {
    datosCuenta findByNumerocta(String numerocta);
}
