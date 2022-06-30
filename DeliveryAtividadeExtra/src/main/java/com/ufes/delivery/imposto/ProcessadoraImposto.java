package com.ufes.delivery.imposto;

import com.ufes.delivery.model.Pedido;
import java.util.ArrayList;

public class ProcessadoraImposto {

    private final ArrayList<IMetodoImposto> metodosImpostos;

    public ProcessadoraImposto() {
        metodosImpostos = new ArrayList<>();
        metodosImpostos.add(new MetodoISS());
        metodosImpostos.add(new MetodoICMS());
    }

    public void processar(Pedido pedido) {
        metodosImpostos.forEach(metodoImposto -> {
            metodoImposto.calcula(pedido);
        });
    }

}
