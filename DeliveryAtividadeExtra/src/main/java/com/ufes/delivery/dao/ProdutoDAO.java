package com.ufes.delivery.dao;

import com.ufes.delivery.model.Produto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProdutoDAO {

    private final ArrayList<Produto> produtos;
    private static ProdutoDAO instance;

    private ProdutoDAO() {
        produtos = new ArrayList<>();
        produtos.add(new Produto(1, "Lápis 0.7", 6, 11.99));
        produtos.add(new Produto(2, "Lápis 0.7 - caixa c/ 100", 15, 29.9));
        produtos.add(new Produto(3, "Borracha", 8, 24.10));
        produtos.add(new Produto(4, "Borracha - caixa c/ 100", 5, 3.78));
        produtos.add(new Produto(5, "Lapiseira", 15, 4.95));
        produtos.add(new Produto(6, "Lapiseira - caixa c/ 100", 7, 19.50));
        produtos.add(new Produto(7, "Folha A4", 13, 2.65));
        produtos.add(new Produto(8, "Folha A4 - caixa c/ 1000", 9, 19.30));
        produtos.add(new Produto(9, "Caneta", 7, 3.55));
        produtos.add(new Produto(10, "Caneta - caixa c/ 100", 6, 2.70));
        produtos.add(new Produto(11, "Mouse Pad", 6, 6.65));
        produtos.add(new Produto(12, "MousePad Gamer", 14, 3.71));
        produtos.add(new Produto(13, "HeadSet", 5, 25.79));
        produtos.add(new Produto(14, "HeadSet Gamer", 13, 3.15));
        produtos.add(new Produto(15, "Teclado", 10, 5.95));
        produtos.add(new Produto(16, "Teclado Gamer", 12, 41.10));
        produtos.add(new Produto(17, "Mouse", 13, 7.89));
        produtos.add(new Produto(18, "Mouse Gamer", 5, 1.45));
        produtos.add(new Produto(19, "Monitor 12 polegadas", 5, 33.95));
        produtos.add(new Produto(20, "Monitor 24 polegadas", 7, 1.29));
    }

    public static ProdutoDAO getInstance() {
        if (instance == null) {
            instance = new ProdutoDAO();
        }
        return instance;
    }

    private void verificaQuantidade(double quantidade) {
        if (quantidade <= 0) {
            throw new RuntimeException("Quantidade deve ser > 0");
        }
    }

    public void adicionaEstoque(int codigo, double quantidade) {
        verificaQuantidade(quantidade);
        Produto produto = buscaProdutoPorCodigo(codigo);
        produto.incrementaEstoque(quantidade);
    }

    private double getQuantidadeEmEstoque(int codigo) {
        return buscaProdutoPorCodigo(codigo).getQuantidadeEmEstoque();
    }

    public void baixaEstoque(int codigo, double quantidade) {
        verificaQuantidade(quantidade);
        Produto produto = buscaProdutoPorCodigo(codigo);
        double quantidadeEmEstoque = getQuantidadeEmEstoque(codigo);
        if (quantidadeEmEstoque >= quantidade) {
            produto.decrementaEstoque(quantidade);
        } else {
            throw new RuntimeException("Quantiade (" + quantidade + ") do produto " + codigo + "insuficiente em estoque (" + quantidadeEmEstoque + ")");
        }

    }

    public Produto buscaProdutoPorNome(String nome) {
        for (Produto produto : produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        throw new RuntimeException("Produto " + nome + " não encontrado!");
    }

    public Produto buscaProdutoPorCodigo(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        throw new RuntimeException("Produto com o código " + codigo + " não encontrado!");
    }

    public List<Produto> getProdutos() {
        return Collections.unmodifiableList(produtos);
    }

    public int getNroProdutos() {
        return produtos.size();
    }

}
