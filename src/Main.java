import model.Filme;
import model.Ingresso;
import model.Sessao;
import service.ClienteService;
import service.FilmeService;
import service.IngressoService;
import service.SalaService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    private final ClienteService clienteService;
    private final FilmeService filmeService;
    private final SalaService salaService;
    private final IngressoService ingressoService;
    private final Scanner scanner;

    public Main() {
        this.clienteService = new ClienteService();
        this.filmeService = new FilmeService();
        this.salaService = new SalaService();
        this.ingressoService = new IngressoService(filmeService, salaService);
        this.scanner = new Scanner(System.in);
    }

    public void executar() {
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = scanner.nextInt();
            scanner.nextLine();

            // SWITCH CORRIGIDO PARA VERSÕES MAIS ANTIGAS DO JAVA
            switch (opcao) {
                case 1:
                    gerenciarClientes();
                    break;
                case 2:
                    gerenciarFilmes();
                    break;
                case 3:
                    gerenciarIngressosSessoes();
                    break;
                case 0:
                    System.out.println("✅ Saindo do sistema...");
                    break;
                default:
                    System.out.println("❌ Opção inválida. Tente novamente.");
                    break;
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
            case 1: {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Telefone: ");
                String telefone = scanner.nextLine();
                clienteService.adicionar(nome, cpf, email, telefone);
                break;
            }
            case 2: {
                System.out.println("\n--- Clientes Cadastrados ---");
                clienteService.listar().forEach(System.out::println);
                break;
            }
            case 3: {
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
                break;
            }
            case 4: {
                System.out.print("ID do cliente para excluir: ");
                Long id = scanner.nextLong();
                scanner.nextLine();
                clienteService.excluir(id);
                break;
            }
            default:
                System.out.println("❌ Opção inválida.");
                break;
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
            case 1: {
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
                break;
            }
            case 2: {
                System.out.println("\n--- Catálogo de Filmes ---");
                filmeService.listar().forEach(System.out::println);
                break;
            }
            case 3: {
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
                break;
            }
            case 4: {
                System.out.print("ID do filme para excluir: ");
                Long id = scanner.nextLong();
                scanner.nextLine();
                filmeService.excluir(id);
                break;
            }
            default:
                System.out.println("❌ Opção inválida.");
                break;
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
            case 1: {
                try {
                    System.out.println("\n--- Sessões Disponíveis ---");
                    ingressoService.listarSessoes().forEach(System.out::println);
                    System.out.print("Escolha o ID da sessão para comprar o ingresso: ");
                    Long sessaoId = scanner.nextLong();
                    scanner.nextLine();

                    Sessao sessaoEscolhida = ingressoService.buscarSessaoPorId(sessaoId);
                    System.out.println("Sala: " + sessaoEscolhida.getSala().getNome() + " - Assentos disponíveis: " + (sessaoEscolhida.getSala().getCapacidade() - sessaoEscolhida.getSala().getAssentosOcupados()));
                    
                    System.out.print("Assento (Ex: A1, A2): ");
                    String assento = scanner.nextLine();

                    System.out.print("Meia entrada? (s/n): ");
                    boolean meiaEntrada = scanner.nextLine().trim().equalsIgnoreCase("s");

                    String documento = null;
                    if (meiaEntrada) {
                        System.out.print("Documento de meia-entrada: ");
                        documento = scanner.nextLine();
                    }

                    Ingresso ingresso = ingressoService.comprarIngresso(sessaoId, assento, meiaEntrada, documento);
                    System.out.println("✅ Ingresso comprado com sucesso: " + ingresso);
                } catch (Exception e) {
                    System.out.println("❌ Erro: " + e.getMessage());
                }
                break;
            }
            case 2: {
                try {
                    System.out.print("ID do ingresso para cancelar: ");
                    Long ingressoId = scanner.nextLong();
                    scanner.nextLine();
                    ingressoService.cancelarIngresso(ingressoId);
                } catch (Exception e) {
                    System.out.println("❌ Erro: " + e.getMessage());
                }
                break;
            }
            case 3: {
                try {
                    System.out.println("\n--- Salas Disponíveis ---");
                    salaService.listarSalas().forEach(System.out::println);
                    System.out.print("ID da sala para a sessão: ");
                    Long salaId = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("\n--- Filmes em Cartaz ---");
                    filmeService.listar().forEach(System.out::println);
                    System.out.print("ID do filme para a sessão: ");
                    Long filmeId = scanner.nextLong();
                    scanner.nextLine();

                    System.out.print("Data e hora da sessão (yyyy-MM-dd HH:mm): ");
                    String dataHoraStr = scanner.nextLine();
                    LocalDateTime horario = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                    Sessao sessao = ingressoService.cadastrarSessao(filmeId, salaId, horario);
                    System.out.println("✅ Sessão cadastrada: " + sessao);
                } catch (DateTimeParseException e) {
                    System.out.println("❌ Formato de data e hora inválido. Use yyyy-MM-dd HH:mm");
                } catch (Exception e) {
                    System.out.println("❌ Erro: " + e.getMessage());
                }
                break;
            }
            case 4: {
                System.out.println("\n--- Sessões Cadastradas ---");
                ingressoService.listarSessoes().forEach(System.out::println);
                break;
            }
            default:
                System.out.println("❌ Opção inválida.");
                break;
        }
    }
}