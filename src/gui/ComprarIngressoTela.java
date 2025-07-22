package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Ingresso;
import model.Sessao;
import service.IngressoService;
import service.SessaoService;
import java.util.List;

public class ComprarIngressoTela {

    public static void exibir(IngressoService ingressoService, SessaoService sessaoService) {
        Stage stage = new Stage();
        stage.setTitle("🎟 Comprar Ingresso");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label lblSessao = new Label("Sessão:");
        ComboBox<Sessao> cbSessoes = new ComboBox<>();
        List<Sessao> sessoes = sessaoService.listar();
        cbSessoes.getItems().addAll(sessoes);

        Label lblAssento = new Label("Assento:");
        TextField tfAssento = new TextField();
        tfAssento.setPromptText("Exemplo: A10");

        CheckBox cbMeiaEntrada = new CheckBox("Meia Entrada");
        TextField tfDocumentoMeia = new TextField();
        tfDocumentoMeia.setPromptText("Documento para meia entrada");
        tfDocumentoMeia.setDisable(true);

        cbMeiaEntrada.selectedProperty().addListener((obs, oldVal, newVal) -> {
            tfDocumentoMeia.setDisable(!newVal);
            if (!newVal) tfDocumentoMeia.clear();
        });

        Button btnComprar = new Button("Comprar");

        btnComprar.setOnAction(e -> {
            try {
                Sessao sessaoSelecionada = cbSessoes.getValue();
                String assento = tfAssento.getText().trim();
                boolean meiaEntrada = cbMeiaEntrada.isSelected();
                String documentoMeia = tfDocumentoMeia.getText().trim();

                if (sessaoSelecionada == null || assento.isEmpty() || (meiaEntrada && documentoMeia.isEmpty())) {
                    alert("Preencha todos os campos necessários!");
                    return;
                }

                Ingresso ingresso = new Ingresso(
                        null,
                        sessaoSelecionada.getFilme().getTitulo(),
                        sessaoSelecionada.getHorario(),
                        assento,
                        meiaEntrada,
                        meiaEntrada ? documentoMeia : null,
                        false
                );

                ingressoService.adicionar(ingresso);

                alert("Ingresso comprado com sucesso!");
                stage.close();

            } catch (Exception ex) {
                alert("Erro ao comprar ingresso: " + ex.getMessage());
            }
        });

        grid.add(lblSessao, 0, 0);
        grid.add(cbSessoes, 1, 0);
        grid.add(lblAssento, 0, 1);
        grid.add(tfAssento, 1, 1);
        grid.add(cbMeiaEntrada, 1, 2);
        grid.add(tfDocumentoMeia, 1, 3);
        grid.add(btnComprar, 1, 4);

        Scene scene = new Scene(grid, 450, 250);
        stage.setScene(scene);
        stage.show();
    }

    private static void alert(String mensagem) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }
}
