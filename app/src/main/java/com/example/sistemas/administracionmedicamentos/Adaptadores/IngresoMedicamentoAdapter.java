package com.example.sistemas.administracionmedicamentos.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.sistemas.administracionmedicamentos.Modelos.IngMedicamentoPaciente;

import java.util.ArrayList;

public class IngresoMedicamentoAdapter extends RecyclerView.Adapter<IngresoMedicamentoAdapter.IngresoViewHolder> {

    private Context mContext;
    private ArrayList<IngMedicamentoPaciente> lIngMedicamento;

    @Override
    public IngresoMedicamentoAdapter.IngresoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull IngresoMedicamentoAdapter.IngresoViewHolder ingresoViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class IngresoViewHolder extends RecyclerView.ViewHolder {

        public IngresoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}