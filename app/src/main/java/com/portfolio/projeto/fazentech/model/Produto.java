package com.portfolio.projeto.fazentech.model;

import java.io.Serializable;

public class Produto implements Serializable {
    private int urlImagem;
    private String nome;
    private Double precoProduto;

    public Produto(){

    }

    public Produto(int urlImagem, String nome, Double precoProduto) {
        this.urlImagem = urlImagem;
        this.nome = nome;
        this.precoProduto = precoProduto;
    }

    public int getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(int urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(Double precoProduto) {
        this.precoProduto = precoProduto;
    }
}
