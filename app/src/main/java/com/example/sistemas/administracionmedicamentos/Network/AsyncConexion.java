package com.example.sistemas.administracionmedicamentos.Network;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.sistemas.administracionmedicamentos.Constantes.Error;
import com.example.sistemas.administracionmedicamentos.Modelos.ListModel;
import com.example.sistemas.administracionmedicamentos.UI.LoginActivity;
import com.example.sistemas.administracionmedicamentos.Utilidades.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class AsyncConexion extends AsyncTask<String,Void,Void> {

    private ResponseListener mResponseListener;
    private ProgressDialog mDialog;
    private String mContent;
    private String Servidor;
    private Context mContexto;
    private List<ListModel>mParametros;
    private String[] mMensaje;

    public AsyncConexion(Context Contexto, ResponseListener mResponseListener, String Servidor, String[] Mensaje, List<ListModel> mParametros){

        this.mResponseListener = mResponseListener;
        this.mContexto = Contexto;
        this.Servidor = Servidor;
        this.mMensaje = Mensaje;
        this.mParametros = mParametros;
    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
        if (Util.EstadoRed(mContexto)){
            mDialog = ProgressDialog.show(mContexto,mMensaje[0], mMensaje[1]); // 1.Titulo 2.Mensaje
            mDialog.isIndeterminate();
        }else {
            this.cancel(true);
            mResponseListener.onError(Error.ERROR_NO_NETWORK);
        }

    }

    @Override
    protected Void doInBackground(String... params) {

        try{
            mContent = Conexion.Conexion(mContexto,Servidor,mParametros);
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);

        mDialog.dismiss();

        if (mResponseListener != null && mContent !=null){
            if (!mContent.equals(""))
                try {
                    JSONArray data = new JSONArray(mContent);
                    mResponseListener.onResponse(data);
                } catch (JSONException e){
                    e.printStackTrace();
                    mResponseListener.onError(Error.ERROR_RESPONSE_NO_VALID); //RESPUESTA NO VALID
                }
            else { //RESPUESTA NO VALIDA
                mResponseListener.onError(Error.ERROR_RESPONSE_NO_VALID);
            }
        }else {
            mResponseListener.onError(Error.ERROR_NO_RESPONSE_SERVER);
        }

    }
}
