package com.example.pedrapapeltesoura.Utilitarios;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pedrapapeltesoura.BancoDeDados.ControleDataBase;
import com.example.pedrapapeltesoura.R;

public class Cadastro extends AppCompatActivity {
    private EditText ptName;
    private EditText ptData;
    private EditText ptTelefone;

    private Button btnSalvar;
    private Button btnLimpar;
    private Button btnVoltar;

    ControleDataBase db;
    CharSequence mensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);



        ptName = findViewById(R.id.ptName);
        ptData  = findViewById(R.id.ptDataNascimento);
        ptTelefone = findViewById(R.id.ptTelefone);
        btnSalvar  = findViewById(R.id.btn_salvar);
        btnLimpar = findViewById(R.id.btn_limpar);
        btnVoltar = findViewById(R.id.btn_voltar1);
        db = new ControleDataBase(this);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome, data, telefone;
                long verifica;
                int pontuacao, tempo;
                int duracao = Toast.LENGTH_SHORT;

                nome = ptName.getText().toString();
                data = ptData.getText().toString();
                telefone = ptTelefone.getText().toString();
                pontuacao = 0;
                tempo = 0;

                verifica = db.inserirDadosEvento1(nome,data,telefone,pontuacao,tempo);

                if(verifica != -1){
                    mensagem = "jogador salvo com sucesso";
                    //o getApplicationContext apresenta o contexto global da aplicação
                    Toast toast = Toast.makeText(getApplicationContext(), mensagem, duracao);
                    toast.show();
                }else{
                    mensagem = "Error em Salvar Jogador";
                    Toast toast = Toast.makeText(getApplicationContext(), mensagem, duracao);
                    toast.show();
                }

                finish();

            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ptName.setText("");
                ptData.setText("");
                ptTelefone.setText("");
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