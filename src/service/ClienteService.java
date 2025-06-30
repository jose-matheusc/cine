package service;

import exception.ClienteException;
import model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private final List<Cliente> clientes = new ArrayList<>();
    private Long proximoId = 1L;

    public Cliente adicionar(String nome, String cpf, String email, String telefone) {
        try {
            Cliente cliente = new Cliente(proximoId++, nome, cpf, email, telefone);
            clientes.add(cliente);
            return cliente;
        }catch (Exception e) {
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

    public Cliente atualizar(Long id, String nome, String cpf, String email, String telefone) {
        Cliente cliente = buscarPorId(id);
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        return cliente;
    }

    public void excluir(Long id) {
        Cliente cliente = buscarPorId(id);
        clientes.remove(cliente);
    }




}
