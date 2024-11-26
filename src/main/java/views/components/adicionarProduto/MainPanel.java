package views.components.adicionarProduto;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.ProdutosDB;
import models.Produto;

public class MainPanel extends JPanel {
    private JTable produtosTabela;
    private DefaultTableModel tableModel;
    private FiltroPanel filtroPanel;
    private BottomPanel bottomPanel;

    public MainPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        filtroPanel = new FiltroPanel();
        bottomPanel = new BottomPanel();

        tableModel = new DefaultTableModel(new Object[]{"Código", "Nome", "Preço", "Estoque"}, 0);
        produtosTabela = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(produtosTabela);
        produtosTabela.setFillsViewportHeight(true);

        listarProdutosTabela();

        add(filtroPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void listarProdutosTabela() {
        ArrayList<Produto> produtos = new ProdutosDB().listarProdutos();
        for (Produto produto : produtos) {
            tableModel.addRow(new Object[]{produto.getCodigo(), produto.getNome(), produto.getPreco(), produto.getQuantidade()});
        }
    }

    public JTable getProdutosTabela() {
        return produtosTabela;
    }

    public FiltroPanel getFiltroPanel() {
        return filtroPanel;
    }

    public BottomPanel getBottomPanel() {
        return bottomPanel;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}