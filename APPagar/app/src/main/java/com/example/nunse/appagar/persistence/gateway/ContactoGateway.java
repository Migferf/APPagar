package com.example.nunse.appagar.persistence.gateway;

import com.example.nunse.appagar.model.Contacto;
import com.example.nunse.appagar.persistence.DBHelper;

import java.util.List;

/**
 * Created by nunse on 13/04/2016.
 */
public interface ContactoGateway {

    void añadirContacto(Contacto contacto);
    void borrarContacto(Contacto contacto);
    void modificarContacto(Contacto contacto);
    List<Contacto> getContactos();
    Contacto getContacto(String numero);

    Contacto getMayorDeudor();
    Contacto getMayorNumeroDeudas();
    Contacto getMenorPorcentajePagado();

    void establecerDB(DBHelper db);
}
