package views;

import controllers.VendaController;
import database.VendaDB;
import views.components.novaVenda.TopPanel;
import views.components.novaVenda.CenterPanel;
import views.components.novaVenda.BottomPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;

import Enum.Pagamento;

public class NovaVendaView extends JFrame {
    private TopPanel topPanel;
    private CenterPanel centerPanel;
    private BottomPanel bottomPanel;

    public NovaVendaView() {
        setTitle("Venda de Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        topPanel = new TopPanel(new VendaDB().getNovoCodigoVenda(), new Date());
        centerPanel = new CenterPanel();
        bottomPanel = new BottomPanel();

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);

        VendaController vendaController = new VendaController(this);
        vendaController.iniciarListeners();
    }

    public String getCodigoVenda() {
        return topPanel.getCodigoVenda();
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
                    continue;
                }
                component.setEnabled(true);
            }
        }
    }

    void adicionarItemLista(String[] item) {
        centerPanel.getProdutosListModel().addRow(item);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            NovaVendaView frame = new NovaVendaView();
            frame.setVisible(true);
        });
    }

    public JTable getProdutoList() {
        return centerPanel.getProdutoList();
    }

    public DefaultTableModel getProdutosListModel() {
        return centerPanel.getProdutosListModel();
    }

    public JComboBox<String> getClienteComboBox() {
        return bottomPanel.getClienteComboBox();
    }

    public JComboBox<Pagamento> getPagamentoComboBox() {
        return bottomPanel.getPagamentoComboBox();
    }

    public JButton getAdicionarItemBttn() {
        return bottomPanel.getAdicionarItemBttn();
    }

    public JButton getRemoverItemBttn() {
        return bottomPanel.getRemoverItemBttn();
    }

    public JButton getFinalizarVendaBttn() {
        return bottomPanel.getFinalizarVendaBttn();
    }
}