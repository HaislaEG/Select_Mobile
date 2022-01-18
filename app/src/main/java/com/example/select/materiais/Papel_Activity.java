package com.example.select.materiais;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.select.R;

public class Papel_Activity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.papel);

        Button btnPapel1 = findViewById(R.id.btnPapel1);
        btnPapel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.cicloorganico.com.br/sem-categoria/15-formas-de-reaproveitar-papel/"));
                startActivity(i);
            }
        });

        Button btnPapel2 = findViewById(R.id.btnPapel2);
        btnPapel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://anossavida.pt/artigos/12-ideias-para-reutilizar-papel"));
                startActivity(i);
            }
        });

        Button btnPapel3 = findViewById(R.id.btnPapel3);
        btnPapel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://grupopaco.com.br/blog/faca-voce-mesmo/10-ideias-criativas-para-reciclagem-do-papel/"));
                startActivity(i);
            }
        });


    }

}
