package com.ufes.delivery.builder;

import com.ufes.delivery.model.Pedido;

public class DiretorCesta {

    public Pedido build(CestaBuilder builder) {
        if (builder == null) {
            throw new RuntimeException("Informe uma classe Builder válida!");
        }

        builder.addPapelaria();
        builder.addInformatica();

        return builder.getPedido();
    }

}
