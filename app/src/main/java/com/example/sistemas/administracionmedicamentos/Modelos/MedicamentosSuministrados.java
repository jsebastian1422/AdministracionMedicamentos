package com.example.sistemas.administracionmedicamentos.Modelos;

public class MedicamentosSuministrados {

    public int suministro_id,ingreso,num_reg_formulacion,usuario_id_control,cantidad_aprovechada;
    public String sw_estado,codigo_producto,fecha_realizado,fecha_registro_control,estacion_id,observacion,sw_id_consumo,nombre,cantidad_suministrada, cantidad_perdidas;

    public MedicamentosSuministrados(int suministro_id, int ingreso, int num_reg_formulacion, int usuario_id_control, int cantidad_aprovechada, String sw_estado, String codigo_producto, String fecha_realizado, String fecha_registro_control, String estacion_id, String observacion, String sw_id_consumo, String nombre, String cantidad_suministrada, String cantidad_perdidas) {
        this.suministro_id = suministro_id;
        this.ingreso = ingreso;
        this.num_reg_formulacion = num_reg_formulacion;
        this.usuario_id_control = usuario_id_control;
        this.cantidad_aprovechada = cantidad_aprovechada;
        this.sw_estado = sw_estado;
        this.codigo_producto = codigo_producto;
        this.fecha_realizado = fecha_realizado;
        this.fecha_registro_control = fecha_registro_control;
        this.estacion_id = estacion_id;
        this.observacion = observacion;
        this.sw_id_consumo = sw_id_consumo;
        this.nombre = nombre;
        this.cantidad_suministrada = cantidad_suministrada;
        this.cantidad_perdidas = cantidad_perdidas;
    }

    public MedicamentosSuministrados() {
    }
}
