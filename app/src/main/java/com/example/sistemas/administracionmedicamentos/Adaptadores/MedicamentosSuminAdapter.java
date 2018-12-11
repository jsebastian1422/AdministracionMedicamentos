package com.example.sistemas.administracionmedicamentos.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistemas.administracionmedicamentos.Modelos.MedicamentosSuministrados;
import com.example.sistemas.administracionmedicamentos.R;

import java.util.ArrayList;
import java.util.List;

public class MedicamentosSuminAdapter extends RecyclerView.Adapter<MedicamentosSuminAdapter.listSuminisViewHolder> {

    private Context mContext;
    private MedicamentosSuministrados medicamentosSuministrados;
    ArrayList<MedicamentosSuministrados> lMedicaSumins;

    public MedicamentosSuminAdapter(Context mContext, ArrayList<MedicamentosSuministrados> lMedicaSumins) {
        this.mContext = mContext;
        this.lMedicaSumins = lMedicaSumins;
    }

    @Override
    public listSuminisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.cardview_medicamentos_suminstrados, null);
        return new listSuminisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(listSuminisViewHolder holder, final int position) {

        MedicamentosSuministrados medicamentosSuministrados = lMedicaSumins.get(position);

        //Cargamos Datos

        holder.txtFechaHora.setText(medicamentosSuministrados.fecha_realizado);
        holder.txtSuministrado.setText( medicamentosSuministrados.cantidad_suministrada);
        holder.txtDesecho.setText(medicamentosSuministrados.cantidad_perdidas);
        holder.txtusuario.setText(medicamentosSuministrados.nombre);
    }

    @Override
    public int getItemCount() {
        return lMedicaSumins.size();
    }

    class listSuminisViewHolder extends RecyclerView.ViewHolder{

        //declarameos los TextView segun la lista

        TextView txtFechaHora,txtSuministrado,txtDesecho,txtusuario;

        public listSuminisViewHolder(View itemView){

            super(itemView);

            txtFechaHora = itemView.findViewById(R.id.txtFechaHora);
            txtSuministrado = itemView.findViewById(R.id.txtSuministrado);
            txtDesecho = itemView.findViewById(R.id.txtDesecho);
            txtusuario = itemView.findViewById(R.id.txtusuario);

        }
    }
}
