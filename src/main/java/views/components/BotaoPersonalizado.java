package views.components;

import javax.swing.JButton;
import javax.swing.ImageIcon;

public class BotaoPersonalizado {
    public int altura;
    public int largura;
    public String path_imagem;
    public JButton botao;

    public BotaoPersonalizado(int altura, int largura, String path_imagem) {
        this.altura = altura;
        this.largura = largura;
        this.path_imagem = path_imagem;

        ImageIcon icon = new ImageIcon(new ImageIcon(path_imagem).getImage().getScaledInstance(largura, altura, java.awt.Image.SCALE_SMOOTH));
        botao = new JButton(icon);
        botao.setBorderPainted(false);
        botao.setContentAreaFilled(false);
        botao.setFocusPainted(false);
        botao.setPreferredSize(new java.awt.Dimension(largura, altura));
    }

    public JButton getBotao() {
        return botao;
    }
}