package service;

import model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoService {
    private final List<Produto> produtos = new ArrayList<>();
    private Long proximoId = 1L;

    public ProdutoService() {
        adicionarProduto("Pipoca Grande", 25.00, 100);
        adicionarProduto("Pipoca Média", 20.00, 100);
        adicionarProduto("Refrigerante 500ml", 8.00, 200);
        adicionarProduto("Água 500ml", 5.00, 200);
        adicionarProduto("Chocolate", 7.00, 150);
        adicionarProduto("Combo Pipoca Média + Refri", 26.00, 80);
    }

    private void adicionarProduto(String nome, double preco, int estoque) {
        Produto produto = new Produto(proximoId++, nome, preco, estoque);
        this.produtos.add(produto);
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

    public Produto buscarPorId(Long id) {
        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));
    }

    public void verificarEstoque(Produto produto, int quantidadeDesejada) {
        if (produto.getEstoque() < quantidadeDesejada) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
        }
    }
}