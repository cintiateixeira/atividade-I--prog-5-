package com.example.pedrapapeltesoura.Utilitarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pedrapapeltesoura.BancoDeDados.ControleDataBase;
import com.example.pedrapapeltesoura.R;

public class EditUser extends AppCompatActivity {

    private ControleDataBase db;
    private EditText ptName2;
    private EditText ptData2;
    private EditText ptTelefone2;
    private Button btnSalvar2;
    private Button btnVoltar2;
    CharSequence mensagem;
    private Intent newIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        ptName2 = findViewById(R.id.ptName2);
        ptData2 = findViewById(R.id.ptDataNascimento2);
        ptTelefone2 = findViewById(R.id.ptTelefone2);
        btnSalvar2 = findViewById(R.id.btn_salvar2);
        btnVoltar2 = findViewById(R.id.btn_voltar2);
        db = new ControleDataBase(this);

        Intent oldIntent = getIntent();
        int idUser = Integer.parseInt(oldIntent.getStringExtra("id"));

        //retorna nome, telefone e data nesta ordem
        String[] consulta = db.carregaUserByID(idUser);

        ptName2.setText(consulta[0]);
        ptTelefone2.setText(consulta[1]);
        ptData2.setText(consulta[2]);

        btnSalvar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome, data, telefone;
                int verifica;
                int duracao = Toast.LENGTH_SHORT;

                nome = ptName2.getText().toString();
                data = ptData2.getText().toString();
                telefone = ptTelefone2.getText().toString();

//verificar retorno
                verifica = db.alterarDados(idUser, nome, data, telefone);

                if (verifica != -1) {
                    mensagem = "Atualização feita com sucesso";
                    //o getApplicationContext apresenta o contexto global da aplicação
                    Toast toast = Toast.makeText(getApplicationContext(), mensagem, duracao);
                    toast.show();
                    finish();
                } else {
                    mensagem = "Error em Salvar Jogador";
                    Toast toast = Toast.makeText(getApplicationContext(), mensagem, duracao);
                    toast.show();
                }

                finish();


            }
        });
        btnVoltar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}