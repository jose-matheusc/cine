package service;

import exception.ClienteException;
import model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private final List<Cliente> clientes = new ArrayList<>();
    private Long proximoId = 1L;

    public void adicionar(String nome, String cpf, String email, String telefone) {
        validarCamposObrigatorios(nome, cpf, email, telefone);
        verificarCpfDuplicado(cpf);
        try {
            // Linha 18 corrigida: agora cria o Cliente como esperado
            Cliente cliente = new Cliente(proximoId++, nome, cpf, email, telefone);
            clientes.add(cliente);
            System.out.println("✅ Cliente adicionado com sucesso.");
        } catch (Exception e) {
            throw new ClienteException("Erro ao adicionar cliente: " + e.getMessage());
        }
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
        System.out.println("✅ Cliente atualizado com sucesso.");
    }

    public void excluir(Long id) {
        Cliente cliente = buscarPorId(id);
        clientes.remove(cliente);
        System.out.println("✅ Cliente excluído com sucesso.");
    }

    private void validarCamposObrigatorios(String nome, String cpf, String email, String telefone) {
        if (nome == null || nome.isBlank()) {
            throw new ClienteException("Nome é obrigatório.");
        }
        if (cpf == null || cpf.isBlank()) {
            throw new ClienteException("CPF é obrigatório.");
        }
        if (email == null || email.isBlank()) {
            throw new ClienteException("Email é obrigatório.");
        }
        if (telefone == null || telefone.isBlank()) {
            throw new ClienteException("Telefone é obrigatório.");
        }
    }

    private void verificarCpfDuplicado(String cpf) {
        boolean existe = clientes.stream().anyMatch(c -> c.getCpf().equals(cpf));
        if (existe) {
            throw new ClienteException("Já existe um cliente com este CPF.");
        }
    }
}