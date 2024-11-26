package views.components.novaVenda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CenterPanel extends JPanel {
    private JTable produtoList;
    private DefaultTableModel produtosListModel;

    public CenterPanel() {
        setBorder(BorderFactory.createTitledBorder("Produtos no carrinho:"));
        setLayout(new BorderLayout());

        String[] columnNames = {"Cod. Produto", "Nome", "Quantidade", "Valor", "Total"};
        produtosListModel = new DefaultTableModel(columnNames, 0);
        produtoList = new JTable(produtosListModel);
        JScrollPane scrollPane = new JScrollPane(produtoList);
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTable getProdutoList() {
        return produtoList;
    }

    public DefaultTableModel getProdutosListModel() {
        return produtosListModel;
    }
}