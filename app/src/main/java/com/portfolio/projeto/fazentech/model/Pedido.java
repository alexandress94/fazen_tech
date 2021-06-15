package com.portfolio.projeto.fazentech.model;

import java.util.List;

public class Pedido {
    private String nomeItemProduto;
    private List<ItemProdutos> itens;
    private Double total;
    private String status = "Pendente";
    private int metodoPagamento;
    private String observaco;

    public Pedido() {
    }

    public Pedido(String nomeItemProduto){
        setNomeItemProduto(nomeItemProduto);
    }

    public String getNomeItemProduto() {
        return nomeItemProduto;
    }

    public void setNomeItemProduto(String nomeItemProduto) {
        this.nomeItemProduto = nomeItemProduto;
    }

    public List<ItemProdutos> getItens() {
        return itens;
    }

    public void setItens(List<ItemProdutos> itens) {
        this.itens = itens;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(int metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getObservaco() {
        return observaco;
    }

    public void setObservaco(String observaco) {
        this.observaco = observaco;
    }
}
