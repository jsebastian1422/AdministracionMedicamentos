package com.example.sistemas.administracionmedicamentos.UI;

import android.arch.core.util.Function;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sistemas.administracionmedicamentos.Adaptadores.IngresoMedicamentoAdapter;
import com.example.sistemas.administracionmedicamentos.Constantes.Error;
import com.example.sistemas.administracionmedicamentos.JSON.JSONConvert;
import com.example.sistemas.administracionmedicamentos.Modelos.BodegaPaciente;
import com.example.sistemas.administracionmedicamentos.Modelos.IngMedicamentoPaciente;
import com.example.sistemas.administracionmedicamentos.Modelos.ListModel;
import com.example.sistemas.administracionmedicamentos.Modelos.Medicamentos;
import com.example.sistemas.administracionmedicamentos.Modelos.Paciente;
import com.example.sistemas.administracionmedicamentos.Modelos.SuministroMedicamento;
import com.example.sistemas.administracionmedicamentos.Network.AsyncConexion;
import com.example.sistemas.administracionmedicamentos.Network.ResponseListener;
import com.example.sistemas.administracionmedicamentos.R;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.BodegaPacientePrefMananger;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.IngresoPacientePrefMananger;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.MedicamentosPrefMananger;
import com.example.sistemas.administracionmedicamentos.SharedPrefMananger.SuminMedicamentoPrefMananger;
import com.example.sistemas.administracionmedicamentos.Utilidades.Util;
import com.google.zxing.Result;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MedicamentosDetalActivity extends AppCompatActivity  implements ResponseListener{

    public Paciente paciente;
    public Medicamentos medicamentos;
    public RecyclerView rcvIngreMedicamento;
    public BodegaPaciente bodegaPaciente;
    private ZXingScannerView mScannerView;

   //Variables definidas para recibir los datos enviados desde el adaptador
   public String Codigo, Producto, Pos,viaAdminis,viaAdminisId,uniDosis,uniDosisId,Frecuen,conUniVent,Obser;
   public float Stock,totalSuministro,totalDesacho, CantTempo = 0;
   public int Cantidad,Dosis,numIngresos;
   ImageButton openScanner, addMedicamento, updMedicamento, dltMedicamento;
   EditText codMedicamento;

   RecyclerView rcvListMedicamento;
   IngresoMedicamentoAdapter adapter;
   ArrayList<SuministroMedicamento> lIngMedicamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detal_medicamentos);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Se estructura el xml
        codMedicamento = (EditText)findViewById(R.id.edtIngreMedicamento);
        openScanner = (ImageButton)findViewById(R.id.openScanner);
        addMedicamento = (ImageButton)findViewById(R.id.addMedicamento);
        rcvListMedicamento = (RecyclerView)findViewById(R.id.rcvListMedicamento);

        getDatosMedicamento();
        addMedicamentoSuminis();
        //Inicializa el escaneo de codigo de barras
        openScanner.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                new IntentIntegrator(MedicamentosDetalActivity.this).initiateScan();
            }
        });

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

    private void addMedicamentoSuminis(){

        addMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (CantTempo <= Stock){

                    while (CantTempo <= Stock){

                        String codProd = Codigo;
                        String ingreso = paciente.ingreso;
                        String codBarra = codMedicamento.getText().toString();

                        List<ListModel> mParametros = new ArrayList<ListModel>();
                        mParametros.add(new ListModel("ingreso", ingreso));
                        mParametros.add(new ListModel("codigo_producto", codProd));
                        mParametros.add(new ListModel("codigo_bar_prodcto", codBarra));

                        new AsyncConexion(MedicamentosDetalActivity.this, MedicamentosDetalActivity.this, SuminMedicamentoPrefMananger.getIP(MedicamentosDetalActivity.this),
                                new String[]{"Ingresando Producto", "Aguarde Por Favor..."}, mParametros).execute();

                        CantTempo ++;
                    }
                }else{
                    Toast.makeText(MedicamentosDetalActivity.this, "¡No Se Pueden Ingresar Mas Productos!", Toast.LENGTH_LONG).show();
                }

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.medic_activity_options, menu);
        return super .onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_list:
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                    Intent listMedic = new Intent();
                    listMedic.setClass(MedicamentosDetalActivity.this, ListSuminstrosActivity.class);
                    listMedic.putExtra("Codigo", Codigo);
                    startActivity(listMedic);
                    return true;
                    }
                });

                default:
                    return super.onOptionsItemSelected(item);
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

    @Override
    public void onResponse(JSONArray response) {

        lIngMedicamento = JSONConvert.getSuministroMedica(response);
        IngresoMedicamentoAdapter adapter = new IngresoMedicamentoAdapter(MedicamentosDetalActivity.this, lIngMedicamento);
        rcvListMedicamento.setHasFixedSize(true);
        rcvListMedicamento.setLayoutManager(new LinearLayoutManager(this));
        rcvListMedicamento.setAdapter(adapter);
    }

    @Override
    public void onError(int error) {

    }

}
