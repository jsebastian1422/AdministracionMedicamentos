package com.example.sistemas.administracionmedicamentos.JSON;

import android.util.Log;

import com.example.sistemas.administracionmedicamentos.Modelos.Medicamentos;
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

    public static ArrayList<Medicamentos> getMedicamentosPaciente (JSONArray response){

        ArrayList<Medicamentos> lMedicamentos = new ArrayList<>();

        try {

            for (int i = 0; i < response.length(); i ++){

                JSONObject mDatos = response.getJSONObject(i);

                //Almacena la informacion del paciente recogido en la busqueda de medicamentos

                Medicamentos medicamentos = new Medicamentos();
                medicamentos.ingreso     =   mDatos.getString("ingreso");
                medicamentos.codigo_producto     =   mDatos.getString("codigo_producto");
                medicamentos.num_reg    =   mDatos.getString("codigo_producto");
                medicamentos.num_reg_formulacion    =   mDatos.getString("num_reg_formulacion");
                medicamentos.sw_estado    =   mDatos.getString("sw_estado");
                medicamentos.observacion    =   mDatos.getString("observacion");
                medicamentos.via_administracion_id    =   mDatos.getString("via_administracion_id");
                medicamentos.unidad_dosificacion    =   mDatos.getString("unidad_dosificacion");
                medicamentos.dosis    =   mDatos.getString("dosis");
                medicamentos.frecuencia    =   mDatos.getString("frecuencia");
                medicamentos.cantidad    =   mDatos.getString("cantidad");
                medicamentos.sw_confirmacion_formulacion    =   mDatos.getString("sw_confirmacion_formulacion");
                medicamentos.sw_requiere_autorizacion_no_pos    =   mDatos.getString("sw_requiere_autorizacion_no_pos");
                medicamentos.dias_tratamiento    =   mDatos.getString("dias_tratamiento");
                medicamentos.justificacion_no_pos_id    =   mDatos.getString("justificacion_no_pos_id");
                medicamentos.grupo_protocolo_formulacion    =   mDatos.getString("grupo_protocolo_formulacion");
                medicamentos.tratamiento_oncologico_id    =   mDatos.getString("tratamiento_oncologico_id");
                medicamentos.tipo_solicitud    =   mDatos.getString("tipo_solicitud");
                medicamentos.evolucion_id    =   mDatos.getString("evolucion_id");
                medicamentos.usuario_id    =   mDatos.getString("usuario_id");
                medicamentos.fecha_registro    =   mDatos.getString("fecha_registro");
                medicamentos.producto    =   mDatos.getString("producto");
                medicamentos.producto_descripcion    =   mDatos.getString("producto_descripcion");
                medicamentos.descripcion_abreviada    =   mDatos.getString("descripcion_abreviada");
                medicamentos.contenido_unidad_venta    =   mDatos.getString("contenido_unidad_venta");
                medicamentos.unidad_id    =   mDatos.getString("unidad_id");
                medicamentos.via_administracion    =   mDatos.getString("via_administracion");
                medicamentos.codigo_pos    =   mDatos.getString("codigo_pos");
                medicamentos.unidad    =   mDatos.getString("unidad");

                lMedicamentos.add(medicamentos);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return lMedicamentos;
    }

}
