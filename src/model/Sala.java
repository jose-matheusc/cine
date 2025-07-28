package model;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Sala {
    private Long id;
    private String nome;
    private String tipo;
    private int capacidade;
    private double fatorPreco;
    private Map<String, Boolean> assentos;

    public Sala(Long id, String nome, String tipo, int capacidade, double fatorPreco) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.fatorPreco = fatorPreco;
        this.assentos = new HashMap<>();
        for (int i = 1; i <= capacidade; i++) {
            this.assentos.put("A" + i, false);
        }
    }
    
    public String getMapaDeAssentos() {
        StringBuilder mapa = new StringBuilder();
        AtomicInteger contador = new AtomicInteger(0);
        
        assentos.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    mapa.append(entry.getKey()).append(":").append(entry.getValue() ? "[X] " : "[ ] ");
                    if (contador.incrementAndGet() % 10 == 0) {
                        mapa.append("\n");
                    }
                });
        return mapa.toString();
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public int getCapacidade() { return capacidade; }
    public double getFatorPreco() { return fatorPreco; }
    public boolean verificarDisponibilidade(String assento) { return !this.assentos.getOrDefault(assento, true); }
    public void ocuparAssento(String assento) { if (assentos.containsKey(assento)) { this.assentos.put(assento, true); } }
    public void liberarAssento(String assento) { if (assentos.containsKey(assento)) { this.assentos.put(assento, false); } }
    public long getAssentosOcupados() { return assentos.values().stream().filter(ocupado -> ocupado).count(); }

    @Override
    public String toString() {
        return "Sala [ID=" + id + ", Nome='" + nome + "']";
    }
}