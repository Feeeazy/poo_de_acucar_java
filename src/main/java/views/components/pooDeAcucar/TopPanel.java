package views.components.pooDeAcucar;

import javax.swing.*;
        import java.awt.*;

public class TopPanel extends JPanel {
    public TopPanel() {
        super(new FlowLayout(FlowLayout.CENTER, 0, 20));
        setBackground(new Color(66, 146, 59));

        ImageIcon logoIcon = new ImageIcon("src/main/java/Img/background.png");
        JLabel logoLabel = new JLabel(logoIcon);
        add(logoLabel);
    }
}
