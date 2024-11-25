package database;

import models.Cliente;
import models.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class ProdutosDB {

    private Connection conexaoBanco;

    public ProdutosDB() {
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        this.conexaoBanco = conexaoBanco.conectar();
    }

    public ArrayList<Produto> listarProdutos() {

        ArrayList<Produto> produtos = new ArrayList<>();

        try {
            String sql = "SELECT * FROM produtos";

            PreparedStatement stmt = conexaoBanco.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Produto produto = new Produto();

                produto.setCodigo(res.getInt("produto_codigo"));
                produto.setNome(res.getString("produto_nome"));
                produto.setQuantidade(res.getInt("produto_quantidade"));
                produto.setPreco(res.getDouble("produto_valor"));

                produtos.add(produto);
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

    public ArrayList<Produto> filtraProdutos(String filtro, String tipoFiltro) {
        ArrayList<Produto> produtos = new ArrayList<>();

        String colunaFiltro = Objects.equals(tipoFiltro, "NOME") ? "produto_nome" : "produto_codigo";

        try {
            String sql = "SELECT * FROM produtos WHERE " + colunaFiltro + " LIKE '%" + filtro + "%'";

            PreparedStatement stmt = conexaoBanco.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Produto produto = new Produto();

                produto.setCodigo(res.getInt("produto_codigo"));
                produto.setNome(res.getString("produto_nome"));
                produto.setQuantidade(res.getInt("produto_quantidade"));
                produto.setPreco(res.getDouble("produto_valor"));

                produtos.add(produto);
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
