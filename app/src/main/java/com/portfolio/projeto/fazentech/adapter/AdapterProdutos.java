package com.portfolio.projeto.fazentech.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.portfolio.projeto.fazentech.R;
import com.portfolio.projeto.fazentech.model.Produto;

import java.util.List;

public class AdapterProdutos extends RecyclerView.Adapter<AdapterProdutos.MyViewHolder> {

    private List<Produto> listaProdutos;

    public AdapterProdutos(List<Produto> lista) {
        this.listaProdutos = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemProduto = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_produtos, parent, false);

        return new MyViewHolder(itemProduto);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Produto prod = listaProdutos.get(position);

        holder.nomeProduto.setText(prod.getNome());
        holder.valorProduto.setText("R$ "+prod.getPrecoProduto());
        holder.imagemProduto.setImageResource(prod.getUrlImagem());
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nomeProduto;
        TextView valorProduto;
        ImageView imagemProduto;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeProduto = itemView.findViewById(R.id.txt_NomeFinProduto);
            valorProduto = itemView.findViewById(R.id.txt_ValorFinProduto);
            imagemProduto = itemView.findViewById(R.id.img_ImagemFinProduto);
        }
    }

}
