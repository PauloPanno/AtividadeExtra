package com.ufes.delivery.desconto;

import com.ufes.delivery.model.Desconto;
import com.ufes.delivery.model.Pedido;

public class MetodoDescontoIcentivoEducacao implements IMetodoDesconto {

    @Override
    public void calcula(Pedido pedido) {
        double percentualDesconto = 0.5;
        double valorDesconto = percentualDesconto * pedido.getValorTotal();
        pedido.add(new Desconto("Desconto Icentivo Educação de 5%", percentualDesconto, valorDesconto));
    }
}