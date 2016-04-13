package com.example.nunse.appagar.persistence.gateway;

import com.example.nunse.appagar.model.Contacto;
import com.example.nunse.appagar.persistence.DBHelper;

import java.util.List;

/**
 * Created by nunse on 13/04/2016.
 */
public interface ContactoGateway {

    public void añadirContacto(Contacto contacto);
    public void borrarContacto(Contacto contacto);
    public void modificarContacto(Contacto contacto);
    public List<Contacto> getContactos();

    void establecerDB(DBHelper db);
}
