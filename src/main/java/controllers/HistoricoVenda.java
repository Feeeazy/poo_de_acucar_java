package controllers;

import java.util.Date;

public class HistoricoVenda {

    private int id_venda;
    private int nome_cliente;
    private int nome_produto;
    private int quantidade;
    private double valor_unitario;
    private double valor_total;
    private Date data_venda;


    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public int getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(int nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public int getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(int nome_produto) {
        this.nome_produto = nome_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor_unitario() {
        return valor_unitario;
    }

    public void setValor_unitario(double valor_unitario) {
        this.valor_unitario = valor_unitario;
    }

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }
}
