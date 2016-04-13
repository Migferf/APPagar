package com.example.nunse.appagar.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by nunse on 31/03/2016.
 */
public class Contacto {

    private String nombre;
    private String apellidos;
    private Bitmap image;
    private String numero;


    private List<Deuda> deudas;


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

    public void añadirDeuda(int cantidad)
    {
        deudas.add(new Deuda(this, cantidad));
    }

    public void añadirDeuda(int cantidad, Date fechaDeuda)
    {
        deudas.add(new Deuda(this,cantidad,fechaDeuda));
    }
    public double getCantidadDeudaTotal()
    {
        double cantidadDeudaTotal = 0;

        for(Deuda d : deudas)
        {
           cantidadDeudaTotal += d.getCantidad();
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
        return deudas;
    }




}
