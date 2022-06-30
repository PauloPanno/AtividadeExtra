package com.ufes.delivery.model;

import java.text.DecimalFormat;

public class Cliente {

    private final String nome;
    private double saldo;

    public Cliente(String nome, double saldo) {
        this.nome = nome;
        this.saldo = saldo;
    }

    public String getNome() {
        return nome;
    }

    public void aumentaSaldo(double valor) {
        this.saldo += valor;
    }

    public void deduzSaldo(double valor) {
        this.saldo -= valor;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "Cliente: " + nome + ", saldo de R$ " + df.format(saldo);
    }

}
