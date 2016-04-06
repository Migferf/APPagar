package com.example.nunse.appagar;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.nunse.appagar.adapters.NuevoContactoAdapter;
import com.example.nunse.appagar.model.Contacto;

import java.util.ArrayList;
import java.util.List;

public class ListaNuevoContacto extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        this.listView = (ListView) findViewById(R.id.listViewNuevo);

        final List<Contacto> contactos = new ArrayList<Contacto>();

        final Contacto contacto = new Contacto();
        contacto.setNombre("Caca");
        contacto.setImage(1);
        contacto.setNumero(123456);
        contactos.add(contacto);

        listView.setAdapter(new NuevoContactoAdapter(this, contactos));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contacto nuevoContacto = (Contacto) listView.getAdapter().getItem(position);

                Log.i("Sportacus", nuevoContacto.getNombre());
            }
        });

    }
}
