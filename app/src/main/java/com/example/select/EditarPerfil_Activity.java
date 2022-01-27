package com.example.select;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.select.util.Config;
import com.example.select.util.HttpRequest;
import com.example.select.util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EditarPerfil_Activity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_perfil);

        //Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.tbEditarPerfil);
        setSupportActionBar(toolbar);  //Passa a ser a toolbar principal da aplicação

        String email = Config.getLogin(EditarPerfil_Activity.this);
        String senha = Config.getPassword(EditarPerfil_Activity.this);


        Button btnEditar = findViewById(R.id.btnEditarSalvar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etNome = findViewById(R.id.etEditarNome);
                EditText etTelefone = findViewById(R.id.etEditarTelefone);
                String nome = etNome.getText().toString();
                String telefone = etTelefone.getText().toString();

                ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        HttpRequest httpRequest = new HttpRequest(Config.CAD_APP_URL + "mobile_alterar_perfil.php", "POST", "UTF-8");
                        httpRequest.setBasicAuth(email, senha);
                        httpRequest.addParam("nome", nome);
                        httpRequest.addParam("telefone", telefone);

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
                                        Toast.makeText(EditarPerfil_Activity.this, "Dados alterados com sucesso", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(EditarPerfil_Activity.this, Perfil_Activity.class);
                                        startActivity(i);
                                        finish();
                                    }
                                });

                            }
                            else {

                                String error = jsonObject.getString("error");

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                        Toast.makeText(EditarPerfil_Activity.this, error, Toast.LENGTH_SHORT).show();
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


    }

}
