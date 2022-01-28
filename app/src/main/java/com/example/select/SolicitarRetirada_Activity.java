package com.example.select;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.example.select.util.Config;
import com.example.select.util.HttpRequest;
import com.example.select.util.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SolicitarRetirada_Activity extends AppCompatActivity {

    private static final int RESULT_TAKE_PICTURE = 2;
    String currentPhotoPath = "";  // Diretório atual da foto escolhida
    List<String> photos = new ArrayList<>();


    public String getCurrentPhotoPath() {
        return currentPhotoPath;
    }

    public void setCurrentPhotoPath(String currentPhotoPath) {
        this.currentPhotoPath = currentPhotoPath;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solicitar_retirada);

        //Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.tbSolicitarRetirada);
        setSupportActionBar(toolbar);  //Passa a ser a toolbar principal da aplicação

        String email = Config.getLogin(SolicitarRetirada_Activity.this);
        String senha = Config.getPassword(SolicitarRetirada_Activity.this);


        Button btnSolicitarProsseguir = findViewById(R.id.btnSolicitarProsseguir);
        btnSolicitarProsseguir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText etRetiradaRua = findViewById(R.id.etRetiradaRua);
                String rua = etRetiradaRua.getText().toString();
                if(rua.isEmpty()){
                    Toast.makeText(SolicitarRetirada_Activity.this, "O campo 'Rua' não foi preenchido", Toast.LENGTH_SHORT).show();
                    return;
                }

                EditText etRetiradaBairro = findViewById(R.id.etRetiradaBairro);
                String bairro = etRetiradaBairro.getText().toString();
                if(bairro.isEmpty()){
                    Toast.makeText(SolicitarRetirada_Activity.this, "O campo 'Bairro' não foi preenchido", Toast.LENGTH_SHORT).show();
                    return;
                }

                EditText etRetiradaCidade = findViewById(R.id.etRetiradaCidade);
                String cidade = etRetiradaCidade.getText().toString();
                if(cidade.isEmpty()){
                    Toast.makeText(SolicitarRetirada_Activity.this, "O campo 'Cidade' não foi preenchido", Toast.LENGTH_SHORT).show();
                    return;
                }

                EditText etRetiradaNumero = findViewById(R.id.etRetiradaNumero);
                String numero = etRetiradaNumero.getText().toString();
                if(numero.isEmpty()){
                    Toast.makeText(SolicitarRetirada_Activity.this, "O campo 'Número' não foi preenchido", Toast.LENGTH_SHORT).show();
                    return;
                }

                EditText etRetiradaUF = findViewById(R.id.etRetiradaUF);
                String uf = etRetiradaUF.getText().toString();
                if(uf.isEmpty()){
                    Toast.makeText(SolicitarRetirada_Activity.this, "O campo 'UF' não foi preenchido", Toast.LENGTH_SHORT).show();
                    return;
                }

                EditText etRetiradaCEP = findViewById(R.id.etRetiradaCEP);
                String cep = etRetiradaCEP.getText().toString();
                if(cep.isEmpty()){
                    Toast.makeText(SolicitarRetirada_Activity.this, "O campo 'CEP' não foi preenchido", Toast.LENGTH_SHORT).show();
                    return;
                }

                EditText etRetiradaReferencia = findViewById(R.id.etReferencia);
                String referencia = etRetiradaReferencia.getText().toString();
                if(referencia.isEmpty()){
                    Toast.makeText(SolicitarRetirada_Activity.this, "O campo 'Referência' não foi preenchido", Toast.LENGTH_SHORT).show();
                    return;
                }

                CheckBox cbSolicitarVidro = findViewById(R.id.cbSolicitarVidro);
                CheckBox cbSolicitarPlastico = findViewById(R.id.cbSolicitarPlastico);
                CheckBox cbSolicitarPapel = findViewById(R.id.cbSolicitarPapel);
                CheckBox cbSolicitarMetal = findViewById(R.id.cbSolicitarMetal);
                String material = "";
                if (cbSolicitarMetal.isChecked()){
                    material = "Metal";

                }
                else if (cbSolicitarPapel.isChecked()){
                    material = "Papel";
                }
                else if (cbSolicitarPlastico.isChecked()){
                    material = "Plástico";
                }
                else if (cbSolicitarVidro.isChecked()) {
                    material = "Vidro";
                }
                else {
                    Toast.makeText(SolicitarRetirada_Activity.this, "Não foi selecionado o material a ser retirado", Toast.LENGTH_SHORT).show();
                }



                // Requisição HTTP
                ExecutorService executorService = Executors.newSingleThreadExecutor();
                String finalMaterial = material;
                executorService.execute(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void run() {


                        HttpRequest httpRequest = new HttpRequest(Config.CAD_APP_URL + "mobile_solicitar_retirada.php", "POST", "UTF-8");
                        httpRequest.setBasicAuth(email, senha);

                        httpRequest.addFile("foto", new File(currentPhotoPath));
                        httpRequest.addParam("material", finalMaterial);
                        httpRequest.addParam("rua", rua);
                        httpRequest.addParam("cep", cep);
                        httpRequest.addParam("bairro", bairro);
                        httpRequest.addParam("referencia", referencia);
                        httpRequest.addParam("uf", uf);
                        httpRequest.addParam("cidade", cidade);
                        httpRequest.addParam("numero", numero);

                        try {

                            InputStream is = httpRequest.execute();
                            String result = Utils.inputStream2String(is, "UTF-8");
                            httpRequest.finish();
                            Log.d("HTTP_REQUEST_RESULT", result);

                            // Transforma a string em dados armazenáveis para a aplicação
                            JSONObject jsonObject1 = new JSONObject(result);

                            int success = jsonObject1.getInt("success");

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    // Verifica se a retirada foi solicitada
                                    if ( success == 1 ) {

                                        Intent i = new Intent(SolicitarRetirada_Activity.this, Conclusao_Activity.class);
                                        startActivity(i);
                                        finish();

                                    }
                                    else {

                                        Toast.makeText(SolicitarRetirada_Activity.this, "Retirada não pôde ser solicitada", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(SolicitarRetirada_Activity.this, Main_Activity.class);
                                        startActivity(i);

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

        ImageView imvFoto = findViewById(R.id.imvFoto);
        imvFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });


    }


    // Função para acessar e disparar a câmera
    private void dispatchTakePictureIntent () {

        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File f = null;

        try {
            f = createImageFile();
        }
        catch (IOException e) {
            Toast.makeText(SolicitarRetirada_Activity.this, "Não foi possível criar o arquivo", Toast.LENGTH_LONG).show();
        }

        setCurrentPhotoPath(f.getAbsolutePath());  // Acessa o endereço da foto

        // Onde a foto vai ser salva
        if (f != null){
            Uri fUri = FileProvider.getUriForFile(SolicitarRetirada_Activity.this, "com.example.select.fileprovider", f);
            i.putExtra(MediaStore.EXTRA_OUTPUT, fUri);  // Quando a foto for tirada ela vai ser salva em fUri
            startActivityForResult(i, RESULT_TAKE_PICTURE);

        }
    }


    // Cria o espaço de armazenamnto da foto tirada
    private File createImageFile () throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());  //Ano, mês, dia, hora, minuto e segundo
        String imageFileName = "JPEG_" + timeStamp;
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File f = File.createTempFile(imageFileName, ".jpg", storageDir);  // Cria a pasta de armazenamento
        return f;
    }


    // Verifica se o usuário tirou ou não a foto
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_TAKE_PICTURE){

            String currentPhotoPath = getCurrentPhotoPath();

            if(resultCode == Activity.RESULT_OK){

                ImageView imvFoto = findViewById(R.id.imvFoto);
                Bitmap bitmap = Utils.getBitmap(currentPhotoPath, imvFoto.getWidth(), imvFoto.getHeight());
                imvFoto.setImageBitmap(bitmap);

            }
            else {  // Se a foto não for tirada ela vai ser excluída

                File f = new File(currentPhotoPath);
                f.delete();
                setCurrentPhotoPath("");
                Intent i = new Intent(SolicitarRetirada_Activity.this, SolicitarRetirada_Activity.class);
                startActivity(i);

            }
        }
    }

}