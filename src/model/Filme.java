package model;

public class Filme {
    private Long id;
    private String titulo;
    private String genero;
    private int duracao;

    public Filme(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Filme() {
    }


    public Filme(Long id, String titulo, String genero, int duracao) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.duracao = duracao;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "Filme {" +
                "id=" + id +
                ", título='" + titulo + '\'' +
                ", gênero='" + genero + '\'' +
                ", duração=" + duracao +
                '}';
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}
