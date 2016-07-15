package com.example.nunse.appagar.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nunse on 24/05/2016.
 */
public class Comentario {

    private static Comentario instance;

    private Comentario()
    {
        inicializarComentarios();
    }

    public static Comentario getInstance()
    {
        if(instance == null)
        {
            instance = new Comentario();
        }
        return instance;
    }

    public String get(double deudaTotal)
    {
        for(double cota : cotas)
        {
            if(deudaTotal <= cota)
            {
                return comentarios.get(cota);
            }
        }
        return null;
    }

    private void inicializarComentarios()
    {
        comentarios = new HashMap<>();
        comentarios.put(0.0, "Amigo");
        comentarios.put(2.0, "¿Amigo?");
        comentarios.put(5.0, "Pequeño ladrón");
        comentarios.put(10.0, "Habla con esta persona");
        comentarios.put(20.0, "Estafador");
        comentarios.put(50.0, "Que te pague y le borras");
        comentarios.put(100.0, "Moroso profesional");
        comentarios.put(Double.MAX_VALUE, "La has cagado");

        cotas = new ArrayList<>();
        cotas.add(0.0);
        cotas.add(2.0);
        cotas.add(5.0);
        cotas.add(10.0);
        cotas.add(20.0);
        cotas.add(50.0);
        cotas.add(100.0);
        cotas.add(Double.MAX_VALUE);
    }
    private double deudaTotal;
    private ArrayList<Double> cotas;
    private Map<Double, String> comentarios;
}
