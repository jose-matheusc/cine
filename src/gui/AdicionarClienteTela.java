package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import service.ClienteService;

public class AdicionarClienteTela {

    public static void exibir(ClienteService clienteService) {
        Stage stage = new Stage();
        stage.setTitle("Adicionar Cliente");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        TextField nomeField = new TextField();
        TextField cpfField = new TextField();
        TextField emailField = new TextField();
        TextField telefoneField = new TextField();
        TextField idadeField = new TextField();

        Button btnSalvar = new Button("Salvar");

        btnSalvar.setOnAction(_ -> {
            try {
                clienteService.adicionar(
                        nomeField.getText(),
                        cpfField.getText(),
                        emailField.getText(),
                        telefoneField.getText(),
                        Integer.parseInt(idadeField.getText())
                );
                stage.close();
            } catch (Exception e) {
                grid.add(new Label(e.getMessage()), 0, grid.getRowCount());
            }
        });

        grid.add(new Label("Nome:"), 0, 0);
        grid.add(nomeField, 1, 0);
        grid.add(new Label("CPF (será a senha):"), 0, 1);
        grid.add(cpfField, 1, 1);
        grid.add(new Label("Email (será o login):"), 0, 2);
        grid.add(emailField, 1, 2);
        grid.add(new Label("Telefone:"), 0, 3);
        grid.add(telefoneField, 1, 3);
        grid.add(new Label("Idade:"), 0, 4);
        grid.add(idadeField, 1, 4);
        grid.add(btnSalvar, 1, 5);

        Scene cena = new Scene(grid, 350, 300);
        stage.setScene(cena);
        stage.show();
    }
}