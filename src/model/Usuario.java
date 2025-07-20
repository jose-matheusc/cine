package model;

public abstract class Usuario {
    protected String login;
    protected String senha;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario() {
    }

    public String getLogin() {
        return login;
    }

    public boolean realizarLogin(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }
}