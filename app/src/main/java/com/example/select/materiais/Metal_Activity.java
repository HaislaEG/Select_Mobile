package com.example.select.materiais;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.select.R;

public class Metal_Activity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metal);

        Button btnMetal1 = findViewById(R.id.btnMetal1);
        btnMetal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://blog.tudogostoso.com.br/estilo-de-vida/praticas-sustentaveis/latas-de-metal-na-decoracao/#:~:text=As%20latas%20de%20metal%20podem,maiores%2C%20para%20plantas%20mais%20vistosas."));
                startActivity(i);
            }
        });

        Button btnMetal2 = findViewById(R.id.btnMetal2);
        btnMetal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://claudia.abril.com.br/sua-vida/16-ideias-para-reaproveitar-latas-de-metal-na-decoracao/"));
                startActivity(i);
            }
        });

        Button btnMetal3 = findViewById(R.id.btnMetal3);
        btnMetal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://catracalivre.com.br/economize/10-ideias-para-transformar-latas-de-aluminio-em-itens-decorativos/"));
                startActivity(i);
            }
        });

    }

}
