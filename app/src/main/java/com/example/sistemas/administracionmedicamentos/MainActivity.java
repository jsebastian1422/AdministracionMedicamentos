package com.example.sistemas.administracionmedicamentos;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sistemas.administracionmedicamentos.Modelos.Usuario;
import com.example.sistemas.administracionmedicamentos.Network.ResponseListener;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.LoginSharedPrefeMananger;
import com.example.sistemas.administracionmedicamentos.UI.BuscarPacienteActivity;
import com.example.sistemas.administracionmedicamentos.UI.LoginActivity;
import com.example.sistemas.administracionmedicamentos.UI.MedicamentosActivity;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity implements ResponseListener {

    Usuario sesionActual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Retorna la sesion actual
        sesionActual = LoginSharedPrefeMananger.getUserLogin(MainActivity.this);
        TextView nombre = (TextView)findViewById(R.id.txt_prueba);

        nombre.setText("Hola" + sesionActual.getNombre());

        //prueba busqueda paciente

        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresopaciente = new Intent(MainActivity.this,BuscarPacienteActivity.class);
                startActivity(ingresopaciente);
                //finish();
            }
        });

        //prueba Medicamentos

        ((Button)findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medicamentos = new Intent(MainActivity.this,MedicamentosActivity.class);
                startActivity(medicamentos);
                //finish();
            }
        });

        //prueba Medicamentos

        ((Button)findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(MainActivity.this,MainActivity.class);
                startActivity(main);
                //finish();
            }
        });
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
        return false;
    }
}
