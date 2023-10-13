package com.example.pedrapapeltesoura.Aplicacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pedrapapeltesoura.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Game1 extends AppCompatActivity {

    public static long startTempo;
    public static int idJogador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat minuto = new SimpleDateFormat("mm");
        SimpleDateFormat segundos = new SimpleDateFormat("ss");

        int m = Integer.parseInt(minuto.format(calendar.getTime()));
        int s = Integer.parseInt(segundos.format(calendar.getTime()));

        startTempo = ((m*60)+s) ;

        Intent i = getIntent();
        idJogador = Integer.parseInt(i.getStringExtra("id"));
    }



    public void pedra(View v){
        Intent i = new Intent(Game1.this, Game2.class);
        i.putExtra("selecao","1");
        startActivity(i);
    }

    public void papel(View v){
        Intent i = new Intent(Game1.this, Game2.class);
        i.putExtra("selecao","2");
        startActivity(i);
    }
    public void tesoura(View v){
        Intent i = new Intent(Game1.this, Game2.class);
        i.putExtra("selecao","3");
        startActivity(i);
    }

}