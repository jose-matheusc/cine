package service;

import model.Sala;
import java.util.ArrayList;
import java.util.List;

public class SalaService {
    private final List<Sala> salas = new ArrayList<>();
    private Long proximoId = 1L;

    public SalaService() {
        adicionarSala("Sala 1 - 2D", "2D", 50);
        adicionarSala("Sala 2 - 2D", "2D", 50);
        adicionarSala("Sala 3 - 3D", "3D", 40);
        adicionarSala("Sala 4 - IMAX", "IMAX", 60);
    }

    private void adicionarSala(String nome, String tipo, int capacidade) {
        Sala sala = new Sala(proximoId++, nome, tipo, capacidade);
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