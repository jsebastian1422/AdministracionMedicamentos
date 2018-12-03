package com.example.sistemas.administracionmedicamentos.UI;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sistemas.administracionmedicamentos.Adaptadores.MedicRecyclerViewAdapter;
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
    MedicRecyclerViewAdapter adapter;
    RecyclerView rcvMedicamentos;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Medicamentos> lMedicamentos = new ArrayList<>();
    List<Medicamentos> listMedicamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Se asigna el recyclerView a la variable rcvMedicamentos

        rcvMedicamentos = (RecyclerView)findViewById(R.id.rcvMedicamentos);
        rcvMedicamentos.setHasFixedSize(true);
        rcvMedicamentos.setLayoutManager(new LinearLayoutManager(this));
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

        TextView paciente = (TextView) findViewById(R.id.txtPaciente);
        paciente.setText(paciente_ingreso.paciente);

        TextView identificacion = (TextView) findViewById(R.id.txtIdentificacion);
        identificacion.setText(paciente_ingreso.tipo_id_paciente + " " + paciente_ingreso.paciente_id);

        //Iniciando la accion de busqueda de medicamentos que corresponden al paciente

        ((Button) findViewById(R.id.btnBuscMeciamento)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String ingresoPaciente = paciente_ingreso.ingreso;
                List<ListModel> mParamentros = new ArrayList<>();
                mParamentros.add(new ListModel("ingreso", ingresoPaciente));

                new AsyncConexion(MedicamentosActivity.this, MedicamentosActivity.this, MedicamentosPrefMananger.getIP(MedicamentosActivity.this),
                            new String[]{"Buscando Medicamentos",  "Aguarde Por Favor"},mParamentros).execute();
            }
        });


    }

    @Override
    public void onResponse(JSONArray response) {
     /*   lMedicamentos = JSONConvert.getMedicamentosPaciente(response);
        MedicRecyclerViewAdapter Adapater = new MedicRecyclerViewAdapter(MedicamentosActivity.this, lMedicamentos);
        rcvMedicamentos.setAdapter(Adapater);*/
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
