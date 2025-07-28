package service;

import model.Ingresso;
import repository.IngressoRepository;

import java.util.List;

public class IngressoService {

    private final IngressoRepository repository = new IngressoRepository();

    public void adicionar(Ingresso ingresso) {
        List<Ingresso> ingressos = repository.listar();
        long novoId = ingressos.stream()
                .mapToLong(i -> i.getId() == null ? 0 : i.getId())
                .max()
                .orElse(0) + 1;
        ingresso.setId(novoId);
        ingressos.add(ingresso);
        repository.salvar(ingressos);
    }

    public List<Ingresso> listar() {
        return repository.listar();
    }

    public boolean cancelar(Long id) {
        List<Ingresso> ingressos = repository.listar();
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getId().equals(id) && !ingresso.isCancelado()) {
                ingresso.setCancelado(true);
                repository.salvar(ingressos);
                return true;
            }
        }
        return false;
    }
}
