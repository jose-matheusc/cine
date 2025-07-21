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
import model.Sessao;
import service.SessaoService;

import java.time.format.DateTimeFormatter;

public class ListarSessoesTela {

    public static void exibir(SessaoService sessaoService) {
        Stage stage = new Stage();
        stage.setTitle("🎟️ Lista de Sessões");

        Label titulo = new Label("Sessões de Cinema");

        TableView<Sessao> tabela = new TableView<>();
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Sessao, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Sessao, String> colFilme = new TableColumn<>("Filme");
        colFilme.setCellValueFactory(celula -> javafx.beans.binding.Bindings.createStringBinding(() ->
                celula.getValue().getFilme().getTitulo()
        ));

        TableColumn<Sessao, String> colHorario = new TableColumn<>("Horário");
        colHorario.setCellValueFactory(celula -> javafx.beans.binding.Bindings.createStringBinding(() ->
                celula.getValue().getHorario().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
        ));

        tabela.getColumns().addAll(colId, colFilme, colHorario);

        ObservableList<Sessao> dados = FXCollections.observableArrayList(sessaoService.listar());
        tabela.setItems(dados);

        VBox layout = new VBox(10, titulo, tabela);
        layout.setPadding(new Insets(20));

        Scene cena = new Scene(layout, 500, 300);
        stage.setScene(cena);
        stage.show();
    }
}
