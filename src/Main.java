import service.ClienteService;

public class Main {

    private final ClienteService clienteService;

    public Main() {
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
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();

                    clienteService.adicionar(nome, cpf, email, telefone);
                    System.out.println("Cliente adicionado com sucesso.");
                    System.out.printf(getOpcao());
                }
                case 2 -> {
                    System.out.println("Clientes cadastrados:");
                    clienteService.listar().forEach(System.out::println);
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

                    clienteService.atualizar(id, nome, cpf, email, telefone);
                    System.out.println("Cliente atualizado com sucesso.");
                    System.out.printf(getOpcao());
                }

                case 4 -> {
                    System.out.print("ID do cliente para excluir: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();

                    clienteService.excluir(id);
                    System.out.println("Cliente excluído com sucesso.");
                    System.out.printf(getOpcao());
                }

                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void main(String[] args) {
        new Main().executar();
    }

    private String getOpcao() {
        return """
            Escolha uma opção:
            1 - Adicionar Cliente
            2 - Listar Clientes
            3 - Atualizar Cliente
            4 - Excluir Cliente
            0 - Sair
            """;
    }

}
