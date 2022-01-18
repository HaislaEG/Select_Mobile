package com.example.select.materiais;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.select.R;

public class Plastico_Activity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plastico);

        Button btnPlastico1 = findViewById(R.id.btnPlastico1);
        btnPlastico1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.simperj.org.br/blog/2018/09/12/quais-sao-as-melhores-formas-de-reaproveitar-o-plastico/"));
                startActivity(i);
            }
        });

        Button btnPlastico2 = findViewById(R.id.btnPlastico2);
        btnPlastico2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plasticovirtual.com.br/8-ideias-criativas-para-reaproveitar-embalagens-plasticas/"));
                startActivity(i);
            }
        });

        Button btnPlastico3 = findViewById(R.id.btnPlastico3);
        btnPlastico3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.tuacasa.com.br/como-reutilizar-objetos-na-decoracao/"));
                startActivity(i);
            }
        });


    }

}
