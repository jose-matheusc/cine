package service;

import com.google.gson.reflect.TypeToken;
import exception.ClienteException;
import model.Cliente;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private static final String NOME_ARQUIVO = "clientes.json";
    private List<Cliente> clientes;
    private Long proximoId = 1L;
    
    public ClienteService() {
        Type tipoLista = new TypeToken<ArrayList<Cliente>>() {}.getType();
        this.clientes = PersistenceService.carregarDados(NOME_ARQUIVO, tipoLista);
        atualizarProximoId();
    }

    private void salvar() {
        PersistenceService.salvarDados(NOME_ARQUIVO, clientes);
    }

    private void atualizarProximoId() {
        if (!clientes.isEmpty()) {
            proximoId = clientes.stream().mapToLong(Cliente::getId).max().orElse(0L) + 1;
        }
    }

    public void adicionar(String nome, String cpf, String email, String telefone, int idade) {
        validarCamposObrigatorios(nome, cpf, email, telefone);
        verificarCpfDuplicado(cpf);
        Cliente cliente = new Cliente(proximoId++, nome, cpf, email, telefone, idade);
        clientes.add(cliente);
        salvar();
        System.out.println("✅ Cliente adicionado com sucesso.");
    }

    public List<Cliente> listar() {
        return clientes;
    }

    public Cliente buscarPorId(Long id) {
        return clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ClienteException("Cliente não encontrado."));
    }

    public void atualizar(Long id, String nome, String cpf, String email, String telefone) {
        validarCamposObrigatorios(nome, cpf, email, telefone);
        Cliente cliente = buscarPorId(id);
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        salvar();
        System.out.println("✅ Cliente atualizado com sucesso.");
    }

    public void excluir(Long id) {
        clientes.remove(buscarPorId(id));
        salvar();
        System.out.println("✅ Cliente excluído com sucesso.");
    }

    private void validarCamposObrigatorios(String n, String c, String e, String t) {
        if (n == null || n.isBlank() || c == null || c.isBlank() || e == null || e.isBlank() || t == null || t.isBlank()) {
            throw new ClienteException("Todos os campos são obrigatórios.");
        }
    }

    private void verificarCpfDuplicado(String cpf) {
        boolean existe = clientes.stream().anyMatch(c -> c.getCpf().equals(cpf));
        if (existe) {
            throw new ClienteException("Já existe um cliente com este CPF.");
        }
    }
}