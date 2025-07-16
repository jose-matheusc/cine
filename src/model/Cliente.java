package model;

public class Cliente extends Usuario {
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public Cliente(String login, String senha, String nome, String cpf, String email, String telefone) {
        super(login, senha);
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    // Getters e Setters
    public String getNome() { 
        return nome; 
    }
    
    public void setNome(String nome) {
        this.nome = nome; 
    }
    
    public String getCpf() { 
        return cpf; 
    }
    
    public void setCpf(String cpf) { 
        this.cpf = cpf; 
    }
    
    public String getEmail() { 
        return email; 
    }
    
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    public String getTelefone() { 
        return telefone; 
    }
    
    public void setTelefone(String telefone) { 
        this.telefone = telefone; 
    }

    @Override
    public String toString() {
        return "Cliente{" +
               "nome='" + nome + '\'' +
               ", cpf='" + cpf + '\'' +
               ", email='" + email + '\'' +
               ", telefone='" + telefone + '\'' +
               ", login='" + login + '\'' +
               '}';
    }
}
