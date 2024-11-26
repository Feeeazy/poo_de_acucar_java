package views.components.adicionarProduto;

import javax.swing.*;
import java.awt.*;
import Enum.FiltrosProdutos;

public class FiltroPanel extends JPanel {
    private JTextField filtroPanel;
    private JComboBox<FiltrosProdutos> filtroComboBox;

    public FiltroPanel() {
        setLayout(new BorderLayout(10, 10));

        JPanel searchEntryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel searchLabel = new JLabel("Pesquisar:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        filtroPanel = new JTextField();
        filtroPanel.setPreferredSize(new Dimension(400, 30));
        filtroPanel.setFont(new Font("Arial", Font.PLAIN, 14));
        searchEntryPanel.add(searchLabel);
        searchEntryPanel.add(filtroPanel);

        JPanel searchOptionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel filtroLabel = new JLabel("Filtro:");
        filtroLabel.setFont(new Font("Arial", Font.BOLD, 14));
        filtroComboBox = new JComboBox<>(FiltrosProdutos.values());
        filtroComboBox.setPreferredSize(new Dimension(120, 30));
        filtroComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        filtroComboBox.setRenderer(listRenderer);
        searchOptionsPanel.add(filtroLabel);
        searchOptionsPanel.add(filtroComboBox);

        add(searchEntryPanel, BorderLayout.WEST);
        add(searchOptionsPanel, BorderLayout.EAST);
    }

    public JTextField getFiltroPanel() {
        return filtroPanel;
    }

    public JComboBox<FiltrosProdutos> getFiltroComboBox() {
        return filtroComboBox;
    }
}