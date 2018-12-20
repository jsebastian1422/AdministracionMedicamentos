package com.example.sistemas.administracionmedicamentos.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.sistemas.administracionmedicamentos.Modelos.IngMedicamentoPaciente;
import com.example.sistemas.administracionmedicamentos.Modelos.Medicamentos;
import com.example.sistemas.administracionmedicamentos.Modelos.SuministroMedicamento;
import com.example.sistemas.administracionmedicamentos.R;

import java.util.ArrayList;

public class IngresoMedicamentoAdapter extends RecyclerView.Adapter<IngresoMedicamentoAdapter.IngresoViewHolder> {

    private Context mContext;
    private ArrayList<SuministroMedicamento> lIngMedicamento;

    public IngresoMedicamentoAdapter(Context mContext, ArrayList<SuministroMedicamento> lIngMedicamento) {
        this.mContext = mContext;
        this.lIngMedicamento = lIngMedicamento;
    }

    @Override
    public IngresoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.cardview_sumins_medicamentos, null);

        return new IngresoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IngresoViewHolder holder, final int position) {

         SuministroMedicamento suministroMedicamento = lIngMedicamento.get(position);

        //Se cargan los datos

        holder.txtIdSum.setText("ID :" + suministroMedicamento.codigo_barras_iym_id);
        holder.txtCodMedic.setText("CODIGO PRODUCTO :" + suministroMedicamento.codigo_producto);
        holder.txtNomMedic.setText("PRODUCTO :" + suministroMedicamento.producto);
        holder.txtProve.setText("FABRICANTE :" + suministroMedicamento.descripcion);
        holder.txtLote.setText("LOTE :" + suministroMedicamento.lote);
        holder.txtCum.setText("CUM :" + suministroMedicamento.codigos_cum_id);
        holder.txtRegSani.setText("REGISTRO SANITARIO :" + suministroMedicamento.registro);
        holder.txtFechaVen.setText("FECHA VENCIMIENTO :" + suministroMedicamento.fecha_vencimiento);


    }

    @Override
    public int getItemCount() {
        return lIngMedicamento.size();
    }


    class IngresoViewHolder extends RecyclerView.ViewHolder {

        TextView txtCodMedic,txtIdSum,txtNomMedic,txtProve,txtLote,txtCum,txtRegSani,txtFechaVen;
        ImageButton edtSumin, dltSumin;

        public IngresoViewHolder(View itemView) {

            super(itemView);

            txtIdSum    = itemView.findViewById(R.id.txtFechaHoraSum);
            txtCodMedic    = itemView.findViewById(R.id.txtCodMedicamento);
            txtNomMedic    = itemView.findViewById(R.id.txtNombreProducto);
            txtProve    = itemView.findViewById(R.id.txtProveMedicamento);
            txtLote    = itemView.findViewById(R.id.txtLoteMedicamento);
            txtCum    = itemView.findViewById(R.id.txtCumMedicamento);
            txtRegSani    = itemView.findViewById(R.id.txtRegiSanitario);
            txtFechaVen    = itemView.findViewById(R.id.txtFechaVenci);

            edtSumin    = itemView.findViewById(R.id.editSuminstro);
            dltSumin    = itemView.findViewById(R.id.deleteSuministro);
        }
    }

}
