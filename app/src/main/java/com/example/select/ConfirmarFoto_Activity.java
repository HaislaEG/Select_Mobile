package com.example.select;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ConfirmarFoto_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_foto);

        //Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.tbConfirmarFoto);
        setSupportActionBar(toolbar);  //Passa a ser a toolbar principal da aplicação

        FloatingActionButton btnFotoEnviar = findViewById(R.id.btnFotoEnviar);
        btnFotoEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pega a imagem e envia para o servidor
            }
        });


        FloatingActionButton btnFotoCancelar = findViewById(R.id.btnFotoCancelar);
        btnFotoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iA = new Intent(ConfirmarFoto_Activity.this, Foto_Activity.class);
                startActivity(iA);
            }
        });
    }



}
