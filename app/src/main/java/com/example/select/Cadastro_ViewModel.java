package com.example.select;

import android.hardware.lights.LightState;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.select.util.HttpRequest;
import com.example.select.util.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cadastro_ViewModel extends ViewModel {

    MutableLiveData<List<Cadastro_Activity>> cadastro;


    public LiveData<List<Cadastro_Activity>> getCadastro(){

        if (cadastro == null){
            cadastro = new MutableLiveData<List<Cadastro_Activity>>();
            //loadCadastro();

        }


        return cadastro;

    }


}
