package com.example.controller.cuenta;


import com.example.model.cuenta.datosCuenta;
import com.example.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cuenta")
public class cuentaController {
    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/registros")
    public ResponseEntity<List<datosCuenta>> getAllCuentas() {
        List<datosCuenta> cuentas = cuentaService.getAllCuentas();
        return ResponseEntity.ok(cuentas);
    }

    @PostMapping("/{cedulaCliente}/crear-cuenta")
    public ResponseEntity<Void> crearCuentaParaCliente(@PathVariable String cedulaCliente, @RequestBody datosCuenta cuenta) {
        try {

            cuenta.setTitular(cedulaCliente);
            cuentaService.saveCuenta(cuenta);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{numeroCta}/actualizar")
    public ResponseEntity<Void> actualizarCuenta(@PathVariable String numeroCta, @RequestBody datosCuenta cuenta) {
        try {
            cuentaService.updateCuenta(numeroCta, cuenta);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{numeroCta}/eliminar")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable String numeroCta) {
        try {
            cuentaService.deleteCuenta(numeroCta);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/{numeroCta}/consignar")
    public ResponseEntity<String> consignarCuenta(@PathVariable String numeroCta, @RequestBody Map<String, Double> requestBody) {
        try {
            Double cantidad = requestBody.get("cantidad");
            cuentaService.consignar(numeroCta, cantidad);
            // Aquí devuelve la URL a la que quieres redirigir al usuario
            String redirectUrl = "/pagina-de-confirmacion"; // Cambia "/pagina-de-confirmacion" por la URL deseada
            return ResponseEntity.ok(redirectUrl);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/{numeroCta}/retirar")
    public ResponseEntity<Void> retirar(@PathVariable String numeroCta, @RequestBody Map<String, Double> requestBody) {
        try {
            double cantidad = requestBody.get("cantidad");
            cuentaService.retirar(numeroCta, cantidad);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{numeroCta}/cambiar-estado")
    public ResponseEntity<Void> cambiarEstadoCuenta(@PathVariable String numeroCta, @RequestParam boolean estado) {
        try {
            cuentaService.cambiarEstadoCuenta(numeroCta, estado);
            return ResponseEntity.ok().build();
        } catch (CuentaService.CuentaNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{numeroCta}/datos-cliente")
    public ResponseEntity<datosCuenta> getDatosClientePorNumeroCuenta(@PathVariable String numeroCta) {
        try {
            datosCuenta cuenta = cuentaService.getDatosClientePorNumeroCuenta(numeroCta);
            if (cuenta != null) {
                return ResponseEntity.ok(cuenta);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




}
