package com.example.nunse.appagar.persistence.gateway.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import com.example.nunse.appagar.conf.DBConf;
import com.example.nunse.appagar.model.Contacto;
import com.example.nunse.appagar.persistence.DBHelper;
import com.example.nunse.appagar.persistence.gateway.ContactoGateway;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nunse on 13/04/2016.
 */
public class ContactoGatewayImpl implements ContactoGateway {

    private static final String
            CONTACTOS = DBConf.get("CONTACTOS"),
            CONTACTOS_NOMBRE = DBConf.get("CONTACTOS_NOMBRE"),
            CONTACTOS_APELLIDOS = DBConf.get("CONTACTOS_APELLIDOS"),
            CONTACTOS_NUMERO = DBConf.get("CONTACTOS_NUMERO"),
            CONTACTOS_IMAGEN = DBConf.get("CONTACTOS_IMAGEN");



    @Override
    public void añadirContacto(Contacto contacto) {

        SQLiteDatabase database = db.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CONTACTOS_NOMBRE, contacto.getNombre());
        values.put(CONTACTOS_APELLIDOS, contacto.getApellidos());
        values.put(CONTACTOS_NUMERO, contacto.getNumero());
        values.put(CONTACTOS_IMAGEN, bitMapToBlob(contacto.getImage()));

        database.insert("Contactos", null, values);
        database.close();

    }

    @Override
    public void borrarContacto(Contacto contacto) {

    }

    @Override
    public void modificarContacto(Contacto contacto) {

    }

    @Override
    public List<Contacto> getContactos() {

        List<Contacto> contactos = new ArrayList<Contacto>();

        SQLiteDatabase database = db.getReadableDatabase();

        Cursor c = database.query(CONTACTOS, null, null, null, null, null, null);

        while(c.moveToNext())
        {
            Contacto contacto = new Contacto();
            contacto.setNombre(c.getString(c.getColumnIndex(CONTACTOS_NOMBRE)));

            contactos.add(contacto);
        }

        return contactos;
    }

    @Override
    public void establecerDB(DBHelper db) {
        this.db = db;
    }



    /**
     * SQLite no permite guardar Bitmap en su base de datos
     * pero si el tipo Blob, este método transforma una imagen
     * a un array de bytes (Blob
     *
     * @param image
     * @return
     */
    private byte[] bitMapToBlob(Bitmap image) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, bos);
        return bos.toByteArray();
    }

    private DBHelper db;
}
