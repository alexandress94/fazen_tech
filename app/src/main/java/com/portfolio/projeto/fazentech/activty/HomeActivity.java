package com.portfolio.projeto.fazentech.activty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.portfolio.projeto.fazentech.R;
import com.portfolio.projeto.fazentech.adapter.AdapterProdutos;
import com.portfolio.projeto.fazentech.helper.ConfiguracaoFirebase;
import com.portfolio.projeto.fazentech.listener.RecyclerItemClickListener;
import com.portfolio.projeto.fazentech.model.ItemProdutos;
import com.portfolio.projeto.fazentech.model.Pedido;
import com.portfolio.projeto.fazentech.model.Produto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private FirebaseAuth autenticacao;
    private MaterialSearchView searchView;
    private RecyclerView recyclerView;
    private List<Produto> listaProdutos = new ArrayList<>();
    private List<ItemProdutos> itensCarrinho = new ArrayList<>();
    private DatabaseReference databaseRef;
    private FloatingActionButton fab;
    private TextView txtCarrinhoTotal;
    private TextView txtCarrinhoQtd;
    private Pedido pedidoRecuperado;
    private ItemProdutos itemProdutos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fab = findViewById(R.id.fab_FloatingButton);
        txtCarrinhoTotal = findViewById(R.id.txt_CarrinhoTotal);
        txtCarrinhoQtd = findViewById(R.id.txt_CarrinhoQtd);

        itemProdutos = new ItemProdutos();

        fabCliqueNoBotaoFlutante();

        //Metodo para criar array de produtos
        criarListaProdutos();

        inicializarComponentes();
        //Inicializa objeto para autenticao
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        databaseRef = ConfiguracaoFirebase.getFirebase();

        //Sobrescrever para adicionar um titulo a Toolbar
        Toolbar tbrToolbar = findViewById(R.id.tbr_Toolbar);
        tbrToolbar.setTitle("FazenTECH");
        setSupportActionBar(tbrToolbar);

        //Configurar Adapter
        AdapterProdutos adapter = new AdapterProdutos(listaProdutos);

        //Filtrar os produtos com o SeachView
        searchView.setHint("Digite para pesquisa");


        //Configurar RecyclerView
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        //Evento de clique no RecyclerView
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Capturando a possicão do clique
                Produto prod = listaProdutos.get(position);
                //AlertDialog no clique do objeto RecyclerView
                confirmarQuantidade(position);

                //Toast.makeText(getApplicationContext(),"Item selecionado " + prod.getNome(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        }));


    }

    //AlertDialog para informar a quantidade na seleção do produto
    public void confirmarQuantidade(final int posicao){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quantidade");
        builder.setMessage("Digite a quantidade");
        //Referenciando um componente XML
        final EditText edtQuantidade = new EditText(this);
        edtQuantidade.setText("1");

        builder.setView(edtQuantidade);

        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String strQuantidade = edtQuantidade.getText().toString();
                final Produto produtoSelecionado = listaProdutos.get(posicao);

                //ItemProdutos itemProdutos = new ItemProdutos();

                itemProdutos.setNomeProduto(produtoSelecionado.getNome());
                itemProdutos.setPrecoProduto(produtoSelecionado.getPrecoProduto());
                itemProdutos.setQuantidadeProduto(Integer.parseInt(strQuantidade));
                itemProdutos.setImgImagem(produtoSelecionado.getUrlImagem());

                itensCarrinho.add(itemProdutos);
                if (pedidoRecuperado == null){
                    pedidoRecuperado = new Pedido( );
                }
                pedidoRecuperado.setNomeItemProduto(produtoSelecionado.getNome());
                pedidoRecuperado.setItens(itensCarrinho);

                int qdtItensCarrinho = 0;
                Double totalCarrinho = 0.0;

                for (ItemProdutos item: itensCarrinho){
                    int qtd = item.getQuantidadeProduto();
                    Double preco = item.getPrecoProduto();

                    totalCarrinho += (qtd*preco);
                    qdtItensCarrinho += qtd;
                }
                DecimalFormat df = new DecimalFormat("0.00");

                txtCarrinhoQtd.setText(String.valueOf("Qtd: "+ qdtItensCarrinho));
                txtCarrinhoTotal.setText("R$ " + df.format(totalCarrinho));




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

    //Inflar o menu de opções - Adicionar itens selecionaveis de menu (toolbar)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);

        //Configurar botão de pesquisa de itens
        MenuItem item = menu.findItem(R.id.men_Pesquisar);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }

    //Ação para executar quando um item do menu toolbar for selecionado
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.men_Sair:
                deslogarUsuario();
                break;
            case R.id.men_Configuracoes:
                abrirConfiguracoes();
        }
        return super.onOptionsItemSelected(item);
    }

    private void inicializarComponentes(){
        searchView = findViewById(R.id.materialSeachView);
        recyclerView = findViewById(R.id.rcv_ProdutosFinalizar);
    }

    private void deslogarUsuario(){
        try {
            autenticacao.signOut();
            finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //Configurar Intent configuracoes
    private void abrirConfiguracoes(){
        startActivity(new Intent(HomeActivity.this, ConfiguracoesActivity.class));
    }

    private void fabCliqueNoBotaoFlutante(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FinzalizarActivity.class);

                intent.putExtra("itemProd", itemProdutos);
                startActivity(intent);

            }
        });
    }

    //Cirando a lista de produtos
    private void criarListaProdutos(){
        Produto prod = new Produto(R.drawable.abacaxi,"Abacaxi - KG", 5.00);
        this.listaProdutos.add(prod);

        prod = new Produto(R.drawable.alface,"Alfase - UN",1.50);
        this.listaProdutos.add(prod);

        prod = new Produto(R.drawable.alho,"Alho - KG",0.59);
        this.listaProdutos.add(prod);

        prod = new Produto(R.drawable.maca,"Maçã - KG",2.59);
        this.listaProdutos.add(prod);

        prod = new Produto(R.drawable.bananaprata,"Banana da prata - KG",2.20);
        this.listaProdutos.add(prod);

        prod = new Produto(R.drawable.batata,"Batata - KG",2.79);
        this.listaProdutos.add(prod);


    }

}
