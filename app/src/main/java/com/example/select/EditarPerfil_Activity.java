package com.example.select;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EditarPerfil_Activity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_perfil);

        //Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.tbEditarPerfil);
        setSupportActionBar(toolbar);  //Passa a ser a toolbar principal da aplicação


    }

}
