package com.example.sistemas.administracionmedicamentos.UI;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MedicamentosDetalActivity extends AppCompatActivity  implements ResponseListener{

    public Paciente paciente;
    public Medicamentos medicamentos;
    public RecyclerView rcvIngreMedicamento;
    public BodegaPaciente bodegaPaciente;
    private ZXingScannerView mScannerView;

   //Variables definidas para recibir los datos enviados desde el adaptador
   public String Codigo, Producto, Pos,viaAdminis,viaAdminisId,uniDosis,uniDosisId,Frecuen,conUniVent,Obser;
   public float Stock,totalSuministro,totalDesacho;
   public int Cantidad,Dosis,numIngresos;
   ImageButton openScanner;
   EditText codMedicamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal_medicamentos);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        getDatosMedicamento();

        //Se estructura el xml
        codMedicamento = (EditText)findViewById(R.id.edtIngreMedicamento);
        openScanner = (ImageButton)findViewById(R.id.openScanner);

        //Inicializa el escaneo de codigo de barras
        openScanner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                new IntentIntegrator(MedicamentosDetalActivity.this).initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        final IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        handleResult(scanResult);
    }

    private void handleResult(IntentResult scanResult){

        if (scanResult != null){

            updateTextView(scanResult.getContents(), scanResult.getFormatName());
        }else {
            Toast.makeText(this, "No Se Detecto Codigo De Barras", Toast.LENGTH_SHORT).show();
        }
    }

    /*Metodo que imprime el cosigo de barras recibido*/
    private void updateTextView(String scan_result, String scan_result_format){

        codMedicamento.setText(scan_result);
    }

    public void getDatosMedicamento(){

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

    /*Metodo que realiiza el ingreso manual del medicamento*/
    public void ingresoManualMedicamento(){

        ImageButton ingresoManualMedic = (ImageButton)findViewById(R.id.addMedicamento);
        codMedicamento = (EditText)findViewById(R.id.edtIngreMedicamento);

        numIngresos = 0;
        /*Aqui se obtine el codigo ingresado de manera manual y una vez presionado el boton de ingreso se realizara la busqueda del paciente*/

        ingresoManualMedic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (numIngresos <= Cantidad){

                }
            }
        });
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

    @Override
    public void onResponse(JSONArray response) {

    }

    @Override
    public void onError(int error) {

    }

}
