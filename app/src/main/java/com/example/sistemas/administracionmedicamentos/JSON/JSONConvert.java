package com.example.sistemas.administracionmedicamentos.JSON;

import android.util.Log;

import com.example.sistemas.administracionmedicamentos.Modelos.Paciente;
import com.example.sistemas.administracionmedicamentos.Modelos.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONConvert {

    public static String[] getUserOnlyName(JSONArray response){

        List<Usuario> lUsuario = getLoginUser(response);
        String[] lUsuarioNombre = new String[lUsuario.size()];
        for (Usuario item : lUsuario){
            lUsuarioNombre[lUsuario.indexOf(item)] = item.nombre;
        }

        return lUsuarioNombre;
    }

    // Funcion que permite convertir la respuesta del servidor (JSON) en objetos o listado de objetos, en este caso de usuarios
    public static ArrayList<Usuario> getLoginUser (JSONArray response){

        ArrayList<Usuario> lUsuario = new ArrayList<>(); // Instanciamos la lista a retornar
        try {

            for (int i = 0; i < response.length(); i++){

                JSONObject mDatos = response.getJSONObject(i); // Obtenemos la informacion  del JSON, basado en una posicion

                //Almacena la informacion del usuario recogido con el login
                Usuario user = new Usuario();
                user.usuario_id = mDatos.getString("usuario_id");
                user.rol_id     = mDatos.getString("rol_id");
                user.usuario    = mDatos.getString("usuario");
                user.passwd     = mDatos.getString("passwd");
                user.nombre     = mDatos.getString("nombre");
                user.descripcion= mDatos.getString("descripcion");
                //user.ip_address  = mDatos.getString("ip_address");

                lUsuario.add(user); // Almacenamos en la lista la informacion de este usuario
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lUsuario;
    }

    public static ArrayList<Paciente>  getPacienteIngreso (JSONArray response){

        ArrayList<Paciente> lPaciente = new ArrayList<>();

        try {

            for (int i = 0; i < response.length(); i ++){

                JSONObject mDatos = response.getJSONObject(i);

                //Almacena la informacion del paciente recogido en la busqueda de ingreso

                Paciente paciente = new Paciente();
                paciente.ingreso    =   mDatos.getString("ingreso");
                paciente.numerodecuenta =   mDatos.getString("numerodecuenta");
                paciente.paciente_id    =   mDatos.getString("paciente_id");
                paciente.historia_numero    =   mDatos.getString("historia_numero");
                paciente.historia_prefijo    =   mDatos.getString("historia_prefijo");
                paciente.tipo_id_paciente    =   mDatos.getString("tipo_id_paciente");
                paciente.paciente    =   mDatos.getString("paciente");
                paciente.cama    =   mDatos.getString("cama");
                paciente.pieza    =   mDatos.getString("pieza");

                lPaciente.add(paciente);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lPaciente;
    }

}
