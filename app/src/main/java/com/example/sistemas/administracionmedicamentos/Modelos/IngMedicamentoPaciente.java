package com.example.sistemas.administracionmedicamentos.Modelos;

import java.time.LocalDateTime;

public class IngMedicamentoPaciente {

    public String codigo_producto, pruebas;

    public IngMedicamentoPaciente() {
    }

    public IngMedicamentoPaciente(String codigo_producto, String pruebas) {
        this.codigo_producto = codigo_producto;
        this.pruebas = pruebas;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public String getPruebas() {
        return pruebas;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public void setPruebas(String pruebas) {
        this.pruebas = pruebas;
    }
}
