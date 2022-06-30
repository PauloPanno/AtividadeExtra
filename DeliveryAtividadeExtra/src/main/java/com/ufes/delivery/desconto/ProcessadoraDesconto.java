package com.ufes.delivery.desconto;

import com.ufes.delivery.model.Pedido;
import java.util.ArrayList;

public class ProcessadoraDesconto {

    private final ArrayList<IMetodoDesconto> metodosDescontos;

    public ProcessadoraDesconto() {
        metodosDescontos = new ArrayList<>();
        metodosDescontos.add(new MetodoDescontoPercentual());
    }

    public void processar(Pedido pedido) {
        metodosDescontos.forEach(metodoDesconto -> {
            metodoDesconto.calcula(pedido);
        });
    }

}
