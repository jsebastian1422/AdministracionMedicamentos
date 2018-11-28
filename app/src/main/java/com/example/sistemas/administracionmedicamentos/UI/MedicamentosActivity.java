package com.example.sistemas.administracionmedicamentos.UI;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemas.administracionmedicamentos.Constantes.Error;
import com.example.sistemas.administracionmedicamentos.JSON.JSONConvert;
import com.example.sistemas.administracionmedicamentos.Modelos.ListModel;
import com.example.sistemas.administracionmedicamentos.Modelos.Medicamentos;
import com.example.sistemas.administracionmedicamentos.Modelos.Paciente;
import com.example.sistemas.administracionmedicamentos.Network.AsyncConexion;
import com.example.sistemas.administracionmedicamentos.Network.ResponseListener;
import com.example.sistemas.administracionmedicamentos.R;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.IngresoPacientePrefMananger;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.MedicamentosPrefMananger;
import com.example.sistemas.administracionmedicamentos.Utilidades.Util;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MedicamentosActivity extends AppCompatActivity implements ResponseListener {

    Paciente paciente_ingreso;
    RecyclerView rcvMedicamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Se asigna el RecyclerView

        rcvMedicamentos = findViewById(R.id.rcvMedicamentos);

        //Retorna el paciente ingresado

        paciente_ingreso = IngresoPacientePrefMananger.getIngresoPaciente(MedicamentosActivity.this);

        //Imprime los valores en la tabla

       TextView habitacion = (TextView) findViewById(R.id.txtHabitacion);
        habitacion.setText(paciente_ingreso.pieza);

        TextView cama = (TextView) findViewById(R.id.txtCama);
        cama.setText(paciente_ingreso.cama);

        TextView cuenta = (TextView) findViewById(R.id.txtCuenta);
        cuenta.setText(paciente_ingreso.numerodecuenta);

        final TextView ingreso = (TextView) findViewById(R.id.txtIngreso);
        ingreso.setText(paciente_ingreso.ingreso);

        final TextView paciente = (TextView) findViewById(R.id.txtPaciente);
        paciente.setText(paciente_ingreso.paciente);

        TextView identificacion = (TextView) findViewById(R.id.txtIdentificacion);
        identificacion.setText(paciente_ingreso.tipo_id_paciente + paciente_ingreso.paciente_id);

        //Buscar los medicamentos que estan relacionados con el paciente.

         ((Button) findViewById(R.id.btnBucarMedicamentos)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String Ingreso = ingreso.getText().toString();

                    List<ListModel> mParametros = new ArrayList<>();
                    mParametros.add(new ListModel("ingreso", Ingreso));

                    new AsyncConexion(MedicamentosActivity.this, MedicamentosActivity.this, MedicamentosPrefMananger.getIP(MedicamentosActivity.this),
                            new String[] {"Buscando Medicamentos", "Aguarde Por Favor.."},mParametros).execute();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onResponse(JSONArray response) {

        if (JSONConvert.getPacienteMedicamento(response).size() == 0){
            Util.Mensaje("¡..Hay Algo Mal..!", "No Se Encontraron Medicamentos", this);
        }else{

            Medicamentos medicamentos = JSONConvert.getPacienteMedicamento(response).get(0);
            rcvMedicamentos.setLayoutManager(new LinearLayoutManager(MedicamentosActivity.this));


        }
    }

    @Override
    public void onError(int error) {

        if (Error.ERROR_NO_NETWORK == error)
            Util.Mensaje("Verifica tu acceso a internet ", "Habilita los datos o el wifi", MedicamentosActivity.this);
        else if (Error.ERROR_NO_RESPONSE_SERVER == error || Error.ERROR_RESPONSE_NO_VALID == error)
            Util.Mensaje("Tenemos problemas", "Hay problemas de nuestra parte, intenta mas tarde", MedicamentosActivity.this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return false;
    }
}
