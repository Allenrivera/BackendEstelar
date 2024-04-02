package com.example.model.movimiento;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;


@Entity
@Table (name = "movimiento")
public class datosMovimiento {

    @Id
        private int idmovimiento;
        private String numerocta;
        private String tipo;
        private double cantidad;
        private Date fecha;

    public int getIdMovimiento() {
        return idmovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idmovimiento = idMovimiento;
    }

    public String getNumeroCta() {
        return numerocta;
    }

    public void setNumeroCta(String numeroCta) {
        this.numerocta = numeroCta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}

