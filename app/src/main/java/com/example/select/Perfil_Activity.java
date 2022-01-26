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

    TextView email = findViewById(R.id.tvPerfilEmail);
    TextView nome = findViewById(R.id.tvPerfilNome);
    TextView dataNasc = findViewById(R.id.tvPerfilDataNasc);
    TextView cpf = findViewById(R.id.tvPerfilCpf);
    TextView telefone = findViewById(R.id.tvPerfilTelefone);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        //Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.tbPerfil);
        setSupportActionBar(toolbar);  //Passa a ser a toolbar principal da aplicação


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


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest(Config.CAD_APP_URL + "perfil.php", "GET", "UTF-8");

                try {
                    InputStream is = httpRequest.execute();
                    String result = Utils.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    int success = jsonObject.getInt("success");
                    if (success == 1) {
                        JSONArray jsonArray = jsonObject.getJSONArray("lista");
                        JSONObject jsonLista = jsonArray.getJSONObject(0);

                        nome.setText(jsonLista.getString("nome"));
                        email.setText(jsonLista.getString("email"));
                        telefone.setText(jsonLista.getString("telefone"));
                        cpf.setText(jsonLista.getString("cpf"));
                        dataNasc.setText(jsonLista.getString("dat_nasc"));

                    }

                }
                catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

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



                Toast.makeText(Perfil_Activity.this, "Excluindo", Toast.LENGTH_SHORT).show();

                HttpRequest httpRequest = new HttpRequest(Config.CAD_APP_URL + "excluir_perfil.php", "POST", "UTF-8");
                httpRequest.addParam("email", email.toString());

                Intent i = new Intent(Perfil_Activity.this, Login_Activity.class);
                startActivity(i);
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


    //Função deletar perfil
    public void deletarConta (){

    }



}