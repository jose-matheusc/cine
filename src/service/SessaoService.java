package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Sessao;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SessaoService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File arquivo = new File("src/repository/Sessoes.json");

    public void adicionar(Sessao sessao) {
        List<Sessao> sessoes = listar();
        long novoId = sessoes.stream().mapToLong(Sessao::getId).max().orElse(0) + 1;
        sessao.setId(novoId);
        sessoes.add(sessao);
        salvar(sessoes);
    }

    public List<Sessao> listar() {
        try {
            if (!arquivo.exists()) return new ArrayList<>();
            return Arrays.asList(objectMapper.readValue(arquivo, Sessao[].class));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler sessões: " + e.getMessage());
        }
    }

    public void atualizar(Sessao sessaoAtualizada) {
        List<Sessao> sessoes = listar();
        for (int i = 0; i < sessoes.size(); i++) {
            if (sessoes.get(i).getId().equals(sessaoAtualizada.getId())) {
                sessoes.set(i, sessaoAtualizada);
                salvar(sessoes);
                return;
            }
        }
        throw new RuntimeException("Sessão não encontrada");
    }

    public boolean excluir(Long id) {
        List<Sessao> sessoes = listar();
        boolean removido = sessoes.removeIf(s -> s.getId().equals(id));
        if (removido) salvar(sessoes);
        return removido;
    }

    private void salvar(List<Sessao> sessoes) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, sessoes);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar sessões: " + e.getMessage());
        }
    }
}
