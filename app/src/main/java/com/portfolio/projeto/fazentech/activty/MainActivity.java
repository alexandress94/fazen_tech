package com.portfolio.projeto.fazentech.activty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.portfolio.projeto.fazentech.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Esconder a ActionBar
        getSupportActionBar().hide();
        Não será utilizado no projeto, será criado uma ActionBar customizada
         */

        //Exibir a imagem de abertura do app por alguns segundos
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Está ação irá abrir a actvity que for informada
                abrirAutenticacao();
            }
        }, 3000);
    }
    //Metodo para chamar outra tela para autenticacao de usuario
    private void abrirAutenticacao(){
        //Criar um objeto Intent para informar a actvity atual e a actyvity que será sobreposta
        Intent i = new Intent(MainActivity.this, AutenticacaoActivity.class);
        startActivity(i);
        finish();
    }
}