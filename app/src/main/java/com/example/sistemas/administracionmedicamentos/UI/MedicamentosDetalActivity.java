package com.example.sistemas.administracionmedicamentos.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sistemas.administracionmedicamentos.Modelos.Medicamentos;
import com.example.sistemas.administracionmedicamentos.Modelos.Paciente;
import com.example.sistemas.administracionmedicamentos.R;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.IngresoPacientePrefMananger;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.MedicamentosPrefMananger;

public class MedicamentosDetalActivity extends AppCompatActivity {

    Paciente paciente;
    Medicamentos medicamentos;
    String Codigo, Medicamento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal_medicamentos);

        //Se obtinen el codigo del medicamento y el ingreso del paciente

        paciente = IngresoPacientePrefMananger.getIngresoPaciente(MedicamentosDetalActivity.this);
        medicamentos = MedicamentosPrefMananger.getMedicamentosPaciente(MedicamentosDetalActivity.this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            Codigo = extras.getString("Codigo");
        }

        TextView ingreso = (TextView)findViewById(R.id.txtIngresos);
        ingreso.setText(paciente.ingreso);

        TextView codigo = (TextView)findViewById(R.id.txtPruebas);
        codigo.setText(Codigo);
    }
}
