package com.example.pedrapapeltesoura.Aplicacao;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pedrapapeltesoura.BancoDeDados.ControleDataBase;
import com.example.pedrapapeltesoura.R;

public class Verifica extends AppCompatActivity {

    Button btnSim, btnNao;
    private int objetive, idUser;
    private ControleDataBase db;
    private CharSequence mensagem;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifica);
        btnSim = findViewById(R.id.vSim);
        btnNao = findViewById(R.id.vNao);
        db = new ControleDataBase(this);

        Intent oldIntent = getIntent();

        objetive = Integer.parseInt(oldIntent.getStringExtra("case"));
        idUser = Integer.parseInt(oldIntent.getStringExtra("id"));

        btnSim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (objetive == 0) {
                    apagar(idUser);
                } else if (objetive == 1) {

                } else if (objetive == 2) {

                }
            }

            public void apagar(int id) {
              int verifica = db.deltarDados(id);

              if(verifica != -1){
                  mensagem = "Usuário excluido";
                  Toast toast = Toast.makeText(getApplicationContext(),mensagem,Toast.LENGTH_SHORT);
                  toast.show();
              }else{
                  mensagem = "Error Na Aplicacao";
                  Toast toast = Toast.makeText(getApplicationContext(),mensagem,Toast.LENGTH_SHORT);
                  toast.show();
              }
              Intent newIntent = new Intent(getApplicationContext(), com.example.pedrapapeltesoura.Aplicacao.ListUserAltDel.class);
              startActivity(newIntent);
            }
        });

        btnNao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (objetive == 0) {
                    finish();
                } else if (objetive == 1) {
                    finish();
                } else if (objetive == 2) {
                    finish();
                }
            }
        });

    }
}