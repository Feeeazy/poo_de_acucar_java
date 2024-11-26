package database;

import models.ItemHistorico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class HistoricoVendasDB {
    private Connection conexaoBanco;

    public HistoricoVendasDB() {
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        this.conexaoBanco = conexaoBanco.conectar();
    }

    public ArrayList<ItemHistorico> listarVendas() {

        ArrayList<ItemHistorico> produtos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM relatorio_vendas;";

            PreparedStatement stmt = conexaoBanco.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                ItemHistorico itemHistorico = new ItemHistorico();

                itemHistorico.setVenda_id(res.getInt("venda_id"));
                itemHistorico.setData_de_venda(res.getDate("data_de_venda"));
                itemHistorico.setProduto(res.getString("produto"));
                itemHistorico.setQuantidade(res.getInt("quantidade"));
                itemHistorico.setPreco(res.getDouble("preco"));
                itemHistorico.setTotal(res.getDouble("total"));
                itemHistorico.setPagamento(res.getString("pagamento"));
                itemHistorico.setCliente(res.getString("cliente"));


                produtos.add(itemHistorico);
            }

        } catch (SQLException err) {
            System.err.println("Erro ao listar os clientes: " + err.getMessage());
        } finally {
            if (conexaoBanco != null) {
                try {
                    conexaoBanco.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao desconectar do banco de dados: " + e.getMessage());
                }
            }
        }

        return produtos;
    }

    public ArrayList<ItemHistorico> filtraVendas(String filtro, String tipoFiltro) {
        ArrayList<ItemHistorico> produtos = new ArrayList<>();

        String colunaFiltro = Objects.equals(tipoFiltro, "CLIENTE") ? "cliente" : "produto";

        try {
            String sql = "SELECT * FROM relatorio_vendas WHERE " + colunaFiltro + " LIKE '%" + filtro + "%'";

            PreparedStatement stmt = conexaoBanco.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                ItemHistorico itemHistorico = new ItemHistorico();

                itemHistorico.setVenda_id(res.getInt("venda_id"));
                itemHistorico.setData_de_venda(res.getDate("data_de_venda"));
                itemHistorico.setProduto(res.getString("produto"));
                itemHistorico.setPreco(res.getDouble("quantidade"));
                itemHistorico.setTotal(res.getDouble("preco"));
                itemHistorico.setPagamento(res.getString("pagamento"));
                itemHistorico.setCliente(res.getString("cliente"));

                produtos.add(itemHistorico);
            }


        } catch (SQLException err) {
            System.err.println("Erro ao listar os clientes: " + err.getMessage());
        } finally {
            if (conexaoBanco != null) {
                try {
                    conexaoBanco.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao desconectar do banco de dados: " + e.getMessage());
                }
            }
        }

        return produtos;
    }
}

