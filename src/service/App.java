package service;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.ClienteService;
import service.IngressoService;

public class App extends Application {

    private final ClienteService clienteService = new ClienteService();
    private final IngressoService ingressoService = new IngressoService();

    @Override
    public void start(Stage primaryStage) {
        VBox menuLayout = new VBox(10);

        Button btnAdicionarCliente = new Button("Adicionar Cliente");
        Button btnListarClientes = new Button("Listar Clientes");
        Button btnAtualizarCliente = new Button("Atualizar Cliente");
        Button btnExcluirCliente = new Button("Excluir Cliente");
        Button btnComprarIngresso = new Button("Comprar Ingresso");
        Button btnCancelarIngresso = new Button("Cancelar Ingresso");
        Button btnCadastrarSessao = new Button("Cadastrar Sessão");
        Button btnListarSessoes = new Button("Listar Sessões");
        Button btnSair = new Button("Sair");

        btnAdicionarCliente.setOnAction(e -> AdicionarClienteTela.exibir(clienteService));
        btnListarClientes.setOnAction(e -> ListarClientesTela.exibir(clienteService));

        btnSair.setOnAction(e -> primaryStage.close());

        menuLayout.getChildren().addAll(
                btnAdicionarCliente,
                btnListarClientes,
                btnAtualizarCliente,
                btnExcluirCliente,
                btnComprarIngresso,
                btnCancelarIngresso,
                btnCadastrarSessao,
                btnListarSessoes,
                btnSair
        );

        Scene cena = new Scene(menuLayout, 300, 400);
        primaryStage.setTitle("Sistema de Cinema");
        primaryStage.setScene(cena);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
