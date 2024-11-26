package views.components.adicionarProduto;

import views.components.BotaoPersonalizado;

import javax.swing.*;
import java.awt.*;

import Enum.Botao;

public class BottomPanel extends JPanel {
    private JSpinner quantidade;
    private BotaoPersonalizado selecionarBttn;

    public BottomPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel quantidadeLabel = new JLabel("Quantidade:");
        quantidade = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        quantidade.setPreferredSize(new Dimension(80, 30));
        selecionarBttn = new BotaoPersonalizado(50, 150, Botao.AddItem.SELECIONAR_ITEM.getPath());

        add(quantidadeLabel);
        add(quantidade);
        add(selecionarBttn.getBotao());
    }

    public JSpinner getQuantidade() {
        return quantidade;
    }

    public BotaoPersonalizado getSelecionarBttn() {
        return selecionarBttn;
    }
}