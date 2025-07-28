package service;

import exception.ClienteException;
import model.Cliente;
import repository.ClienteRepository;

import java.util.List;

public class ClienteService {

    private final ClienteRepository repository = new ClienteRepository();

    public Cliente buscarPorId(Long id) {
        return repository.listar().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void atualizarCliente(Long id, String nome, String cpf, String email, String telefone) {
        List<Cliente> clientes = repository.listar();
        Cliente cliente = clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ClienteException("Cliente não encontrado"));

        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);

        repository.salvar(clientes);
    }

    public boolean excluirPorId(Long id) {
        List<Cliente> clientes = repository.listar();
        boolean removido = clientes.removeIf(c -> c.getId().equals(id));
        if (removido) {
            repository.salvar(clientes);
        }
        return removido;
    }

    public void adicionar(String nome, String cpf, String email, String telefone, String login, String senha) {
        validarCamposObrigatorios(nome, cpf, email, telefone);
        verificarCpfDuplicado(cpf);

        List<Cliente> clientes = repository.listar();
        Long novoId = clientes.isEmpty() ? 1L : clientes.getLast().getId() + 1;
        Cliente cliente = new Cliente(login, senha, novoId, nome, cpf, email, telefone);
        clientes.add(cliente);
        repository.salvar(clientes);
    }

    public List<Cliente> carregarClientesDoArquivo() {
        return repository.listar();
    }

    public void atualizar(Long id, String nome, String cpf, String email, String telefone) {
        validarCamposObrigatorios(nome, cpf, email, telefone);
        Cliente cliente = buscarPorId(id);
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);

        List<Cliente> clientes = repository.listar();
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(id)) {
                clientes.set(i, cliente);
                repository.salvar(clientes);
                return;
            }
        }
    }

    public void excluir(Long id) {
        List<Cliente> clientes = repository.listar();
        clientes.removeIf(c -> c.getId().equals(id));
        repository.salvar(clientes);
    }

    private void validarCamposObrigatorios(String nome, String cpf, String email, String telefone) {
        if (nome.isBlank()) throw new ClienteException("Nome é obrigatório.");
        if (cpf.isBlank()) throw new ClienteException("CPF é obrigatório.");
        if (email.isBlank()) throw new ClienteException("Email é obrigatório.");
        if (telefone.isBlank()) throw new ClienteException("Telefone é obrigatório.");
    }

    private void verificarCpfDuplicado(String cpf) {
        boolean existe = repository.listar().stream().anyMatch(c -> c.getCpf().equals(cpf));
        if (existe) throw new ClienteException("Já existe um cliente com este CPF.");
    }
}
