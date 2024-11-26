package views;

import database.ProdutosDB;
import models.Produto;
import views.components.BotaoPersonalizado;
import views.components.adicionarProduto.MainPanel;
import views.components.adicionarProduto.FiltroPanel;
import views.components.adicionarProduto.BottomPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Enum.FiltrosProdutos;

public class AddProdutoView extends JFrame {
    private JTextField filtroTextField;
    private JTable protudosTabela;
    private JComboBox<FiltrosProdutos> filtroComboBox;
    private JSpinner quantidade;

    public AddProdutoView(views.NovaVendaView novaVendaView) {
        setTitle("Adicionar Produto");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        MainPanel mainPanel = new MainPanel();
        add(mainPanel);

        protudosTabela = mainPanel.getProdutosTabela();
        FiltroPanel searchPanel = mainPanel.getFiltroPanel();
        filtroTextField = searchPanel.getFiltroPanel();
        filtroComboBox = searchPanel.getFiltroComboBox();

        BottomPanel bottomPanel = mainPanel.getBottomPanel();
        quantidade = bottomPanel.getQuantidade();
        BotaoPersonalizado selectButton = bottomPanel.getSelecionarBttn();

        filtroTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    filtraProdutos(mainPanel.getTableModel());
                }
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                novaVendaView.reativarInteracoes(novaVendaView.getContentPane(), novaVendaView.getCodigoVenda());
                dispose();
            }
        });

        selectButton.getBotao().addActionListener(e -> {
            novaVendaView.reativarInteracoes(novaVendaView.getContentPane(), novaVendaView.getCodigoVenda());
            int codigo = (int) protudosTabela.getValueAt(protudosTabela.getSelectedRow(), 0);
            String nome = (String) protudosTabela.getValueAt(protudosTabela.getSelectedRow(), 1);
            double preco = (double) protudosTabela.getValueAt(protudosTabela.getSelectedRow(), 2);
            int quantidade = (int) this.quantidade.getValue();

            String[] item = {
                    String.valueOf(codigo),
                    nome,
                    String.valueOf(quantidade),
                    String.valueOf(preco),
                    String.valueOf(quantidade * preco)
            };

            novaVendaView.adicionarItemLista(item);
            this.dispose();
        });
    }

    private void filtraProdutos(DefaultTableModel model) {
        String filtro = filtroTextField.getText();
        String tipoFiltro = filtroComboBox.getSelectedItem().toString();
        model.setRowCount(0);

        ArrayList<Produto> produtos = new ProdutosDB().filtraProdutos(filtro, tipoFiltro);
        for (Produto produto : produtos) {
            model.addRow(new Object[]{produto.getCodigo(), produto.getNome(), produto.getPreco(), produto.getQuantidade()});
        }
    }
}
