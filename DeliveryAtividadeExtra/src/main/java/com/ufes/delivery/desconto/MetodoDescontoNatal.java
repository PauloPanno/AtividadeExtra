package com.ufes.delivery.desconto;

import com.ufes.delivery.model.Desconto;
import com.ufes.delivery.model.Pedido;

public class MetodoDescontoNatal implements IMetodoDesconto {

    @Override
    public void calcula(Pedido pedido) {
        double percentualDesconto = 0.03;
        double valorDesconto = percentualDesconto * pedido.getValorTotal();
        pedido.add(new Desconto("Desconto de Natal de 3%", percentualDesconto, valorDesconto));
    }
}