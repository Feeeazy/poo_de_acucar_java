package views.components;

import javax.swing.*;

public class Popup extends JFrame {
    public String title;
    public String message;

    public Popup() {}

    public Popup(String title, String message) {
        this.title = title;
        this.message = message;
        setTitle(title);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
   }

    public void showMessage() {
        JOptionPane.showMessageDialog(null, message);
   }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Popup().setVisible(true));
    }

}
