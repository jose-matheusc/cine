package service;

import gui.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private final ClienteService clienteService = new ClienteService();
    private final FilmeService filmeService = new FilmeService();
    private final SessaoService sessaoService = new SessaoService();
    private final IngressoService ingressoService = new IngressoService();

    @Override
    public void start(Stage primaryStage) {
        VBox menuLayout = new VBox(20);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setPadding(new Insets(30));
        menuLayout.setStyle("-fx-background-color: #f2f2f2;");

        String estiloBotao = "-fx-background-color: #007acc; -fx-text-fill: white; "
                + "-fx-font-size: 14px; -fx-pref-width: 220px; -fx-padding: 10px;";

        Button btnAdicionarCliente = new Button("➕ Adicionar Cliente");
        Button btnListarClientes = new Button("📄 Listar Clientes");
        Button btnAtualizarCliente = new Button("✏ Atualizar Cliente");
        Button btnExcluirCliente = new Button("🗑 Excluir Cliente");

        Button btnAdicionarFilme = new Button("🎬 Adicionar Filme");
        Button btnListarFilmes = new Button("📽 Listar Filmes");

        Button btnCadastrarSessao = new Button("🕒 Cadastrar Sessão");
        Button btnListarSessoes = new Button("📅 Listar Sessões");

        Button btnComprarIngresso = new Button("🎟 Comprar Ingresso");
        Button btnCancelarIngresso = new Button("❌ Cancelar Ingresso");

        Button btnSair = new Button("🚪 Sair");

        for (Button btn : new Button[]{
                btnAdicionarCliente, btnListarClientes, btnAtualizarCliente, btnExcluirCliente,
                btnAdicionarFilme, btnListarFilmes,
                btnCadastrarSessao, btnListarSessoes,
                btnComprarIngresso, btnCancelarIngresso,
                btnSair
        }) {
            btn.setStyle(estiloBotao);
        }

        btnAdicionarCliente.setOnAction(e -> AdicionarClienteTela.exibir(clienteService));
        btnListarClientes.setOnAction(e -> ListarClientesTela.exibir(clienteService));
        btnAtualizarCliente.setOnAction(e -> AtualizarClienteTela.exibir(clienteService));
        btnExcluirCliente.setOnAction(e -> ExcluirClienteTela.exibir(clienteService));

        btnAdicionarFilme.setOnAction(e -> {
            AdicionarFilmeTela.exibir(filmeService);
        });

        btnListarFilmes.setOnAction(e -> {
            ListarFilmesTela.exibir(filmeService);
        });


        btnAdicionarFilme.setOnAction(e -> {
            AdicionarFilmeTela.exibir(filmeService);
        });


        btnListarSessoes.setOnAction(e -> ListarSessoesTela.exibir(sessaoService));


        btnCadastrarSessao.setOnAction(e -> {
            AdicionarSessaoTela.exibir(sessaoService, filmeService);
        });

        btnComprarIngresso.setOnAction(e -> {
            ComprarIngressoTela.exibir(ingressoService, sessaoService);
        });

        btnCancelarIngresso.setOnAction(e -> {
            CancelarIngressoTela.exibir(ingressoService);
        });


        btnSair.setOnAction(e -> primaryStage.close());

        menuLayout.getChildren().addAll(
                btnAdicionarCliente,
                btnListarClientes,
                btnAtualizarCliente,
                btnExcluirCliente,
                btnAdicionarFilme,
                btnListarFilmes,
                btnCadastrarSessao,
                btnListarSessoes,
                btnComprarIngresso,
                btnCancelarIngresso,
                btnSair
        );

        Scene cena = new Scene(menuLayout, 350, 600);
        primaryStage.setTitle("🎬 Sistema de Cinema");
        primaryStage.setScene(cena);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
