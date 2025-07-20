package service;

import model.Sala;
import java.util.ArrayList;
import java.util.List;

public class SalaService {
    private final List<Sala> salas = new ArrayList<>();
    private Long proximoId = 1L;

    public SalaService() {
        adicionarSala("Sala 1 - 2D", "2D", 50, 1.0);
        adicionarSala("Sala 2 - 2D", "2D", 50, 1.0);
        adicionarSala("Sala 3 - 3D", "3D", 40, 1.2);
        adicionarSala("Sala 4 - IMAX", "IMAX", 60, 1.5);
    }

    private void adicionarSala(String nome, String tipo, int capacidade, double fatorPreco) {
        Sala sala = new Sala(proximoId++, nome, tipo, capacidade, fatorPreco);
        this.salas.add(sala);
    }

    public List<Sala> listarSalas() {
        return salas;
    }

    public Sala buscarPorId(Long id) {
        return salas.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Sala não encontrada."));
    }
}