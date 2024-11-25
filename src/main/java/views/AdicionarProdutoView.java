package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Enum.FiltrosProdutos;
import database.ProdutosDB;
import models.Produto;

public class AdicionarProdutoView extends JFrame {
    private JTextField searchField;
    private JTable productTable;
    private JComboBox<FiltrosProdutos> filtroComboBox;
    private JSpinner quantitySpinner;

    public AdicionarProdutoView(TelaVenda telaVenda) {
        setTitle("Adicionar Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel searchPanel = new JPanel(new FlowLayout());

        JLabel searchLabel = new JLabel("Pesquisar:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(400, 30));
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));

        JPanel searchEntryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchEntryPanel.add(searchLabel);
        searchEntryPanel.add(searchField);



        JLabel filtroLabel = new JLabel("Filtro:");
        filtroLabel.setFont(new Font("Arial", Font.BOLD, 14));
        filtroComboBox = new JComboBox<FiltrosProdutos>(FiltrosProdutos.values());
        filtroComboBox.setPreferredSize(new Dimension(120, 30));
        filtroComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        filtroComboBox.setRenderer(listRenderer);
        JPanel searchOptionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        searchOptionsPanel.add(filtroLabel);
        searchOptionsPanel.add(filtroComboBox);

        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(searchEntryPanel, BorderLayout.WEST);
        searchPanel.add(searchOptionsPanel, BorderLayout.EAST);



        // Tabela de produtos
        String[] columnNames = {"Código", "Nome", "Preço", "Estoque"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        ArrayList<Produto> produtos = new ProdutosDB().listarProdutos();

        for (Produto produto : produtos) {
            model.addRow(new Object[]{produto.getCodigo(), produto.getNome(), produto.getPreco(), produto.getQuantidade()});
        }

        productTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(productTable);
        productTable.setFillsViewportHeight(true);

        // Painel inferior com ComboBox, quantidade e botão
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        SpinnerModel spinnerModel = new SpinnerNumberModel(1, 1, 999, 1);
        quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.setPreferredSize(new Dimension(80, 30));

        JButton selectButton = new JButton("Selecionar item");
        selectButton.setPreferredSize(new Dimension(120, 30));

        bottomPanel.add(new JLabel("Quantidade:"));
        bottomPanel.add(quantitySpinner);
        bottomPanel.add(selectButton);

        // Adiciona os componentes ao painel principal
        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Adiciona o painel principal à janela antes da estilização
        add(mainPanel);

        // Add key listener to the search field
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    filtraProdutos(model);
                }
            }
        });

        selectButton.addActionListener(e -> {
            telaVenda.reativarInteracoes(telaVenda.getContentPane(), telaVenda.getCodigoVenda());
            int codigo = (int) productTable.getValueAt(productTable.getSelectedRow(), 0);
            String nome = (String) productTable.getValueAt(productTable.getSelectedRow(), 1);
            double preco = (double) productTable.getValueAt(productTable.getSelectedRow(), 2);
            int quantidade = (int) quantitySpinner.getValue();


            String[] item = {
                    String.valueOf(codigo),
                    nome,
                    String.valueOf(quantidade),
                    String.valueOf(preco),
                    String.valueOf(quantidade * preco)
            };

            telaVenda.adicionarItemLista(item);

            this.dispose();
        });
    }

    private void filtraProdutos(DefaultTableModel model) {
        // Example implementation of the filtraProdutos method
        String filtro = searchField.getText();
        String tipo_filtro = filtroComboBox.getSelectedItem().toString();
        model.setRowCount(0); // Clear the table

        // Retrieve and filter products based on the search term
        ArrayList<Produto> produtos = new ProdutosDB().filtraProdutos(filtro,tipo_filtro);
        for (Produto produto : produtos) {
            model.addRow(new Object[]{produto.getCodigo(), produto.getNome(), produto.getPreco(), produto.getQuantidade()});
        }
    }

}