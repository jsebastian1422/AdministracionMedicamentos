package com.example.sistemas.administracionmedicamentos.Modelos;

public class BodegaPaciente {

    public String ingreso,stock,stock_paciente,stock_almacen,cantidad_en_solicitud,cantidad_pendiente_por_recibir,cantidad_en_devolucion,total_solicitado
            ,total_cancelado,total_cancelado_antes_de_confirmar,total_cancelado_por_la_bodega,total_despachado,total_recibido,total_devuelto,total_consumo_directo
            ,total_suministrado,total_perdidas,total_aprovechamiento,codigo_producto,sw_tipo_producto;


    public BodegaPaciente(String ingreso, String stock, String stock_paciente, String stock_almacen, String cantidad_en_solicitud, String cantidad_pendiente_por_recibir, String cantidad_en_devolucion, String total_solicitado, String total_cancelado, String total_cancelado_antes_de_confirmar, String total_cancelado_por_la_bodega, String total_despachado, String total_recibido, String total_devuelto, String total_consumo_directo, String total_suministrado, String total_perdidas, String total_aprovechamiento, String codigo_producto, String sw_tipo_producto) {
        this.ingreso = ingreso;
        this.stock = stock;
        this.stock_paciente = stock_paciente;
        this.stock_almacen = stock_almacen;
        this.cantidad_en_solicitud = cantidad_en_solicitud;
        this.cantidad_pendiente_por_recibir = cantidad_pendiente_por_recibir;
        this.cantidad_en_devolucion = cantidad_en_devolucion;
        this.total_solicitado = total_solicitado;
        this.total_cancelado = total_cancelado;
        this.total_cancelado_antes_de_confirmar = total_cancelado_antes_de_confirmar;
        this.total_cancelado_por_la_bodega = total_cancelado_por_la_bodega;
        this.total_despachado = total_despachado;
        this.total_recibido = total_recibido;
        this.total_devuelto = total_devuelto;
        this.total_consumo_directo = total_consumo_directo;
        this.total_suministrado = total_suministrado;
        this.total_perdidas = total_perdidas;
        this.total_aprovechamiento = total_aprovechamiento;
        this.codigo_producto = codigo_producto;
        this.sw_tipo_producto = sw_tipo_producto;
    }

    public BodegaPaciente() {
    }
}
