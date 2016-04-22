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
import com.example.nunse.appagar.persistence.DBHelper;
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contacto contacto = (Contacto) listView.getAdapter().getItem(position);

                contacto.setNombre("NuevoNombre");
                PersistenceFactory.getContactoGateway().modificarContacto(contacto);

                onResume();

            }
        });
        onResume();

    }

    @Override
    public void onResume()
    {
        List<Contacto> contactos = PersistenceFactory.getContactoGateway().getContactos();
        this.listView.setAdapter(new ContactoAdapter(this, contactos));
        super.onResume();

    }


    public void añadirContacto(View view)
    {
        Intent i = new Intent(this, ListaNuevoContacto.class);
        startActivity(i);
    }
}
