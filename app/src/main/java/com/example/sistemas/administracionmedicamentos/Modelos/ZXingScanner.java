package com.example.sistemas.administracionmedicamentos.Modelos;

import android.view.View;
import android.widget.EditText;

import com.example.sistemas.administracionmedicamentos.R;
import com.example.sistemas.administracionmedicamentos.UI.BuscarPacienteActivity;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ZXingScanner implements ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;

    @Override
    public void handleResult(Result result) {

        mScannerView.stopCamera();

    }

}
