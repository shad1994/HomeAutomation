package com.example.shadab.homeautomation;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by shadab on 12/27/2016.
 */
public class VolleySingleton {
    private  RequestQueue requestQueue;
    private static VolleySingleton sInstance=null;

    private VolleySingleton()
    {
        requestQueue= Volley.newRequestQueue(MyApplication.getsInstance().getApplicationContext());
    }

    public static VolleySingleton getsInstance(){
        if(sInstance==null){
            sInstance=new VolleySingleton();

        }
        return sInstance;
    }

    public  RequestQueue getRequestQueue(){return requestQueue;}
}
