package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Filme;
import service.FilmeService;

public class AdicionarFilmeTela {

    public static void exibir(FilmeService filmeService) {
        Stage stage = new Stage();
        stage.setTitle("Adicionar Filme");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        TextField tituloField = new TextField();
        tituloField.setPromptText("Título do filme");

        TextField generoField = new TextField();
        generoField.setPromptText("Gênero");

        TextField duracaoField = new TextField();
        duracaoField.setPromptText("Duração (minutos)");

        Button btnSalvar = new Button("Salvar");

        btnSalvar.setOnAction(e -> {
            try {
                String titulo = tituloField.getText().trim();
                String genero = generoField.getText().trim();
                int duracao = Integer.parseInt(duracaoField.getText().trim());

                if (titulo.isEmpty() || genero.isEmpty()) {
                    alert("Preencha todos os campos!");
                    return;
                }

                Filme filme = new Filme(null, titulo, genero, duracao);
                filmeService.adicionar(filme);

                alert("Filme adicionado com sucesso!");
                stage.close();

            } catch (NumberFormatException ex) {
                alert("Duração inválida. Informe um número inteiro.");
            } catch (Exception ex) {
                alert("Erro: " + ex.getMessage());
            }
        });


        grid.add(new Label("Título:"), 0, 0);
        grid.add(tituloField, 1, 0);
        grid.add(new Label("Gênero:"), 0, 1);
        grid.add(generoField, 1, 1);
        grid.add(new Label("Duração:"), 0, 2);
        grid.add(duracaoField, 1, 2);
        grid.add(btnSalvar, 1, 3);

        Scene scene = new Scene(grid, 350, 200);
        stage.setScene(scene);
        stage.show();
    }

    private static void alert(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
