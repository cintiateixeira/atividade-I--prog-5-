package com.example.realparadolar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class operacaoRealParaDolar extends AppCompatActivity {

    private EditText editEntrada;
    private TextView textResultado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacao_real_para_dolar);

        editEntrada = findViewById(R.id.editEntrada);
        textResultado = findViewById(R.id.textResultado);

        Button btnCalcular = findViewById(R.id.btnCalcular);
        Button btnLimparDados = findViewById(R.id.btnLimparDados);

        btnLimparDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editEntrada.setText("");
                textResultado.setText("");
            }
        });

    }
    public void calcularDolar (View view){
        //variavel com o valor inserido pelo usuario
        float entrada = Float.parseFloat(editEntrada.getText().toString());

        float valorDolar = 0.202839F;
        //variavel com o valor do dolar atual

        DecimalFormat format = new DecimalFormat("#.###");
        float result = ( entrada * valorDolar);
        String valorFormatado = format.format(result);

        textResultado.setText("O valor em Dolar é: " + valorFormatado);
    }

}