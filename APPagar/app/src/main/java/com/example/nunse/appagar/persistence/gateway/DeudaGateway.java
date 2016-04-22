package com.example.nunse.appagar.persistence.gateway;

import com.example.nunse.appagar.model.Contacto;
import com.example.nunse.appagar.model.Deuda;
import com.example.nunse.appagar.persistence.DBHelper;

import java.util.List;

/**
 * Created by nunse on 13/04/2016.
 */
public interface DeudaGateway {

    public void añadirDeuda(Contacto contacto, Deuda deuda);

    /**
     * Método para <b>borrar definitivamente</b> una deuda de la base de datos.
     * Debe usarse cuando el usuario se haya confundido con la deuda, <b>no</b> para saldarla.
     * Debe utilizarse el método saldar deuda del modelo, que modifica la existente.
     * @see DeudaGateway
     */
    public void borrarDeuda(Contacto contacto, Deuda deuda);
    public void modificarDeuda(Contacto contacto, Deuda deuda);
    public List<Deuda> getDeudas(Contacto contacto);

    public void establecerDB(DBHelper db);
}
