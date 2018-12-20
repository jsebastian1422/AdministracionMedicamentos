package com.example.sistemas.administracionmedicamentos.SharedPrefMananger;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sistemas.administracionmedicamentos.Modelos.SuministroMedicamento;

public class SuminMedicamentoPrefMananger {

    private static String IP;

    // Se inicializa la información de configuración de IP, y se establece la información de IP dado el servicio web y se retorna el Shared preference de Configuración
    private static SharedPreferences Ajustes(Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("SuminMedicamentoPrefMananger", Context.MODE_PRIVATE); // Obtenemos la instancia de shared preference en la cual se brinda un nombre y el modo de acceso
        if (preferences.getString("IP", "").isEmpty()){ // Obtenemos que si en los valores obtenidos anteriormente existe IP y si este contiene información alguna

            setIP("http://192.168.100.32/~pruebas1/SIIS/app_AdminisMedicamentosMobile/json/jsonSuministroMedicamentos.php?", Contexto); // Basado en que IP esta vacío o no existe, se establece
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

        SharedPreferences preferences = Contexto.getSharedPreferences("SuminMedicamentos", Context.MODE_PRIVATE);  // Obtenemos la instancia de shared preference en la cual se brinda un nombre y el modo de acceso
        SharedPreferences.Editor editor = preferences.edit(); // Se establece en shared preference obtenido en modo edición
        editor.putString("IP", IP); // Se agregan los valores
        editor.commit();  // Se confirman los cambios
    }

    public static boolean SuministroMedica (Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("SuminMedicamentos", Context.MODE_PRIVATE);
        if (preferences.getString("codigo_barras_iym_id", "").isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    public void setSuministroMedica(SuministroMedicamento suministroMedica, Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("SuminMedicamentos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("codigo_barras_iym_id", suministroMedica.codigo_barras_iym_id);
        editor.putInt("codigos_cum_id", suministroMedica.codigos_cum_id);
        editor.putInt("fabricante_id", suministroMedica.fabricante_id);
        editor.putString("codigo_producto", suministroMedica.codigo_producto);
        editor.putString("lote", suministroMedica.lote);
        editor.putString("fecha_vencimiento", suministroMedica.fecha_vencimiento);
        editor.putString("producto", suministroMedica.producto);
        editor.putString("descripcion", suministroMedica.descripcion);
        editor.putString("expediente", suministroMedica.expediente);
        editor.putString("consecutivo", suministroMedica.consecutivo);
        editor.putString("registro", suministroMedica.registro);

        editor.commit();
    }

    public static SuministroMedicamento getSuministroMedica (Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("MedicSuminstrado", Context.MODE_PRIVATE);
        SuministroMedicamento suministroMedicamento = new SuministroMedicamento();
        suministroMedicamento.codigo_barras_iym_id = preferences.getInt("codigo_barras_iym_id", 0);
        suministroMedicamento.codigos_cum_id = preferences.getInt("codigos_cum_id", 0);;
        suministroMedicamento.fabricante_id = preferences.getInt("fabricante_id", 0);;
        suministroMedicamento.codigo_producto = preferences.getString("", "");
        suministroMedicamento.lote = preferences.getString("lote", "");
        suministroMedicamento.fecha_vencimiento = preferences.getString("fecha_vencimiento", "");
        suministroMedicamento.producto = preferences.getString("producto", "");
        suministroMedicamento.descripcion = preferences.getString("descripcion", "");
        suministroMedicamento.expediente = preferences.getString("expediente", "");
        suministroMedicamento.codigo_producto = preferences.getString("codigo_producto", "");
        suministroMedicamento.registro = preferences.getString("registro", "");

        return suministroMedicamento;
    }
}
