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

        TextField loginField = new TextField();
        TextField senhaField = new TextField();
        TextField nomeField = new TextField();
        TextField cpfField = new TextField();
        TextField emailField = new TextField();
        TextField telefoneField = new TextField();

        Button btnSalvar = new Button("Salvar");

        btnSalvar.setOnAction(_ -> {
            clienteService.adicionar(
                    loginField.getText(),
                    senhaField.getText(),
                    nomeField.getText(),
                    cpfField.getText(),
                    emailField.getText(),
                    telefoneField.getText()
            );
            stage.close();
        });

        grid.add(new Label("Login:"), 0, 0);
        grid.add(loginField, 1, 0);
        grid.add(new Label("Senha:"), 0, 1);
        grid.add(senhaField, 1, 1);
        grid.add(new Label("Nome:"), 0, 2);
        grid.add(nomeField, 1, 2);
        grid.add(new Label("CPF:"), 0, 3);
        grid.add(cpfField, 1, 3);
        grid.add(new Label("Email:"), 0, 4);
        grid.add(emailField, 1, 4);
        grid.add(new Label("Telefone:"), 0, 5);
        grid.add(telefoneField, 1, 5);
        grid.add(btnSalvar, 1, 6);

        Scene cena = new Scene(grid, 350, 300);
        stage.setScene(cena);
        stage.show();
    }
}
