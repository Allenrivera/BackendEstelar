package com.example.model.cuenta;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table ( name = "cuenta")
public class datosCuenta {

    @Id
        private String numerocta;
        private String titular;
        private double saldo;
        private Date fechaapertura;
        private boolean estado;



    public String getNumeroCta() {
            return numerocta;
        }

        public void setNumeroCta(String numeroCta) {
            this.numerocta = numeroCta;
        }

        public String getTitular() {
            return titular;
        }

        public void setTitular(String titular) {
            this.titular = titular;
        }

        public double getSaldo() {
            return saldo;
        }

        public void setSaldo(double saldo) {
            this.saldo = saldo;
        }

        public Date getFechaapertura() {
            return fechaapertura;
        }

        public void setFechaapertura(Date fechaapertura) {
            this.fechaapertura = fechaapertura;
        }

        public boolean isEstado() {
            return estado;
        }

        public void setEstado(boolean estado) {
            this.estado = estado;
        }
    }



