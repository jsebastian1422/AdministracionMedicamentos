package com.example.sistemas.administracionmedicamentos.Modelos;

public class Medicamentos {

    public String ingreso, codigo_producto, num_reg, num_reg_formulacion, sw_estado, observacion, via_administracion_id, unidad_dosificacion,
            dosis, frecuencia, cantidad, sw_confirmacion_formulacion, sw_requiere_autorizacion_no_pos, dias_tratamiento, justificacion_no_pos_id,
            grupo_protocolo_formulacion, tratamiento_oncologico_id, tipo_solicitud, evolucion_id, usuario_id, fecha_registro, producto, producto_descripcion, descripcion_abreviada,
            contenido_unidad_venta, unidad_id, via_administracion, codigo_pos, unidad;

    public Medicamentos(String ingreso, String codigo_producto, String num_reg, String num_reg_formulacion, String sw_estado, String observacion, String via_administracion_id, String unidad_dosificacion, String dosis, String frecuencia, String cantidad, String sw_confirmacion_formulacion, String sw_requiere_autorizacion_no_pos, String dias_tratamiento, String justificacion_no_pos_id, String grupo_protocolo_formulacion, String tratamiento_oncologico_id, String tipo_solicitud, String evolucion_id, String usuario_id, String fecha_registro, String producto, String producto_descripcion, String descripcion_abreviada, String contenido_unidad_venta, String unidad_id, String via_administracion, String codigo_pos, String unidad) {
        this.ingreso = ingreso;
        this.codigo_producto = codigo_producto;
        this.num_reg = num_reg;
        this.num_reg_formulacion = num_reg_formulacion;
        this.sw_estado = sw_estado;
        this.observacion = observacion;
        this.via_administracion_id = via_administracion_id;
        this.unidad_dosificacion = unidad_dosificacion;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
        this.cantidad = cantidad;
        this.sw_confirmacion_formulacion = sw_confirmacion_formulacion;
        this.sw_requiere_autorizacion_no_pos = sw_requiere_autorizacion_no_pos;
        this.dias_tratamiento = dias_tratamiento;
        this.justificacion_no_pos_id = justificacion_no_pos_id;
        this.grupo_protocolo_formulacion = grupo_protocolo_formulacion;
        this.tratamiento_oncologico_id = tratamiento_oncologico_id;
        this.tipo_solicitud = tipo_solicitud;
        this.evolucion_id = evolucion_id;
        this.usuario_id = usuario_id;
        this.fecha_registro = fecha_registro;
        this.producto = producto;
        this.producto_descripcion = producto_descripcion;
        this.descripcion_abreviada = descripcion_abreviada;
        this.contenido_unidad_venta = contenido_unidad_venta;
        this.unidad_id = unidad_id;
        this.via_administracion = via_administracion;
        this.codigo_pos = codigo_pos;
        this.unidad = unidad;
    }

    public Medicamentos() {
    }
}