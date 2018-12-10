package com.example.sistemas.administracionmedicamentos.UI;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.sistemas.administracionmedicamentos.Constantes.Error;
import com.example.sistemas.administracionmedicamentos.JSON.JSONConvert;
import com.example.sistemas.administracionmedicamentos.Modelos.BodegaPaciente;
import com.example.sistemas.administracionmedicamentos.Modelos.ListModel;
import com.example.sistemas.administracionmedicamentos.Modelos.Medicamentos;
import com.example.sistemas.administracionmedicamentos.Modelos.Paciente;
import com.example.sistemas.administracionmedicamentos.Network.AsyncConexion;
import com.example.sistemas.administracionmedicamentos.Network.ResponseListener;
import com.example.sistemas.administracionmedicamentos.R;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.BodegaPacientePrefMananger;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.IngresoPacientePrefMananger;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.MedicamentosPrefMananger;
import com.example.sistemas.administracionmedicamentos.Utilidades.Util;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MedicamentosDetalActivity extends AppCompatActivity  {

    Paciente paciente;
    Medicamentos medicamentos;
    BodegaPaciente bodegaPaciente;

    //Variables definidas para recibir los datos enviados desde el adaptador
    String Codigo, Producto, Pos,viaAdminis,viaAdminisId,uniDosis,uniDosisId,Frecuen,conUniVent,Obser;
    float Stock,totalSuministro,totalDesacho;
    int Cantidad,Dosis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal_medicamentos);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Se obtinen el codigo del medicamento y el ingreso del paciente

        paciente = IngresoPacientePrefMananger.getIngresoPaciente(MedicamentosDetalActivity.this);
        medicamentos = MedicamentosPrefMananger.getMedicamentosPaciente(MedicamentosDetalActivity.this);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            //Se reciben los datos traidos desde el adapatador identificados con el KEY
            Codigo      = extras.getString("codigo_producto");
            Producto    = extras.getString("producto");
            Pos         = extras.getString("codigo_pos");
            viaAdminis  = extras.getString("via_administracion");
            viaAdminisId= extras.getString("via_administracion_id");
            uniDosis    = extras.getString("unidad_dosificacion");
            uniDosisId  = extras.getString("unidad_id");
            Dosis       = extras.getInt("dosis");
            Frecuen     = extras.getString("frecuencia");
            Cantidad    = extras.getInt("cantidad");
            conUniVent  = extras.getString("contenido_unidad_venta");
            Obser  = extras.getString("observacion");
            Stock    = extras.getFloat("stock");
            totalSuministro  = extras.getFloat("total_suministrado");
            totalDesacho  = extras.getFloat("total_despachado");

        }

        //Aquí se imprimen los datos recibidos en la tabla que se encuentra en el XML del activity

        TextView medicamento = (TextView)findViewById(R.id.txtMedicamentos);
        medicamento.setText(Producto + "(" + Codigo + " - " + Pos + ")");

        TextView via_Adminis = (TextView)findViewById(R.id.txtViaAdminis);
        via_Adminis.setText(viaAdminis);

        TextView dosis = (TextView)findViewById(R.id.txtDosis);
        dosis.setText( "" + Dosis + uniDosis);

        TextView frecuen = (TextView)findViewById(R.id.txtFrecuencia);
        frecuen.setText(Frecuen);

        TextView cantidad = (TextView)findViewById(R.id.txtCantidad);
        cantidad.setText(""+ Cantidad + " - " + conUniVent);

        TextView stock  = (TextView)findViewById(R.id.txtCantBodegaPaci);
        stock.setText("" + Stock);

        TextView suminstro = (TextView)findViewById(R.id.txtCantSuminstrada);
        suminstro.setText("" + totalSuministro);

        TextView despacho = (TextView)findViewById(R.id.txtCantConfirBodega);
        despacho.setText("" + totalDesacho);

        //La fila OBSERVACION se encontra invisible si el medicamento seleccionado no cuenta con una
        TableRow rowObsercacio = (TableRow)findViewById(R.id.rowObservacion);
        TextView observacion = (TextView)findViewById(R.id.txtObservacion);
        observacion.setText(Obser);

        if (Obser.isEmpty()){

            rowObsercacio.setEnabled(false);
        }else{
            rowObsercacio.setVisibility(View.VISIBLE);
        }

        




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
