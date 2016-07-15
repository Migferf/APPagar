package com.example.nunse.appagar.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nunse.appagar.R;
import com.example.nunse.appagar.model.Comentario;
import com.example.nunse.appagar.model.Contacto;

import java.util.List;

/**
 * Created by nunse on 31/03/2016.
 */
public class ContactoAdapter extends BaseAdapter{

    private Context context;
    private List<Contacto> contactos;

    public ContactoAdapter(Context context, List<Contacto> contactos)
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
            rowView = inflater.inflate(R.layout.list_contacto, parent, false);
        }

        TextView idContacto = (TextView) rowView.findViewById(R.id.idContacto);
        TextView idDeuda = (TextView) rowView.findViewById(R.id.idDeuda);
        TextView idNivel = (TextView) rowView.findViewById(R.id.idNivel);

        Contacto contacto = this.contactos.get(position);

        idContacto.setText(contacto.getNombre());
        idDeuda.setText("Deuda total: " + contacto.getCantidadDeudaTotal());
        idNivel.setText("Nivel: " + Comentario.getInstance().get(contacto.getCantidadDeudaTotal()));

        return rowView;
    }
}
