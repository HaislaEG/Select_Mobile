package com.example.select.util;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.select.Login_Activity;
import com.example.select.Perfil_Activity;
import com.example.select.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GetDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final String login = Config.getLogin(GetDataActivity.this);
        final String password = Config.getPassword(GetDataActivity.this);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpRequest httpRequest = new HttpRequest(Config.CAD_APP_URL + "get_data.php", "GET", "UTF-8");
                httpRequest.setBasicAuth(login, password);

                try {
                    InputStream is = httpRequest.execute();
                    String result = Utils.inputStream2String(is, "UTF-8");
                    httpRequest.finish();

                    JSONObject jsonObject = new JSONObject(result);
                    final int success = jsonObject.getInt("success");
                    if(success == 1) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(GetDataActivity.this, Perfil_Activity.class);
                                startActivity(i);
                            }
                        });

                    }
                    else {
                        final String error = jsonObject.getString("error");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(GetDataActivity.this, error, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
