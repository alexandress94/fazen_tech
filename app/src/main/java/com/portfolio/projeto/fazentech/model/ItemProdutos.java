package com.portfolio.projeto.fazentech.model;

import java.io.Serializable;

public class ItemProdutos implements Serializable {

    private String nomeProduto;
    private int quantidadeProduto;
    private Double precoProduto;
    private int imgImagem;

    public ItemProdutos() {
    }

    public ItemProdutos(String nomeProduto, int quantidadeProduto, Double precoProduto, int imgImagem) {
        this.nomeProduto = nomeProduto;
        this.quantidadeProduto = quantidadeProduto;
        this.precoProduto = precoProduto;
        this.imgImagem = imgImagem;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidadeProduto() {
        return quantidadeProduto;
    }

    public void setQuantidadeProduto(int quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public int getImgImagem() {
        return imgImagem;
    }

    public void setImgImagem(int imgImagem) {
        this.imgImagem = imgImagem;
    }
}
