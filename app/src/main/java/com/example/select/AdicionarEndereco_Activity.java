package com.example.select;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AdicionarEndereco_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_endereco);

        //Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.tbAdd);
        setSupportActionBar(toolbar);  //Passa a ser a toolbar principal da aplicação


        Button btnEnderecoSalvar = findViewById(R.id.btnEnderecoSalvar);
        btnEnderecoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdicionarEndereco_Activity.this, Main_Activity.class);
                startActivity(i);
            }
        });


        Button btnEnderecoCancelar = findViewById(R.id.btnEnderecoCancelar);
        btnEnderecoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdicionarEndereco_Activity.this, Main_Activity.class);
                startActivity(i);
            }
        });
    }
}
