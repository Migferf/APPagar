package com.example.nunse.appagar.model;

import java.util.Date;

/**
 * Created by nunse on 31/03/2016.
 */
public class Deuda {

    private Contacto deudor;
    private String descripcion;
    private double cantidad;
    private Date fechaDeuda;
    private boolean saldada;


    public Deuda(Contacto deudor, double cantidad)
    {
        this.deudor = deudor;
        this.cantidad = cantidad;
        this.fechaDeuda = new Date(System.currentTimeMillis());
    }

    public Deuda(Contacto deudor, double cantidad, Date fechaDeuda)
    {
        this.deudor = deudor;
        this.cantidad = cantidad;
        this.fechaDeuda = fechaDeuda;
    }

    public Contacto getDeudor()
    {
        return deudor;
    }

    public double getCantidad()
    {
        return cantidad;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
    public Date getFechaDeuda()
    {
        return fechaDeuda;
    }

    public void setDeudor(Contacto deudor)
    {
        this.deudor = deudor;
    }

    public boolean isSaldada()
    {
        return saldada;
    }

    public void saldarDeuda()
    {
        saldada = true;
    }


}
