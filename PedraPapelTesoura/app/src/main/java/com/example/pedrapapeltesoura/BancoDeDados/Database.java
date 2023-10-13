package com.example.pedrapapeltesoura.BancoDeDados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "JO_KEN_PO";
    protected static final String TABELA_USER = "usuarios";
    protected static final String TABELA_PARTIDA = "partida";
    protected static final String TABELA_JOGOU = "Jogou";
    protected static final String ID_USER = "_id";
    protected static final String ID_PARTIDA = "idPartida";
    protected static final String ID_PARTIDA_FK = "idPartida_FK";
    protected static final String ID_USER_FK = "idUser_FK";
    private static final int VERSAO = 6;
    protected static final String NOME_USER = "nome";
    protected static final String DATA_NASCIMENTO = "dataNascimento";
    protected static final String TELEFONE_USER = "telefone";
    protected static final String PONTUACAO = "pontuacao";
    private static final String PONTUACAO_USER = "pontuacao_user";
    protected static final String TEMPO = "tempoEmJogo";
    private static final String TEMPO_USER = "tempoEmJogo_user";



    public Database(Context context){
        //o super se comunica com a classe que o Database está estendendo
        super(context, NOME_BANCO, null, VERSAO);}

    public static String getTabelaUser() {
        return TABELA_USER;
    }

    public static String getIdUser() {
        return ID_USER;
    }

    public static String getNomeUser() {
        return NOME_USER;
    }

    public static String getPontuacaoUser() { return PONTUACAO_USER;}

    public static String getTempoUser() { return TEMPO_USER;}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql1 =
                "CREATE TABLE IF NOT EXISTS "+TABELA_USER+"\n" +
                "(\n" +
                ID_USER+ " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                NOME_USER+ " TEXT NOT NULL,\n" +
                DATA_NASCIMENTO+ " DATE NOT NULL,\n" +
                TELEFONE_USER+ " TEXT NOT NULL,\n" +
                PONTUACAO_USER+ " INTEGER,\n" +
                TEMPO_USER+ " INTEGER\n" +
                ");\n" +
                "\n" ;

        String sql2 =
                "CREATE TABLE IF NOT EXISTS  "+TABELA_PARTIDA+"\n" +
                "(\n" +
                ID_PARTIDA +" INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                PONTUACAO+" INTEGER NOT NULL,\n" +
                TEMPO+ " INTEGER NOT NULL\n" +
                ");\n" +
                "\n" ;

        String sql3 =
                "CREATE TABLE IF NOT EXISTS  "+TABELA_JOGOU+"\n" +
                "(\n" +
                ID_PARTIDA_FK+ " INTEGER NOT NULL,\n" +
                ID_USER_FK+ " INTEGER NOT NULL,\n" +
                "FOREIGN KEY ("+ID_PARTIDA_FK+") REFERENCES partida("+ID_PARTIDA+"),\n" +
                "FOREIGN KEY ("+ID_USER_FK+") REFERENCES usuarios("+ID_USER+")\n" +
                ");";

        sqLiteDatabase.execSQL(sql1);
        sqLiteDatabase.execSQL(sql2);
        sqLiteDatabase.execSQL(sql3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABELA_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TABELA_JOGOU);
        db.execSQL("DROP TABLE IF EXISTS "+TABELA_PARTIDA);

        onCreate(db);

    }
}
