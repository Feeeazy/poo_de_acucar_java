package database;

import models.Cliente;

import java.sql.*;
import java.util.ArrayList;

public class ClientesDB {

    private Connection conexaoBanco;

    public ClientesDB() {
        ConexaoBanco conexaoBanco = new ConexaoBanco();
        this.conexaoBanco = conexaoBanco.conectar();
    }

    public ArrayList<Cliente> listarClientes() {

        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM cliente";

            PreparedStatement stmt = conexaoBanco.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Cliente cliente = new Cliente();

                cliente.setNome(res.getString("cliente_nome"));
                cliente.setCpf(res.getString("cliente_CPF"));
                cliente.setEmail(res.getString("cliente_contato"));

                clientes.add(cliente);
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

        return clientes;
    }

    public long buscarIdCliente(String nome) {
        long cliente_id = 0L;

        try{
            String sql = "SELECT cliente_CPF FROM cliente WHERE cliente_nome = '" + nome + "'";

            PreparedStatement stmt = conexaoBanco.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();
            res.next();

            cliente_id = res.getLong("cliente_CPF");

        } catch (SQLException err) {
            System.err.println("Erro ao identificar o client_CPF: " + err.getMessage());
        } finally {
            if (conexaoBanco != null) {
                try {
                    conexaoBanco.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao desconectar do banco de dados: " + e.getMessage());
                }
            }
        }

        return cliente_id;
    }
}