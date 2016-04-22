package com.example.nunse.appagar.persistence.gateway.impl;

import android.content.ContentValues;
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

    private static final String DEUDAS = DBConf.get("DEUDAS");
    private static final String DEUDAS_ID = DBConf.get("DEUDAS_ID");
    private static final String DEUDAS_CANTIDAD = DBConf.get("DEUDAS_CANTIDAD");
    private static final String DEUDAS_FECHA = DBConf.get("DEUDAS_FECHA");
    private static final String DEUDAS_SALDADA = DBConf.get("DEUDAS_SALDADA");
    private static final String DEUDAS_DEUDOR = DBConf.get("DEUDAS_DEUDOR");

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
        values.put(DEUDAS_FECHA, (int) deuda.getFechaDeuda().getTime());
        values.put(DEUDAS_SALDADA, deuda.isSaldada() ? 1 : 0);
        values.put(DEUDAS_DEUDOR, contacto.getNumero());

        return values;
    }

    private Deuda getValues(Cursor c)
    {
        int id = c.getInt(c.getColumnIndex(DEUDAS_ID));
        double cantidad = c.getDouble(c.getColumnIndex(DEUDAS_CANTIDAD));
        Date fecha = new Date(c.getInt(c.getColumnIndex(DEUDAS_FECHA)));
        String numeroDeudor = c.getString(c.getColumnIndex(DEUDAS_DEUDOR));

        Contacto deudor = new Contacto();
        deudor.setNumero(numeroDeudor);

        Deuda deuda = new Deuda(id, cantidad, fecha, deudor);

        return deuda;
    }

    @Override
    public void establecerDB(DBHelper db) {
        this.db = db;
    }

    private DBHelper db;
}
