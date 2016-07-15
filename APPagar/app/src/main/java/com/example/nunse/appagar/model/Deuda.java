package com.example.nunse.appagar.model;

import com.example.nunse.appagar.conf.ActualContacto;
import com.example.nunse.appagar.conf.Utilisimo;
import com.example.nunse.appagar.persistence.PersistenceFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by nunse on 31/03/2016.
 */
public class Deuda {

    private Contacto deudor;
    private String descripcion;
    private double cantidad;
    private double cantidadRestante;
    private Date fechaDeuda;
    private boolean saldada;

    private int id;


    public Deuda (int id, double cantidad, double cantidadRestante, Date fechaDeuda, String descripcion, boolean saldada)
    {
        this.id = id;
        this.cantidad = cantidad;
        this.cantidadRestante = cantidadRestante;
        this.fechaDeuda = fechaDeuda;
        this.descripcion = descripcion;
        this.saldada = saldada;
    }

    public Deuda (double cantidad, String descripcion)
    {
        this.cantidad = cantidad;
        this.cantidadRestante = cantidad;
        this.fechaDeuda = fechaDeuda;
        this.descripcion = descripcion;
        this.saldada = false;
        this.fechaDeuda = Calendar.getInstance().getTime();
    }

    public Contacto getDeudor()
    {
        return deudor;
    }

    public double getCantidad()
    {
        return cantidad;
    }

    public double getCantidadRestante() {
        return cantidadRestante;
    }

    public void saldarCantidad(double cantidadSaldada) {
        this.cantidadRestante = cantidadRestante - cantidadSaldada;
        this.cantidadRestante = Utilisimo.round(cantidadRestante, 2);
        if(cantidadRestante <= 0)
        {
            saldada = true;
        }
        PersistenceFactory.getDeudaGateway().modificarDeuda(ActualContacto.getActual(), this);
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
        this.cantidadRestante = 0;
    }


    public int getID() {
        return id;
    }


}
