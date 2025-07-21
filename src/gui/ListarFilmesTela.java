package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Filme;
import service.FilmeService;

public class ListarFilmesTela {

    public static void exibir(FilmeService filmeService) {
        Stage stage = new Stage();
        stage.setTitle("🎬 Lista de Filmes");

        Label titulo = new Label("Filmes Disponíveis");

        TableView<Filme> tabela = new TableView<>();
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Filme, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Filme, String> colTitulo = new TableColumn<>("Título");
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        tabela.getColumns().addAll(colId, colTitulo);

        ObservableList<Filme> dados = FXCollections.observableArrayList(filmeService.listar());
        tabela.setItems(dados);

        VBox layout = new VBox(10, titulo, tabela);
        layout.setPadding(new Insets(20));

        Scene cena = new Scene(layout, 400, 300);
        stage.setScene(cena);
        stage.show();
    }
}
