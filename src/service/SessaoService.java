package service;

import model.Sessao;
import repository.SessaoRepository;

import java.util.List;

public class SessaoService {

    private final SessaoRepository repository = new SessaoRepository();

    public void adicionar(Sessao sessao) {
        List<Sessao> sessoes = repository.listar();
        long novoId = sessoes.stream().mapToLong(Sessao::getId).max().orElse(0) + 1;
        sessao.setId(novoId);
        sessoes.add(sessao);
        repository.salvar(sessoes);
    }

    public List<Sessao> listar() {
        return repository.listar();
    }

    public void atualizar(Sessao sessaoAtualizada) {
        List<Sessao> sessoes = repository.listar();
        for (int i = 0; i < sessoes.size(); i++) {
            if (sessoes.get(i).getId().equals(sessaoAtualizada.getId())) {
                sessoes.set(i, sessaoAtualizada);
                repository.salvar(sessoes);
                return;
            }
        }
        throw new RuntimeException("Sessão não encontrada");
    }

    public boolean excluir(Long id) {
        List<Sessao> sessoes = repository.listar();
        boolean removido = sessoes.removeIf(s -> s.getId().equals(id));
        if (removido) repository.salvar(sessoes);
        return removido;
    }
}
