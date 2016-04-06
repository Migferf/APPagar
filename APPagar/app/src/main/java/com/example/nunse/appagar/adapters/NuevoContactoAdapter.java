package com.example.nunse.appagar.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nunse.appagar.R;
import com.example.nunse.appagar.model.Contacto;

import java.util.List;

/**
 * Created by nunse on 05/04/2016.
 */
public class NuevoContactoAdapter extends BaseAdapter{

    private Context context;
    private List<Contacto> contactos;

    public NuevoContactoAdapter(Context context, List<Contacto> contactos)
    {
        this.context = context;
        this.contactos = contactos;
    }
    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Object getItem(int position) {
        return contactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.lista_nuevo_contacto, parent, false);
        }

        TextView idNombreContacto = (TextView) rowView.findViewById(R.id.idNuevoNombre);
        ImageView idImagen = (ImageView) rowView.findViewById(R.id.idNuevoImagenContacto);
        TextView idNumero = (TextView) rowView.findViewById(R.id.idNuevoNumero);

        Contacto contacto = this.contactos.get(position);

        Log.i("Sportacus", contacto.getNombre());
        Log.i("Sportacus", ""+contacto.getNumero());
        Log.i("Sportacus", ""+idNumero);
        idNombreContacto.setText(contacto.getNombre());

        idImagen.setImageResource(contacto.getImage());

        idNumero.setText("Tlf: " + contacto.getNumero());

        return rowView;
    }
}
