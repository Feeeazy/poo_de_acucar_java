package views;

import javax.swing.*;
import controllers.ViewController;
import views.components.pooDeAcucar.MainPanel;

public class PooDeAcucarView extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public PooDeAcucarView(ViewController viewController) {
        setTitle("Poo de Açucar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        MainPanel mainPanel = new MainPanel(viewController);
        add(mainPanel);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            ViewController viewController = new ViewController();
            PooDeAcucarView frame = new PooDeAcucarView(viewController);
            frame.setVisible(true);
        });
    }
}