package com.ufes.delivery.builder;

import com.ufes.delivery.model.Cliente;
import com.ufes.delivery.model.Estabelecimento;
import com.ufes.delivery.model.ItemPedido;

public class CestaTopBuilder extends CestaBuilder {

    public CestaTopBuilder(Cliente cliente, Estabelecimento vendedor) {
        super(cliente, vendedor);
    }

    @Override
    public void addPapelaria() {
        pedido.incluir(new ItemPedido(pedido, dao.buscaProdutoPorCodigo(2), 6));
        pedido.incluir(new ItemPedido(pedido, dao.buscaProdutoPorCodigo(7), 7));
    }

    @Override
    public void addInformatica() {
        pedido.incluir(new ItemPedido(pedido, dao.buscaProdutoPorCodigo(13), 3));
        pedido.incluir(new ItemPedido(pedido, dao.buscaProdutoPorCodigo(15), 4));
        pedido.incluir(new ItemPedido(pedido, dao.buscaProdutoPorCodigo(19), 1));
    }
}
