package com.example.select;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Perfil_Activity extends AppCompatActivity {

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


        //Botão para adicionar um endereço
        Button btnPerfilAddEndereco = findViewById(R.id.btnPerfilAddEndereco);
        btnPerfilAddEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Perfil_Activity.this, AdicionarEndereco_Activity.class);
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


        //Botão para cancelar as possíveis alterações
        Button btnPerfilCancelar = findViewById(R.id.btnPerfilCancelar);
        btnPerfilCancelar.setOnClickListener(new View.OnClickListener() {
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
                Toast.makeText(Perfil_Activity.this, "Excluindo", Toast.LENGTH_SHORT).show();
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


}