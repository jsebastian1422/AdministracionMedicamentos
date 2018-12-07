package com.example.sistemas.administracionmedicamentos.SharedPrefMananger;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sistemas.administracionmedicamentos.Modelos.Medicamentos;

public class MedicamentosPrefMananger {

    private static String IP;

    // Se inicializa la información de configuración de IP, y se establece la información de IP dado el servicio web y se retorna el Shared preference de Configuración
    private static SharedPreferences Ajustes(Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("MedicamentosPrefMananger", Context.MODE_PRIVATE); // Obtenemos la instancia de shared preference en la cual se brinda un nombre y el modo de acceso
        if (preferences.getString("IP", "").isEmpty()){ // Obtenemos que si en los valores obtenidos anteriormente existe IP y si este contiene información alguna

            setIP("http://192.168.100.32/~pruebas1/SIIS/app_AdminisMedicamentosMobile/json/jsonMedicamentosPaciente.php", Contexto); // Basado en que IP esta vacío o no existe, se establece
        }

        return preferences;
    }

    // Se obtiene la IP la cual se hará la petición, basado en la dirección establecida en el método ajustes
    public static String getIP(Context Contexto) {
        IP = Ajustes(Contexto).getString("IP", ""); // Obtenemos el valor IP dado en el shared preference que envía el método Ajustes
        return IP;
    }

    // Se brinda la posibilidad de cambiar la dirección IP de Ajustes
    public static void setIP(String IP, Context Contexto) {

        SharedPreferences preferences = Contexto.getSharedPreferences("MedicamentosPrefMananger", Context.MODE_PRIVATE);  // Obtenemos la instancia de shared preference en la cual se brinda un nombre y el modo de acceso
        SharedPreferences.Editor editor = preferences.edit(); // Se establece en shared preference obtenido en modo edición
        editor.putString("IP", IP); // Se agregan los valores
        editor.commit();  // Se confirman los cambios
    }

    public static boolean IngresoPaciente(Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("Medicamentos", Context.MODE_PRIVATE);
        if (preferences.getString("ingreso", "").isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    public static void setMedicamentosPaciente(Medicamentos medicamentos, Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("Medicamentos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ingreso", medicamentos.ingreso);
        editor.putString("codigo_producto", medicamentos.codigo_producto);
        editor.putString("num_reg", medicamentos.num_reg);
        editor.putString("num_reg_formulacion", medicamentos.num_reg_formulacion);
        editor.putString("sw_estado", medicamentos.sw_estado);
        editor.putString("observacion", medicamentos.observacion);
        editor.putString("via_administracion_id", medicamentos.via_administracion_id);
        editor.putString("unidad_dosificacion", medicamentos.unidad_dosificacion);
        editor.putInt("dosis", medicamentos.dosis);
        editor.putString("frecuencia", medicamentos.frecuencia);
        editor.putFloat("cantidad", medicamentos.cantidad);
        editor.putString("sw_confirmacion_formulacion", medicamentos.sw_confirmacion_formulacion);
        editor.putString("sw_requiere_autorizacion_no_pos", medicamentos.sw_requiere_autorizacion_no_pos);
        editor.putInt("dias_tratamiento", medicamentos.dias_tratamiento);
        editor.putString("justificacion_no_pos_id", medicamentos.justificacion_no_pos_id);
        editor.putString("grupo_protocolo_formulacion", medicamentos.grupo_protocolo_formulacion);
        editor.putString("tratamiento_oncologico_id", medicamentos.tratamiento_oncologico_id);
        editor.putString("tipo_solicitud", medicamentos.tipo_solicitud);
        editor.putString("evolucion_id", medicamentos.evolucion_id);
        editor.putString("usuario_id", medicamentos.usuario_id);
        editor.putString("fecha_registro", medicamentos.fecha_registro);
        editor.putString("producto", medicamentos.producto);
        editor.putString("producto_descripcion", medicamentos.producto_descripcion);
        editor.putString("descripcion_abreviada", medicamentos.descripcion_abreviada);
        editor.putString("contenido_unidad_venta", medicamentos.contenido_unidad_venta);
        editor.putString("unidad_id", medicamentos.unidad_id);
        editor.putString("via_administracion", medicamentos.via_administracion);
        editor.putString("codigo_pos", medicamentos.codigo_pos);
        editor.putString("unidad", medicamentos.unidad);
        editor.putFloat("stock", medicamentos.stock);
        editor.putFloat("total_suministrado", medicamentos.total_suministrado);
        editor.putFloat("total_despachado", medicamentos.total_despachado);

        editor.commit();

    }

    public static Medicamentos getMedicamentosPaciente (Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("Medicamentos", Context.MODE_PRIVATE);
        Medicamentos medicamentos = new Medicamentos();
        medicamentos.ingreso     =   preferences.getString("ingreso", "");
        medicamentos.codigo_producto     =   preferences.getString("codigo_producto", "");
        medicamentos.num_reg    =   preferences.getString("codigo_producto", "");
        medicamentos.num_reg_formulacion    =   preferences.getString("num_reg_formulacion", "");
        medicamentos.sw_estado    =   preferences.getString("sw_estado", "");
        medicamentos.observacion    =   preferences.getString("observacion", "");
        medicamentos.via_administracion_id    =   preferences.getString("via_administracion_id", "");
        medicamentos.unidad_dosificacion    =   preferences.getString("unidad_dosificacion", "");
        medicamentos.dosis    =   preferences.getInt("dosis", 0);
        medicamentos.frecuencia    =   preferences.getString("frecuencia", "");
        medicamentos.cantidad    =   preferences.getInt("cantidad", 0);
        medicamentos.sw_confirmacion_formulacion    =   preferences.getString("sw_confirmacion_formulacion", "");
        medicamentos.sw_requiere_autorizacion_no_pos    =   preferences.getString("sw_requiere_autorizacion_no_pos", "");
        medicamentos.dias_tratamiento    =   preferences.getInt("dias_tratamiento", 0);
        medicamentos.justificacion_no_pos_id    =   preferences.getString("justificacion_no_pos_id", "");
        medicamentos.grupo_protocolo_formulacion    =   preferences.getString("grupo_protocolo_formulacion", "");
        medicamentos.tratamiento_oncologico_id    =   preferences.getString("tratamiento_oncologico_id", "");
        medicamentos.tipo_solicitud    =   preferences.getString("tipo_solicitud", "");
        medicamentos.evolucion_id    =   preferences.getString("evolucion_id", "");
        medicamentos.usuario_id    =   preferences.getString("usuario_id", "");
        medicamentos.fecha_registro    =   preferences.getString("fecha_registro", "");
        medicamentos.producto    =   preferences.getString("producto", "");
        medicamentos.producto_descripcion    =   preferences.getString("producto_descripcion", "");
        medicamentos.descripcion_abreviada    =   preferences.getString("descripcion_abreviada", "");
        medicamentos.contenido_unidad_venta    =   preferences.getString("contenido_unidad_venta", "");
        medicamentos.unidad_id    =   preferences.getString("unidad_id", "");
        medicamentos.via_administracion    =   preferences.getString("via_administracion", "");
        medicamentos.codigo_pos    =   preferences.getString("codigo_pos", "");
        medicamentos.unidad    =   preferences.getString("unidad", "");
        medicamentos.stock    =   preferences.getInt("stock", 0);
        medicamentos.total_suministrado    =   preferences.getInt("total_suministrado", 0);
        medicamentos.total_despachado    =   preferences.getInt("total_despachado", 0);

        return medicamentos;


    }
}
