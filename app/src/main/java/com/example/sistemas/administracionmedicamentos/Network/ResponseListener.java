package com.example.sistemas.administracionmedicamentos.Network;

import org.json.JSONArray;
import org.json.JSONObject;

// Clase que permite implementar metodos de respuesta y error, basados en resultados de la peticion HTTP

public interface ResponseListener {

    void onResponse (JSONArray response);
    void onError (int error);
}
