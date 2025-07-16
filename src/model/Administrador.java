package model;

public class Administrador extends Usuario {
    public Administrador(String login, String senha) {
        super(login, senha);
    }

    // Métodos específicos do administrador (a lógica será nos controllers)
    // gerenciarFilmes(), gerenciarSalas(), configurarSessao(), etc.
}
