package com.example.apidestrozasuenyos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class weboView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webo_view);

        Intent myIntent = getIntent();

        String url = myIntent.getStringExtra("url");

        TextView enlace = (TextView) findViewById(R.id.url);

        enlace.setText(url);

        WebView webview = (WebView) findViewById(R.id.internes);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(url);

        Button cerrarNav = (Button) findViewById(R.id.salirNavegador);

        cerrarNav.setBackgroundColor(Color.RED);

        cerrarNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

    }
}