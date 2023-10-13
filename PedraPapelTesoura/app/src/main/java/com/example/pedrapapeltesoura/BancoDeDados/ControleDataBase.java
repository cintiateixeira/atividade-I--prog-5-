package com.example.pedrapapeltesoura.BancoDeDados;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

public class ControleDataBase {
    private SQLiteDatabase db;//Vou usar para interagir com o banco de dados
    private Database banco;//Vou usar para criar meu banco de dados dentro desse contexto


    public ControleDataBase(Context contexto) {
        banco = new Database(contexto);
    }

    //Irei inserir os dados em 3 tabelas de acordo com o meu ERD. Onde decidirei qual tablela estarei me referenciando no momento da implementação
    public long inserirDadosEvento1(String NOME_USER, String DATA_NASCIMENTO, String TELEFONE_USER, int PONTUACAO, int TEMPO_USER) {
        ContentValues values;
        long resultado;
//db refere-se a classe SQLITE e banco Refere-se a classe que Cria o banco de dados
        db = banco.getWritableDatabase();
        //o atributo db do tipo SQLiteDatabase recebe um objeto do tipo SQLiteDataBase pelo método getWritableDataBase()
        //que informa par o android que irei usar o db para inserir e consultar dados dentro do Banco de Dados


        values = new ContentValues();

        values.put(Database.NOME_USER, NOME_USER);
        values.put(Database.DATA_NASCIMENTO, DATA_NASCIMENTO);
        values.put(Database.TELEFONE_USER, TELEFONE_USER);
        values.put(Database.getPontuacaoUser(), PONTUACAO);
        values.put(Database.getTempoUser(), TEMPO_USER);

        resultado = db.insert(Database.getTabelaUser(), null, values);
        db.close();

        return resultado;
//Esse objeto atua como conteiner permitindo que seja transmitido valores atraves de chave
    }

    ;

    public String inserirDadosEvento2(int PONTUACAO, long TEMPO) {
        ContentValues values = new ContentValues();
        db = banco.getWritableDatabase();

        values.put(Database.PONTUACAO, PONTUACAO);
        values.put(Database.TEMPO, TEMPO);

        if ((db.insert(Database.TABELA_PARTIDA, null, values)) == -1) {
            return null;
        }

        Cursor cursor = db.rawQuery("SELECT last_insert_rowid() as ultimoId", null);
        if (cursor.moveToFirst()) {
            @SuppressLint("Range") String ultimoID = cursor.getString(cursor.getColumnIndex("ultimoId"));
            db.close();
            return ultimoID;
        }
        return null;
    }

    public long inserirDadosEvento3(int ID_USER, int ID_PARTIDA) {
        ContentValues values = new ContentValues();
        long resultado;
        db = banco.getWritableDatabase();

        values.put(Database.ID_USER_FK, ID_USER);
        values.put(Database.ID_PARTIDA_FK, ID_PARTIDA);

        if ((db.insert(Database.TABELA_JOGOU, null, values)) == -1) {
            db.close();
            return -1;
        } else {
            db.close();
            return 1;
        }
    }

    public Cursor carregarDados() {
        //o Cursor ira me retornar e iterar os dados de acordo com a minha instrução
        Cursor cursor;
        //inserindo quais colunas posso quero que seja retornada

        //String[] campos = { Database.ID_USER, Database.NOME_USER};

        //abrindo acesso a gravação no banco de dados
        db = banco.getReadableDatabase();
        //o cursor passara a apontar para o banco sqllite retornado nesta operação
        cursor = db.rawQuery("SELECT " + Database.ID_USER + " , " + Database.NOME_USER + " FROM " + Database.TABELA_USER, null);
        //Renomeei o idUser para _id por que o simpleCursorAdapter espera por padrão o _id como coluna
        //  cursor = db.query(Database.TABELA_USER,campos,null,null,null,null,null);

        if (cursor != null) {
            //Como o cursor itera as tuplas até chegar no final, preciso que ele se mova para o primeiro e assim manter a ordem da consulta
            cursor.moveToFirst();
            db.close();
            return cursor;
        }

        return null;
    }

    @SuppressLint("Range") //Desativa o aviso de erro
    public String[] carregaUserByID(int id) {

        String nome;
        String telefone;
        String data;
        db = banco.getReadableDatabase();

        String sql = "SELECT * FROM " + Database.TABELA_USER +
                " WHERE " + Database.ID_USER + " = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{String.valueOf(id)});

        cursor.moveToFirst();

        if (cursor != null) {

            nome = cursor.getString(cursor.getColumnIndex(Database.NOME_USER));
            telefone = cursor.getString(cursor.getColumnIndex(Database.TELEFONE_USER));
            data = cursor.getString(cursor.getColumnIndex(Database.DATA_NASCIMENTO));
            //Retornando a consulta em ordem de nome, telefone  data
            String[] resultado = {nome, telefone, data};
            db.close();
            return resultado;
        } else {
            db.close();
            return null;
        }
    }

    ;

    public Cursor carregarHanked() {

        Cursor cursorHanked;

        //-------------PRIMEIRA CONSULTA---------------------------
        db = banco.getReadableDatabase();

        String sqlHanked =
                "SELECT u." + Database.getIdUser() + ", u." + Database.getNomeUser() + ", u." + Database.getPontuacaoUser() + ", u." + Database.getTempoUser() + "\n" +
                "FROM " + Database.TABELA_USER + " u\n" +
                "INNER JOIN " + Database.TABELA_JOGOU + " j ON u." + Database.ID_USER + " = j." + Database.ID_USER_FK + "\n" +
                "INNER JOIN " + Database.TABELA_PARTIDA + " p ON p." + Database.ID_PARTIDA + " = j." + Database.ID_PARTIDA_FK + "\n" +
                "ORDER BY " + Database.getPontuacaoUser() + " DESC, " + Database.getTempoUser() + " ASC;";

        cursorHanked = db.rawQuery(sqlHanked, null);
        //uso o String[] para catalogar em ordem quais valores vão ser substituido por ? na minha consulta

        if (cursorHanked != null) {
            db.close();
            return cursorHanked;
        } else {
            db.close();
            return null;
        }

    }

    public int alterarDados(int ID, String NOME, String DATA_NASCIMENTO, String TELEFONE) {

        try {
            db = banco.getWritableDatabase();
            ContentValues values;
            String where;

            values = new ContentValues();
            values.put(Database.NOME_USER, NOME);
            values.put(Database.DATA_NASCIMENTO, DATA_NASCIMENTO);
            values.put(Database.TELEFONE_USER, TELEFONE);

            where = Database.getIdUser() + " = " + ID;

            return db.update(Database.getTabelaUser(), values, where, null);

        } catch (Exception e) {
            return -1;
        } finally {
            db.close();
        }
    }

    public int uploadLevel(int ID, int PONTOS, long TEMPO) {

        db = banco.getWritableDatabase();
        ContentValues values;
        String where;


        Cursor cursor = db.rawQuery("SELECT " + Database.getPontuacaoUser() + " , " + Database.getTempoUser() + " FROM " + Database.TABELA_USER + " WHERE " + Database.ID_USER + " = " + ID, null);
        int pontuacao = cursor.getColumnIndex(Database.getPontuacaoUser());
        int tempo = cursor.getColumnIndex(Database.getTempoUser());

        PONTOS += pontuacao;
        TEMPO += tempo;


        values = new ContentValues();
        values.put(Database.getPontuacaoUser(), PONTOS);
        values.put(Database.getTempoUser(), TEMPO);

        where = Database.getIdUser() + " = " + ID;

        return db.update(Database.getTabelaUser(), values, where, null);
    }


    public int deltarDados(int id) {
        String where;
        try {
            db = banco.getWritableDatabase();
            where = Database.getIdUser() + " = " + id;
            return db.delete(Database.getTabelaUser(), where, null);


        } catch (Exception e) {

            e.printStackTrace();
            return -1;

        } finally {
            db.close();
        }
    }

}
