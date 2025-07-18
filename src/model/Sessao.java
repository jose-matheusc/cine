package model;

import java.time.LocalDateTime;

public class Sessao {
    private Long id;
    private Filme filme;
    private Sala sala;
    private LocalDateTime horario;

    public Sessao(Long id, Filme filme, Sala sala, LocalDateTime horario) {
        this.id = id;
        this.filme = filme;
        this.sala = sala;
        this.horario = horario;
    }

    public Long getId() { return id; }
    public Filme getFilme() { return filme; }
    public Sala getSala() { return sala; }
    public LocalDateTime getHorario() { return horario; }

    @Override
    public String toString() {
        return "Sessao [ID=" + id + ", Filme=" + filme.getTitulo() + ", Sala=" + sala.getNome() + ", Horário=" + horario + ']';
    }
}