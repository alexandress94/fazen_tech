package com.portfolio.projeto.fazentech.activty;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.portfolio.projeto.fazentech.R;
import com.portfolio.projeto.fazentech.adapter.AdapterFinalizar;
import com.portfolio.projeto.fazentech.adapter.AdapterProdutos;
import com.portfolio.projeto.fazentech.model.ItemProdutos;
import com.portfolio.projeto.fazentech.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class FinzalizarActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private List<ItemProdutos> itProdutos = new ArrayList<>();
    private AdapterFinalizar adapterFinalizar;
    private Button btnEnviarPedido;
    private int metodoPagamento;
    private TextView qtd;
    private  TextView vlr;
    //private ItemProdutos iPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finzalizar);

        Bundle bundle = getIntent().getExtras();
        ItemProdutos itemProdutos = (ItemProdutos) bundle.getSerializable("itemProd");



        for (ItemProdutos i: itProdutos){

        }

        itProdutos.add(itemProdutos);

        btnEnviarPedido = findViewById(R.id.btn_EvniarPedido);
        qtd = findViewById(R.id.txt_CarrinhoQtdFin);
        vlr = findViewById(R.id.txt_CarrinhoTotalFin);

        //Sobrescrever para adicionar um titulo a Toolbar
        Toolbar tbrToolbar = findViewById(R.id.tbr_Toolbar);
        tbrToolbar.setTitle("Solicitando pedido");
        setSupportActionBar(tbrToolbar);
        //Adicionar botão de voltar no toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Configurar Adapter
        adapterFinalizar = new AdapterFinalizar(itProdutos);

        recyclerView = findViewById(R.id.rcv_ProdutosFinalizar);

        //Configurar RecyclerView
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterFinalizar);

        criarListaProdutos();
        btnEnviarPedido();
    }


    private void criarListaProdutos() {
        /*

        iPro = new ItemProdutos("Alface",1,1.50 ,R.drawable.alface);
        this.itemProdutos.add(iPro);

        //qtd.setText(itProd.getQuantidadeProduto());
        //vlr.setText(String.valueOf(itProd.getPrecoProduto()));

        */
    }

    private void btnEnviarPedido(){
        btnEnviarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Teste", Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(FinzalizarActivity.this);
                builder.setTitle("Selecione uma opção para pagamento");

                CharSequence[] itens = new CharSequence[]{
                    "Dinheiro","Cartão de Crédito"
                };

                builder.setSingleChoiceItems(itens, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        metodoPagamento = i;
                    }
                });

                final EditText edtTObservacao = new EditText(FinzalizarActivity.this);
                edtTObservacao.setHint("Digite sua observação");
                builder.setView(edtTObservacao);

                builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //String observacao = edtTObservacao.getText().toString();
                        Toast.makeText(getApplicationContext(), "Pedido enviado com sucesso!", Toast.LENGTH_LONG).show();

                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}