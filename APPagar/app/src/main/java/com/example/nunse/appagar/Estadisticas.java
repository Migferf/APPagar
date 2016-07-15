package com.example.nunse.appagar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nunse.appagar.model.Contacto;
import com.example.nunse.appagar.persistence.PersistenceFactory;

import java.text.DecimalFormat;

public class Estadisticas extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        inicializar();
    }


    private void inicializar()
    {
        TextView nombreMayorDeudor = (TextView) this.findViewById(R.id.nombreMayorDeudor);
        TextView mayorDeuda = (TextView) this.findViewById(R.id.cantidadMayorDeudor);

        TextView nombreMayorNumDeudas = (TextView) this.findViewById(R.id.nombreMuchasDeudas);
        TextView numeroMayorDeudas = (TextView) this.findViewById(R.id.numeroDeudasEstadisticas);

        TextView nombreMenorPorcentaje = (TextView) this.findViewById(R.id.menorPorcentajePagado);
        TextView menorPorcentaje = (TextView) this.findViewById(R.id.porcentajePagadas);

        TextView tv4 = (TextView) this.findViewById(R.id.textView4);
        TextView tv7 = (TextView) this.findViewById(R.id.textView7);

        Contacto mayorDeudor = PersistenceFactory.getContactoGateway().getMayorDeudor();
        nombreMayorDeudor.setText(mayorDeudor.getNombre());
        mayorDeuda.setText(mayorDeudor.getCantidadDeudaTotal() + " €");

        Contacto contactoNum = PersistenceFactory.getContactoGateway().getMayorNumeroDeudas();
        nombreMayorNumDeudas.setText(contactoNum.getNombre());
        numeroMayorDeudas.setText(contactoNum.getDeudas().size() + " deudas");

        Contacto contactoPorcentaje = PersistenceFactory.getContactoGateway().getMenorPorcentajePagado();
        if(contactoPorcentaje!=null)
        {
            nombreMenorPorcentaje.setText(contactoPorcentaje.getNombre());
            double porcentaje = 100 - ((double)contactoPorcentaje.getDeudasNoSaldadas().size() / (double)contactoPorcentaje.getDeudas().size()) * 100;
            menorPorcentaje.setText(new DecimalFormat("#.#").format(porcentaje) + " %");
        }
        else
        {
            nombreMenorPorcentaje.setText("");
            tv4.setText("No tienes a nadie con suficientes deudas para calcular esto (4 deudas) " +
                    "\nEso es bueno para ti :D");
            tv7.setText("");
            menorPorcentaje.setText("");
        }



    }
}
