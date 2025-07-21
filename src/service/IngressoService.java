package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Ingresso;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class IngressoService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File arquivo = new File("src/repository/Ingressos.json");

    public void adicionar(Ingresso ingresso) {
        List<Ingresso> ingressos = listar();
        long novoId = ingressos.stream().mapToLong(Ingresso::getId).max().orElse(0) + 1;
        ingresso.setId(novoId);
        ingressos.add(ingresso);
        salvar(ingressos);
    }

    public boolean excluir(Long id) {
        List<Ingresso> ingressos = listar();
        boolean removido = ingressos.removeIf(i -> i.getId().equals(id));
        if (removido) {
            salvar(ingressos);
        }
        return removido;
    }


    public List<Ingresso> listar() {
        try {
            if (!arquivo.exists()) return new ArrayList<>();
            return Arrays.asList(objectMapper.readValue(arquivo, Ingresso[].class));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler ingressos: " + e.getMessage());
        }
    }

    public boolean cancelar(Long id) {
        List<Ingresso> ingressos = listar();
        boolean removido = ingressos.removeIf(i -> i.getId().equals(id));
        if (removido) salvar(ingressos);
        return removido;
    }

    private void salvar(List<Ingresso> ingressos) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, ingressos);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar ingressos: " + e.getMessage());
        }
    }
}
