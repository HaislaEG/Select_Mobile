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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foto);

        //Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.tbFoto);
        setSupportActionBar(toolbar);  //Passa a ser a toolbar principal da aplicação

        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.CAMERA);

        //checkForPermissions(permissions);

        FloatingActionButton btnTirarFoto = findViewById(R.id.btnTirarFoto);
        btnTirarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Foto_Activity.this, ConfirmarFoto_Activity.class);
                startActivity(i);
            }
        });
    }


}