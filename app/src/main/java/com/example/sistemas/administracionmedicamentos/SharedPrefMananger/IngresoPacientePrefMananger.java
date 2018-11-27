package com.example.sistemas.administracionmedicamentos.SharedPrefMananger;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sistemas.administracionmedicamentos.Modelos.Paciente;

public class IngresoPacientePrefMananger {

    public static String IP;
    private static SharedPreferences Ajustes(Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("proceso", Context.MODE_PRIVATE);
        if (preferences.getString("IP", "").isEmpty()){

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("IP", "http://192.168.100.32/~pruebas1/SIIS/app_AdminisMedicamentosMobile/json/jsonBusquedaPaciente.php");
            editor.commit();
        }

        return preferences;
    }

    public static String getIP(Context Contexto) {
        IP = Ajustes(Contexto).getString("IP", "");
        return IP;
    }

    public static void setIP(String IP, Context Contexto) {

        SharedPreferences preferences = Contexto.getSharedPreferences("proceso", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("IP", IP);
        editor.commit();
    }

    public static boolean IngresoPaciente(Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("IngresoPaciente", Context.MODE_PRIVATE);
        if (preferences.getString("ingreso", "").isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    public static void setIngresoPaciente(Paciente paciente, Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("IngresoPaciente", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ingreso", paciente.ingreso);
        editor.putString("numerodecuenta", paciente.numerodecuenta);
        editor.putString("paciente_id", paciente.paciente_id);
        editor.putString("historia_numero", paciente.historia_numero);
        editor.putString("historia_prefijo", paciente.historia_prefijo);
        editor.putString("tipo_id_paciente", paciente.tipo_id_paciente);
        editor.putString("paciente", paciente.paciente);
        editor.putString("cama", paciente.cama);
        editor.putString("pieza", paciente.pieza);
        editor.commit();

    }

    public static Paciente getIngresoPaciente (Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("IngresoPaciente", Context.MODE_PRIVATE);
        Paciente paciente = new Paciente();
        paciente.ingreso    =   preferences.getString("ingreso", "");
        paciente.numerodecuenta =   preferences.getString("numerodecuenta", "");
        paciente.paciente_id    =   preferences.getString("paciente_id","");
        paciente.historia_numero    =   preferences.getString("historia_numero", "");
        paciente.historia_prefijo    =   preferences.getString("historia_prefijo", "");
        paciente.tipo_id_paciente    =   preferences.getString("tipo_id_paciente", "");
        paciente.paciente    =   preferences.getString("paciente", "");
        paciente.cama    =   preferences.getString("cama", "");
        paciente.pieza    =   preferences.getString("pieza", "");
        return paciente;
    }
}
