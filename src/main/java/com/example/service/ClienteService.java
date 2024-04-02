package com.example.service;


import com.example.model.cliente.datosCliente;
import com.example.repository.clienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final clienteRepository clienteRepository;

    @Autowired
    public ClienteService(clienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public List<datosCliente> obtenerClientes() {
        return clienteRepository.findAll();
    }

    public datosCliente obtenerClientePorCedula(String cedula) {
        // Implementa la lógica para obtener el cliente por su cédula
        // Puedes usar tu repositorio o cualquier otra lógica de acceso a datos
        return clienteRepository.findByCedulacliente(cedula);
    }



    public void agregarCliente(datosCliente cliente) {
        // Verifica si ya existe un cliente con la misma cédula
        datosCliente clienteExistente = clienteRepository.findByCedulacliente(cliente.getCedulaCliente());

        if (clienteExistente != null) {

            throw new RuntimeException("Ya existe un cliente con la misma cédula");
        }

        clienteRepository.save(cliente);
    }

    public void actualizarCliente(String cedula, datosCliente cliente) {
        // Implementa la lógica de actualización en el servicio
        // Puedes lanzar excepciones si la cédula no existe, etc.
        clienteRepository.save(cliente);
    }

    public void eliminarCliente(String cedula) {
        // Implementa la lógica de eliminación en el servicio
        // Puedes lanzar excepciones si la cédula no existe, etc.
         clienteRepository.deleteById(cedula);
    }

    public Optional<datosCliente> findbycedulacliente(String cedulaCliente) {
        datosCliente cliente = clienteRepository.findByCedulacliente(cedulaCliente);
        return Optional.ofNullable(cliente);
    }


}

