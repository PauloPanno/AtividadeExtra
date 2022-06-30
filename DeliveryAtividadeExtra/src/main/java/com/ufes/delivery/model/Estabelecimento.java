package com.ufes.delivery.model;

public class Estabelecimento {

    private final String nome;

    public Estabelecimento(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    @Override
    public String toString() {
        return "Estabelecimento: " + getNome();
    }

}
