package com.example.nunse.appagar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nunse.appagar.conf.ActualContacto;
import com.example.nunse.appagar.model.Deuda;
import com.example.nunse.appagar.persistence.PersistenceFactory;

public class NuevaDeuda extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_deuda);
    }


    public void guardarDeuda(View view)
    {
        EditText cantidadText = (EditText) findViewById(R.id.cantidadDeuda);
        EditText descripcionText = (EditText) findViewById(R.id.descripcionDeuda);

        String dfdsf = cantidadText.getText().toString();
        if(cantidadText.getText().toString().equals(""))
        {
            Toast.makeText(this, "No has introducido una cantidad", Toast.LENGTH_LONG).show();
        }
        else
        {
            double cantidad = Double.parseDouble(cantidadText.getText().toString());
            String descripcion = descripcionText.getText().toString();

            Deuda deuda = new Deuda(cantidad, descripcion);

            PersistenceFactory.getDeudaGateway().añadirDeuda(ActualContacto.getActual(), deuda);
            finish();
        }

    }
}
