package com.example.select;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login_Activity extends AppCompatActivity {

    private EditText email = findViewById(R.id.etLoginEmail);
    private EditText senha = findViewById(R.id.etLoginSenha);
    private Button btnLogin = findViewById(R.id.btnLogin);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.setEnabled(true);

                String loginEmail = email.getText().toString();  // Email digitado pelo usuário
                String loginSenha = senha.getText().toString();  // Senha digitada pelo usuário

                // Verifica se os dados foram digitados
                if ( (!TextUtils.isEmpty(loginEmail)) || (!TextUtils.isEmpty(loginSenha)) ){

                }

            }
        });

        Button btnLoginCadastrar = findViewById(R.id.btnLoginCadastrar);
        btnLoginCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iA = new Intent(Login_Activity.this, Cadastro_Activity.class);
                startActivity(iA);
            }
        });

        Button btnLoginEsqueciSenha = findViewById(R.id.btnLoginEsqueciSenha);
        btnLoginEsqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_Activity.this, AlterarSenha_Activity.class);
                startActivity(i);
            }
        });
    }
}