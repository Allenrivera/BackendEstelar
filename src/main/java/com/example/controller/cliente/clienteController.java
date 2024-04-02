package com.example.controller.cliente;

import com.example.model.cliente.datosCliente;
import com.example.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clientes")
public class clienteController {

    private final com.example.service.ClienteService ClienteService;

    @Autowired
    public clienteController(ClienteService ClienteService) {
        this.ClienteService = ClienteService;
    }


    @GetMapping("/lista")
    public ResponseEntity<List<datosCliente>> obtenerClientes() {
        List<datosCliente> clientes = ClienteService.obtenerClientes();
        return ResponseEntity.ok(clientes);
    }
    @GetMapping("/obtenerPorCedula/{cedula}")
    public ResponseEntity<datosCliente> obtenerClientePorCedula(@PathVariable String cedula) {
        try {
            datosCliente cliente = ClienteService.obtenerClientePorCedula(cedula);

            if (cliente != null) {
                return ResponseEntity.ok(cliente);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PostMapping("/agregar")
    public ResponseEntity<Void> agregarCliente(@RequestBody datosCliente cliente) {
        try {
            ClienteService.agregarCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }

    @PutMapping("/actualizar/{cedula}")
    public ResponseEntity<Void> actualizarCliente(@PathVariable String cedula, @RequestBody datosCliente cliente) {
        try {
            ClienteService.actualizarCliente(cedula, cliente);
            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/eliminar/{cedula}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String cedula) {
        try {
            ClienteService.eliminarCliente(cedula);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}