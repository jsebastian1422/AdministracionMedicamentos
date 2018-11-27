package com.example.sistemas.administracionmedicamentos.VolleySingleton;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private VolleySingleton(Context contexto){

        mCtx = contexto;
        requestQueue = getRequestQueue();
    }

    public static synchronized VolleySingleton getmInstance(Context contexto){

        if (mInstance == null) {
            mInstance = new VolleySingleton(contexto);
        }

        return mInstance;
    }

    private RequestQueue getRequestQueue() {

        if (requestQueue == null){

            // getApplicationContext() evita la perdida de clave
            //Activity or BroadcastReceiver

            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }

        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){

        getRequestQueue().add(request);
    }
}
