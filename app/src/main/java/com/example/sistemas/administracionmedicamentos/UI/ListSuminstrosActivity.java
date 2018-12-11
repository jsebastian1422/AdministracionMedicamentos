package com.example.sistemas.administracionmedicamentos.UI;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.sistemas.administracionmedicamentos.Adaptadores.MedicamentosSuminAdapter;
import com.example.sistemas.administracionmedicamentos.Constantes.Error;
import com.example.sistemas.administracionmedicamentos.JSON.JSONConvert;
import com.example.sistemas.administracionmedicamentos.Modelos.ListModel;
import com.example.sistemas.administracionmedicamentos.Modelos.MedicamentosSuministrados;
import com.example.sistemas.administracionmedicamentos.Modelos.Paciente;
import com.example.sistemas.administracionmedicamentos.Network.AsyncConexion;
import com.example.sistemas.administracionmedicamentos.Network.ResponseListener;
import com.example.sistemas.administracionmedicamentos.R;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.IngresoPacientePrefMananger;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.MedicaSuminisPrefMananger;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.MedicamentosPrefMananger;
import com.example.sistemas.administracionmedicamentos.Utilidades.Util;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ListSuminstrosActivity extends AppCompatActivity implements ResponseListener {

    public Paciente paciente;
    public String Codigo,Ingreso;
    ArrayList<MedicamentosSuministrados> lMedicaSuminist;
    RecyclerView rcvListMedicamentos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_suminstros);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Obtiener el recyclerview desde el XML

        rcvListMedicamentos = (RecyclerView)findViewById(R.id.rcvListMedicamentos);

        getDatosSuministro();

    }

    public void getDatosSuministro(){

        paciente = IngresoPacientePrefMananger.getIngresoPaciente(ListSuminstrosActivity.this);
        Ingreso = paciente.ingreso;

        //Imprime los valores en la tabla

        TextView nombre = (TextView) findViewById(R.id.txtPaciente);
        nombre.setText(paciente.paciente);

        TextView identificacion = (TextView) findViewById(R.id.txtIdentificacion);
        identificacion.setText(paciente.tipo_id_paciente + paciente.paciente_id);
        Intent retorna = getIntent();
        Codigo = retorna.getStringExtra("Codigo");

        List<ListModel> mParametros = new ArrayList<ListModel>();

        mParametros.add(new ListModel("ingreso", Ingreso));
        mParametros.add(new ListModel("codigo_producto", Codigo));
        new AsyncConexion(ListSuminstrosActivity.this, ListSuminstrosActivity.this, MedicaSuminisPrefMananger.getIP(ListSuminstrosActivity.this),
                new String[]{"Buscanco Medicamentos Ingresados", "Aguarde Por Favor..."}, mParametros).execute();


    }

    @Override
    public void onResponse(JSONArray response) {

        lMedicaSuminist = JSONConvert.getSuministroMedicamentos(response);
        MedicamentosSuminAdapter adapter = new MedicamentosSuminAdapter(ListSuminstrosActivity.this, lMedicaSuminist);
        rcvListMedicamentos.setHasFixedSize(true);
        rcvListMedicamentos.setLayoutManager(new LinearLayoutManager(this));
        rcvListMedicamentos.setAdapter(adapter);
    }

    @Override
    public void onError(int error) {

        if (Error.ERROR_NO_NETWORK == error)
            Util.Mensaje("Verifica tu acceso a internet ", "Habilita los datos o el wifi", ListSuminstrosActivity.this);
        else if (Error.ERROR_NO_RESPONSE_SERVER == error || Error.ERROR_RESPONSE_NO_VALID == error)
            Util.Mensaje("Tenemos problemas", "Hay problemas de nuestra parte, intenta mas tarde", ListSuminstrosActivity.this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return false;
    }
}
