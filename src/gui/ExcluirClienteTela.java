package gui;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.ClienteService;

public class ExcluirClienteTela {

    public static void exibir(ClienteService clienteService) {
        Stage stage = new Stage();
        stage.setTitle("Excluir Cliente");

        TextField idField = new TextField();
        idField.setPromptText("ID do cliente");

        Button btnExcluir = new Button("Excluir");

        btnExcluir.setOnAction(e -> {
            try {
                Long id = Long.parseLong(idField.getText());
                boolean excluido = clienteService.excluirPorId(id);
                if (excluido) {
                    alert("Cliente excluído com sucesso!");
                    stage.close();
                } else {
                    alert("Cliente não encontrado.");
                }
            } catch (NumberFormatException ex) {
                alert("ID inválido.");
            }
        });

        VBox layout = new VBox(10, new Label("ID do Cliente:"), idField, btnExcluir);
        layout.setPadding(new javafx.geometry.Insets(20));

        Scene scene = new Scene(layout, 300, 150);
        stage.setScene(scene);
        stage.show();
    }

    private static void alert(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION, mensagem, ButtonType.OK);
        alerta.showAndWait();
    }
}

