package com.example.sistemas.administracionmedicamentos.Adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sistemas.administracionmedicamentos.Modelos.Medicamentos;
import com.example.sistemas.administracionmedicamentos.R;

import java.util.List;

public class MedicRecyclerViewAdapter /*extends RecyclerView.Adapter<MedicRecyclerViewAdapter.MedicViewHolder> */{

   /* private Context mContexto;
    private List<Medicamentos> lMedicamentos;

    public MedicRecyclerViewAdapter(Context mContexto, List<Medicamentos> lMedicamentos){

        this.mContexto = mContexto;
        this.lMedicamentos = lMedicamentos;
    }

    @Override
    public MedicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mContexto);
        View view = inflater.inflate(R.layout.cardview_listmedicamentos, null);
        return new MedicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MedicViewHolder holder, int position) {

        Medicamentos medicamentos = lMedicamentos.get(position);

        holder.txtCodigo.setText(medicamentos.getCodigo_producto());
        holder.txtMedicamentos.setText(medicamentos.getProducto());
        holder.txtCantidad.setText(medicamentos.getCantidad());

    }

    @Override
    public int getItemCount() {
        return lMedicamentos.size();
    }

    class MedicViewHolder extends RecyclerView.ViewHolder{

        TextView txtCodigo, txtMedicamentos, txtCantidad;

        public MedicViewHolder(View itemView) {
            super(itemView);

            //Se enlazan los controles java con los widgets xml
            txtCodigo = itemView.findViewById(R.id.txtCodigo);
            txtMedicamentos = itemView.findViewById(R.id.txtMedicamentos);
            txtCantidad = itemView.findViewById(R.id.txtCantidad);
        }
    }*/
}
