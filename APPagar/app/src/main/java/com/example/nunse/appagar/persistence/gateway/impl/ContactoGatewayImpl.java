package com.example.nunse.appagar.persistence.gateway.impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.nunse.appagar.conf.DBConf;
import com.example.nunse.appagar.model.Contacto;
import com.example.nunse.appagar.persistence.DBHelper;
import com.example.nunse.appagar.persistence.gateway.ContactoGateway;
import com.example.nunse.appagar.persistence.gateway.DeudaGateway;

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
            CONTACTOS_IMAGEN = DBConf.get("CONTACTOS_IMAGEN"),
            CONTACTOS_MAYORDEUDOR = DBConf.get("CONTACTOS_MAYORDEUDOR"),
            CONTACTOS_MAYORNUMDEUDAS = DBConf.get("CONTACTOS_MAYORNUMDEUDAS"),
            CONTACTOS_MENORPORCENTAJEPAGADO = DBConf.get("CONTACTOS_MENORPORCENTAJEPAGADO");



    @Override
    public void añadirContacto(Contacto contacto) {

        SQLiteDatabase database = db.getWritableDatabase();

        database.insert(CONTACTOS, null, putValues(contacto));
        database.close();

    }

    @Override
    public void borrarContacto(Contacto contacto) {

        SQLiteDatabase database = db.getWritableDatabase();
        database.delete(CONTACTOS, CONTACTOS_NUMERO + "='" + contacto.getNumero() + "'", null);
        database.close();

    }

    @Override
    public void modificarContacto(Contacto contacto) {

        SQLiteDatabase database = db.getWritableDatabase();

        database.update(CONTACTOS, putValues(contacto),
                CONTACTOS_NUMERO + "='" + contacto.getNumero() + "'", null);

        database.close();

    }

    @Override
    public List<Contacto> getContactos() {

        List<Contacto> contactos = new ArrayList<Contacto>();

        SQLiteDatabase database = db.getReadableDatabase();

        Cursor c = database.query(CONTACTOS, null, null, null, null, null, null); //Todos los contactos
        while(c.moveToNext())
        {
            contactos.add(getValues(c));
        }
        database.close();
        return contactos;
    }

    @Override
    public Contacto getContacto(String numero) {

        Contacto contacto = null;
        SQLiteDatabase database = db.getReadableDatabase();

        Cursor c = database.query(CONTACTOS, null, CONTACTOS_NUMERO + "= ?",
                new String[]{numero}
                , null, null, null); //Todas las deudas de un contacto

        while(c.moveToNext())
        {
            contacto = getValues(c);
        }
        database.close();
        return contacto;
    }

    @Override
    public Contacto getMayorDeudor() {

       List<Contacto> contactos = getContactos();

        Contacto mayorDeudor = null;
        double deudaMayor = 0.0;

        for(Contacto c : contactos)
        {
            double deudaContacto = c.getCantidadDeudaTotal();
            if(deudaContacto > deudaMayor)
            {
                mayorDeudor = c;
                deudaMayor = deudaContacto;
            }
        }
        return mayorDeudor;
    }

    @Override
    public Contacto getMayorNumeroDeudas() {

        List<Contacto> contactos = getContactos();

        Contacto contacto = null;
        int mayorNumeroDeudas = 0;

        for(Contacto c : contactos)
        {
            int numDeudasActual = c.getDeudas().size();
            if(numDeudasActual > mayorNumeroDeudas)
            {
                contacto = c;
                mayorNumeroDeudas = numDeudasActual;
            }
        }
        return contacto;
    }

    @Override
    public Contacto getMenorPorcentajePagado() {

        List<Contacto> contactos = getContactos();

        Contacto contacto = null;
        double porcentajeImpago = 0;

        for(Contacto c : contactos)
        {
            double numDeudasTotalesActual = c.getDeudas().size();
            if(numDeudasTotalesActual < 4)
                continue; //Demasiado pocas para valorarlo
            double numDeudasSinPagarActual = c.getDeudasNoSaldadas().size();

            if(numDeudasSinPagarActual / numDeudasTotalesActual > porcentajeImpago)
            {
                contacto = c;
                porcentajeImpago = numDeudasSinPagarActual / numDeudasTotalesActual;
            }
        }
        return contacto;
    }


    /**
     * Introduce los valores de un contacto en un ContentValues para luego
     * persistirlos.
     * @param contacto
     * @return
     */
    private ContentValues putValues(Contacto contacto)
    {
        ContentValues values = new ContentValues();

        values.put(CONTACTOS_NOMBRE, contacto.getNombre());
        values.put(CONTACTOS_APELLIDOS, contacto.getApellidos());
        values.put(CONTACTOS_NUMERO, contacto.getNumero());
        values.put(CONTACTOS_IMAGEN, bitMapToBlob(contacto.getImage()));

        return values;
    }

    /**
     * Construye un contacto a partir de los valores actuales de un cursor
     * @param c
     * @return
     */
    private Contacto getValues(Cursor c)
    {
        String nombre = c.getString(c.getColumnIndex(CONTACTOS_NOMBRE));
        String apellidos = c.getString(c.getColumnIndex(CONTACTOS_APELLIDOS));
        String numero = c.getString(c.getColumnIndex(CONTACTOS_NUMERO));
        Bitmap imagen = blobToBitMap(c.getBlob(c.getColumnIndex(CONTACTOS_IMAGEN)));


        Contacto contacto = new Contacto();
        contacto.setNombre(nombre);
        contacto.setApellidos(apellidos);
        contacto.setNumero(numero);
        contacto.setImage(imagen);

        return contacto;
    }

    /**
     * SQLite no permite guardar Bitmap en su base de datos
     * pero si el tipo Blob, este método transforma una imagen
     * a un array de bytes (Blob).
     *
     * @param image
     * @return
     */
    private byte[] bitMapToBlob(Bitmap image) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, bos);
        return bos.toByteArray();
    }

    /**
     * SQLite sólo permite guardar imágenes como arrays de bytes,
     * este método transforma ese tipo de datos en un Bitmap.
     * @param byteImagen
     * @return
     */
    private Bitmap blobToBitMap(byte[] byteImagen)
    {
        return BitmapFactory.decodeByteArray(byteImagen, 0, byteImagen.length);
    }

    @Override
    public void establecerDB(DBHelper db) {
        this.db = db;
    }

    private DBHelper db;
}
