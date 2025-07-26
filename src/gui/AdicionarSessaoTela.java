package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Filme;
import model.Sessao;
import service.FilmeService;
import service.SessaoService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdicionarSessaoTela {

    public static void exibir(SessaoService sessaoService, FilmeService filmeService) {
        Stage stage = new Stage();
        stage.setTitle("Cadastrar Sessão");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        ComboBox<Filme> cbFilmes = new ComboBox<>();
        List<Filme> filmes = filmeService.listar();
        cbFilmes.getItems().addAll(filmes);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String horarioPadrao = LocalDateTime.now().plusHours(1).format(formatter);

        TextField horarioField = new TextField(horarioPadrao);
        horarioField.setPromptText("Ex: 2025-08-10 19:30");


        Button btnSalvar = new Button("Salvar");

        btnSalvar.setOnAction(e -> {
            try {
                Filme filmeSelecionado = cbFilmes.getValue();
                String horarioTexto = horarioField.getText().trim();

                if (filmeSelecionado == null || horarioTexto.isEmpty()) {
                    alert("Preencha todos os campos.");
                    return;
                }

                LocalDateTime horario = LocalDateTime.parse(horarioTexto, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                Sessao sessao = new Sessao(null, filmeSelecionado, horario);
                sessaoService.adicionar(sessao);

                alert("Sessão cadastrada com sucesso!");
                stage.close();
            } catch (Exception ex) {
                alert("Erro: " + ex.getMessage());
            }
        });

        grid.add(new Label("Filme:"), 0, 0);
        grid.add(cbFilmes, 1, 0);
        grid.add(new Label("Horário:"), 0, 1);
        grid.add(horarioField, 1, 1);
        grid.add(btnSalvar, 1, 2);

        Scene scene = new Scene(grid, 400, 200);
        stage.setScene(scene);
        stage.show();
    }

    private static void alert(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
