package com.example.realparadolar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 4000;
    //defini o tempo que a tela splash é exibida

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRealParaDolar = findViewById(R.id.btnRealParaDolar);
        btnRealParaDolar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, operacaoRealParaDolar.class);
                startActivity(intent);
            }
        });

        Button btnDolarParaReal = findViewById(R.id.btnDolarParaReal);
        btnDolarParaReal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, dolarParaReal.class);
                startActivity(i);
            }
        });

        Button btnRealParaBitcoin = findViewById(R.id.btnRealParaBitcoin);
        btnRealParaBitcoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, realParaBitcoin.class);
                startActivity(in);
            }
        });


    }
}