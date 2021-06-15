package com.portfolio.projeto.fazentech.activty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.portfolio.projeto.fazentech.R;

public class ProdutosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        //Metodo para carregar os componentes iniciais
        inicializarComponentes();

        //Sobrescrever para adicionar um titulo a Toolbar
        Toolbar tbrToolbar = findViewById(R.id.tbr_Toolbar);
        tbrToolbar.setTitle("Finalizar Pedidos");
        setSupportActionBar(tbrToolbar);
        //Adicionar botão de voltar no toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void inicializarComponentes(){

    }

}