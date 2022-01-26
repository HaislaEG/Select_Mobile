package com.example.select;

import android.os.Bundle;
import android.widget.EditText;

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

    EditText nome = findViewById(R.id.etEditarNome);
    EditText telefone = findViewById(R.id.etEditarTelefone);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_perfil);

        //Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.tbEditarPerfil);
        setSupportActionBar(toolbar);  //Passa a ser a toolbar principal da aplicação


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest(Config.CAD_APP_URL + "perfil.php", "POST", "UTF-8");

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
                        telefone.setText(jsonLista.getString("telefone"));

                    }

                }
                catch (IOException | JSONException e) {
                    e.printStackTrace();
                }

            }

        });


    }

}
