package models;

public class ItemVenda {

    public int venda_id;
    public int quantidade;
    public int produto_id;
    public double valor;

    public ItemVenda(int venda_id, int quantidade, int produto_id, double valor) {
        this.venda_id = venda_id;
        this.quantidade = quantidade;
        this.produto_id = produto_id;
        this.valor = valor;
    }

    public ItemVenda() {

    }

    public int getVenda_id() {
        return venda_id;
    }

    public void setVenda_id(int venda_id) {
        this.venda_id = venda_id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(int produto_id) {
        this.produto_id = produto_id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
