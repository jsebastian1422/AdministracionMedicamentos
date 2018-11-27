package com.example.sistemas.administracionmedicamentos.Modelos;

public class Paciente {

    public String ingreso,numerodecuenta,paciente_id,historia_numero,historia_prefijo,tipo_id_paciente,paciente,cama,pieza;

    public Paciente(String ingreso, String numerodecuenta, String paciente_id, String historia_numero, String historia_prefijo, String tipo_id_paciente, String paciente, String cama, String pieza) {
        this.ingreso = ingreso;
        this.numerodecuenta = numerodecuenta;
        this.paciente_id = paciente_id;
        this.historia_numero = historia_numero;
        this.historia_prefijo = historia_prefijo;
        this.tipo_id_paciente = tipo_id_paciente;
        this.paciente = paciente;
        this.cama = cama;
        this.pieza = pieza;
    }

    public Paciente() {
    }
}
