package com.example.nunse.appagar.model;

import java.util.Date;

/**
 * Created by nunse on 31/03/2016.
 */
public class Deuda {

    private Contacto deudor;
    private double cantidad;
    private Date fechaDeuda;
    private boolean saldada;

    private int id;


    public Deuda (int id, double cantidad, Date fechaDeuda ,Contacto deudor)
    {
        this.id = id;
        this.cantidad = cantidad;
        this.fechaDeuda = fechaDeuda;
        this.deudor = deudor;
    }
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


    public int getID() {
        return id;
    }

}
