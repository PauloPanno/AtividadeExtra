package com.ufes.delivery.model.state;

import com.ufes.delivery.model.Pedido;

public class ReembolsadoState extends PedidoState {

    public ReembolsadoState(Pedido pedido) {
        super(pedido, "Pedido reembolsado");
    }

    @Override
    public void avaliar(double nota) {
        super.avaliar(nota);
    }

}
