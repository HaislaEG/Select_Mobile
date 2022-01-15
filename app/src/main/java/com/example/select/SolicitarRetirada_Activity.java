package com.example.select;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SolicitarRetirada_Activity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solicitar_retirada);

        //Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.tbSolicitarRetirada);
        setSupportActionBar(toolbar);  //Passa a ser a toolbar principal da aplicação


        Button btnSolicitarProsseguir = findViewById(R.id.btnSolicitarProsseguir);
        btnSolicitarProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SolicitarRetirada_Activity.this, Foto_Activity.class);
                startActivity(i);
            }
        });

        Button btnSolicitarCancelar = findViewById(R.id.btnSolicitarCancelar);
        btnSolicitarCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iA = new Intent(SolicitarRetirada_Activity.this, Main_Activity.class);
                startActivity(iA);
            }
        });

    }

}