package models;

import java.util.Date;

public class Venda {

    public int id_venda;
    public Date data_venda;
    public long id_cliente;
    public String tipo_pagamento;

    public Venda(int id_venda, Date data_venda, long id_cliente, String tipo_pagamento) {
        this.id_venda = id_venda;
        this.data_venda = data_venda;
        this.id_cliente = id_cliente;
        this.tipo_pagamento = tipo_pagamento;
    }

    public int getId_venda() {
        return id_venda;
    }

    public void setId_venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }

    public long getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getTipo_pagamento() {
        return tipo_pagamento;
    }

    public void setTipo_pagamento(String tipo_pagamento) {
        this.tipo_pagamento = tipo_pagamento;
    }


}
