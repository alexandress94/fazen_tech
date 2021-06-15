package com.portfolio.projeto.fazentech.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.portfolio.projeto.fazentech.R;
import com.portfolio.projeto.fazentech.model.ItemProdutos;

import java.util.List;

public class AdapterFinalizar extends RecyclerView.Adapter<AdapterFinalizar.MyViewHolder> {

    private List<ItemProdutos> ip;

    public AdapterFinalizar(List<ItemProdutos> lista) {
        this.ip = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_finalizar, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemProdutos iProd = ip.get(position);

        holder.nomeProduto.setText(iProd.getNomeProduto());
        holder.valorProduto.setText("R$ "+iProd.getPrecoProduto());
        holder.imagemProduto.setImageResource(iProd.getImgImagem());
    }

    @Override
    public int getItemCount() {
        return ip.size();
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
