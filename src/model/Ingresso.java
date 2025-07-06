package model;

import java.time.LocalDateTime;

public class Ingresso {
    private Long id;
    private String filme;
    private LocalDateTime horarioSessao;
    private String assento;
    private boolean meiaEntrada;
    private String documentoMeiaEntrada;
    private boolean cancelado = false;

    public Ingresso(Long id, String filme, LocalDateTime horarioSessao, String assento, boolean meiaEntrada, String documentoMeiaEntrada, boolean cancelado) {
        this.id = id;
        this.filme = filme;
        this.horarioSessao = horarioSessao;
        this.assento = assento;
        this.meiaEntrada = meiaEntrada;
        this.documentoMeiaEntrada = documentoMeiaEntrada;
        this.cancelado = cancelado;
    }

    public Ingresso() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilme() {
        return filme;
    }

    public void setFilme(String filme) {
        this.filme = filme;
    }

    public LocalDateTime getHorarioSessao() {
        return horarioSessao;
    }

    public void setHorarioSessao(LocalDateTime horarioSessao) {
        this.horarioSessao = horarioSessao;
    }

    public String getAssento() {
        return assento;
    }

    public void setAssento(String assento) {
        this.assento = assento;
    }

    public boolean isMeiaEntrada() {
        return meiaEntrada;
    }

    public void setMeiaEntrada(boolean meiaEntrada) {
        this.meiaEntrada = meiaEntrada;
    }

    public String getDocumentoMeiaEntrada() {
        return documentoMeiaEntrada;
    }

    public void setDocumentoMeiaEntrada(String documentoMeiaEntrada) {
        this.documentoMeiaEntrada = documentoMeiaEntrada;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }
}
