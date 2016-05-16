package com.example.nunse.appagar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.nunse.appagar.model.Contacto;

public class ListaDeudasContacto extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_deudas_contacto);

        String numero = (String) getIntent().getExtras().get("contacto");
        Log.i("Sportacus", contacto.toString());
    }


    private Contacto contacto;
}
