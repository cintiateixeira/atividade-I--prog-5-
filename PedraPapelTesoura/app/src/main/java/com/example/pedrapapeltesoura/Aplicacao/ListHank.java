package com.example.pedrapapeltesoura.Aplicacao;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.pedrapapeltesoura.BancoDeDados.ControleDataBase;
import com.example.pedrapapeltesoura.BancoDeDados.Database;
import com.example.pedrapapeltesoura.R;

public class ListHank extends AppCompatActivity {
    protected ListView hank;
    private ControleDataBase db;

    private Button btnJogar;
    private Button btnVoltar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hank);
        btnJogar = findViewById(R.id.hJogar);
        btnVoltar = findViewById(R.id.hVoltar);

        db = new ControleDataBase(this);
        hank = (ListView) findViewById(R.id.lvHank);

        String[] colunas = {Database.getIdUser(),Database.getNomeUser(), Database.getPontuacaoUser(), Database.getTempoUser()};//A colunas que irão aparecer
        int[] idViews = new int[]{R.id.hId,R.id.nomeJogador, R.id.pontuacaojogador, R.id.TempoJogando};

        Cursor hankedCursor = db.carregarHanked();

        SimpleCursorAdapter adaptadorh = new SimpleCursorAdapter(getBaseContext(), R.layout.partidas, hankedCursor, colunas, idViews, 0);

        hank.setAdapter(adaptadorh);

        btnJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(),com.example.pedrapapeltesoura.Aplicacao.ListUserAltDel.class);
                startActivity(i);
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}