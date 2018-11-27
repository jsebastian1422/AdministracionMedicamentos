package com.example.sistemas.administracionmedicamentos.SharedPrefMananger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.sistemas.administracionmedicamentos.Modelos.Usuario;
import com.example.sistemas.administracionmedicamentos.UI.LoginActivity;

public class LoginSharedPrefeMananger {

    private static String IP;

    // Se inicializa la información de configuración de IP, y se establece la información de IP dado el servicio web y se retorna el Shared preference de Configuración
    private static SharedPreferences Ajustes(Context  Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("LoginSharedPrefeMananger", Context.MODE_PRIVATE); // Obtenemos la instancia de shared preference en la cual se brinda un nombre y el modo de acceso
        if (preferences.getString("IP", "").isEmpty()){ // Obtenemos que si en los valores obtenidos anteriormente existe IP y si este contiene información alguna

            setIP("http://192.168.100.32/~pruebas1/SIIS/app_AdminisMedicamentosMobile/json/jsonLogin.php", Contexto); // Basado en que IP esta vacío o no existe, se establece
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

        SharedPreferences preferences = Contexto.getSharedPreferences("LoginSharedPrefeMananger", Context.MODE_PRIVATE);  // Obtenemos la instancia de shared preference en la cual se brinda un nombre y el modo de acceso
        SharedPreferences.Editor editor = preferences.edit(); // Se establece en shared preference obtenido en modo edición
        editor.putString("IP", IP); // Se agregan los valores
        editor.commit();  // Se confirman los cambios
    }

    // Verificcamos en la instancia de Shared preference Login, si hay información de usuario almacenado y este devuelve un Boolean t: si hay un información de un usuario, f: si no hay información (no esta autenticado)
    public static boolean Login(Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("Login", Context.MODE_PRIVATE);  // Obtenemos la instancia de shared preference en la cual se brinda un nombre y el modo de acceso
        if (preferences.getString("usuario", "").isEmpty()){
            return false;
        }else {
            return true;
        }
    }

    public static void setUserLogin(Usuario usuario, Context Contexto){

        SharedPreferences preferences = Contexto.getSharedPreferences("Login", Context.MODE_PRIVATE); // Obtenemos la instancia de shared preference en la cual se brinda un nombre y el modo de acceso
        SharedPreferences.Editor editor = preferences.edit();// Se establece en shared preference obtenido en modo edición

        // Se agregan los valores
        editor.putString("usuario_id", usuario.usuario_id);
        editor.putString("rol_id", usuario.rol_id);
        editor.putString("usuario", usuario.usuario);
        editor.putString("passwd", usuario.passwd);
        editor.putString("nombre", usuario.nombre);
        editor.putString("descripcion", usuario.descripcion);
        //editor.putString("ip_address", usuario.ip_address);
        editor.commit(); // Se confirman los cambios

    }

    public static Usuario getUserLogin(Context Contexto){

        SharedPreferences sharedPreferences = Contexto.getSharedPreferences("Login", Context.MODE_PRIVATE); // Obtenemos la instancia de shared preference en la cual se brinda un nombre y el modo de acceso
        Usuario user = new Usuario();  // Como este devuelve un objeto de tipo Usuario, es necesario crear una instancia y posteriormente se establecen los valores
        user.nombre     = sharedPreferences.getString("nombre", "");
        user.usuario    = sharedPreferences.getString("usuario", "");
        user.passwd     = sharedPreferences.getString("passwd", "");
        user.descripcion= sharedPreferences.getString("descripcion", "");
        user.usuario_id = sharedPreferences.getString("usuario_id", "");
        user.rol_id     = sharedPreferences.getString("rol_id", "");
        //user.ip_address = sharedPreferences.getString("ip_address", "");
        return user;  // Se retorna el objeto usuario, con la información obtenida del shared preference de Login
    }
}
