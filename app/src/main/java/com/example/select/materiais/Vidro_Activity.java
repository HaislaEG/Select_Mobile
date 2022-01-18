package com.example.select.materiais;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.select.R;

public class Vidro_Activity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vidro);

        Button btnVidro1 = findViewById(R.id.btnVidro1);
        btnVidro1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sustentarqui.com.br/reutilizar-garrafas-de-vidro-decoracao-2/"));
                startActivity(i);
            }
        });

        Button btnVidro2 = findViewById(R.id.btnVidro2);
        btnVidro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.vivadecora.com.br/revista/potes-de-vidro/"));
                startActivity(i);
            }
        });


    }

}
