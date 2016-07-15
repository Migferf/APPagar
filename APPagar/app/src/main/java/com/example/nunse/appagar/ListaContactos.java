package com.example.nunse.appagar;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nunse.appagar.adapters.ContactoAdapter;
import com.example.nunse.appagar.conf.ActualContacto;
import com.example.nunse.appagar.model.Contacto;
import com.example.nunse.appagar.persistence.DBHelper;
import com.example.nunse.appagar.persistence.PersistenceFactory;
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

        contactos = new ArrayList<>();

        contactos.addAll(PersistenceFactory.getContactoGateway().getContactos());

        this.listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(new ContactoAdapter(this, contactos));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Contacto contacto = (Contacto) listView.getAdapter().getItem(position);
                ListaContactos.this.mostrarDeudas(contacto);
            }
        });

        listView.setLongClickable(true);

        final AlertDialog.Builder alert = new AlertDialog.Builder(
                this);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final Contacto contacto = (Contacto) listView.getAdapter().getItem(position);


                alert.setTitle("Borrar");
                alert.setMessage("¿Quieres borrar este deudor?");

                alert.setNegativeButton("Jamás", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                alert.setPositiveButton("Claro", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        contacto.borrar();
                        onResume();

                    }
                });

                alert.show();

                return true;
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

        ActualContacto.set(contacto);

        startActivity(i);
    }

    public void mostrarEstadisticas(View view)
    {
        if(!aptoEstadisticas())
        {
            Toast.makeText(this, "No tienes suficientes datos para las estadísticas", Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent i = new Intent(this, Estadisticas.class);
            startActivity(i);
        }

    }

    private boolean aptoEstadisticas()
    {
        if(contactos.size() < 1)
        {
            return false;
        }

        for(Contacto c : contactos)
        {
            if(c.getDeudasNoSaldadas().size() > 0)
            {
                return true;
            }
        }
        return false;
    }

    private List<Contacto> contactos;
}
