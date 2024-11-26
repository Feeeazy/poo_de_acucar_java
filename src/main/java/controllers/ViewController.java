package controllers;

import views.AddProdutoView;
import views.HistoricoVendaView;
import views.PooDeAcucarView;
import views.NovaVendaView;

import javax.swing.*;

public class ViewController {
    private NovaVendaView novaVendaView;
    private AddProdutoView addProdutoView;
    private PooDeAcucarView pooDeAcucarView;
    private HistoricoVendaView historicoVendaView;

    public ViewController() {}

    public void iniciarNovaVenda() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            novaVendaView = new NovaVendaView();
            novaVendaView.setVisible(true);
        });
    }

    public void abrirAddProduto(NovaVendaView novaVendaView) {
        this.novaVendaView = novaVendaView;
        SwingUtilities.invokeLater(() -> {
            this.novaVendaView.desativarInteracoes(novaVendaView.getContentPane());
            addProdutoView = new AddProdutoView(this.novaVendaView);
            addProdutoView.setVisible(true);
        });
    }

    public void abrirTelaPrincipal() {
        SwingUtilities.invokeLater(() -> {
            pooDeAcucarView = new PooDeAcucarView(this);
            pooDeAcucarView.setVisible(true);
        });
    }

    public void abrirHistoricoVendas() {
        SwingUtilities.invokeLater(() -> {
            historicoVendaView = new HistoricoVendaView();
            historicoVendaView.setVisible(true);
        });
    }
}