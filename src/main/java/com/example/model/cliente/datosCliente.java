package com.example.model.cliente;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class datosCliente {

    @Id
    @Column(name = "cedulacliente", length = 15, unique = true)
    private String cedulacliente;
    private int clave;
    private String nombre;
    private double telefono;

    public String getCedulaCliente() {
        return cedulacliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulacliente = cedulaCliente;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTelefono() {
        return telefono;
    }

    public void setTelefono(double telefono) {
        this.telefono = telefono;
    }
}
