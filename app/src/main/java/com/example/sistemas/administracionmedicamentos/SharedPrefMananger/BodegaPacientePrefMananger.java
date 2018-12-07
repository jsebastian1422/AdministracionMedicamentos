package com.example.sistemas.administracionmedicamentos.SharedPrefMananger;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sistemas.administracionmedicamentos.Modelos.BodegaPaciente;

public class BodegaPacientePrefMananger {

    public static String IP;
    private static SharedPreferences Ajustes(Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("BodPacientePrefMananger", Context.MODE_PRIVATE);
        if (preferences.getString("IP", "").isEmpty()){

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("IP", "http://192.168.100.32/~pruebas1/SIIS/app_AdminisMedicamentosMobile/json/jsonBuscarBodegaPaciente.php");
            editor.commit();
        }

        return preferences;
    }

    public static String getIP(Context Contexto) {
        IP = Ajustes(Contexto).getString("IP", "");
        return IP;
    }

    public static void setIP(String IP, Context Contexto) {

        SharedPreferences preferences = Contexto.getSharedPreferences("BodPacientePrefMananger", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("IP", IP);
        editor.commit();
    }

    public static boolean IngresoPaciente(Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("BodegaPaciente", Context.MODE_PRIVATE);
        if (preferences.getString("ingreso", "").isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    public static void setBodegaPaciente(BodegaPaciente bodegaPaciente, Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("BodegaPaciente", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("ingreso",  bodegaPaciente.ingreso);
        editor.putString("stock",  bodegaPaciente.stock);
        editor.putString("stock_paciente",  bodegaPaciente.stock_paciente);
        editor.putString("stock_almacen",  bodegaPaciente.stock_almacen);
        editor.putString("cantidad_en_solicitud",  bodegaPaciente.cantidad_en_solicitud);
        editor.putString("cantidad_pendiente_por_recibir", bodegaPaciente.cantidad_pendiente_por_recibir);
        editor.putString("cantidad_en_devolucion",  bodegaPaciente.cantidad_en_devolucion);
        editor.putString("total_solicitado",  bodegaPaciente.total_solicitado);
        editor.putString("total_cancelado",  bodegaPaciente.total_cancelado);
        editor.putString("total_cancelado_antes_de_confirmar",  bodegaPaciente.total_cancelado_antes_de_confirmar);
        editor.putString("total_cancelado_por_la_bodega",  bodegaPaciente.total_cancelado_por_la_bodega);
        editor.putString("total_despachado",  bodegaPaciente.total_despachado);
        editor.putString("total_recibido",  bodegaPaciente.total_recibido);
        editor.putString("total_devuelto",  bodegaPaciente.total_devuelto);
        editor.putString("total_consumo_directo",  bodegaPaciente.total_consumo_directo);
        editor.putString("total_suministrado",  bodegaPaciente.total_suministrado);
        editor.putString("total_perdidas",  bodegaPaciente.total_perdidas);
        editor.putString("total_aprovechamiento",  bodegaPaciente.total_aprovechamiento);
        editor.putString("codigo_producto", bodegaPaciente.codigo_producto);
        editor.putString("sw_tipo_producto", bodegaPaciente.sw_tipo_producto);
        editor.commit();
    }

    public static BodegaPaciente getBodegaPaciente(Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("BodegaPaciente", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        BodegaPaciente bodegaPaciente = new BodegaPaciente();
        bodegaPaciente.ingreso    = preferences.getString("ingreso", "");
        bodegaPaciente.stock    = preferences.getString("stock", "");
        bodegaPaciente.stock_paciente    = preferences.getString("stock_paciente", "");
        bodegaPaciente.stock_almacen    = preferences.getString("stock_almacen", "");
        bodegaPaciente.cantidad_en_solicitud    = preferences.getString("cantidad_en_solicitud", "");
        bodegaPaciente.cantidad_pendiente_por_recibir    = preferences.getString("cantidad_pendiente_por_recibir", "");
        bodegaPaciente.cantidad_en_devolucion    = preferences.getString("cantidad_en_devolucion", "");
        bodegaPaciente.total_solicitado    = preferences.getString("total_solicitado", "");
        bodegaPaciente.total_cancelado = preferences.getString("total_cancelado", "");
        bodegaPaciente.total_cancelado_antes_de_confirmar    = preferences.getString("total_cancelado_antes_de_confirmar","");
        bodegaPaciente.total_cancelado_por_la_bodega    = preferences.getString("total_cancelado_por_la_bodega", "");
        bodegaPaciente.total_despachado    =   preferences.getString("total_despachado", "");
        bodegaPaciente.total_recibido    =  preferences.getString("total_recibido", "");
        bodegaPaciente.total_devuelto    =  preferences.getString("total_devuelto", "");
        bodegaPaciente.total_consumo_directo    = preferences.getString("total_consumo_directo", "");
        bodegaPaciente.total_suministrado    =  preferences.getString("total_suministrado", "");
        bodegaPaciente.total_perdidas    =  preferences.getString("total_perdidas", "");
        bodegaPaciente.total_aprovechamiento    = preferences.getString("total_aprovechamiento", "");
        bodegaPaciente.codigo_producto    =   preferences.getString("codigo_producto", "");
        bodegaPaciente.sw_tipo_producto    =   preferences.getString("sw_tipo_producto", "");
        return bodegaPaciente;
    }
}
