package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.IngressoService;

public class CancelarIngressoTela {

    public static void exibir(IngressoService ingressoService) {
        Stage stage = new Stage();
        stage.setTitle("❌ Cancelar Ingresso");

        TextField tfId = new TextField();
        tfId.setPromptText("ID do ingresso");

        Button btnCancelar = new Button("Cancelar Ingresso");

        btnCancelar.setOnAction(e -> {
            try {
                Long id = Long.parseLong(tfId.getText().trim());
                boolean sucesso = ingressoService.cancelar(id);
                if (sucesso) {
                    alert("Ingresso cancelado com sucesso!");
                    stage.close();
                } else {
                    alert("Ingresso não encontrado ou já cancelado.");
                }
            } catch (NumberFormatException ex) {
                alert("ID inválido.");
            }
        });

        VBox layout = new VBox(10, new Label("Informe o ID do ingresso:"), tfId, btnCancelar);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 300, 150);
        stage.setScene(scene);
        stage.show();
    }

    private static void alert(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
