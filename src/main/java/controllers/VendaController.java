package controllers;

import views.NovaVendaView;
import database.ClientesDB;
import database.ItemVendaDB;
import database.VendaDB;
import models.ItemVenda;
import models.Venda;
import views.components.Popup;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Date;

public class VendaController {
    private final NovaVendaView novaVendaView;

    public VendaController(NovaVendaView novaVendaView) {
        this.novaVendaView = novaVendaView;
    }

    public void iniciarListeners() {
        novaVendaView.getFinalizarVendaBttn().addActionListener(e -> finalizarVenda());
        novaVendaView.getAdicionarItemBttn().addActionListener(e -> adicionarProduto());
        novaVendaView.getRemoverItemBttn().addActionListener(e -> removerProduto());
    }

    private void finalizarVenda() {
        DefaultTableModel produtosListModel = novaVendaView.getProdutosListModel();
        if (produtosListModel.getRowCount() == 0) {
            Popup carrinhoVazioErro = new Popup("Erro", "Venda vazia");
            carrinhoVazioErro.showMessage();
            return;
        }

        ClientesDB clientesDB = new ClientesDB();
        VendaDB vendaDB = new VendaDB();

        long cliente_id = clientesDB.buscarIdCliente((String) novaVendaView.getClienteComboBox().getSelectedItem());
        String pagamento = novaVendaView.getPagamentoComboBox().getSelectedItem().toString();

        Venda nova_venda = new Venda(
            Integer.parseInt(novaVendaView.getCodigoVenda()),
            new Date(),
            cliente_id,
            pagamento
        );

        vendaDB.inserirVenda(nova_venda);

        ArrayList<ItemVenda> itens = new ArrayList<>();

        for (int i = 0; i < produtosListModel.getRowCount(); i++) {
            ItemVenda itemVenda = new ItemVenda();

            itemVenda.setVenda_id(Integer.parseInt(novaVendaView.getCodigoVenda()));
            itemVenda.setProduto_id(Integer.parseInt(produtosListModel.getValueAt(i, 0).toString()));
            itemVenda.setQuantidade(Integer.parseInt(produtosListModel.getValueAt(i, 2).toString()));
            itemVenda.setValor(Double.parseDouble(produtosListModel.getValueAt(i, 3).toString()));

            itens.add(itemVenda);
        }

        for (ItemVenda itemVenda : itens) {
            ItemVendaDB itemVendaDB = new ItemVendaDB();
            itemVendaDB.inserirItemVenda(itemVenda);
        }

        Popup popup = new Popup("Venda realizada", "Venda realizada com sucesso!");
        popup.showMessage();
        novaVendaView.dispose();
    }

    private void adicionarProduto() {
        ViewController viewController = new ViewController();
        viewController.abrirAddProduto(novaVendaView);
    }

    private void removerProduto() {
        int linhaSelecionada = novaVendaView.getProdutoList().getSelectedRow();
        if (linhaSelecionada != -1) {
            novaVendaView.getProdutosListModel().removeRow(linhaSelecionada);
        }
    }
}