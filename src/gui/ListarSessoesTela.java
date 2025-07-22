package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Sessao;
import service.SessaoService;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ListarSessoesTela {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void exibir(SessaoService sessaoService) {
        Stage stage = new Stage();
        stage.setTitle("Lista de Sessões");

        TableView<Sessao> table = new TableView<>();

        TableColumn<Sessao, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(
                data.getValue().getId() != null ? data.getValue().getId() : 0L).asObject());

        TableColumn<Sessao, String> colTitulo = new TableColumn<>("Filme");
        colTitulo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getFilme().getTitulo()));

        TableColumn<Sessao, String> colGenero = new TableColumn<>("Gênero");
        colGenero.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getFilme().getGenero()));

        TableColumn<Sessao, Integer> colDuracao = new TableColumn<>("Duração (min)");
        colDuracao.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(
                data.getValue().getFilme().getDuracao()).asObject());

        TableColumn<Sessao, String> colHorario = new TableColumn<>("Horário");
        colHorario.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getHorario().format(FORMATTER)));

        table.getColumns().addAll(colId, colTitulo, colGenero, colDuracao, colHorario);

        List<Sessao> sessoes = sessaoService.listar();
        ObservableList<Sessao> observableList = FXCollections.observableArrayList(sessoes);
        table.setItems(observableList);

        VBox layout = new VBox(10);
        layout.setPadding(new javafx.geometry.Insets(10));
        layout.getChildren().addAll(new Label("Sessões Cadastradas"), table);

        Scene scene = new Scene(layout, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
