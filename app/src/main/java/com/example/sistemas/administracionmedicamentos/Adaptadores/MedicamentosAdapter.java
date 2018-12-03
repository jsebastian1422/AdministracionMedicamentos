package com.example.sistemas.administracionmedicamentos.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistemas.administracionmedicamentos.Modelos.Medicamentos;
import com.example.sistemas.administracionmedicamentos.R;

import java.util.ArrayList;
import java.util.List;

public class MedicamentosAdapter extends RecyclerView.Adapter<MedicamentosAdapter.MedicViewHolder>{

    private Context mContext;
    private ArrayList<Medicamentos> lMediamentos;
    private View.OnClickListener listener;

    public MedicamentosAdapter(Context mContext, ArrayList<Medicamentos> lMediamentos) {
        this.mContext = mContext;
        this.lMediamentos = lMediamentos;
    }

    @Override
    public MedicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.cardview_medicamentos, null);
        
        return new MedicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MedicViewHolder holder, int position) {

        Medicamentos medicamentos = lMediamentos.get(position);

        //Cargamos Los Datos

        holder.txtCodigo.setText(medicamentos.codigo_producto);
        holder.txtProducto.setText(medicamentos.producto);
        holder.txtCantidad.setText(medicamentos.cantidad);


    }

    @Override
    public int getItemCount() {
        return lMediamentos.size();
    }

    class MedicViewHolder extends RecyclerView.ViewHolder{

        //declarameos los TextView segun la lista

        TextView txtCodigo,txtProducto,txtCantidad;

        public MedicViewHolder( View itemView) {
            super(itemView);

            txtCodigo   = itemView.findViewById(R.id.txtCodigo);
            txtProducto = itemView.findViewById(R.id.txtProducto);
            txtCantidad = itemView.findViewById(R.id.txtCantidad);
        }
    }
}
