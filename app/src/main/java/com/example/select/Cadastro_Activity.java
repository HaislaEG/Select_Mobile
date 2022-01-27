package com.example.select;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.select.util.Config;
import com.example.select.util.HttpRequest;
import com.example.select.util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kotlin.OverloadResolutionByLambdaReturnType;

public class Cadastro_Activity extends AppCompatActivity {

    static int RequisicaoEscolherFoto = 1;
    ImageView imvCadastroFoto;
    Uri localFotoSelecionada;  // Endereço do local onde a imagem selecionada está

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        //Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.tbCadastro);
        setSupportActionBar(toolbar);  //Passa a ser a toolbar principal da aplicação


        // Salvar os dados do cadastro
        Button btnCadastroSalvar = findViewById(R.id.btnCadastroSalvar);
        btnCadastroSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Verifica se os dados foram preenchidos pelo usuário
                    //Nome
                EditText etCadastroNome = findViewById(R.id.etCadastroNome);
                String nome = etCadastroNome.getText().toString();
                if(nome.isEmpty()){
                    Toast.makeText(Cadastro_Activity.this, "O campo 'Nome completo' não foi preenchido", Toast.LENGTH_SHORT).show();
                    return;
                }

                    //CPF
                EditText etCadastroCpf = findViewById(R.id.etCadastroCpf);
                String cpf = etCadastroCpf.getText().toString();
                if(cpf.isEmpty()){
                    Toast.makeText(Cadastro_Activity.this, "O campo 'CPF' não foi preenchido", Toast.LENGTH_SHORT).show();
                    return;
                }

                    //Email
                EditText etCadastroEmail = findViewById(R.id.etCadastroEmail);
                String email = etCadastroEmail.getText().toString();
                if(email.isEmpty()){
                    Toast.makeText(Cadastro_Activity.this, "O campo 'Email' não foi preenchido", Toast.LENGTH_SHORT).show();
                    return;
                }

                    //Senha
                EditText etCadastroSenha = findViewById(R.id.etCadastroSenha);
                String senha = etCadastroSenha.getText().toString();
                if(senha.isEmpty()){
                    Toast.makeText(Cadastro_Activity.this, "O campo 'Senha' não foi preenchido", Toast.LENGTH_SHORT).show();
                    return;
                }

                    //Confirmar senha
                EditText etCadastroConfirmarSenha = findViewById(R.id.etCadastroConfirmarSenha);
                String confirmarSenha = etCadastroConfirmarSenha.getText().toString();
                if(confirmarSenha.isEmpty()){
                    Toast.makeText(Cadastro_Activity.this, "O campo 'Confirmar Senha' não foi preenchido", Toast.LENGTH_SHORT).show();
                    return;
                }

                    //Telefone
                EditText etCadastroTelefone = findViewById(R.id.etCadastroTelefone);
                String telefone = etCadastroTelefone.getText().toString();
                if(telefone.isEmpty()){
                    Toast.makeText(Cadastro_Activity.this, "O campo 'Telefone' não foi preenchido", Toast.LENGTH_SHORT).show();
                    return;
                }

                    //Data de nascimento
                EditText etCadastroDataNasc = findViewById(R.id.etCadastroDataNasc);
                String dataNasc = etCadastroDataNasc.getText().toString();
                if(dataNasc.isEmpty()){
                    Toast.makeText(Cadastro_Activity.this, "O campo 'Data de nascimento' não foi preenchido", Toast.LENGTH_SHORT).show();
                    return;
                }

                    // Check box
                CheckBox cbTermos = findViewById(R.id.cbTermos);
                if(!cbTermos.isChecked()){
                    Toast.makeText(Cadastro_Activity.this, "Você não concordou com nossos termos e condições de uso", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!senha.equals(confirmarSenha)){

                    Toast.makeText(Cadastro_Activity.this, "A senha e a confirmação estão diferentes!", Toast.LENGTH_SHORT).show();
                    return;

                }


                // Requisição HTTP
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {

                        HttpRequest httpRequest = new HttpRequest(Config.CAD_APP_URL + "mobile_cadastro.php", "POST", "UTF-8");
                        httpRequest.addParam("nome", nome);
                        httpRequest.addParam("senha", senha);
                        httpRequest.addParam("email", email);
                        httpRequest.addParam("telefone", telefone);
                        httpRequest.addParam("cpf", cpf);
                        httpRequest.addParam("dat_nasc", dataNasc);

                        try {

                            InputStream is = httpRequest.execute();

                            String result = Utils.inputStream2String(is, "UTF-8");
                            httpRequest.finish();

                            Log.d("HTTP_REQUEST_RESULT", result);

                            // Transforma a string em dados armazenáveis para a aplicação
                            JSONObject jsonObject = new JSONObject(result);
                            int success = jsonObject.getInt("success");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // Verifica se o cadastro foi realizado
                                    if (success == 1) {

                                        Toast.makeText(Cadastro_Activity.this, "Cadastro realizado com sucesso! Agora faça seu login!", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(Cadastro_Activity.this, Login_Activity.class);
                                        startActivity(i);
                                        finish();

                                    }
                                    else {

                                        Toast.makeText(Cadastro_Activity.this, "Cadastro não pôde ser realizado", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                        }
                        catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });

    }

}