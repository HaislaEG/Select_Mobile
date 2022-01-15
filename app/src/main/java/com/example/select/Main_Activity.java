package com.example.select;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Main_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        //Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.tbMain);
        setSupportActionBar(toolbar);  //Passa a ser a toolbar principal da aplicação



        FloatingActionButton btnMainPerfil = findViewById(R.id.btnMainPerfil);
        btnMainPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main_Activity.this, Perfil_Activity.class);
                startActivity(i);
            }
        });


        FloatingActionButton btnMainSair = findViewById(R.id.btnMainSair);
        btnMainSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main_Activity.this, Login_Activity.class);
                startActivity(i);
            }
        });


        Button btnSolicitarRetirada = findViewById(R.id.btnSolicitarRetirada);
        btnSolicitarRetirada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main_Activity.this, SolicitarRetirada_Activity.class);
                startActivity(i);
            }
        });

    }
}