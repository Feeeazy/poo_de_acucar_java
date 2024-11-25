package views;

import controllers.ViewController;
import database.ClientesDB;
import database.ItemVendaDB;
import database.VendaDB;
import models.Cliente;
import models.ItemVenda;
import models.Venda;
import views.components.BotaoPersonalizado;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Enum.Pagamento;
import views.components.Popup;

public class TelaVenda extends JFrame {

    private JComboBox<String> clienteComboBox;
    private JComboBox<String> pagamentoComboBox;
    private JTable productList;
    private DefaultTableModel listModel;
    private JTextField codigoVendaText;
    private Date data_venda;
    private JLabel dataVendaLabel;

    public TelaVenda() {
        setTitle("Venda de Produto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);


        // Painel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Painel superior
        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel codigoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel codigoVendaLabel = new JLabel("Codigo Venda:");
        codigoVendaText = new JTextField(getNovoCodigoVenda()); // TODO Receber este parametro do banco
        codigoVendaText.setEnabled(false);
        codigoVendaText.setPreferredSize(new Dimension(40, 20));
        codigoVendaText.setHorizontalAlignment(JTextField.RIGHT);
        codigoVendaText.setFont(new Font("Arial", Font.BOLD, 14));
        codigoPanel.add(codigoVendaLabel, BorderLayout.WEST);
        codigoPanel.add(codigoVendaText, BorderLayout.CENTER);

        JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel dataLabel = new JLabel("Data: ");
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data_venda = null;
        try {
            data_venda = formato.parse(formato.format(new Date()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        dataVendaLabel = new JLabel(data_venda.toString());

        dataPanel.add(dataLabel);
        dataPanel.add(dataVendaLabel);

        topPanel.add(codigoPanel, BorderLayout.WEST);
        topPanel.add(dataPanel, BorderLayout.EAST);

        // Lista de produtos
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder("Produtos no carrinho:"));

        String[] columnNames = {"Cod_Produto", "Nome", "Quantidade", "Valor", "Total"};
        listModel = new DefaultTableModel(columnNames, 0);
        productList = new JTable(listModel);
        JScrollPane scrollPane = new JScrollPane(productList);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Painel inferior
        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel actionButtonsPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 20, 10));
        JPanel comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));

        // ComboBoxes com labels
        JPanel clientePanel = new JPanel(new BorderLayout());
        clientePanel.add(new JLabel("Cliente:"), BorderLayout.NORTH);
        ArrayList<Cliente> clientes = new ClientesDB().listarClientes();
        JComboBox<String> clienteComboBox = new JComboBox<>(); // TODO Valores da comboBox vai ser o resultado da pesquisa na tabela Clientes do banco

        for (Cliente cliente : clientes) {
            clienteComboBox.addItem(cliente.getNome());
        }
        clienteComboBox.setPreferredSize(new Dimension(120, 25));

        clientePanel.add(clienteComboBox, BorderLayout.CENTER);

        JPanel pagamentoPanel = new JPanel(new BorderLayout());
        pagamentoPanel.add(new JLabel("Pagamento:"), BorderLayout.NORTH);
        JComboBox<Pagamento> pagamentoComboBox = new JComboBox<>(Pagamento.values());
        pagamentoPanel.add(pagamentoComboBox, BorderLayout.CENTER);
        pagamentoPanel.setPreferredSize(new Dimension(120, 39));

        // Botões com imagens
        BotaoPersonalizado retirarItemBtn = new BotaoPersonalizado(75,100, "src/main/java/Img/removeItemBttn.png");

        BotaoPersonalizado adicionarProdutoBtn = new BotaoPersonalizado(75,100, "src/main/java/Img/addProductBttn.png");

        BotaoPersonalizado finalizarCompraBtn = new BotaoPersonalizado(75,100, "src/main/java/Img/buyBttn.png");

        comboBoxPanel.add(clientePanel);
        comboBoxPanel.add(pagamentoPanel);
        actionButtonsPanel.add(retirarItemBtn.getBotao());
        actionButtonsPanel.add(adicionarProdutoBtn.getBotao());
        actionButtonsPanel.add(finalizarCompraBtn.getBotao());

        bottomPanel.add(comboBoxPanel, BorderLayout.WEST);
        bottomPanel.add(actionButtonsPanel, BorderLayout.EAST);

        // Adiciona os painéis ao painel principal
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Adiciona o painel principal à janela
        add(mainPanel);

        Date finalData_venda = data_venda;
        finalizarCompraBtn.getBotao().addActionListener(e ->
            {
                ClientesDB clientesDB = new ClientesDB();
                VendaDB vendaDB = new VendaDB();

                long cliente_id = clientesDB.buscarIdCliente((String) clienteComboBox.getSelectedItem());
                String pagamento = pagamentoComboBox.getSelectedItem().toString();

                Venda nova_venda = new Venda(
                        Integer.parseInt(codigoVendaText.getText()),
                        finalData_venda,
                        cliente_id,
                        pagamento
                );

                vendaDB.inserirVenda(nova_venda);

                ArrayList<ItemVenda> itens = new ArrayList<>();

                for (int i = 0; i < listModel.getRowCount(); i++) {
                    ItemVenda itemVenda = new ItemVenda();

                    itemVenda.setVenda_id(Integer.parseInt(codigoVendaText.getText()));
                    itemVenda.setProduto_id(Integer.parseInt(listModel.getValueAt(i, 0).toString()));
                    itemVenda.setQuantidade(Integer.parseInt(listModel.getValueAt(i, 2).toString()));
                    itemVenda.setValor(Double.parseDouble(listModel.getValueAt(i, 3).toString()));

                    itens.add(itemVenda);
                }

                for(ItemVenda itemVenda : itens){
                    ItemVendaDB itemVendaDB = new ItemVendaDB();
                    itemVendaDB.inserirItemVenda(itemVenda);
                }

                Popup popup = new Popup("Venda realizada", "Venda realizada com sucesso!");
                popup.showMessage();
                this.dispose();

        });


        adicionarProdutoBtn.getBotao().addActionListener(e -> {
            ViewController viewController = new ViewController();
            viewController.abrirAdicionarProduto(this);
        });

        retirarItemBtn.getBotao().addActionListener(e -> {
            int linhaSelecionada = productList.getSelectedRow();
            if (linhaSelecionada != -1) {
                listModel.removeRow(linhaSelecionada);
            }
        });

    }

    private String getNovoCodigoVenda() {
        VendaDB vendaDB = new VendaDB();

        return vendaDB.getNovoCodigoVenda();
    }

    public String getCodigoVenda(){
        return codigoVendaText.getText();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        SwingUtilities.invokeLater(() -> {
            TelaVenda frame = new TelaVenda();
            frame.setVisible(true);
        });
    }

    public void desativarInteracoes(Container panel) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                desativarInteracoes((JPanel) component);
            } else {
                component.setEnabled(false);
            }
        }
    }

    void reativarInteracoes(Container panel, String codigoVenda) {
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                reativarInteracoes((JPanel) component, codigoVenda);
            } else {
                if (component instanceof JTextField && codigoVenda.equals(((JTextField) component).getText())) {
                    continue; // Não reativar o codigoVendaText
                }
                component.setEnabled(true);
            }
        }
    }

    void adicionarItemLista(String[] item) {
        listModel.addRow(item);
    }
}
