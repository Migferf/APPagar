package com.example.nunse.appagar;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nunse.appagar.adapters.NuevoContactoAdapter;
import com.example.nunse.appagar.model.Contacto;
import com.example.nunse.appagar.persistence.ContactosTelefono;
import com.example.nunse.appagar.persistence.PersistenceFactory;

import java.util.ArrayList;
import java.util.List;

public class ListaNuevoContacto extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);

        this.listView = (ListView) findViewById(R.id.listViewNuevo);

        List<Contacto> contactos;

        contactos = new ContactosTelefono().getContactosTelefono();
        listView.setAdapter(new NuevoContactoAdapter(this, contactos));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contacto nuevoContacto = (Contacto) listView.getAdapter().getItem(position);

                nuevoContacto.guardar();

                finish();

            }
        });

    }
}
