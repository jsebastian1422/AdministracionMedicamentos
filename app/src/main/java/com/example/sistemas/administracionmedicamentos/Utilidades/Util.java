package com.example.sistemas.administracionmedicamentos.Utilidades;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.Response;
import com.example.sistemas.administracionmedicamentos.Modelos.Usuario;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.LoginSharedPrefeMananger;
import com.example.sistemas.administracionmedicamentos.UI.LoginActivity;

import org.json.JSONArray;

public class Util {

    // Metodo que permite visualizar un mensaje en una alerta de dialogo
    public static AlertDialog.Builder Mensaje (String Titulo, String Mensaje, Context contexto){

        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(contexto); // Creamos la instania de la clase
        dlgAlert.setMessage(Mensaje); // Determinamos el mensaje
        dlgAlert.setTitle(Titulo); // Determinamos el titulo
        dlgAlert.setPositiveButton("OK",null);  // Determinamos el boton OK, sin evento alguno, ya que este simplemente es algo informativo
        dlgAlert.setCancelable(true); // Permite detertminar que el mensaje se puede cerrar facilmente
        dlgAlert.create().show(); // Se muestra el mensaje
        return  dlgAlert;
    }

    // Este metodo permite determinar si existe conectividad alguna ya sea (Wifi, datos, ethernet, entre otros)
    public static boolean EstadoRed (Context Contexto) {
        // Obtenemos informacion de la conectividad del dispositivo, basado en informacion del sistema
        ConnectivityManager connMgr = (ConnectivityManager) Contexto.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); // Obtenemos informacion de la conexion actual

        if (networkInfo != null &&networkInfo.isConnected()){ // Verificamos que haya conectividad alguna
            return true;


        }else
            return false;
    }

    public static void CerrarSesion(Context context) {

        LoginSharedPrefeMananger.setUserLogin(new Usuario(),context);
        Intent i = new Intent(context,LoginActivity.class);
        context.startActivity(i);
        ((Activity) context).finish();



    }
}
