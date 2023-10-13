package com.example.pedrapapeltesoura.Aplicacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pedrapapeltesoura.R;

import java.util.Random;

public class Game2 extends AppCompatActivity {
public static int pontuacao = 0;
private int random;
private TextView result;
private ImageView img1B;
private ImageView img2C;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);
        Intent intent = getIntent();
        result = findViewById(R.id.result);
        img1B = findViewById(R.id.imageBot);
        img2C = findViewById(R.id.imageClient);

        Bundle extras = intent.getExtras();
        Random r = new Random();
        random = r.nextInt(4);

        if(random == 0){
            random++;
        }

        if(extras != null && extras.containsKey("selecao") && random < 4){
            String aux = extras.getString("selecao");
            int escolhido = Integer.parseInt(aux);
            String resultado = resultado(escolhido,random);
            result.setText(resultado);
            setImages(escolhido, random, img1B,img2C);


        }else{
            result.setText("ERROR");
            img1B.setImageResource(R.drawable.error_905);
            img2C.setImageResource(R.drawable.error_905);
        }
    }

    public String resultado(int client, int bot){

        if(client == 1){ //pedra
            //-------------------------
    if(bot == 1){ //pedra
        return "Empate :)";
    }else if(bot == 2){ //papel
        return "Perdeu kk";
    }else{ //tesoura
        pontuacao ++;
        return "Ganhou!!";
    }
    //--------------------------
        }else if(client == 2){
            if(bot == 1){
                pontuacao++;
                return "Ganhou!!";
            }else if(bot == 2){
                return "Empate :)";
            }else{
                return "Perdeu kk";
            }
            //---------------------
        }else{ //tesoura
            if(bot == 1){
                return "Perdeu kk";
            }else if(bot == 2){ //papel
                pontuacao++;
                return "Ganhou!!";
            }else{
                return "Empate :)";
            }
        }
    }

    public void setImages( int client, int bot, ImageView imgBot, ImageView imgClient){
        if(bot == 3){
            imgBot.setImageResource(R.drawable.tesouras);
        }else if(bot == 2){
            imgBot.setImageResource(R.drawable.artigos_de_papelaria_com_silhueta);
        }else{
            imgBot.setImageResource(R.drawable.pedra);
        }

        if(client == 3){
            imgClient.setImageResource(R.drawable.tesouras);
        }else if(client == 2){
            imgClient.setImageResource(R.drawable.artigos_de_papelaria_com_silhueta);
        }else{
            imgClient.setImageResource(R.drawable.pedra);
        }

    }


}