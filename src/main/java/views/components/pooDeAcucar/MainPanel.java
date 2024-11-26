package views.components.pooDeAcucar;

import javax.swing.*;
import java.awt.*;
import controllers.ViewController;

public class MainPanel extends JPanel {
    public MainPanel(ViewController viewController) {
        super(new BorderLayout());
        setBackground(new Color(66, 146, 59));

        TopPanel topPanel = new TopPanel();
        BottomPanel bottomPanel = new BottomPanel(viewController);

        add(topPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}