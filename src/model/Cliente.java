    package model;

    public class Cliente extends Usuario {
        private Long id;
        private String nome;
        private String cpf;
        private String email;
        private String telefone;

        public Cliente(String login, String senha, Long id, String nome, String cpf, String email, String telefone) {
            super(login, senha);
            this.id = id;
            this.nome = nome;
            this.cpf = cpf;
            this.email = email;
            this.telefone = telefone;
        }

        public Cliente() {
            super();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

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
