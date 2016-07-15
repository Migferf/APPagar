package com.example.nunse.appagar.persistence;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.ArraySet;
import android.util.Log;

import com.example.nunse.appagar.conf.APPagar;
import com.example.nunse.appagar.R;
import com.example.nunse.appagar.model.Contacto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by nunse on 06/04/2016.
 */
public class ContactosTelefono {

    public List<Contacto> getContactosTelefono()
    {
        List<Contacto> contactosTelefono = new ArrayList<Contacto>();
        Set<Contacto> contactosSinRepetir = new HashSet<>();

        Cursor c = APPagar.getContext().getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                new String[]{
                        Phone.DISPLAY_NAME,
                        Phone.NUMBER,
                        ContactsContract.Contacts.Photo.PHOTO, }, //Foto del contacto
                null,
                null,
                null
        );

        while(c.moveToNext())
        {
            String nombre = c.getString(c.getColumnIndex(Phone.DISPLAY_NAME));

            String numero = c.getString(c.getColumnIndex(Phone.NUMBER));

            if(numero == null || numero=="" || numero.contains("gprofile")
                    || numero.contains("whatsapp") || numero.toCharArray().length < 3
                    || !Character.isDigit(numero.charAt(0)))
                continue;
            Bitmap photo = getBitmapPhoto(c.getBlob(c.getColumnIndex(ContactsContract.Contacts.Photo.PHOTO)));

            if(nombre.equals(numero)) //Contacto duplicado en la memoria del teléfono
            {
                continue;
            }
            Contacto nuevoContacto = new Contacto();

            nuevoContacto.setNombre(nombre);
            nuevoContacto.setNumero(numero);
            nuevoContacto.setImage(photo);

            contactosSinRepetir.add(nuevoContacto);
        }
        contactosTelefono.addAll(contactosSinRepetir);
        return contactosTelefono;

    }

    public Bitmap getBitmapPhoto(byte[] photo) {

        //Para solucionar problemas de memoria :(
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 2;
        options.inJustDecodeBounds = false;
        options.inTempStorage = new byte[16 * 1024];

        Bitmap bitmapPhoto = BitmapFactory.decodeResource(
                APPagar.getContext().getResources(), R.drawable.photo, options);
        bitmapPhoto = Bitmap.createScaledBitmap(bitmapPhoto, 400, 300, false);

        if(photo != null)
        {
            bitmapPhoto = BitmapFactory.decodeByteArray(photo, 0, photo.length);
        }
        return bitmapPhoto;
    }
}
