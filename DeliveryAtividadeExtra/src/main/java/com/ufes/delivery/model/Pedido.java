package com.ufes.delivery.model;

import com.ufes.delivery.imposto.Imposto;
import com.ufes.delivery.model.state.PedidoState;
import com.ufes.delivery.model.state.PedidoNovoState;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Pedido {

    private final int numero;
    private final LocalDateTime data;
    private final Cliente cliente;
    private final ArrayList<Imposto> impostos;
    private final ArrayList<Desconto> descontosConcedidos;
    private final ArrayList<ItemPedido> itens;
    private final Estabelecimento estabelecimento;
    private PedidoState state;
    private double valorReembolsado;
    private final ArrayList<EventoPedido> eventosPedido = new ArrayList<>();

    public Pedido(int numero, Cliente cliente, Estabelecimento estabelecimento) {
        this.numero = numero;
        this.data = LocalDateTime.now();
        this.cliente = cliente;
        this.estabelecimento = estabelecimento;
        itens = new ArrayList<>();
        descontosConcedidos = new ArrayList<>();
        impostos = new ArrayList<>();
        state = new PedidoNovoState(this);
    }

    public void setValorReembolsado(double valorReembolsado) {
        if (!state.getNomeEstado().toLowerCase().contains("cancelado")) {
            throw new RuntimeException("Para ser reembolsado o pedido precisa ser cancelado!"
                    + "\nO pedido está " + this.state.getNomeEstado());
        }
        this.valorReembolsado = valorReembolsado;
    }

    public double getValorReembolsado() {
        return valorReembolsado;
    }

    public double getValorFinal() {
        double valorTotalPago = 0;
        if (!state.getNomeEstado().equalsIgnoreCase("novo")) {
            valorTotalPago = this.getValorTotal() - getValorTotalDescontos() + getValorTotalImpostos();
        }
        return valorTotalPago;
    }

    public void setState(PedidoState state) {
        this.state = state;
    }

    public void add(EventoPedido evento) {
        if (evento == null) {
            throw new RuntimeException("Informe um evento válido!");
        }
        eventosPedido.add(evento);
    }

    public void add(Desconto desconto) {
        if (desconto == null) {
            throw new RuntimeException("Informe um desconto válido!");
        }
        this.descontosConcedidos.add(desconto);
    }

    public void add(Imposto imposto) {
        if (imposto == null) {
            throw new RuntimeException("Informe um imposto válido!");
        }
        this.impostos.add(imposto);
    }

    public void add(ItemPedido item) {
        itens.add(item);
    }

    public double getValorTotalImpostos() {
        double valorTotalImpostos = 0;
        valorTotalImpostos = impostos.stream().map(imposto -> imposto.getValor()).reduce(valorTotalImpostos, (accumulator, _item) -> accumulator + _item);
        return valorTotalImpostos;
    }

    public double getValorTotalDescontos() {
        double valorTotalDescontos = 0;
        valorTotalDescontos = descontosConcedidos.stream().map(desconto -> desconto.getValor()).reduce(valorTotalDescontos, (accumulator, _item) -> accumulator + _item);
        return valorTotalDescontos;
    }

    public LocalDateTime getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public double getValorTotal() {
        double valorTotal = 0;
        valorTotal = itens.stream().map(item -> item.getValorTotal()).reduce(valorTotal, (accumulator, _item) -> accumulator + _item);
        return valorTotal;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList<EventoPedido> getHistoricoPedido() {
        return eventosPedido;
    }

    public void pagar() {
        state.pagar();
    }

    public void cancelar() {
        state.cancelar();
    }

    public void preparar() {
        state.preparar();
    }

    public void sairParaEntrega() {
        state.sairParaEntrega();
    }

    public void entregar() {
        state.entregar();
    }

    public void concluir() {
        state.concluir();
    }

    public void reembolsar() {
        state.reembolsar();
    }

    public void incluir(ItemPedido item) {
        state.incluir(item);
    }

    public void removeItem(String nome) {
        state.removeItem(nome);
    }

    public void avaliar(double nota) {
        state.avaliar(nota);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        DecimalFormat df = new DecimalFormat("0.00");

        StringBuilder strItens = new StringBuilder();
        itens.forEach(item -> {
            strItens.append("\n\t");
            strItens.append(item.toString());
        });

        StringBuilder strDescontos = new StringBuilder();
        descontosConcedidos.forEach(desconto -> {
            strDescontos.append("\n\t");
            strDescontos.append(desconto.toString());
        });
        if (strDescontos.length() == 0) {
            strDescontos.append("Descontos não concedidos");
        }

        StringBuilder strImpostos = new StringBuilder();
        impostos.forEach(imposto -> {
            strImpostos.append("\n\t");
            strImpostos.append(imposto.toString());
        });
        if (strImpostos.length() == 0) {
            strImpostos.append("Impostos não calculados");
        }

        StringBuilder strEventos = new StringBuilder();

        eventosPedido.forEach(eventoPedido -> {
            strEventos.append("\n\t");
            strEventos.append(eventoPedido.toString());
        });

        return "\n-------------------------------------------------------------------------------------------------"
                + "\nPedido: " + getNumero() + ", data: " + data.format(formatter) + ", estado: " + state.getNomeEstado()
                + "\nCliente: " + cliente
                + "\nEstabelecimento: " + estabelecimento
                + "\nItens do pedido:"
                + strItens.toString()
                + "\nValor total dos itens R$: " + df.format(getValorTotal())
                + "\n\nDescontos concedidos:"
                + strDescontos.toString()
                + "\nValor total em descontos R$: " + df.format(getValorTotalDescontos())
                + "\n\nImpostos calculados:"
                + strImpostos.toString()
                + "\nValor total em impostos R$: " + df.format(getValorTotalImpostos())
                + "\n\nValor total do pedido R$: " + df.format(getValorFinal())
                + "\n\nEventos no pedido:"
                + strEventos.toString();
    }

}
