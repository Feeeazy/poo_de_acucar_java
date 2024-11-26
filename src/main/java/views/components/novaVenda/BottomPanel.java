package views.components.novaVenda;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import models.Cliente;
import views.components.BotaoPersonalizado;
import Enum.Botao;
import Enum.Pagamento;
import database.ClientesDB;

public class BottomPanel extends JPanel {
    private JComboBox<String> clienteComboBox;
    private JComboBox<Pagamento> pagamentoComboBox;
    private JButton adicionarItemBttn;
    private JButton removerItemBttn;
    private JButton finalizarVendaBttn;

    public BottomPanel() {
        setLayout(new BorderLayout());

        JPanel comboBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JPanel clientePanel = new JPanel(new BorderLayout());
        clientePanel.add(new JLabel("Cliente:"), BorderLayout.NORTH);
        ArrayList<Cliente> clientes = new ClientesDB().listarClientes();
        clienteComboBox = new JComboBox<>();
        for (Cliente cliente : clientes) {
            clienteComboBox.addItem(cliente.getNome());
        }
        clienteComboBox.setPreferredSize(new Dimension(120, 25));
        clientePanel.add(clienteComboBox, BorderLayout.CENTER);

        JPanel pagamentoPanel = new JPanel(new BorderLayout());
        pagamentoPanel.add(new JLabel("Pagamento:"), BorderLayout.NORTH);
        pagamentoComboBox = new JComboBox<>(Pagamento.values());
        pagamentoPanel.add(pagamentoComboBox, BorderLayout.CENTER);
        pagamentoPanel.setPreferredSize(new Dimension(120, 39));

        comboBoxPanel.add(clientePanel);
        comboBoxPanel.add(pagamentoPanel);

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 20, 10));
        adicionarItemBttn = new BotaoPersonalizado(75, 100, Botao.Venda.ADICIONAR_ITEM.getPath()).getBotao();
        removerItemBttn = new BotaoPersonalizado(75, 100, Botao.Venda.REMOVER_ITEM.getPath()).getBotao();
        finalizarVendaBttn = new BotaoPersonalizado(75, 100, Botao.Venda.FINALIZAR_VENDA.getPath()).getBotao();

        botoesPanel.add(adicionarItemBttn);
        botoesPanel.add(removerItemBttn);
        botoesPanel.add(finalizarVendaBttn);

        add(comboBoxPanel, BorderLayout.WEST);
        add(botoesPanel, BorderLayout.EAST);
    }

    public JComboBox<String> getClienteComboBox() {
        return clienteComboBox;
    }

    public JComboBox<Pagamento> getPagamentoComboBox() {
        return pagamentoComboBox;
    }

    public JButton getAdicionarItemBttn() {
        return adicionarItemBttn;
    }

    public JButton getRemoverItemBttn() {
        return removerItemBttn;
    }

    public JButton getFinalizarVendaBttn() {
        return finalizarVendaBttn;
    }
}