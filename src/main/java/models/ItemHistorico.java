package models;

import java.util.Date;

public class ItemHistorico {

    public int venda_id;
    public Date data_de_venda;
    public String produto;
    public int quantidade;
    public double preco;
    public double total;
    public String pagamento;
    public String cliente;

    public ItemHistorico(int venda_id, Date data_de_venda, String produto, int quantidade, double preco, double total, String pagamento, String cliente) {
        this.venda_id = venda_id;
        this.data_de_venda = data_de_venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
        this.total = total;
        this.pagamento = pagamento;
    }

    public ItemHistorico() {

    }

    public int getVenda_id() {
        return venda_id;
    }

    public void setVenda_id(int venda_id) {
        this.venda_id = venda_id;
    }

    public Date getData_de_venda() {
        return data_de_venda;
    }

    public void setData_de_venda(Date data_de_venda) {
        this.data_de_venda = data_de_venda;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
