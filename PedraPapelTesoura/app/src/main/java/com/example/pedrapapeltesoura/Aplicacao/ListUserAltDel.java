package com.example.pedrapapeltesoura.Aplicacao;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.pedrapapeltesoura.BancoDeDados.ControleDataBase;
import com.example.pedrapapeltesoura.BancoDeDados.Database;
import com.example.pedrapapeltesoura.R;
import com.example.pedrapapeltesoura.Utilitarios.Cadastro;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ListUserAltDel extends AppCompatActivity {

    protected ListView apresenta;
    protected Database db;
    protected Cursor cursor;
    protected ControleDataBase font;
    protected Button btnJC, btnEditar, btnExcluir, btnVoltar;
    private int oldSelectItem[] = new int[10];
    private int contSelectItem = 0;
    private long tempoTotal;
    private boolean contadorTime = false;
    private CharSequence mensagem;


    @Override
    protected void onResume() {
        super.onResume();
        // Atualize o tempo de início quando a Activity volta à atividade.
        if (contadorTime == true) {

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat minutos = new SimpleDateFormat("mm");
            SimpleDateFormat segundos = new SimpleDateFormat("ss");
            int m = Integer.parseInt(minutos.format(calendar.getTime()));
            int s = Integer.parseInt(segundos.format(calendar.getTime()));

            tempoTotal = ((m * 60) + s) - (Game1.startTempo);

            int pontuacao = Game2.pontuacao;
            int idJogador = Game1.idJogador;
            inserirPartida(idJogador, pontuacao, tempoTotal);

            contadorTime = false;
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user_alt_del);
        font = new ControleDataBase(this);

        btnJC = (Button) findViewById(R.id.btn_jogar_cadastrar);
        btnExcluir = (Button) findViewById(R.id.btn_excluir);
        btnEditar = (Button) findViewById(R.id.btn_editar);
        btnVoltar = (Button) findViewById(R.id.btn_voltar);

//font.inserirDadosEvento1("marcos","2002-07-2018",994614843,4,120);

        btnJC.setText("Cadastrar");
        btnExcluir.setVisibility(View.INVISIBLE);
        btnEditar.setVisibility(View.INVISIBLE);


        cursor = font.carregarDados(); //O carregar dados me devolve um Cursor

        db = new Database(this); //Cria mmeu banco de dadosd e acordo com o contexto

        String[] colunas = {Database.getIdUser(), Database.getNomeUser()};//A colunas que irão aparecer
        int[] idViews = new int[]{R.id.idUsuarios, R.id.nomeUsuarios};
        // Isso define os IDs dos elementos de layout que serão usados
        // para exibir as colunas correspondentes do Cursor. Por exemplo,
        // R.id.idUser corresponderá à coluna Database.getIdUser() e R.id.tableUser corresponderá à coluna Database.getTabelaUser().
//--------------OK

        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.usuarios, cursor, colunas, idViews, 0);
        apresenta = (ListView) findViewById(R.id.listView);
        apresenta.setAdapter(adaptador);


        btnJC.setText("Cadastrar");
        btnJC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CharSequence text = btnJC.getText();
                String vertent = text.toString();

                if (vertent.equals("Cadastrar")) {
                    Intent intent = new Intent(getBaseContext(), Cadastro.class);
                    startActivity(intent);
                }

            }
        });


        apresenta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //mudando a cor da linha selecionada

                oldSelectItem[contSelectItem] = position;

                if (contSelectItem > 0) {

                    View oldItemSelect = apresenta.getChildAt(oldSelectItem[contSelectItem - 1]);
                    oldItemSelect.setBackgroundColor(Color.TRANSPARENT);
                    View newItemSelect = apresenta.getChildAt(position);
                    newItemSelect.setBackgroundColor(Color.BLUE);

                    if (contSelectItem == 9) {
                        contSelectItem = 0;
                    }
                } else {
                    view.setBackgroundColor(Color.BLUE);
                }
                contSelectItem++;

                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(Database.getIdUser()));

                btnJC.setText("Jogar");
                btnJC.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        CharSequence text = btnJC.getText();
                        String vertent = text.toString();
                        if (vertent.equals("Jogar")) {
                            Intent intent = new Intent(getBaseContext(), com.example.pedrapapeltesoura.Aplicacao.Game1.class);
                            intent.putExtra("id", codigo); //Mandando o ID do jogador para computar pontos diretamente para ele
                            contadorTime = true;//Garante que quando eu fechar o jogo, o tempo para de contar
                            startActivity(intent);
                        }
                    }
                });

                btnEditar.setVisibility(View.VISIBLE); //mostrando a opção de editar
                btnExcluir.setVisibility(View.VISIBLE); //mostrando a opção de excluir

                btnEditar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getBaseContext(), com.example.pedrapapeltesoura.Utilitarios.EditUser.class);
                        //objetivo -> editar
                        i.putExtra("id", codigo);
                        startActivity(i);
                    }
                });

                btnExcluir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getBaseContext(), com.example.pedrapapeltesoura.Aplicacao.Verifica.class);
                        //objetivo -> excluir
                        i.putExtra("id", codigo);
                        i.putExtra("case", "0");
                        startActivity(i);
                    }
                });

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void inserirPartida( int idUser, int pontuacao,long tempo ) {
        int idPartida = Integer.parseInt(font.inserirDadosEvento2(pontuacao, tempo));

        if(font.inserirDadosEvento3(idPartida,idUser) == -1){
            mensagem = "ERROR";
            Toast toast = Toast.makeText(getApplicationContext(),mensagem,Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}