package com.example.nunse.appagar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;

import com.example.nunse.appagar.conf.ActualContacto;
import com.example.nunse.appagar.conf.Utilisimo;
import com.example.nunse.appagar.model.Deuda;

public class SaldarDeuda extends Activity {

    private Deuda deuda;
    private double cantidadActual = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saldar_deuda);
        deuda = ActualContacto.getActual().getDeudaActual();
        inicializar();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cantidadActual = progress;
                cantidadActual = deuda.getCantidadRestante() * cantidadActual / 100;
                cantidadActual = Utilisimo.round(cantidadActual, 2);
                cantidad.setText(cantidadActual  + "€");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Nada
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void inicializar()
    {
        seekBar = (SeekBar) findViewById(R.id.sbCantidad);
        cantidad = (EditText) findViewById(R.id.editTextCantidad);
        seekBar.setProgress(seekBar.getMax());
        cantidad.setText(deuda.getCantidadRestante() + "€");
    }

    public void saldar(View view)
    {
        String cant = cantidad.getText().toString();
        cant = cant.replace("€", "");

        double cantidadSaldar = Double.parseDouble(cant);

        cantidadSaldar = deuda.getCantidadRestante() > cantidadSaldar
                ? cantidadSaldar : deuda.getCantidadRestante();

        cantidadSaldar = Utilisimo.round(cantidadSaldar, 2);
        deuda.saldarCantidad(cantidadSaldar);

        Intent i = new Intent(this, ListaDeudasContacto.class);
        startActivity(i);
        finish();

    }


    private SeekBar seekBar;
    private EditText cantidad;
}
