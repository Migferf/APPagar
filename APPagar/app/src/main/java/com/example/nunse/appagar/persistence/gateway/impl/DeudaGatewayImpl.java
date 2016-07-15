package com.example.nunse.appagar.persistence.gateway.impl;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.example.nunse.appagar.conf.DBConf;
import com.example.nunse.appagar.model.Contacto;
import com.example.nunse.appagar.model.Deuda;
import com.example.nunse.appagar.persistence.DBHelper;
import com.example.nunse.appagar.persistence.gateway.DeudaGateway;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nunse on 13/04/2016.
 */
public class DeudaGatewayImpl implements DeudaGateway{

    private static final String DEUDAS = DBConf.get("DEUDAS"),
                                DEUDAS_ID = DBConf.get("DEUDAS_ID"),
                                DEUDAS_CANTIDAD = DBConf.get("DEUDAS_CANTIDAD"),
                                DEUDAS_FECHA = DBConf.get("DEUDAS_FECHA"),
                                DEUDAS_SALDADA = DBConf.get("DEUDAS_SALDADA"),
                                DEUDAS_DEUDOR = DBConf.get("DEUDAS_DEUDOR"),
                                DEUDAS_DESCRIPCION = DBConf.get("DEUDAS_DESCRIPCION"),
                                DEUDAS_CANTIDAD_RESTANTE = DBConf.get("DEUDAS_CANTIDAD_RESTANTE");

    @Override
    public void añadirDeuda(Contacto contacto, Deuda deuda) {

        SQLiteDatabase database = db.getWritableDatabase();

        database.insert(DEUDAS, null, putValues(contacto, deuda));
        database.close();


    }

    @Override
    public void borrarDeuda(Contacto contacto, Deuda deuda) {

        SQLiteDatabase database = db.getWritableDatabase();

        database.delete(DEUDAS, DEUDAS_ID + "=" + deuda.getID(), null);
        database.close();
    }

    @Override
    public void modificarDeuda(Contacto contacto, Deuda deuda) {

        SQLiteDatabase database = db.getWritableDatabase();

        database.update(DEUDAS, putValues(contacto, deuda), DEUDAS_ID + "=" + deuda.getID(), null);
        database.close();
    }

    @Override
    public List<Deuda> getDeudas(Contacto contacto) {

        List<Deuda> deudas = new ArrayList<Deuda>();

        SQLiteDatabase database = db.getReadableDatabase();

        Cursor c = database.query(DEUDAS, null, DEUDAS_DEUDOR + "= ?",
                new String[]{contacto.getNumero()}
                , null, null, null); //Todas las deudas de un contacto

        while(c.moveToNext())
        {
            deudas.add(getValues(c));
        }
        database.close();
        return deudas;
    }

    private ContentValues putValues(Contacto contacto, Deuda deuda)
    {
        ContentValues values = new ContentValues();

        values.put(DEUDAS_CANTIDAD, deuda.getCantidad());
        values.put(DEUDAS_CANTIDAD_RESTANTE, deuda.getCantidadRestante());
        values.put(DEUDAS_FECHA, deuda.getFechaDeuda().getTime() + "");
        values.put(DEUDAS_SALDADA, deuda.isSaldada() ? 1 : 0);
        values.put(DEUDAS_DESCRIPCION, deuda.getDescripcion());
        values.put(DEUDAS_DEUDOR, contacto.getNumero());

        return values;
    }

    private Deuda getValues(Cursor c)
    {
        int id = c.getInt(c.getColumnIndex(DEUDAS_ID));
        double cantidad = c.getDouble(c.getColumnIndex(DEUDAS_CANTIDAD));
        double cantidadRestante = c.getDouble(c.getColumnIndex(DEUDAS_CANTIDAD_RESTANTE));
        Date fecha = new Date(Long.parseLong(c.getString(c.getColumnIndex(DEUDAS_FECHA))));
        String descripcion = c.getString(c.getColumnIndex(DEUDAS_DESCRIPCION));
        boolean saldada = c.getInt(c.getColumnIndex(DEUDAS_SALDADA)) ==1 ? true : false;

        Deuda deuda = new Deuda(id, cantidad, cantidadRestante, fecha, descripcion, saldada);

        return deuda;
    }

    @Override
    public void establecerDB(DBHelper db) {
        this.db = db;
    }

    private DBHelper db;
}
