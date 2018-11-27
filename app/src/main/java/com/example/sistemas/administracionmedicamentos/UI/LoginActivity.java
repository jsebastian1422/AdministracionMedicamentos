package com.example.sistemas.administracionmedicamentos.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sistemas.administracionmedicamentos.Constantes.Error;
import com.example.sistemas.administracionmedicamentos.JSON.JSONConvert;
import com.example.sistemas.administracionmedicamentos.MainActivity;
import com.example.sistemas.administracionmedicamentos.Modelos.ListModel;
import com.example.sistemas.administracionmedicamentos.Modelos.Usuario;
import com.example.sistemas.administracionmedicamentos.Network.AsyncConexion;
import com.example.sistemas.administracionmedicamentos.Network.ResponseListener;
import com.example.sistemas.administracionmedicamentos.R;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.LoginSharedPrefeMananger;
import com.example.sistemas.administracionmedicamentos.Utilidades.Util;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.Format;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import static com.android.volley.toolbox.Volley.newRequestQueue;

public class LoginActivity extends AppCompatActivity implements ResponseListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView textinfo = (TextView)findViewById(R.id.ip);

        //Obtiene la direccion ip del dispositivio

        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipInt = wifiInfo.getIpAddress();

        String ipAddress = null;
        try {
            ipAddress = InetAddress.getByAddress(
                    ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(ipInt).array()).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        final String info = "ip" + ipAddress;
        final String diviceipaddress = ipAddress;
        textinfo.setText(info);


        //Inicia la accion del login
        ((Button) findViewById(R.id.btnIngresar)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Se declaran los inputs
                EditText edtUsuario = ((EditText) findViewById(R.id.txtUser));
                EditText edtContraseña = ((EditText) findViewById(R.id.txtContraseña));

                if (edtUsuario.getText().toString().length() > 0 && edtContraseña.getText().toString().length() >0){

                    String usuario  = edtUsuario.getText().toString();
                    String contra   = edtContraseña.getText().toString();
                    String ipAddress = diviceipaddress.toString();

                    List<ListModel> mParametros = new ArrayList<ListModel>();
                    mParametros.add(new ListModel("user", usuario));
                    mParametros.add(new ListModel("contra", contra));
                    mParametros.add(new ListModel("ip_address", ipAddress));

                    new AsyncConexion(LoginActivity.this, LoginActivity.this, LoginSharedPrefeMananger.getIP(LoginActivity.this),
                            new String[]{"Iniciando Sesión", "Aguarde Por Favor..."},mParametros).execute();
                }else{
                    edtUsuario.setError((edtUsuario.getText().length() > 0) ? null : "Verifica error");
                    edtContraseña.setError((edtContraseña.getText().length() > 0) ? null : "Verifica error");
                }
            }
        });

    }

    @Override
    public void onResponse(JSONArray response) {

        if (JSONConvert.getLoginUser(response).size() == 0)
            Util.Mensaje("¡Hay algo mal..!", "Credenciales invalidad, verifica tus detalles", this);
        else {
            Usuario user = JSONConvert.getLoginUser(response).get(0);
            LoginSharedPrefeMananger.setUserLogin(user, LoginActivity.this);
            Intent main = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(main);
            finish();
        }
    }

    @Override
    public void onError(int error) {

        if (Error.ERROR_NO_NETWORK == error)
            Util.Mensaje("Verifica tu acceso a internet ", "Habilita los datos o el wifi", LoginActivity.this);
        else if (Error.ERROR_NO_RESPONSE_SERVER == error || Error.ERROR_RESPONSE_NO_VALID == error)
            Util.Mensaje("Tenemos problemas", "Hay problemas de nuestra parte, intenta mas tarde", LoginActivity.this);
    }
}