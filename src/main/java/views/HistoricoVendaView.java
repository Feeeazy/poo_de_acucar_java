package views;

import database.HistoricoVendasDB;
import models.ItemHistorico;
import views.components.historicoVendas.FiltroPanel;
import views.components.historicoVendas.MainPanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import Enum.FiltrosVendas;

public class HistoricoVendaView extends JFrame {
    private JTextField filtroTextField;
    private JTable vendasTabela;
    private JComboBox<FiltrosVendas> filtroComboBox;

    public HistoricoVendaView() {
        setTitle("Historico de Vendas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        MainPanel mainPanel = new MainPanel();
        add(mainPanel);

        vendasTabela = mainPanel.getVendasTabela();
        FiltroPanel filtroPanel = mainPanel.getFiltroPanel();
        filtroTextField = filtroPanel.getFiltroPanel();
        filtroComboBox = filtroPanel.getFiltroComboBox();

        filtroTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    filtraVendas(mainPanel.getTableModel());
                }
            }
        });
    }
    private void filtraVendas(DefaultTableModel model) {
        String filtro = filtroTextField.getText();
        String tipoFiltro = filtroComboBox.getSelectedItem().toString();
        model.setRowCount(0);

        ArrayList<ItemHistorico> itensHistorico = new HistoricoVendasDB().filtraVendas(filtro, tipoFiltro);
        for (ItemHistorico itemHistorico : itensHistorico) {
            model.addRow(new Object[]{
                    itemHistorico.getVenda_id(),
                    itemHistorico.getData_de_venda(),
                    itemHistorico.getProduto(),
                    itemHistorico.getQuantidade(),
                    itemHistorico.getPreco(),
                    itemHistorico.getTotal(),
                    itemHistorico.getPagamento(),
                    itemHistorico.getCliente()});
        }
    }
}
