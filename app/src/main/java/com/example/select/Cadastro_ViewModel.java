package com.example.select;

import android.hardware.lights.LightState;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class Cadastro_ViewModel extends ViewModel {

    MutableLiveData<List<Cadastro_Activity>> cadastro;


    public LiveData<List<Cadastro_Activity>> getCadastro(){

        if (cadastro == null){
            cadastro = new MutableLiveData<List<Cadastro_Activity>>();
            loadCadastro();

        }


        return cadastro;

    }

    // "Puxa" a conexão com o servidor
    private void loadCadastro(){


    }


}
