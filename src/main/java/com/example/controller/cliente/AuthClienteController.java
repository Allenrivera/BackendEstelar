package com.example.controller.cliente;

import com.example.model.cliente.datosCliente;
import com.example.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("sesionCliente")
public class AuthClienteController {

    private final ClienteService clienteService;

    public AuthClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginCliente(@RequestBody Map<String, String> credentials) {
        String cedulaCliente = credentials.get("cedulacliente");
        String claveStr = credentials.get("clave");

        Optional<datosCliente> optionalDatosCliente = clienteService.findbycedulacliente(cedulaCliente);
        if (optionalDatosCliente.isPresent()) {
            datosCliente cliente = optionalDatosCliente.get();
            int clave = cliente.getClave(); // Obtener la clave como un int
            if (String.valueOf(clave).equals(claveStr)) {
                return ResponseEntity.ok("¡Inicio de sesión exitoso!");
            } else {
                return ResponseEntity.badRequest().body("La clave es incorrecta");
            }
        } else {
            return ResponseEntity.badRequest().body("El cliente con la cédula especificada no existe");
        }
    }
}
