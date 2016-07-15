package com.example.nunse.appagar.model;

import android.graphics.Bitmap;

import com.example.nunse.appagar.persistence.PersistenceFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nunse on 31/03/2016.
 */
public class Contacto{

    private String nombre;
    private String apellidos;
    private Bitmap image;
    private String numero;


    private List<Deuda> deudas;



    private Deuda deudaActual;


    public Contacto()
    {
        deudas = new ArrayList<Deuda>();
    }

    public Contacto(String nombre, String apellidos)
    {
        this.nombre = nombre;
        this.apellidos = apellidos;
        deudas = new ArrayList<Deuda>();
    }

    public Contacto(String nombre, String apellidos, List<Deuda> deudas)
    {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.deudas = deudas;
    }

    public double getCantidadDeudaTotal()
    {
        double cantidadDeudaTotal = 0;
        List<Deuda> deudas = getDeudasNoSaldadas();
        for(Deuda d : deudas)
        {
           cantidadDeudaTotal += d.getCantidadRestante();
        }
        return cantidadDeudaTotal;
    }

    public String getNombre()
    {
        return nombre;
    }

    public String getApellidos()
    {
        return apellidos;
    }

    public Bitmap getImage()
    {
        return image;
    }

    public void setImage(Bitmap image)
    {
        this.image = image;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }

    public List<Deuda> getDeudas()
    {
        return PersistenceFactory.getDeudaGateway().getDeudas(this);
    }

    public Deuda getDeudaActual() {
        return deudaActual;
    }

    public void setDeudaActual(Deuda deudaActual) {
        this.deudaActual = deudaActual;
    }

    public List<Deuda> getDeudasNoSaldadas() {

        List<Deuda> deudas = getDeudas();

        List<Deuda> deudasSaldadas = new ArrayList<>();

        for(Deuda deuda: deudas)
        {
            if(!deuda.isSaldada())
                deudasSaldadas.add(deuda);
        }
        return deudasSaldadas;
    }

    public void guardar()
    {
        PersistenceFactory.getContactoGateway().añadirContacto(this);
    }

    public void borrar()
    {
        PersistenceFactory.getContactoGateway().borrarContacto(this);
    }
    @Override
    public boolean equals(Object c)
    {
        Contacto contacto = (Contacto) c;
        return contacto.getNombre().equals(this.getNombre());
    }

    @Override
    public int hashCode()
    {
        return nombre.hashCode();
    }
}
