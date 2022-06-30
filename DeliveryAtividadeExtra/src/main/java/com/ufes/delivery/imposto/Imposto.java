package com.ufes.delivery.imposto;

import java.text.DecimalFormat;

public class Imposto {

    private final String nome;
    private final double percentual;
    private final double valor;

    public Imposto(String nome, double percentual, double valor) {
        this.nome = nome;
        this.percentual = percentual;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public double getPercentual() {
        return percentual;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "Imposto: " + nome + ", (%):" + df.format(percentual * 100) + ", valor (R$): " + df.format(valor);
    }

}
