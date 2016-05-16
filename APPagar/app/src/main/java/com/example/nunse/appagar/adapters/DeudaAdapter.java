package com.example.nunse.appagar.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.nunse.appagar.R;
import com.example.nunse.appagar.model.Deuda;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by nunse on 16/05/2016.
 */
public class DeudaAdapter extends BaseExpandableListAdapter{

    private Context context;

    private List<Deuda> deudas;

    public DeudaAdapter(Context context, List<Deuda> deudas)
    {
        this.context = context;
        this.deudas = deudas;
    }


    @Override
    public int getGroupCount() {
        return deudas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return deudas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        Deuda deuda = (Deuda) getGroup(groupPosition);

        if(convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater)
                    this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.lista_deudas, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.idDeuda);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setText(deuda.getCantidad() + "€");
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        Deuda deuda = (Deuda) getGroup(groupPosition);
        if(convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater)
                    this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.detalles_deuda,null);
        }

        TextView descripcion = (TextView) convertView.findViewById(R.id.descripcion);
        TextView fecha = (TextView) convertView.findViewById(R.id.fecha);

        descripcion.setText(deuda.getDescripcion());
        fecha.setText(deuda.getFechaDeuda().toString());

        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
