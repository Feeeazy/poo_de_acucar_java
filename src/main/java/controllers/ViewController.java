package controllers;

import views.AdicionarProdutoView;
import views.PooDeAcucarScreen;
import views.TelaVenda;

import javax.swing.*;

public class ViewController {

    private TelaVenda telaVenda;
    private AdicionarProdutoView adicionarProdutoView;
    private PooDeAcucarScreen pooDeAcucarScreen;

    public ViewController() {}

    public void iniciarTelaVenda() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            telaVenda = new TelaVenda();
            telaVenda.setVisible(true);
        });
    }

    public void abrirAdicionarProduto(TelaVenda telaVenda) {
        this.telaVenda = telaVenda;
        SwingUtilities.invokeLater(() -> {
            this.telaVenda.desativarInteracoes(telaVenda.getContentPane());
            adicionarProdutoView = new AdicionarProdutoView(this.telaVenda);
            adicionarProdutoView.setVisible(true);
        });
    }

    public void abrirTelaPrincipal() {
        SwingUtilities.invokeLater(() -> {
            pooDeAcucarScreen = new PooDeAcucarScreen();
            pooDeAcucarScreen.setVisible(true);
        });
    }
}
