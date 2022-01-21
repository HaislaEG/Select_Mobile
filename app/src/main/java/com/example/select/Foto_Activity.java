package com.example.select;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.select.util.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Foto_Activity extends AppCompatActivity {


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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foto);

        //Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.tbFoto);
        setSupportActionBar(toolbar);  //Passa a ser a toolbar principal da aplicação


        FloatingActionButton btnTirarFoto = findViewById(R.id.btnTirarFoto);
        btnTirarFoto.setOnClickListener(new View.OnClickListener() {
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
            Toast.makeText(Foto_Activity.this, "Não foi possível criar o arquivo", Toast.LENGTH_LONG).show();
        }

        currentPhotoPath = f.getAbsolutePath();  // Acessa o endereço da foto

        // Onde a foto vai ser salva
        if (f != null){
            Uri fUri = FileProvider.getUriForFile(Foto_Activity.this, "com.example.select.fileprovider", f);
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

            currentPhotoPath = getCurrentPhotoPath();

            if(resultCode == Activity.RESULT_OK){

                ImageView imvFoto = findViewById(R.id.imvFoto);
                Bitmap bitmap = Utils.getBitmap(currentPhotoPath, imvFoto.getWidth(), imvFoto.getHeight());
                imvFoto.setImageBitmap(bitmap);


            }
            else {  // Se a foto não for tirada ela vai ser excluída

                File f = new File(currentPhotoPath);
                f.delete();
                setCurrentPhotoPath("");
                Intent i = new Intent(Foto_Activity.this, Foto_Activity.class);
                startActivity(i);

            }
        }
    }


}