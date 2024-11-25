package database;

import models.ItemVenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ItemVendaDB {

    private Connection conexaoBanco;

    public ItemVendaDB() {
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        this.conexaoBanco = conexaoBanco.conectar();
    }

    public void inserirItemVenda(ItemVenda itemVenda) {

        try{
            String sql = "INSERT INTO item_venda (venda_id, itemV_quantidade, itemV_preco_unitario, produto_id) VALUES (?, ?, ?, ?)";

            PreparedStatement stmtItemVenda = conexaoBanco.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmtItemVenda.setInt(1, itemVenda.getVenda_id());
            stmtItemVenda.setInt(2, itemVenda.getQuantidade());
            stmtItemVenda.setDouble(3, itemVenda.getValor());
            stmtItemVenda.setInt(4, itemVenda.getProduto_id());

            stmtItemVenda.executeUpdate();


        } catch (SQLException err) {
            System.err.println("Erro ao inserir novo itemVenda: " + err.getMessage());
        } finally {
            if (conexaoBanco != null) {
                try {
                    conexaoBanco.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao desconectar do banco de dados: " + e.getMessage());
                }
            }
        }
    }

}
