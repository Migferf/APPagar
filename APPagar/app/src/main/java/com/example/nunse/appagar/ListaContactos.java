package com.example.nunse.appagar;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.provider.ContactsContract.CommonDataKinds.Phone;

import com.example.nunse.appagar.adapters.ContactoAdapter;
import com.example.nunse.appagar.conf.DBConf;
import com.example.nunse.appagar.model.Contacto;
import com.example.nunse.appagar.persistence.PersistenceFactory;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

public class ListaContactos extends AppCompatActivity {


    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contactos);

        this.listView = (ListView) findViewById(R.id.listView);

        List<Contacto> contactos = new ArrayList<Contacto>();

        contactos.add(new Contacto("Manuel José", "Ramirez"));
        contactos.add(new Contacto("Almiro", "Mirolá"));
        contactos.add(new Contacto("Gutierrez", "Gutierrez"));
        contactos.add(new Contacto("Sportacus", "Sporti"));
        contactos.add(new Contacto("La Manuela", "Manolera"));
        contactos.add(new Contacto("Manuel José", "Ramirez"));
        contactos.add(new Contacto("Almiro", "Mirolá"));
        contactos.add(new Contacto("Gutierrez", "Gutierrez"));
        contactos.add(new Contacto("Sportacus", "Sporti"));
        contactos.add(new Contacto("La Manuela", "Manolera"));
        contactos.add(new Contacto("Manuel José", "Ramirez"));
        contactos.add(new Contacto("Almiro", "Mirolá"));
        contactos.add(new Contacto("Gutierrez", "Gutierrez"));
        contactos.add(new Contacto("Sportacus", "Sporti"));
        contactos.add(new Contacto("La Manuela", "Manolera"));
        contactos.add(new Contacto("Manuel José", "Ramirez"));
        contactos.add(new Contacto("Almiro", "Mirolá"));
        contactos.add(new Contacto("Gutierrez", "Gutierrez"));
        contactos.add(new Contacto("Sportacus", "Sporti"));
        contactos.add(new Contacto("La Manuela", "Manolera"));


        contactos.addAll(PersistenceFactory.getContactoGateway().getContactos());

        this.listView.setAdapter(new ContactoAdapter(this, contactos));

    }

    @Override
    public void onResume()
    {
        super.onResume();

    }


    public void añadirContacto(View view)
    {
        Intent i = new Intent(this, ListaNuevoContacto.class);
        startActivity(i);
    }
}
