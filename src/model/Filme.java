package model;

public class Filme {
    private Long id;
    private String titulo;

    public Filme(Long id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    @Override
    public String toString() {
        return "Filme {" + "id=" + id + ", título='" + titulo + "'}";
    }
}
