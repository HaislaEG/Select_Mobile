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

import com.example.select.util.HttpRequest;
import com.example.select.util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Login_Activity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText email = findViewById(R.id.etLoginEmail);
        EditText senha = findViewById(R.id.etLoginSenha);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                    loadCadastro(loginEmail, loginSenha);
                }



            }
        });

        Button btnLoginCadastrar = findViewById(R.id.btnLoginCadastrar);
        btnLoginCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login_Activity.this, Cadastro_Activity.class);
                startActivity(i);
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


    // "Puxa" a conexão com o servidor
    public void loadCadastro(String Email, String Senha){

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {

                HttpRequest httpRequest = new HttpRequest("https://select-bd.herokuapp.com/get_all_Usuario.php", "GET", "UTF-8");
                try {

                    InputStream is = httpRequest.execute();
                    String result = Utils.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if (success == 1) {

                        JSONArray jsonArray = jsonObject.getJSONArray("Usuario");

                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jCadastro = jsonArray.getJSONObject(i);
                            String Str = jCadastro.getString().toString();
                            if ((Str.contains(Email)) && (Str.contains(Senha))) {
                                Intent iA = new Intent(Login_Activity.this, Perfil_Activity.class);
                                startActivity(iA);
                            }
                        }


                    }


                }
                catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    });


}