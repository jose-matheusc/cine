package service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Ingresso;
import model.Produto;
import model.Sessao;
import model.Venda;

public class RelatorioService {

    private final VendaService vendaService;
    private final IngressoService ingressoService;

    public RelatorioService(VendaService vendaService, IngressoService ingressoService) {
        this.vendaService = vendaService;
        this.ingressoService = ingressoService;
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
    
    public void gerarRelatorioOcupacaoDasSalas() {
        System.out.println("\n--- Relatório de Ocupação por Sessão ---");
        List<Sessao> sessoes = ingressoService.listarSessoes();

        if (sessoes.isEmpty()) {
            System.out.println("Nenhuma sessão cadastrada para gerar relatório.");
            return;
        }

        sessoes.forEach(sessao -> {
            long ingressosVendidos = vendaService.listarVendas().stream()
                .flatMap(venda -> venda.getIngressos().stream())
                .filter(ingresso -> ingresso.getSessao().getId().equals(sessao.getId()))
                .count();
            
            double capacidade = sessao.getSala().getCapacidade();
            double ocupacao = (capacidade > 0) ? (ingressosVendidos / capacidade) * 100 : 0;

            System.out.println(
                "Sessão ID: " + sessao.getId() + 
                " | Filme: " + sessao.getFilme().getTitulo() + 
                " | Ocupação: " + ingressosVendidos + "/" + (int)capacidade +
                " (" + String.format("%.1f", ocupacao) + "%)"
            );
        });
    }
    
    public void gerarRelatorioClientesFrequentes() {
        System.out.println("\n--- Relatório de Clientes Frequentes ---");
        
        Map<String, Long> comprasPorCliente = vendaService.listarVendas().stream()
            .collect(Collectors.groupingBy(venda -> venda.getCliente().getNome(), Collectors.counting()));
            
        if (comprasPorCliente.isEmpty()) {
            System.out.println("Nenhuma venda registrada para gerar relatório.");
            return;
        }

        comprasPorCliente.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .forEach(entry -> {
                System.out.println("Cliente: " + entry.getKey() + " | Total de Compras: " + entry.getValue());
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