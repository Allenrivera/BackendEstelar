package com.example.repository;

import com.example.model.cliente.datosCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface clienteRepository extends JpaRepository<datosCliente, String> {
    datosCliente findByCedulacliente(String cedulacliente);

    datosCliente save(datosCliente cliente);


}


