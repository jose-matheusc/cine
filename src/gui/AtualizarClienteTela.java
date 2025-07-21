package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Cliente;
import service.ClienteService;

public class AtualizarClienteTela {

    public static void exibir(ClienteService clienteService) {
        Stage stage = new Stage();
        stage.setTitle("Atualizar Cliente");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        TextField idField = new TextField();
        idField.setPromptText("ID do cliente");

        TextField nomeField = new TextField();
        TextField cpfField = new TextField();
        TextField emailField = new TextField();
        TextField telefoneField = new TextField();

        Button btnBuscar = new Button("Buscar");
        Button btnAtualizar = new Button("Atualizar");

        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);
        grid.add(btnBuscar, 2, 0);

        grid.add(new Label("Nome:"), 0, 1);
        grid.add(nomeField, 1, 1);

        grid.add(new Label("CPF:"), 0, 2);
        grid.add(cpfField, 1, 2);

        grid.add(new Label("Email:"), 0, 3);
        grid.add(emailField, 1, 3);

        grid.add(new Label("Telefone:"), 0, 4);
        grid.add(telefoneField, 1, 4);

        grid.add(btnAtualizar, 1, 5);

        btnBuscar.setOnAction(e -> {
            try {
                Long id = Long.parseLong(idField.getText());
                Cliente cliente = clienteService.buscarPorId(id);
                if (cliente != null) {
                    nomeField.setText(cliente.getNome());
                    cpfField.setText(cliente.getCpf());
                    emailField.setText(cliente.getEmail());
                    telefoneField.setText(cliente.getTelefone());
                } else {
                    alert("Cliente não encontrado!");
                }
            } catch (NumberFormatException ex) {
                alert("ID inválido.");
            }
        });

        btnAtualizar.setOnAction(e -> {
            try {
                Long id = Long.parseLong(idField.getText());
                clienteService.atualizarCliente(id, nomeField.getText(), cpfField.getText(), emailField.getText(), telefoneField.getText());
                alert("Cliente atualizado com sucesso!");
                stage.close();
            } catch (Exception ex) {
                alert(ex.getMessage());
            }
        });

        Scene cena = new Scene(grid, 400, 250);
        stage.setScene(cena);
        stage.show();
    }

    private static void alert(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION, mensagem, ButtonType.OK);
        alerta.showAndWait();
    }
}
