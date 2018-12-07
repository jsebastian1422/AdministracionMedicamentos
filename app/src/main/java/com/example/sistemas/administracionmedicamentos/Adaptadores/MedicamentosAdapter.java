package com.example.sistemas.administracionmedicamentos.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistemas.administracionmedicamentos.Modelos.Medicamentos;
import com.example.sistemas.administracionmedicamentos.R;
import com.example.sistemas.administracionmedicamentos.UI.MedicamentosDetalActivity;

import java.util.ArrayList;

public class MedicamentosAdapter extends RecyclerView.Adapter<MedicamentosAdapter.MedicViewHolder>{

    private Context mContext;
    private Medicamentos medicamentos;
    private ArrayList<Medicamentos> lMediamentos;
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
    public void onBindViewHolder(MedicViewHolder holder, final int position) {

        Medicamentos medicamentos = lMediamentos.get(position);

        //Cargamos Los Datos

        holder.txtCodigo.setText(medicamentos.codigo_producto);
        holder.txtProducto.setText(medicamentos.producto);
        holder.txtCantidad.setText("" + medicamentos.cantidad);

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

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    Medicamentos medic = lMediamentos.get(getAdapterPosition());
                    //Bundle b = new Bundle();
                    //b.putString("codigo_producto", medic.codigo_producto);

                    Intent MedicaDatalle = new Intent(view.getContext(), MedicamentosDetalActivity.class);

                    //Envia los datos del Objeto selccionado estos seran referenciados por el KEY "producto"

                    MedicaDatalle.putExtra("producto", medic.producto);
                    MedicaDatalle.putExtra("codigo_producto", medic.codigo_producto);
                    MedicaDatalle.putExtra("codigo_pos", medic.codigo_pos);

                    //Envia la via de administracion con su value
                    MedicaDatalle.putExtra("via_administracion", medic.via_administracion);
                    MedicaDatalle.putExtra("via_administracion_id", medic.via_administracion_id);

                    //Envia la unidad de dosificacion y su id
                    MedicaDatalle.putExtra("unidad_dosificacion", medic.unidad_dosificacion);
                    MedicaDatalle.putExtra("unidad_id", medic.unidad_id);

                    //Envia la dosis a aplicar y la frecuencia
                    MedicaDatalle.putExtra("dosis", medic.dosis);
                    MedicaDatalle.putExtra("frecuencia", medic.frecuencia);

                    //Envia la cantidad por unidad
                    MedicaDatalle.putExtra("cantidad", medic.cantidad);
                    MedicaDatalle.putExtra("contenido_unidad_venta", medic.contenido_unidad_venta);

                    //Envia la observacion
                    MedicaDatalle.putExtra("observacion", medic.observacion);

                    //Envia la bodega del paciente
                    MedicaDatalle.putExtra("stock", medic.stock);
                    MedicaDatalle.putExtra("total_suministrado", medic.total_suministrado);
                    MedicaDatalle.putExtra("total_despachado", medic.total_despachado);

                    //MedicaDatalle.putExtra("Codigo", txtCodigo.getText());
                    //view.getContext().startActivity(MedicaDatalle);
                    view.getContext().startActivity(MedicaDatalle);
                }
            });

        }
    }
}
