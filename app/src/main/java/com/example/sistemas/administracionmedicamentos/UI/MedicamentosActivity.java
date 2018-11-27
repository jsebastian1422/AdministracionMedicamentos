package com.example.sistemas.administracionmedicamentos.UI;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sistemas.administracionmedicamentos.Modelos.Paciente;
import com.example.sistemas.administracionmedicamentos.Network.ResponseListener;
import com.example.sistemas.administracionmedicamentos.R;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.IngresoPacientePrefMananger;

import org.json.JSONArray;

public class MedicamentosActivity extends AppCompatActivity implements ResponseListener {

    Paciente paciente_ingreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicamentos);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Retorna el paciente ingresado

        paciente_ingreso = IngresoPacientePrefMananger.getIngresoPaciente(MedicamentosActivity.this);

        //Imprime los valores en la tabla

       TextView habitacion = (TextView) findViewById(R.id.txtHabitacion);
        habitacion.setText(paciente_ingreso.pieza);

        TextView cama = (TextView) findViewById(R.id.txtCama);
        cama.setText(paciente_ingreso.cama);

        TextView cuenta = (TextView) findViewById(R.id.txtCuenta);
        cuenta.setText(paciente_ingreso.numerodecuenta);

        TextView ingreso = (TextView) findViewById(R.id.txtIngreso);
        ingreso.setText(paciente_ingreso.ingreso);

        TextView paciente = (TextView) findViewById(R.id.txtPaciente);
        paciente.setText(paciente_ingreso.paciente);

        TextView identificacion = (TextView) findViewById(R.id.txtIdentificacion);
        identificacion.setText(paciente_ingreso.tipo_id_paciente + paciente_ingreso.paciente_id);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onResponse(JSONArray response) {

    }

    @Override
    public void onError(int error) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return false;
    }
}
