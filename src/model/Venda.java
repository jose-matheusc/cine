package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private Long id;
    private Cliente cliente;
    private List<Ingresso> ingressos;
    private List<Produto> produtos;
    private double valorTotal;
    private LocalDateTime dataHora;

    public Venda(Long id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.ingressos = new ArrayList<>();
        this.produtos = new ArrayList<>();
        this.valorTotal = 0.0;
        this.dataHora = LocalDateTime.now();
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    
    public void adicionarIngresso(Ingresso ingresso) {
        this.ingressos.add(ingresso);
        recalcularValorTotal();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            this.produtos.add(produto);
        }
        produto.reduzirEstoque(quantidade);
        recalcularValorTotal();
    }

    private void recalcularValorTotal() {
        double totalIngressos = ingressos.stream().mapToDouble(Ingresso::getValor).sum();
        double totalProdutos = produtos.stream().mapToDouble(Produto::getPreco).sum();
        this.valorTotal = totalIngressos + totalProdutos;
    }

    @Override
    public String toString() {
        return "Venda [" +
               "ID=" + id +
               ", Cliente=" + cliente.getNome() +
               ", Valor Total=R$ " + String.format("%.2f", valorTotal) +
               ']';
    }
}