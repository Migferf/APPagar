package com.example.nunse.appagar;

import com.example.nunse.appagar.model.Contacto;

/**
 * Created by nunse on 21/05/2016.
 */
public class ActualContacto {


    private static Contacto actualContacto;


    public static Contacto getActual()
    {
        return actualContacto;
    }

    public static void set(Contacto contacto)
    {
        actualContacto = contacto;
    }
}
