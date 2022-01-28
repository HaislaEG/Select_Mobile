package com.example.select;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.select.util.Config;
import com.example.select.util.GetDataActivity;
import com.example.select.util.HttpRequest;
import com.example.select.util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Login_Activity extends AppCompatActivity {


    static int RESULT_REQUEST_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        checkForPermissions(permissions);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etEmail = findViewById(R.id.etLoginEmail);
                EditText etSenha = findViewById(R.id.etLoginSenha);

                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();

                // Verifica se os dados foram digitados
                if (email.equals("")){

                    Toast.makeText(Login_Activity.this, "Email não inserido", Toast.LENGTH_SHORT).show();

                }
                else if (senha.equals("")){

                    Toast.makeText(Login_Activity.this, "Senha não inserida", Toast.LENGTH_SHORT).show();

                }
                else{

                    loadCadastro(email, senha);
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
                HttpRequest httpRequest = new HttpRequest(Config.CAD_APP_URL + "mobile_login.php", "POST", "UTF-8");
                httpRequest.setBasicAuth(Email, Senha);

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
                                Config.setLogin(Login_Activity.this, Email);
                                Config.setPassword(Login_Activity.this, Senha);
                                Toast.makeText(Login_Activity.this, "Login realizado com sucesso", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Login_Activity.this, Main_Activity.class);
                                startActivity(i);
                                finish();
                            }
                        });
                    }
                    else {
                        final String error = jsonObject.getString("error");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Login_Activity.this, error, Toast.LENGTH_LONG).show();
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


    /*   CÓDIGO QUE FAZ A VERIFICAÇÃO DAS PERMISSÕES NECESSÁRIAS PARA O FUNCIONAMENTO DA APLIACAÇÃO   */
    // Verifica as permissões necessárias para o funcionamento do apicativo
    private void checkForPermissions (List<String> permissions) {

        List<String> permissionsNotGranted = new ArrayList<>();

        for (String permission : permissions) {
            if (!hasPermission(permission)){
                permissionsNotGranted.add(permission);
            }
        }

        // Requerimento das permissões que não existem
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(permissionsNotGranted.toArray(new String[permissionsNotGranted.size()]), RESULT_REQUEST_PERMISSION);
        }

    }


    //Checa se as permissões necessárias ao funcionamento do apliativo já existem ou não
    private boolean hasPermission (String permission){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ActivityCompat.checkSelfPermission(Login_Activity.this, permission) == PackageManager.PERMISSION_GRANTED;
        }

        return false;
    }


    // Verifica as permissões e Avisa ao usuário que elas são necessárias para o funcionamento do aplicativo
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        List<String> permissionsRejected = new ArrayList<>();

        if (requestCode == RESULT_REQUEST_PERMISSION){
            for (String permission : permissions) {
                if (!hasPermission(permission)) {
                    permissionsRejected.add(permission);
                }
            }
        }

        if (permissionsRejected.size() > 0){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(shouldShowRequestPermissionRationale(permissionsRejected.get(0))){
                    new AlertDialog.Builder(Login_Activity.this).setMessage("Para usar essa app é preciso conceder essas permissões").
                            setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(permissionsRejected.toArray(new String[permissionsRejected.size()]), RESULT_REQUEST_PERMISSION);
                                }
                            }).create().show();
                }
            }
        }
    }


}