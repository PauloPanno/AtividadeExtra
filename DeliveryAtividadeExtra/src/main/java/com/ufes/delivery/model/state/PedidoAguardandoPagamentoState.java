package com.ufes.delivery.model.state;

import com.ufes.delivery.dao.ProdutoDAO;
import com.ufes.delivery.model.Cliente;
import com.ufes.delivery.model.ItemPedido;
import com.ufes.delivery.model.Pedido;

public class PedidoAguardandoPagamentoState extends PedidoState {

    public PedidoAguardandoPagamentoState(Pedido pedido) {
        super(pedido, "Aguardando o pagamento");
    }

    @Override
    public void pagar() {
        Cliente cliente = pedido.getCliente();
        double valorAPagar = pedido.getValorFinal();
        cliente.deduzSaldo(valorAPagar);

        baixarProdutos();
        this.pedido.setState(new PedidoConfirmadoState(pedido));
    }

    private void baixarProdutos() {
        ProdutoDAO dao = ProdutoDAO.getInstance();

        for (ItemPedido item : pedido.getItens()) {
            dao.baixaEstoque(item.getCodigoProduto(), item.getQuantidade());
        }
    }

    @Override
    public void cancelar() {
        this.pedido.setState(new PedidoCanceladoState(this.pedido, "Pedido cancelado pelo cliente"));
    }

}
