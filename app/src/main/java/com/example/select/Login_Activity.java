package com.example.select;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                /*
                v.setEnabled(true);

                String loginEmail = email.getText().toString();  // Email digitado pelo usuário
                String loginSenha = senha.getText().toString();  // Senha digitada pelo usuário

                // Verifica se os dados foram digitados
                if (loginEmail.equals("")){

                    Toast.makeText(Login_Activity.this, "Email não inserido", Toast.LENGTH_SHORT).show();

                }
                else if (loginSenha.equals("")){

                    Toast.makeText(Login_Activity.this, "Senha não inserida", Toast.LENGTH_SHORT).show();

                }
                else{

                    //if ( (loginEmail && loginSenha)  ) {}

                }*/

                Intent i = new Intent(Login_Activity.this, Main_Activity.class);
                startActivity(i);

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