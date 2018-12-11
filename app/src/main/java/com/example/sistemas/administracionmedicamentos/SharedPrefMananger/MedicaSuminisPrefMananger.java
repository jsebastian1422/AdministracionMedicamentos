package com.example.sistemas.administracionmedicamentos.SharedPrefMananger;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sistemas.administracionmedicamentos.Modelos.MedicamentosSuministrados;

public class MedicaSuminisPrefMananger {

    private static String IP;

    // Se inicializa la información de configuración de IP, y se establece la información de IP dado el servicio web y se retorna el Shared preference de Configuración
    private static SharedPreferences Ajustes(Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("MedicaSuminisPrefMananger", Context.MODE_PRIVATE); // Obtenemos la instancia de shared preference en la cual se brinda un nombre y el modo de acceso
        if (preferences.getString("IP", "").isEmpty()){ // Obtenemos que si en los valores obtenidos anteriormente existe IP y si este contiene información alguna

            setIP("http://192.168.100.32/~pruebas1/SIIS/app_AdminisMedicamentosMobile/json/jsonTotalSuminstros.php", Contexto); // Basado en que IP esta vacío o no existe, se establece
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

        SharedPreferences preferences = Contexto.getSharedPreferences("MedicaSuminisPrefMananger", Context.MODE_PRIVATE);  // Obtenemos la instancia de shared preference en la cual se brinda un nombre y el modo de acceso
        SharedPreferences.Editor editor = preferences.edit(); // Se establece en shared preference obtenido en modo edición
        editor.putString("IP", IP); // Se agregan los valores
        editor.commit();  // Se confirman los cambios
    }

    public static boolean SuministradoMedica (Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("MedicSuminstrado", Context.MODE_PRIVATE);
        if (preferences.getString("suministro_id", "").isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    public static void setSuministroMedicamentos(MedicamentosSuministrados medicamentosSuministrados, Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("MedicSuminstrado", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putInt("suministro_id",  medicamentosSuministrados.suministro_id);
        editor.putInt("ingreso",  medicamentosSuministrados.ingreso);
        editor.putInt("num_reg_formulacion",  medicamentosSuministrados.num_reg_formulacion);
        editor.putInt("usuario_id_control",  medicamentosSuministrados.usuario_id_control);
        editor.putString("cantidad_suministrada",  medicamentosSuministrados.cantidad_suministrada);
        editor.putString("cantidad_perdidas",  medicamentosSuministrados.cantidad_perdidas);
        editor.putInt("cantidad_aprovechada",  medicamentosSuministrados.cantidad_aprovechada);
        editor.putString("sw_estado",  medicamentosSuministrados.sw_estado);
        editor.putString("codigo_producto",  medicamentosSuministrados.codigo_producto);
        editor.putString("fecha_realizado",  medicamentosSuministrados.fecha_realizado);
        editor.putString("fecha_registro_control",  medicamentosSuministrados.fecha_registro_control);
        editor.putString("estacion_id",  medicamentosSuministrados.estacion_id);
        editor.putString("observacion",  medicamentosSuministrados.observacion);
        editor.putString("sw_id_consumo",  medicamentosSuministrados.sw_id_consumo);
        editor.putString("nombre",  medicamentosSuministrados.nombre);

        editor.commit();
    }

    public static MedicamentosSuministrados getSuministroMedicamentos(Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("MedicSuminstrado", Context.MODE_PRIVATE);
        MedicamentosSuministrados medicamentosSuministrados = new MedicamentosSuministrados();
        medicamentosSuministrados.suministro_id = preferences.getInt("suministro_id", 0);
        medicamentosSuministrados.ingreso = preferences.getInt("ingreso", 0);
        medicamentosSuministrados.num_reg_formulacion = preferences.getInt("num_reg_formulacion", 0);
        medicamentosSuministrados.usuario_id_control = preferences.getInt("usuario_id_control", 0);
        medicamentosSuministrados.cantidad_suministrada = preferences.getString("cantidad_suministrada", "");
        medicamentosSuministrados.cantidad_perdidas = preferences.getString("cantidad_perdidas", "");
        medicamentosSuministrados.cantidad_aprovechada = preferences.getInt("cantidad_aprovechada", 0);
        medicamentosSuministrados.sw_estado = preferences.getString("sw_estado", "");
        medicamentosSuministrados.codigo_producto = preferences.getString("codigo_producto", "");
        medicamentosSuministrados.fecha_realizado = preferences.getString("fecha_realizado", "");
        medicamentosSuministrados.fecha_registro_control = preferences.getString("fecha_registro_control", "");
        medicamentosSuministrados.estacion_id = preferences.getString("estacion_id", "");
        medicamentosSuministrados.observacion = preferences.getString("observacion", "");
        medicamentosSuministrados.sw_id_consumo = preferences.getString("sw_id_consumo", "");
        medicamentosSuministrados.nombre = preferences.getString("nombre", "");

        return medicamentosSuministrados;

    }

}
