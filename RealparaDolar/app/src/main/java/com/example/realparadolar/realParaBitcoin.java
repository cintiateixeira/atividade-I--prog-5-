package com.example.realparadolar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class realParaBitcoin extends AppCompatActivity {

    private EditText editEntrada3;
    private TextView textResultado3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_para_bitcoin);

        editEntrada3 = findViewById(R.id.editEntrada3);
        textResultado3 = findViewById(R.id.textResultado3);

        Button btnConverter3 = findViewById(R.id.btnConverter3);

        Button btnLimparDados3 = findViewById(R.id.btnLimparDados3);
        btnLimparDados3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editEntrada3.setText("");
                textResultado3.setText("");
            }
        });

    }

    public void calcularRealBitcoin(View view){

        float entrada = Float.parseFloat(editEntrada3.getText().toString());
        //pegando os dados inseridos

        float valorBitcoin = 0.0000076F;

        DecimalFormat formato = new DecimalFormat("#.##");
        //delimita as casas decimais
        float result3 = (entrada * valorBitcoin);
        String valorFormatado = formato.format(result3);

        textResultado3.setText("O valor em Bitcoin é: " + valorFormatado);
    }
}