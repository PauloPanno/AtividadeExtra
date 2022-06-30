package com.ufes.delivery.desconto;

import com.ufes.delivery.model.Desconto;
import com.ufes.delivery.model.Pedido;

public class MetodoDescontoTipoProduto implements IMetodoDesconto {

    @Override
    public void calcula(Pedido pedido) {
        double percentualDesconto = 0.01;
        double valorDesconto = percentualDesconto * pedido.getValorTotal();
        pedido.add(new Desconto("Desconto de Tipo de Produto de 1%", percentualDesconto, valorDesconto));
    }
}
