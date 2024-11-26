package Enum;

public enum Botao {;

    public enum Venda {
        ADICIONAR_ITEM("src/main/java/Img/addProdutoBttn.png"),
        FINALIZAR_VENDA("src/main/java/Img/finalizarVendaBttn.png"),
        REMOVER_ITEM("src/main/java/Img/removeItemBttn.png");

        private final String path;

        Venda(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

    }

    public enum AddItem {
        SELECIONAR_ITEM("src/main/java/Img/selecionarItemBttn.png");

        private final String path;

        AddItem(String path){
            this.path = path;
        }

        public String getPath() {
            return path;
        }

    }

    public enum Main {
        NOVA_VENDA("src/main/java/Img/novaVendaBttn.png"),
        HISTORICO_VENDAS("src/main/java/Img/historicoVendaBttn.png");

        private final String path;

        Main(String path){
            this.path = path;
        }

        public String getPath() {
            return path;
        }

    }
}