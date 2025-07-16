import model.Filme;
import model.Ingresso;
import service.ClienteService;
import service.FilmeService;
import service.IngressoService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    private final ClienteService clienteService;
    private final FilmeService filmeService;
    private final IngressoService ingressoService;
    private final Scanner scanner;

    public Main() {
        this.clienteService = new ClienteService();
        this.filmeService = new FilmeService();
        this.ingressoService = new IngressoService(filmeService);
        this.scanner = new Scanner(System.in);
    }

    public void executar() {
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> gerenciarClientes();
                case 2 -> gerenciarFilmes();
                case 3 -> gerenciarIngressosSessoes();
                case 0 -> System.out.println("✅ Saindo do sistema...");
                default -> System.out.println("❌ Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n--- CINE-SYSTEM ---");
        System.out.println("1 - Gerenciar Clientes");
        System.out.println("2 - Gerenciar Filmes");
        System.out.println("3 - Ingressos e Sessões");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void gerenciarClientes() {
        System.out.println("\n-- Gerenciar Clientes --");
        System.out.println("1 - Adicionar Cliente");
        System.out.println("2 - Listar Clientes");
        System.out.println("3 - Atualizar Cliente");
        System.out.println("4 - Excluir Cliente");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Telefone: ");
                String telefone = scanner.nextLine();
                clienteService.adicionar(nome, cpf, email, telefone);
            }
            case 2 -> {
                System.out.println("\n--- Clientes Cadastrados ---");
                clienteService.listar().forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("ID do cliente para atualizar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Novo Email: ");
                String email = scanner.nextLine();
                System.out.print("Novo Telefone: ");
                String telefone = scanner.nextLine();
                clienteService.atualizar(id, nome, cpf, email, telefone);
            }
            case 4 -> {
                System.out.print("ID do cliente para excluir: ");
                Long id = scanner.nextLong();
                scanner.nextLine();
                clienteService.excluir(id);
            }
            default -> System.out.println("❌ Opção inválida.");
        }
    }

    private void gerenciarFilmes() {
        System.out.println("\n-- Gerenciar Filmes --");
        System.out.println("1 - Adicionar Filme");
        System.out.println("2 - Listar Filmes");
        System.out.println("3 - Atualizar Filme");
        System.out.println("4 - Excluir Filme");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> {
                System.out.print("Título: ");
                String titulo = scanner.nextLine();
                System.out.print("Sinopse: ");
                String sinopse = scanner.nextLine();
                System.out.print("Duração (minutos): ");
                int duracao = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Gênero: ");
                String genero = scanner.nextLine();
                System.out.print("Classificação Indicativa (idade): ");
                int classificacao = scanner.nextInt();
                scanner.nextLine();
                filmeService.adicionar(titulo, sinopse, duracao, genero, classificacao);
            }
            case 2 -> {
                System.out.println("\n--- Catálogo de Filmes ---");
                filmeService.listar().forEach(System.out::println);
            }
            case 3 -> {
                System.out.print("ID do filme para atualizar: ");
                Long id = scanner.nextLong();
                scanner.nextLine();
                System.out.print("Novo Título: ");
                String titulo = scanner.nextLine();
                System.out.print("Nova Sinopse: ");
                String sinopse = scanner.nextLine();
                System.out.print("Nova Duração (minutos): ");
                int duracao = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Novo Gênero: ");
                String genero = scanner.nextLine();
                System.out.print("Nova Classificação Indicativa (idade): ");
                int classificacao = scanner.nextInt();
                scanner.nextLine();
                filmeService.atualizar(id, titulo, sinopse, duracao, genero, classificacao);
            }
            case 4 -> {
                System.out.print("ID do filme para excluir: ");
                Long id = scanner.nextLong();
                scanner.nextLine();
                filmeService.excluir(id);
            }
            default -> System.out.println("❌ Opção inválida.");
        }
    }

    private void gerenciarIngressosSessoes() {
        System.out.println("\n-- Ingressos e Sessões --");
        System.out.println("1 - Comprar Ingresso");
        System.out.println("2 - Cancelar Ingresso");
        System.out.println("3 - Cadastrar Sessão");
        System.out.println("4 - Listar Sessões");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch(opcao) {
            case 1 -> {
                try {
                    System.out.println("\n--- Filmes em Cartaz ---");
                    filmeService.listar().forEach(f -> System.out.println("ID: " + f.getId() + " - " + f.getTitulo()));
                    System.out.print("Escolha o ID do filme: ");
                    Long filmeId = scanner.nextLong();
                    scanner.nextLine();
                    Filme filmeEscolhido = filmeService.buscarPorId(filmeId);

                    System.out.print("Data e hora da sessão (yyyy-MM-dd HH:mm): ");
                    String dataHoraStr = scanner.nextLine();
                    LocalDateTime horarioSessao = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                    System.out.print("Assento: ");
                    String assento = scanner.nextLine();

                    System.out.print("Meia entrada? (s/n): ");
                    boolean meiaEntrada = scanner.nextLine().trim().equalsIgnoreCase("s");

                    String documento = null;
                    if (meiaEntrada) {
                        System.out.print("Documento de meia-entrada: ");
                        documento = scanner.nextLine();
                    }

                    Ingresso ingresso = ingressoService.comprarIngresso(filmeEscolhido.getTitulo(), horarioSessao, assento, meiaEntrada, documento);
                    System.out.println("✅ Ingresso comprado com sucesso: " + ingresso);
                } catch (DateTimeParseException e) {
                    System.out.println("❌ Formato de data e hora inválido. Use yyyy-MM-dd HH:mm");
                } catch (Exception e) {
                    System.out.println("❌ Erro: " + e.getMessage());
                }
            }
            case 2 -> {
                try {
                    System.out.print("ID do ingresso para cancelar: ");
                    Long ingressoId = scanner.nextLong();
                    scanner.nextLine();
                    ingressoService.cancelarIngresso(ingressoId);
                } catch (Exception e) {
                    System.out.println("❌ Erro: " + e.getMessage());
                }
            }
            case 3 -> {
                try {
                    System.out.println("\n--- Filmes Disponíveis ---");
                    filmeService.listar().forEach(System.out::println);
                    System.out.print("ID do filme para criar a sessão: ");
                    Long filmeId = scanner.nextLong();
                    scanner.nextLine();

                    System.out.print("Data e hora da sessão (yyyy-MM-dd HH:mm): ");
                    String dataHoraStr = scanner.nextLine();
                    LocalDateTime horario = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                    var sessao = ingressoService.cadastrarSessao(filmeId, horario);
                    System.out.println("✅ Sessão cadastrada: " + sessao);
                } catch (DateTimeParseException e) {
                    System.out.println("❌ Formato de data e hora inválido. Use yyyy-MM-dd HH:mm");
                } catch (Exception e) {
                    System.out.println("❌ Erro: " + e.getMessage());
                }
            }
            case 4 -> {
                System.out.println("\n--- Sessões Cadastradas ---");
                ingressoService.listarSessoes().forEach(System.out::println);
            }
            default -> System.out.println("❌ Opção inválida.");
        }
    }
}