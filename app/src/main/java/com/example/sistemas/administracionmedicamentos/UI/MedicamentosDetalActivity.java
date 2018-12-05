package com.example.sistemas.administracionmedicamentos.UI;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.sistemas.administracionmedicamentos.Modelos.Medicamentos;
import com.example.sistemas.administracionmedicamentos.Modelos.Paciente;
import com.example.sistemas.administracionmedicamentos.R;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.IngresoPacientePrefMananger;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.MedicamentosPrefMananger;

public class MedicamentosDetalActivity extends AppCompatActivity {

    Paciente paciente;
    Medicamentos medicamentos;

    //Variables definidas para recibir los datos enviados desde el adaptador
    String Codigo, Producto, Pos,viaAdminis,viaAdminisId,uniDosis,uniDosisId,Frecuen,Dosis,Cantidad,conUniVent,Obser;
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
            Codigo      = extras.getString("producto");
            Producto    = extras.getString("codigo_producto");
            Pos         = extras.getString("codigo_pos");
            viaAdminis  = extras.getString("via_administracion");
            viaAdminisId= extras.getString("via_administracion_id");
            uniDosis    = extras.getString("unidad_dosificacion");
            uniDosisId  = extras.getString("unidad_id");
            Dosis       = extras.getString("dosis");
            Frecuen     = extras.getString("frecuencia");
            Cantidad    = extras.getString("cantidad");
            conUniVent  = extras.getString("contenido_unidad_venta");
            Obser  = extras.getString("observacion");
        }

        //Aquí se imprimen los datos recibidos en la tabla que se encuentra en el XML del activity
        /*TextView ingreso = (TextView)findViewById(R.id.txtIngresos);
        ingreso.setText(paciente.ingreso);*/

        TextView medicamento = (TextView)findViewById(R.id.txtMedicamentos);
        medicamento.setText(Producto + "(" + Codigo + " - " + Pos + ")");

        TextView via_Adminis = (TextView)findViewById(R.id.txtViaAdminis);
        via_Adminis.setText(viaAdminis);

        TextView dosis = (TextView)findViewById(R.id.txtDosis);
        dosis.setText( Dosis + uniDosis);

        TextView frecuen = (TextView)findViewById(R.id.txtFrecuencia);
        frecuen.setText(Frecuen);

        TextView cantidad = (TextView)findViewById(R.id.txtCantidad);
        cantidad.setText(Cantidad + " - " + conUniVent);

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
