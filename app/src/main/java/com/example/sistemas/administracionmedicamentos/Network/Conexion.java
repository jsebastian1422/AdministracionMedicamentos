package com.example.sistemas.administracionmedicamentos.Network;

import android.content.Context;
import android.util.Log;

import com.example.sistemas.administracionmedicamentos.Modelos.ListModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class Conexion {

    // Metodo que conecta a una pagina web por medio de HTTP y retorna en una cadena la respuesta del servidor
    public static String Conexion(Context Contexto, String Servidor, List<ListModel> Parametros) throws IOException {
        HttpURLConnection conn = null;
        try {
            // Conversion de Objetos
            String data = getQuery(Parametros); // Obtenemos la informacion que se transferira por GET ej:user=SIIS&contra=123456
            Servidor += "?" + data;

            Log.e("SER", Servidor); // Imprimimos en la consola RUN, que direccion esta ejecutando
            URL url = new URL(Servidor); // Se instancia la clase USL con la direccion del servidor
            conn = (HttpURLConnection) url.openConnection();  // Se abre la conexion
            conn.setReadTimeout(15000); // se establece 15 segundos como tiempo de espera de respuesta del servidor
            conn.setConnectTimeout(10000); // se establece 10 segundos como tiempo de espera de conexion
            conn.setRequestMethod("GET"); // Se establece el metodo a usar en la peticion en este caso GET
            conn.setDoOutput(true); // este permite establecer unos datos de salida, en este caso no es necesario, ya que no se esta utilizando metodo POST o PUT
            conn.setDoInput(true);  // este permite establcer que habra informacion de entrada, para determinar la respuesta del servidor
            int responseCode = conn.getResponseCode(); // Obtenemos el codigo de respuesta EJ: 200 es OK, 404 la pagina no existe


            if (responseCode == HttpURLConnection.HTTP_OK) { // Si el codigo de respuesta es 200, procesamos obtenemos el flujo de datos, de lo contrario, se envia nulo como respuesta del servidor

                // Lee la información que retorna el servidor y lo acumula en eun StringBuilder

                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);

                }
                return (result.toString()); // Lee la información que retorna el servidor y lo acumula en eun StringBuilder
            }

        } finally {
            conn.disconnect();
        }
        return null;
    }

    // funcion que permite generar la URL, basado en los parametros que se proporcionan en una lista de ListModel
    public static String getQuery(List<ListModel> listado) {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (ListModel Item : listado) {
            if (first)
                first = false;
            else
                result.append("&");
            try {
                result.append(URLEncoder.encode(Item.key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(Item.value, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        return result.toString();
    }
}
