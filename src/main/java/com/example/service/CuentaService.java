package com.example.service;


import com.example.model.cliente.datosCliente;
import com.example.model.cuenta.datosCuenta;
import com.example.model.movimiento.datosMovimiento;
import com.example.repository.CuentaRepository;
import com.example.repository.MovimientoRepository;
import com.example.repository.clienteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private clienteRepository clienteRepository;

    public List<datosCuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }


    public datosCuenta saveCuenta(datosCuenta cuenta) {
        try {
            datosCuenta cuentaExistente = cuentaRepository.findById(cuenta.getNumeroCta()).orElse(null);

            if (cuentaExistente != null) {
                throw new RuntimeException("Ya existe una cuenta bancaria con este número: " + cuenta.getNumeroCta());
            }

            return cuentaRepository.save(cuenta);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Error de integridad de datos al intentar crear la cuenta.", e);
        } catch (Exception e) {
            throw new RuntimeException("Error interno del servidor al intentar crear la cuenta.", e);
        }
    }


    public void updateCuenta(String numeroCta, datosCuenta nuevaCuenta) {
        Optional<datosCuenta> cuentaExistente = cuentaRepository.findById(numeroCta);

        if (cuentaExistente.isPresent()) {
            // La cuenta existe, entonces actualiza los campos
            datosCuenta cuenta = cuentaExistente.get();
            cuenta.setSaldo(nuevaCuenta.getSaldo());
            cuenta.setFechaapertura(nuevaCuenta.getFechaapertura());
            cuenta.setEstado(nuevaCuenta.isEstado());
            // Guarda la cuenta actualizada
            cuentaRepository.save(cuenta);
        } else {
            // La cuenta no existe, lanza una excepción
            throw new EntityNotFoundException("Cuenta no encontrada con número " + numeroCta);
        }
    }

    @Transactional
    public void cambiarEstadoCuenta(String numeroCta, boolean nuevoEstado) {
        Optional<datosCuenta> optionalCuenta = cuentaRepository.findById(numeroCta);

        if (optionalCuenta.isPresent()) {
            datosCuenta cuenta = optionalCuenta.get();
            cuenta.setEstado(nuevoEstado);
            cuentaRepository.save(cuenta);
        } else {
            throw new CuentaNotFoundException("Cuenta no encontrada con número: " + numeroCta);
        }
    }
    public class CuentaNotFoundException extends RuntimeException {
        public CuentaNotFoundException(String mensaje) {
            super(mensaje);
        }
    }





    public void deleteCuenta(String numeroCta) {
        cuentaRepository.deleteById(numeroCta);
    }

    @Transactional
    public void consignar(String numeroCta, double cantidad) {
        Optional<datosCuenta> cuentaOptional = cuentaRepository.findById(numeroCta);
        if (cuentaOptional.isPresent()) {
            datosCuenta cuenta = cuentaOptional.get();
            double nuevoSaldo = cuenta.getSaldo() + cantidad;
            cuenta.setSaldo(nuevoSaldo);
            cuentaRepository.save(cuenta);

            // Registrar movimiento de consignación
            datosMovimiento movimiento = new datosMovimiento();
            movimiento.setNumeroCta(numeroCta);
            movimiento.setTipo("Consignacion");
            movimiento.setCantidad(cantidad);
            movimiento.setFecha(new Date());
            movimientoRepository.save(movimiento);
        } else {
            throw new EntityNotFoundException("Cuenta no encontrada con número " + numeroCta);
        }
    }

    @Transactional
    public void retirar(String numeroCta, double cantidad) {
        Optional<datosCuenta> cuentaOptional = cuentaRepository.findById(numeroCta);
        if (cuentaOptional.isPresent()) {
            datosCuenta cuenta = cuentaOptional.get();
            double nuevoSaldo = cuenta.getSaldo() - cantidad;
            if (nuevoSaldo >= 0) {
                cuenta.setSaldo(nuevoSaldo);
                cuentaRepository.save(cuenta);

                // Registrar movimiento de retiro
                datosMovimiento movimiento = new datosMovimiento();
                movimiento.setNumeroCta(numeroCta);
                movimiento.setTipo("Retiro");
                movimiento.setCantidad(cantidad);
                movimiento.setFecha(new Date());
                movimientoRepository.save(movimiento);
            } else {
                throw new IllegalArgumentException("Fondos insuficientes para realizar el retiro.");

            }
        } else {
            throw new EntityNotFoundException("Cuenta no encontrada con número " + numeroCta);
        }
    }

    public datosCuenta getDatosClientePorNumeroCuenta(String numeroCta) {
        Optional<datosCuenta> cuentaOptional = cuentaRepository.findById(numeroCta);
        if (cuentaOptional.isPresent()) {
            datosCuenta cuenta = cuentaOptional.get();

            // Obtener el titular de la cuenta
            String cedulaTitular = cuenta.getTitular();

            // Buscar al cliente correspondiente en la tabla datosCliente
            datosCliente cliente = clienteRepository.findByCedulacliente(cedulaTitular);

            // Asignar el nombre del cliente a la cuenta
            if (cliente != null) {
                cuenta.setTitular(cliente.getNombre());
            }

            return cuenta;
        } else {
            throw new EntityNotFoundException("Cuenta no encontrada con número " + numeroCta);
        }
    }


}
