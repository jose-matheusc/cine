package service;

import model.Filme;
import repository.FilmeRepository;

import java.util.List;

public class FilmeService {

    private final FilmeRepository repository = new FilmeRepository();

    public void adicionar(Filme filme) {
        List<Filme> filmes = repository.listar();
        long novoId = filmes.stream().mapToLong(Filme::getId).max().orElse(0) + 1;
        filme.setId(novoId);
        filmes.add(filme);
        repository.salvar(filmes);
    }

    public void atualizar(Filme filmeAtualizado) {
        List<Filme> filmes = repository.listar();
        for (int i = 0; i < filmes.size(); i++) {
            if (filmes.get(i).getId().equals(filmeAtualizado.getId())) {
                filmes.set(i, filmeAtualizado);
                repository.salvar(filmes);
                return;
            }
        }
        throw new RuntimeException("Filme não encontrado");
    }

    public boolean excluir(Long id) {
        List<Filme> filmes = repository.listar();
        boolean removido = filmes.removeIf(f -> f.getId().equals(id));
        if (removido) {
            repository.salvar(filmes);
        }
        return removido;
    }

    public List<Filme> listar() {
        return repository.listar();
    }

    public Filme buscarPorId(Long id) {
        return repository.listar().stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
