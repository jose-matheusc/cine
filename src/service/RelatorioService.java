package service;

import model.Ingresso;
import model.Produto;
import model.Venda;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RelatorioService {

    private final VendaService vendaService;

    public RelatorioService(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    public void gerarRelatorioVendasPorFilme() {
        System.out.println("\n--- Relatório de Vendas por Filme ---");

        Map<String, List<Ingresso>> ingressosPorFilme = vendaService.listarVendas().stream()
                .flatMap(venda -> venda.getIngressos().stream())
                .collect(Collectors.groupingBy(ingresso -> ingresso.getSessao().getFilme().getTitulo()));

        ingressosPorFilme.forEach((titulo, ingressos) -> {
            int quantidade = ingressos.size();
            double receita = ingressos.stream().mapToDouble(Ingresso::getValor).sum();
            System.out.println(
                    "Filme: " + titulo + 
                    " | Ingressos Vendidos: " + quantidade + 
                    " | Receita: R$ " + String.format("%.2f", receita)
            );
        });
    }

    public void gerarRelatorioVendasDeProdutos() {
        System.out.println("\n--- Relatório de Vendas de Produtos ---");

        Map<String, Long> quantidadePorProduto = vendaService.listarVendas().stream()
                .flatMap(venda -> venda.getProdutos().stream())
                .collect(Collectors.groupingBy(Produto::getNome, Collectors.counting()));

        quantidadePorProduto.forEach((nome, quantidade) -> {
            System.out.println("Produto: " + nome + " | Quantidade Vendida: " + quantidade);
        });
    }
    
    public void gerarRelatorioReceitaTotal() {
        System.out.println("\n--- Relatório de Receita Total ---");

        double receitaTotal = vendaService.listarVendas().stream()
                .mapToDouble(Venda::getValorTotal)
                .sum();
        
        double receitaIngressos = vendaService.listarVendas().stream()
                .flatMap(venda -> venda.getIngressos().stream())
                .mapToDouble(Ingresso::getValor)
                .sum();

        double receitaProdutos = vendaService.listarVendas().stream()
                .flatMap(venda -> venda.getProdutos().stream())
                .mapToDouble(Produto::getPreco)
                .sum();

        System.out.println("Receita de Ingressos: R$ " + String.format("%.2f", receitaIngressos));
        System.out.println("Receita de Produtos: R$ " + String.format("%.2f", receitaProdutos));
        System.out.println("------------------------------------");
        System.out.println("RECEITA TOTAL: R$ " + String.format("%.2f", receitaTotal));
    }
}