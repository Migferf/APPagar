package com.example.nunse.appagar;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.Toast;

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

        onResume();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        this.listView = (ListView) findViewById(R.id.listView);

        List<Contacto> contactos = new ArrayList<Contacto>();

        contactos.addAll(PersistenceFactory.getContactoGateway().getContactos());

        this.listView.setAdapter(new ContactoAdapter(this, contactos));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("Sportacus", "hola");
                Contacto contacto = (Contacto) listView.getAdapter().getItem(position);
                Log.i("Sportacus", "hola 2");
                mostrarDeudas(contacto);
            }
        });

    }


    public void añadirContacto(View view)
    {
        Intent i = new Intent(this, ListaNuevoContacto.class);
        startActivity(i);
    }

    public void mostrarDeudas(Contacto contacto)
    {
        Intent i = new Intent(this, ListaDeudasContacto.class);

        i.putExtra("contacto", contacto.getNumero());

        startActivity(i);
    }
}
