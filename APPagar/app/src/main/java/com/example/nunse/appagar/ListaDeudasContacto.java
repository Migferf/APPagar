package com.example.nunse.appagar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import com.example.nunse.appagar.adapters.DeudaAdapter;
import com.example.nunse.appagar.model.Contacto;
import com.example.nunse.appagar.model.Deuda;
import com.example.nunse.appagar.persistence.PersistenceFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaDeudasContacto extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_deudas_contacto);



        onResume();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        contacto = ActualContacto.getActual();

        List<Deuda> deudas = new ArrayList<>();

        deudas.addAll(contacto.getDeudas());

        Log.i("Sportacus", "Deudas: " + deudas.size());

        this.listView = (ExpandableListView) findViewById(R.id.exp);

        listView.setAdapter(new DeudaAdapter(this, deudas));

    }


    private Contacto contacto;
    private ExpandableListView listView;
}
