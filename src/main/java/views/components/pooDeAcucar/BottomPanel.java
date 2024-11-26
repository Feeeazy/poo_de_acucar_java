package views.components.pooDeAcucar;

import controllers.ViewController;
import views.components.BotaoPersonalizado;

import javax.swing.*;
import java.awt.*;

import Enum.Botao;

public class BottomPanel extends JPanel {
    public BottomPanel(ViewController viewController) {
        super(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setBackground(new Color(66, 146, 59));

        BotaoPersonalizado historicoBttn = new BotaoPersonalizado(
                69, 137, Botao.Main.HISTORICO_VENDAS.getPath()
        );
        historicoBttn.getBotao().addActionListener(ActionEvent -> viewController.abrirHistoricoVendas());

        BotaoPersonalizado novaVendaBttn = new BotaoPersonalizado(
                69, 137, Botao.Main.NOVA_VENDA.getPath()
        );
        novaVendaBttn.getBotao().addActionListener(ActionEvent -> viewController.iniciarNovaVenda());

        add(historicoBttn.getBotao());
        add(novaVendaBttn.getBotao());
    }
}