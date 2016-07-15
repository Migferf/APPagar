package com.example.nunse.appagar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.nunse.appagar.adapters.DeudaAdapter;
import com.example.nunse.appagar.conf.ActivitySaver;
import com.example.nunse.appagar.conf.ActualContacto;
import com.example.nunse.appagar.model.Comentario;
import com.example.nunse.appagar.model.Contacto;
import com.example.nunse.appagar.model.Deuda;


import java.util.ArrayList;
import java.util.List;

public class ListaDeudasContacto extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_deudas_contacto);
        contacto = ActualContacto.getActual();
        ActivitySaver.setSaved(this);


        onResume();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        establecerDatosContacto();

        List<Deuda> deudas = new ArrayList<>();

        deudas.addAll(contacto.getDeudasNoSaldadas());

        Log.i("Sportacus", "Deudas: " + deudas.size());

        this.listView = (ExpandableListView) findViewById(R.id.exp);

        listView.setAdapter(new DeudaAdapter(this, deudas));

    }

    private void establecerDatosContacto()
    {
        TextView nombre = (TextView) this.findViewById(R.id.deudor);
        TextView apellidos = (TextView) this.findViewById(R.id.apellidos);
        TextView numDeudas = (TextView) this.findViewById(R.id.numDeudas);
        TextView numSaldadas = (TextView) this.findViewById(R.id.numSaldadas);
        TextView cantidadPagar = (TextView) this.findViewById(R.id.cantidadPagar);
        TextView comentario = (TextView) this.findViewById(R.id.comentario);

        nombre.setText(contacto.getNombre());
        apellidos.setText(contacto.getApellidos());
        numDeudas.setText("Nº deudas: " + contacto.getDeudas().size());
        numSaldadas.setText("Nº deudas saldadas: " +
                + (contacto.getDeudas().size() - contacto.getDeudasNoSaldadas().size()));
        cantidadPagar.setText("Cantidad total a pagar: " + contacto.getCantidadDeudaTotal());
        comentario.setText("Nivel: "
                + Comentario.getInstance().get(contacto.getCantidadDeudaTotal()));

    }
    public void añadirDeuda(View view)
    {
        Intent i = new Intent(this, NuevaDeuda.class);
        startActivity(i);
    }

    private Contacto contacto;
    private ExpandableListView listView;
}
