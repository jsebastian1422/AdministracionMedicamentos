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

    public String getIngreso() {
        return ingreso;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public String getNum_reg() {
        return num_reg;
    }

    public String getNum_reg_formulacion() {
        return num_reg_formulacion;
    }

    public String getSw_estado() {
        return sw_estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public String getVia_administracion_id() {
        return via_administracion_id;
    }

    public String getUnidad_dosificacion() {
        return unidad_dosificacion;
    }

    public String getDosis() {
        return dosis;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public String getCantidad() {
        return cantidad;
    }

    public String getSw_confirmacion_formulacion() {
        return sw_confirmacion_formulacion;
    }

    public String getSw_requiere_autorizacion_no_pos() {
        return sw_requiere_autorizacion_no_pos;
    }

    public String getDias_tratamiento() {
        return dias_tratamiento;
    }

    public String getJustificacion_no_pos_id() {
        return justificacion_no_pos_id;
    }

    public String getGrupo_protocolo_formulacion() {
        return grupo_protocolo_formulacion;
    }

    public String getTratamiento_oncologico_id() {
        return tratamiento_oncologico_id;
    }

    public String getTipo_solicitud() {
        return tipo_solicitud;
    }

    public String getEvolucion_id() {
        return evolucion_id;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public String getProducto() {
        return producto;
    }

    public String getProducto_descripcion() {
        return producto_descripcion;
    }

    public String getDescripcion_abreviada() {
        return descripcion_abreviada;
    }

    public String getContenido_unidad_venta() {
        return contenido_unidad_venta;
    }

    public String getUnidad_id() {
        return unidad_id;
    }

    public String getVia_administracion() {
        return via_administracion;
    }

    public String getCodigo_pos() {
        return codigo_pos;
    }

    public String getUnidad() {
        return unidad;
    }
}