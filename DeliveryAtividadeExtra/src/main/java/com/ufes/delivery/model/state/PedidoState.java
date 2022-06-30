package com.ufes.delivery.model.state;

import com.ufes.delivery.model.EventoPedido;
import com.ufes.delivery.model.ItemPedido;
import com.ufes.delivery.model.Pedido;
import java.time.LocalDateTime;

public abstract class PedidoState {

    protected Pedido pedido;
    private String nomeEstado;

    public PedidoState(Pedido pedido, String nomeEstado) {
        this.pedido = pedido;
        this.nomeEstado = nomeEstado;
        pedido.add(new EventoPedido(LocalDateTime.now(), nomeEstado));
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void pagar() {
        throw new RuntimeException("O pedido " + pedido.getNumero() + " não pode ser pago no estado \'" + getNomeEstado() + "\'");
    }

    public void cancelar() {
        throw new RuntimeException("O pedido " + pedido.getNumero() + " não pode ser cancelado no estado \'" + getNomeEstado() + "\'");
    }

    public void preparar() {
        throw new RuntimeException("O pedido " + pedido.getNumero() + " não pode ser preparado no estado \'" + getNomeEstado() + "\'");
    }

    public void sairParaEntrega() {
        throw new RuntimeException("O pedido " + pedido.getNumero() + " não pode sair para entrega no estado \'" + getNomeEstado() + "\'");
    }

    public void entregar() {
        throw new RuntimeException("O pedido " + pedido.getNumero() + " não pode ser entregue no estado \'" + getNomeEstado() + "\'");
    }

    public void concluir() {
        throw new RuntimeException("O pedido " + pedido.getNumero() + " não pode ser concluído no estado \'" + getNomeEstado() + "\'");
    }

    public void reembolsar() {
        throw new RuntimeException("O pedido " + pedido.getNumero() + " não pode ser reembolsado no estado \'" + getNomeEstado() + "\'");
    }

    public void incluir(ItemPedido item) {
        throw new RuntimeException("Itens não podem ser adicionados ao pedido " + pedido.getNumero() + " no estado \'" + getNomeEstado() + "\'");
    }

    public void removeItem(String nome) {
        throw new RuntimeException("Itens não podem ser removidos do pedido " + pedido.getNumero() + " no estado \'" + getNomeEstado() + "\'");
    }

    public void avaliar(double nota) {
        if (getNomeEstado().toLowerCase().contains("entregue") || getNomeEstado().toLowerCase().contains("reembolsado")) {
            if (nota >= 1 && nota <= 5) {
                pedido.add(new EventoPedido(LocalDateTime.now(), "Avaliação do pedido: " + nota));
            } else {
                throw new RuntimeException("Informe uma nota entre 1 e 5");
            }
        } else {
            throw new RuntimeException("O pedido " + pedido.getNumero() + " não pode ser avaliado no estado \'" + getNomeEstado() + "\'");
        }
    }

    @Override
    public String toString() {
        return getNomeEstado();
    }

}
