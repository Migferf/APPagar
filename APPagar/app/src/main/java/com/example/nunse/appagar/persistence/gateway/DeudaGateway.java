package com.example.nunse.appagar.persistence.gateway;

import com.example.nunse.appagar.model.Contacto;
import com.example.nunse.appagar.model.Deuda;
import com.example.nunse.appagar.persistence.DBHelper;

/**
 * Created by nunse on 13/04/2016.
 */
public interface DeudaGateway {

    public void añadirDeuda(Contacto contacto);

    /**
     * Método para <b>borrar definitivamente</b> una deuda de la base de datos.
     * Debe usarse cuando el usuario se haya confundido con la deuda, <b>no</b> para saldarla.
     * Utilizar el método saldar deuda del modelo, y luego modificar la existente.
     * @see DeudaGateway
     */
    public void borrarDeuda(Contacto contacto, Deuda deuda);
    public void modificarDeuda(Contacto contacto, Deuda deuda);
    public void getDeudas(Contacto contacto);

    public void establecerDB(DBHelper db);
}
