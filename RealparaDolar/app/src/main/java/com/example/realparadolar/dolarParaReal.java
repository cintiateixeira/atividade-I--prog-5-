package com.example.realparadolar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class dolarParaReal extends AppCompatActivity {

    private EditText editEntrada2;
    private TextView textResultado2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dolar_para_real);

        editEntrada2 = findViewById(R.id.editEntrada2);
        textResultado2 = findViewById(R.id.textResultado2);

        Button btnConverter2 = findViewById(R.id.btnConverter2);

        Button btnLimparDados2 = findViewById(R.id.btnLimparDados2);
        btnLimparDados2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editEntrada2.setText("");
                textResultado2.setText("");
            }
        });
    }

    public void calcularReal(View view){

        float entrada = Float.parseFloat(editEntrada2.getText().toString());
        //valor do campo de entrada

        float valorReal = 4.93F;

        DecimalFormat formato = new DecimalFormat("#.##");
        float result2 = (entrada * valorReal);
        String valorFormatado = formato.format(result2);
        //delimita as casas decimais

        textResultado2.setText("O valor em real é: " + valorFormatado);

    }
}