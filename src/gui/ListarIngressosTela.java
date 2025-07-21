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
import model.Ingresso;
import service.IngressoService;

import java.time.format.DateTimeFormatter;

public class ListarIngressosTela {

    public static void exibir(IngressoService ingressoService) {
        Stage stage = new Stage();
        stage.setTitle("🎫 Lista de Ingressos");

        Label titulo = new Label("Ingressos Comprados");

        TableView<Ingresso> tabela = new TableView<>();
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Ingresso, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Ingresso, String> colFilme = new TableColumn<>("Filme");
        colFilme.setCellValueFactory(new PropertyValueFactory<>("filme"));

        TableColumn<Ingresso, String> colHorario = new TableColumn<>("Horário");
        colHorario.setCellValueFactory(celula -> javafx.beans.binding.Bindings.createStringBinding(() ->
                celula.getValue().getHorarioSessao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
        ));

        TableColumn<Ingresso, String> colAssento = new TableColumn<>("Assento");
        colAssento.setCellValueFactory(new PropertyValueFactory<>("assento"));

        TableColumn<Ingresso, Boolean> colMeia = new TableColumn<>("Meia");
        colMeia.setCellValueFactory(new PropertyValueFactory<>("meiaEntrada"));

        TableColumn<Ingresso, Boolean> colCancelado = new TableColumn<>("Cancelado");
        colCancelado.setCellValueFactory(new PropertyValueFactory<>("cancelado"));

        tabela.getColumns().addAll(colId, colFilme, colHorario, colAssento, colMeia, colCancelado);

        ObservableList<Ingresso> dados = FXCollections.observableArrayList(ingressoService.listar());
        tabela.setItems(dados);

        VBox layout = new VBox(10, titulo, tabela);
        layout.setPadding(new Insets(20));

        Scene cena = new Scene(layout, 800, 400);
        stage.setScene(cena);
        stage.show();
    }
}
