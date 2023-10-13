package com.example.pedrapapeltesoura.Aplicacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pedrapapeltesoura.R;

public class MenuOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_one);
    }

    public void nextListUserAltDel(View v){
        Intent i = new Intent(this,ListUserAltDel.class);

        startActivity(i);
    }
    public void nextListHank(View v){
        Intent i = new Intent(this,ListHank.class);
        startActivity(i);
    }

}