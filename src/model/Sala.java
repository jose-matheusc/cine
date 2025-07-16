package model;

import java.util.HashMap;
import java.util.Map;

public class Sala {
    private Long id;
    private String nome;
    private String tipo;
    private int capacidade;
    private Map<String, Boolean> assentos;

    public Sala(Long id, String nome, String tipo, int capacidade) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.assentos = new HashMap<>();
        for (int i = 1; i <= capacidade; i++) {
            this.assentos.put("A" + i, false);
        }
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public Map<String, Boolean> getAssentos() {
        return assentos;
    }

    public boolean verificarDisponibilidade(String assento) {
        return !this.assentos.getOrDefault(assento, true);
    }

    public void ocuparAssento(String assento) {
        if (assentos.containsKey(assento)) {
            this.assentos.put(assento, true);
        }
    }

    public void liberarAssento(String assento) {
        if (assentos.containsKey(assento)) {
            this.assentos.put(assento, false);
        }
    }

    public long getAssentosOcupados() {
        return assentos.values().stream().filter(ocupado -> ocupado).count();
    }

    @Override
    public String toString() {
        return "Sala [" +
               "ID=" + id +
               ", Nome='" + nome + '\'' +
               ", Tipo='" + tipo + '\'' +
               ", Capacidade=" + capacidade +
               ", Ocupados=" + getAssentosOcupados() +
               ']';
    }
}