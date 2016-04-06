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
import com.example.nunse.appagar.model.Contacto;

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

        this.listView.setAdapter(new ContactoAdapter(this, contactos));

    }


    public void añadirContacto(View view)
    {
        Cursor c = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                new String[]{Phone.NUMBER, Phone.DATA1},
                null,
                null,
                null
        );

        while(c.moveToNext())
        {
            String last="";
            String actual=c.getString(c.getColumnIndex(Phone.NUMBER));
            Log.i("Sportacus says", c.getString(c.getColumnIndex(Phone.NUMBER)));
            Log.i("Sportacus says",c.getString(c.getColumnIndex(Phone.DATA1)));
        }

        Intent i = new Intent(this, ListaNuevoContacto.class);
        startActivity(i);
    }
}
