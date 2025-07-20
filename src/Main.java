import model.Ingresso;
import service.ClienteService;
import service.IngressoService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        IngressoService ingressoService = new IngressoService();
        Main main = new Main(ingressoService);
        main.executar();
    }

    private final ClienteService clienteService;

    private final IngressoService ingressoService;


    public Main(IngressoService ingressoService) {
        this.ingressoService = ingressoService;
        this.clienteService = new ClienteService();
    }

    public void executar() {
        System.out.printf(getOpcao());

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int opcao;

        while (true) {
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Login: ");
                    String login = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();

                    clienteService.adicionar(login, senha, nome, cpf, email, telefone);
                    System.out.println("Cliente adicionado com sucesso.");
                    System.out.printf(getOpcao());
                }
                case 2 -> {
                    System.out.println("Clientes cadastrados:");
//                    clienteService.listar().forEach(System.out::println);
                    System.out.printf(getOpcao());
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

//                    clienteService.atualizar(id, nome, cpf, email, telefone);
                    System.out.println("Cliente atualizado com sucesso.");
                    System.out.printf(getOpcao());
                }

                case 4 -> {
                    System.out.print("ID do cliente para excluir: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();

//                    clienteService.excluir(id);
                    System.out.println("Cliente excluído com sucesso.");
                    System.out.printf(getOpcao());
                }

                case 5 -> {
                    System.out.print("Filme: ");
                    String filme = scanner.nextLine();

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

                    Ingresso ingresso = ingressoService.comprarIngresso(filme, horarioSessao, assento, meiaEntrada, documento);
                    System.out.println("Ingresso comprado com sucesso: " + ingresso);
                }

                case 6 -> {
                    System.out.print("ID do ingresso para cancelar: ");
                    Long ingressoId = Long.parseLong(scanner.nextLine());

                    ingressoService.cancelarIngresso(ingressoId);
                    System.out.println("Ingresso cancelado com sucesso.");
                }

                case 7 -> {
                    System.out.print("Título do filme: ");
                    String titulo = scanner.nextLine();
                    var filme = ingressoService.cadastrarFilme(titulo);

                    System.out.print("Data e hora da sessão (yyyy-MM-dd HH:mm): ");
                    String dataHoraStr = scanner.nextLine();
                    LocalDateTime horario = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                    var sessao = ingressoService.cadastrarSessao(filme.getId(), horario);
                    System.out.println("✅ Sessão cadastrada: " + sessao);
                }

                case 8 -> {
                    System.out.println("🎞️ Sessões cadastradas:");
                    ingressoService.listarSessoes().forEach(System.out::println);
                }


                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }


    private String getOpcao() {
        return """
            Escolha uma opção:
            1 - Adicionar Cliente
            2 - Listar Clientes
            3 - Atualizar Cliente
            4 - Excluir Cliente
            5 - Comprar ingresso
            6 - Cancelar ingresso
            0 - Sair
            """;
    }

}
