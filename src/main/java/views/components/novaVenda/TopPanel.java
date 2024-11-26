package views.components.novaVenda;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TopPanel extends JPanel {
    private JTextField codigoVendaText;
    private JLabel dataVendaLabel;

    public TopPanel(String codigoVenda, Date dataVenda) {
        setLayout(new BorderLayout());

        JPanel codigoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel codigoVendaLabel = new JLabel("Codigo Venda:");
        codigoVendaText = new JTextField(codigoVenda);
        codigoVendaText.setEnabled(false);
        codigoVendaText.setPreferredSize(new Dimension(40, 20));
        codigoVendaText.setHorizontalAlignment(JTextField.RIGHT);
        codigoVendaText.setFont(new Font("Arial", Font.BOLD, 14));
        codigoPanel.add(codigoVendaLabel);
        codigoPanel.add(codigoVendaText);

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        dataVendaLabel = new JLabel(formato.format(dataVenda));

        JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel dataLabel = new JLabel("Data:");
        dataPanel.add(dataLabel);
        dataPanel.add(dataVendaLabel);

        add(codigoPanel, BorderLayout.WEST);
        add(dataPanel, BorderLayout.EAST);
    }

    public String getCodigoVenda() {
        return codigoVendaText.getText();
    }
}