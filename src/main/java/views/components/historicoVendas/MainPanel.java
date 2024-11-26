package views.components.historicoVendas;

import database.HistoricoVendasDB;
import models.ItemHistorico;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class MainPanel extends JPanel {
    private JTable vendasTabela;
    private DefaultTableModel tableModel;
    private FiltroPanel filtroPanel;

    public MainPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        filtroPanel = new FiltroPanel();

        tableModel = new DefaultTableModel(new Object[]{"venda_id", "data_de_venda", "produto", "quantidade", "preco", "total", "pagamento", "cliente"}, 0);
        vendasTabela = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(vendasTabela);
        vendasTabela.setFillsViewportHeight(true);

        listarVendasTabela();

        add(filtroPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void listarVendasTabela() {
        ArrayList<ItemHistorico> itemHistoricos = new HistoricoVendasDB().listarVendas();
        for (ItemHistorico itemHistorico : itemHistoricos) {
            tableModel.addRow(new Object[]{
                    itemHistorico.getVenda_id(),
                    itemHistorico.getData_de_venda(),
                    itemHistorico.getProduto(),
                    itemHistorico.getQuantidade(),
                    itemHistorico.getPreco(),
                    itemHistorico.getTotal(),
                    itemHistorico.getPagamento(),
                    itemHistorico.getCliente()
            });
        }
    }

    public JTable getVendasTabela() {
        return vendasTabela;
    }

    public FiltroPanel getFiltroPanel() {
        return filtroPanel;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}