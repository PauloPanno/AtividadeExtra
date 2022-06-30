package com.ufes.delivery.desconto;

import com.ufes.delivery.model.Desconto;
import com.ufes.delivery.model.Pedido;

public class MetodoDescontoPercentual implements IMetodoDesconto {

    @Override
    public void calcula(Pedido pedido) {
        double percentualDesconto = 0.10;
        double valorDesconto = percentualDesconto * pedido.getValorTotal();
        pedido.add(new Desconto("Desconto de 10%", percentualDesconto, valorDesconto));
    }

}
