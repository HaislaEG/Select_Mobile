package com.example.select;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.select.util.Config;
import com.example.select.util.HttpRequest;
import com.example.select.util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Perfil_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        //Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.tbPerfil);
        setSupportActionBar(toolbar);  //Passa a ser a toolbar principal da aplicação

        String email = Config.getLogin(Perfil_Activity.this);
        String senha = Config.getPassword(Perfil_Activity.this);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest(Config.CAD_APP_URL + "mobile_perfil.php", "GET", "UTF-8");
                httpRequest.setBasicAuth(email, senha);

                try {
                    InputStream is = httpRequest.execute();
                    String result = Utils.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if (success == 1) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                TextView email = findViewById(R.id.tvPerfilEmail);
                                try {
                                    email.setText(jsonObject.getString("email"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                TextView nome = findViewById(R.id.tvPerfilNome);
                                try {
                                    nome.setText(jsonObject.getString("nome"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                TextView dataNasc = findViewById(R.id.tvPerfilDataNasc);
                                try {
                                    dataNasc.setText(jsonObject.getString("dat_nasc"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                TextView cpf = findViewById(R.id.tvPerfilCpf);
                                try {
                                    cpf.setText(jsonObject.getString("cpf"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                TextView telefone = findViewById(R.id.tvPerfilTelefone);
                                try {
                                    telefone.setText(jsonObject.getString("telefone"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                    }
                    else {

                        String error = jsonObject.getString("error");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(Perfil_Activity.this, error, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
                catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        //Botão para editar perfil
        FloatingActionButton btnPerfilEditar = findViewById(R.id.btnPerfilEditar);
        btnPerfilEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Perfil_Activity.this, EditarPerfil_Activity.class);
                startActivity(i);
            }
        });


        //Botão para alterar a senha
        Button btnPerfilAlterarSenha = findViewById(R.id.btnPerfilAlterarSenha);
        btnPerfilAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Perfil_Activity.this, AlterarSenha_Activity.class);
                startActivity(i);
            }
        });


        //Botão para excluir a conta
        Button btnPerfilExcluirConta = findViewById(R.id.btnPerfilExcluirConta);
        btnPerfilExcluirConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exibirConfirmacao();
            }
        });


        //Botão parar salvar as informações do perfil
        Button btnPerfilSalvar = findViewById(R.id.btnPerfilSalvar);
        btnPerfilSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Perfil_Activity.this, Main_Activity.class);
                startActivity(i);
            }
        });

    }


    public void exibirConfirmacao(){
        AlertDialog.Builder msgBox = new AlertDialog.Builder(this);
        msgBox.setTitle("Deseja mesmo excluir a conta?");
        msgBox.setIcon(android.R.drawable.ic_delete);



        //Mensagem positiva de exclusão
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String email = Config.getLogin(Perfil_Activity.this);
                String senha = Config.getPassword(Perfil_Activity.this);

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpRequest httpRequest = new HttpRequest(Config.CAD_APP_URL + "mobile_excluir_perfil.php", "POST", "UTF-8");
                        httpRequest.setBasicAuth(email, senha);

                        try {
                            InputStream is = httpRequest.execute();
                            String result = Utils.inputStream2String(is, "UTF-8");
                            httpRequest.finish();

                            JSONObject jsonObject = new JSONObject(result);
                            int success = jsonObject.getInt("success");
                            if (success == 1) {

                                Toast.makeText(Perfil_Activity.this, "Excluindo", Toast.LENGTH_SHORT).show();

                                Intent i = new Intent(Perfil_Activity.this, Login_Activity.class);
                                startActivity(i);



                            }
                            else{

                                String error = jsonObject.getString("error");

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Toast.makeText(Perfil_Activity.this, error, Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }
                        catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        // Mensagem negativa de exclusão
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(Perfil_Activity.this, Perfil_Activity.class);
                startActivity(i);
            }
        });

        msgBox.show();
    }



}