package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Filme;

import java.io.*;
import java.util.*;

public class FilmeService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File arquivo = new File("src/repository/Filmes.json");

    public void adicionar(Filme filme) {
        List<Filme> filmes = listar();
        long novoId = filmes.stream().mapToLong(Filme::getId).max().orElse(0) + 1;
        filme.setId(novoId);
        filmes.add(filme);
        salvar(filmes);
    }

    public void atualizar(Filme filmeAtualizado) {
        List<Filme> filmes = listar();
        for (int i = 0; i < filmes.size(); i++) {
            if (filmes.get(i).getId().equals(filmeAtualizado.getId())) {
                filmes.set(i, filmeAtualizado);
                salvar(filmes);
                return;
            }
        }
        throw new RuntimeException("Filme não encontrado");
    }

    public boolean excluir(Long id) {
        List<Filme> filmes = listar();
        boolean removido = filmes.removeIf(f -> f.getId().equals(id));
        if (removido) salvar(filmes);
        return removido;
    }

    private void salvar(List<Filme> filmes) {
        try (Writer writer = new FileWriter(arquivo)) {
            objectMapper.writeValue(writer, filmes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Filme> listar() {
        try (Reader reader = new FileReader(arquivo)) {
            return objectMapper.readValue(reader, new TypeReference<List<Filme>>() {});
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }


}
