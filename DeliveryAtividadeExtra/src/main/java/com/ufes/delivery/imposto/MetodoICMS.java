package com.ufes.delivery.imposto;

import com.ufes.delivery.model.Pedido;

public class MetodoICMS implements IMetodoImposto {

    @Override
    public void calcula(Pedido pedido) {
        double percential = 0.7;
        double valor = percential * pedido.getValorTotal();
        pedido.add(new Imposto("ICMS", percential, valor));
    }
}
