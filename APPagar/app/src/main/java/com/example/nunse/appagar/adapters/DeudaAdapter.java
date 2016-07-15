package com.example.nunse.appagar.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.nunse.appagar.ListaDeudasContacto;
import com.example.nunse.appagar.R;
import com.example.nunse.appagar.SaldarDeuda;
import com.example.nunse.appagar.conf.ActivitySaver;
import com.example.nunse.appagar.conf.ActualContacto;
import com.example.nunse.appagar.model.Deuda;
import com.example.nunse.appagar.persistence.PersistenceFactory;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by nunse on 16/05/2016.
 */
public class DeudaAdapter extends BaseExpandableListAdapter{

    private Context _context;
    private List<Deuda> deudas; // header titles
    // child data in format of header title, child title

    public DeudaAdapter(Context context, List<Deuda> listDataHeader) {
        this._context = context;
        this.deudas = listDataHeader;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return null; //No se usa
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Deuda deuda = (Deuda) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.detalles_deuda, null);
        }

        TextView txDescripcion = (TextView) convertView
                .findViewById(R.id.descripcion);

        TextView txFecha = (TextView) convertView
                .findViewById(R.id.fecha);

        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        txFecha.setText(formatter.format(deuda.getFechaDeuda()));
        txDescripcion.setText(deuda.getDescripcion());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.deudas.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.deudas.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        final Deuda deuda = (Deuda) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.lista_deudas, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.idDeuda);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(deuda.getCantidadRestante() +" €");

        Button button = (Button) convertView.findViewById(R.id.saldar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ActivitySaver.getSaved(), SaldarDeuda.class);
                ActualContacto.getActual().setDeudaActual(deuda);
                ActivitySaver.getSaved().finish();
                ActivitySaver.getSaved().startActivity(i);
            }
        });
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
