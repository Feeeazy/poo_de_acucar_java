package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PooDeAcucarScreen extends JFrame {
    private static final int BUTTON_WIDTH = 137;
    private static final int BUTTON_HEIGHT = 69;

    public PooDeAcucarScreen() {
        setTitle("Poo de Açucar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(66, 146, 59));

        // Painel superior com logo
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        topPanel.setBackground(new Color(66, 146, 59));

        ImageIcon logoIcon = new ImageIcon("src/main/java/Img/logo.png");
        JLabel logoLabel = new JLabel(logoIcon);
        topPanel.add(logoLabel);

        // Painel inferior com botões
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        bottomPanel.setBackground(new Color(66, 146, 59));

        BotaoPersonalizado historicoButton = new BotaoPersonalizado(
                BUTTON_HEIGHT, BUTTON_WIDTH, "src/main/java/Img/Poo de Açuca (3).png"
        );
        historicoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Implementar ação do botão "Histórico de Vendas"
                System.out.println("Botão 'Histórico de Vendas' clicado");
            }
        });

        BotaoPersonalizado novaVendaButton = new BotaoPersonalizado(
                BUTTON_HEIGHT, BUTTON_WIDTH, "src/main/java/Img/Poo de Açuca (4).png"
        );
        novaVendaButton.addActionListener(ActionEvent -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            SwingUtilities.invokeLater(() -> {
                TelaVenda telaVenda = new TelaVenda();
                telaVenda.setVisible(true);
            });
        });

        bottomPanel.add(historicoButton);
        bottomPanel.add(novaVendaButton);

        // Adiciona os painéis ao painel principal
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private static class BotaoPersonalizado extends JButton {
        public BotaoPersonalizado(int height, int width, String imagePath) {
            setPreferredSize(new Dimension(width, height));
            setIcon(new ImageIcon(imagePath));
            setContentAreaFilled(false);
            setBorderPainted(false);
            setFocusPainted(false);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            PooDeAcucarScreen frame = new PooDeAcucarScreen();
            frame.setVisible(true);
        });
    }
}