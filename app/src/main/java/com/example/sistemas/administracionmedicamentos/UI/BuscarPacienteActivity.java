package com.example.sistemas.administracionmedicamentos.UI;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemas.administracionmedicamentos.Constantes.Error;
import com.example.sistemas.administracionmedicamentos.JSON.JSONConvert;
import com.example.sistemas.administracionmedicamentos.MainActivity;
import com.example.sistemas.administracionmedicamentos.Modelos.ListModel;
import com.example.sistemas.administracionmedicamentos.Modelos.Paciente;
import com.example.sistemas.administracionmedicamentos.Modelos.Usuario;
import com.example.sistemas.administracionmedicamentos.Network.AsyncConexion;
import com.example.sistemas.administracionmedicamentos.Network.ResponseListener;
import com.example.sistemas.administracionmedicamentos.R;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.IngresoPacientePrefMananger;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.LoginSharedPrefeMananger;
import com.example.sistemas.administracionmedicamentos.Utilidades.Util;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.view.View.Z;

public class BuscarPacienteActivity extends AppCompatActivity implements ResponseListener {

    private ZXingScannerView mScannerView;
    Button openScanner;
    EditText codIngreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_paciente);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        codIngreso  = (EditText)findViewById(R.id.edtIngreso);
        openScanner = (Button)findViewById(R.id.btnScaner);

        //Se inicia la captura de codigo de barras al precionar el boton

        openScanner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new IntentIntegrator(BuscarPacienteActivity.this).initiateScan();
            }
        });

        ((Button)findViewById(R.id.btnIngresar)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (codIngreso.getText().toString().length() > 0){

                    String ingreso =  codIngreso.getText().toString();

                    List<ListModel> mParametros = new ArrayList<ListModel>();
                    mParametros.add(new ListModel("ingreso", ingreso));

                    new AsyncConexion(BuscarPacienteActivity.this, BuscarPacienteActivity.this, IngresoPacientePrefMananger.getIP(BuscarPacienteActivity.this),
                            new String[]{"Ingresando Paciente", "Aguarde Por Favor..."}, mParametros).execute();
                }else{

                    codIngreso.setError((codIngreso.getText().length() > 0) ? null : "Verique el error");
                }
            }
        });

    }

    /*METODOS QUE PERMITEN EL SCANEO*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        final IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        handleResult(scanResult);
    }

    private void handleResult (IntentResult scanResult){

        if (scanResult != null){

            updateTextView(scanResult.getContents(), scanResult.getFormatName());
        }else {
            Toast.makeText(this, "No Se Detecto Codigo De Barras", Toast.LENGTH_SHORT).show();
        }
    }

    /*Metodo que imprime el cosigo de barras recibido*/
    private void updateTextView(String scan_result, String scan_result_format){

        codIngreso.setText(scan_result);
    }

    @Override
    public void onResponse(JSONArray response) {

        if (JSONConvert.getPacienteIngreso(response).size() == 0)
            Util.Mensaje("¡Hay algo mal..!", "Credenciales invalidad, verifica tus detalles", this);
        else {
            Paciente paciente = JSONConvert.getPacienteIngreso(response).get(0);
            IngresoPacientePrefMananger.setIngresoPaciente(paciente, BuscarPacienteActivity.this);
            Intent medicamentos = new Intent(BuscarPacienteActivity.this,MedicamentosActivity.class);
            startActivityForResult(medicamentos, 0);
            //finish();
        }
    }

    @Override
    public void onError(int error) {

        if (Error.ERROR_NO_NETWORK == error)
            Util.Mensaje("Verifica tu acceso a internet ", "Habilita los datos o el wifi", BuscarPacienteActivity.this);
        else if (Error.ERROR_NO_RESPONSE_SERVER == error || Error.ERROR_RESPONSE_NO_VALID == error)
            Util.Mensaje("Tenemos problemas", "Hay problemas de nuestra parte, intenta mas tarde", BuscarPacienteActivity.this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }


}
