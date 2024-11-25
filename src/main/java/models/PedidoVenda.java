package models;

public class PedidoVenda {

    public int id_pedido;
    public int id_venda;
    public int id_produto;
    public int quantidade;
    public double preco_unitario;
    public double preco_total;

    public PedidoVenda(int id_pedido, int id_venda, int id_produto, int quantidade, double preco_unitario, double preco_total) {
        this.id_pedido = id_pedido;
        this.id_venda = id_venda;
        this.id_produto = id_produto;
        this.quantidade = quantidade;
        this.preco_unitario = preco_unitario;
        this.preco_total = preco_total;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPreco_unitario() {
        return preco_unitario;
    }

    public void setPreco_unitario(double preco_unitario) {
        this.preco_unitario = preco_unitario;
    }

    public double getPreco_total() {
        return preco_total;
    }

    public void setPreco_total(double preco_total) {
        this.preco_total = preco_total;
    }
}
