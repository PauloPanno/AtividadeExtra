package com.ufes.delivery.desconto;

import com.ufes.delivery.model.Pedido;

interface IMetodoDesconto {

    public void calcula(Pedido pedido);
}
