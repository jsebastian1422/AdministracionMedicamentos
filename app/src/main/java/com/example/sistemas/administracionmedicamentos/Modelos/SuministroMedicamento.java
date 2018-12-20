package com.example.sistemas.administracionmedicamentos.Modelos;

public class SuministroMedicamento {

    public int codigo_barras_iym_id,codigos_cum_id,fabricante_id;
    public String codigo_producto,lote,fecha_vencimiento,producto,descripcion,expediente,consecutivo,registro;

    public SuministroMedicamento(int codigo_barras_iym_id, int codigos_cum_id, int fabricante_id, String codigo_producto, String lote, String fecha_vencimiento, String producto, String descripcion, String expediente, String consecutivo, String registro) {
        this.codigo_barras_iym_id = codigo_barras_iym_id;
        this.codigos_cum_id = codigos_cum_id;
        this.fabricante_id = fabricante_id;
        this.codigo_producto = codigo_producto;
        this.lote = lote;
        this.fecha_vencimiento = fecha_vencimiento;
        this.producto = producto;
        this.descripcion = descripcion;
        this.expediente = expediente;
        this.consecutivo = consecutivo;
        this.registro = registro;
    }

    public SuministroMedicamento() {
    }
}
