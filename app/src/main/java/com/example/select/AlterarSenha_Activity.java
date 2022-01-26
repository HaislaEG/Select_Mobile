package com.example.select;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AlterarSenha_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar_senha);

        //Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.tbAlterarSenha);
        setSupportActionBar(toolbar);  //Passa a ser a toolbar principal da aplicação


        Button btnSenhaSalvar = findViewById(R.id.btnSenhaSalvar);
        btnSenhaSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AlterarSenha_Activity.this, Main_Activity.class);
                startActivity(i);
            }
        });

    }
}
