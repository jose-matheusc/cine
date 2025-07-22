package model;

import java.time.LocalDateTime;

public class Sessao {
    private Long id;
    private Filme filme;
    private LocalDateTime horario;


    public Sessao() {
    }

    public Sessao(Long id, Filme filme, LocalDateTime horario) {
        this.id = id;
        this.filme = filme;
        this.horario = horario;
    }

    public Long getId() {
        return id;
    }

    public Filme getFilme() {
        return filme;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    @Override
    public String toString() {
        return "Sessao {" +
                "id=" + id +
                ", filme=" + filme.getTitulo() +
                ", horário=" + horario +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }
}
