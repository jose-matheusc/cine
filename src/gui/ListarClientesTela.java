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
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Cliente;
import service.ClienteService;

public class ListarClientesTela {

    public static void exibir(ClienteService clienteService) {
        Stage stage = new Stage();
        stage.setTitle("📋 Lista de Clientes");

        Label titulo = new Label("Lista de Clientes Cadastrados");
        titulo.setFont(new Font("Arial", 20));
        titulo.setPadding(new Insets(10, 0, 10, 0));

        TableView<Cliente> tabela = new TableView<>();
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Cliente, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Cliente, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Cliente, String> colCpf = new TableColumn<>("CPF");
        colCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        TableColumn<Cliente, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Cliente, String> colTelefone = new TableColumn<>("Telefone");
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        tabela.getColumns().addAll(colId, colNome, colCpf, colEmail, colTelefone);

        ObservableList<Cliente> dados = FXCollections.observableArrayList(clienteService.carregarClientesDoArquivo());
        tabela.setItems(dados);

        VBox layout = new VBox(10, titulo, tabela);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f9f9f9;");

        Scene cena = new Scene(layout, 700, 450);
        stage.setScene(cena);
        stage.show();
    }
}
