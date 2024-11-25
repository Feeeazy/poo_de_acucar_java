package database;

import models.Produto;
import models.Venda;

import java.sql.*;
import java.util.ArrayList;

public class VendaDB {

    private Connection conexaoBanco;

    public VendaDB() {
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        this.conexaoBanco = conexaoBanco.conectar();
    }

    public String getNovoCodigoVenda() {

        String codigoVenda = null;
        try {
            String sql = "SELECT MAX(venda_id) + 1 as new_code from venda;";

            PreparedStatement stmt = conexaoBanco.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();

            res.next();
            codigoVenda = res.getString(1);


        } catch (SQLException err) {
            System.err.println("Erro ao identificar o novo codigo de venda: " + err.getMessage());
        } finally {
            if (conexaoBanco != null) {
                try {
                    conexaoBanco.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao desconectar do banco de dados: " + e.getMessage());
                }
            }
        }

        return codigoVenda;
    };

    public void inserirVenda(Venda venda) {

        try{
            String sql = "INSERT INTO venda (venda_data, cliente_CPF, pagamento) VALUES (?, ?, ?)";
                    
            PreparedStatement stmtVenda = conexaoBanco.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmtVenda.setDate(1, new Date(venda.getData_venda().getTime()));
            stmtVenda.setLong(2, venda.getId_cliente());
            stmtVenda.setString(3, venda.getTipo_pagamento());

            stmtVenda.executeUpdate();
            
        } catch (SQLException err) {
            System.err.println("Erro ao inserir nova venda: " + err.getMessage());
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
