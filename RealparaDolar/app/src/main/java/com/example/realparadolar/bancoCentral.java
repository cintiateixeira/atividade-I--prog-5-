package com.example.realparadolar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class bancoCentral extends AppCompatActivity {
private WebView wv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banco_central);

        wv = findViewById(R.id.webView);
        WebSettings webSettings = wv.getSettings();
       webSettings.setDomStorageEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        wv.setWebViewClient(new WebViewClient());
        String url = "https://www.bcb.gov.br";

        wv.loadUrl(url); //Chamo a página web

    }
}