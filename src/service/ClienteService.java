package service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import exception.ClienteException;
import model.Cliente;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {

    private Integer proximoId = 1;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File arquivo = new File("src/repository/Cliente.json");

    private List<Cliente> pegarClientes() {
        try {
            return objectMapper.readValue(arquivo, new TypeReference<List<Cliente>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente buscarPorId(Long id) {
        return pegarClientes().stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    public void atualizarCliente(Long id, String nome, String cpf, String email, String telefone) {
        List<Cliente> clientes = pegarClientes();
        Cliente cliente = clientes.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ClienteException("Cliente não encontrado"));

        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);

        salvarClientesEmArquivo(clientes);
    }


    public boolean excluirPorId(Long id) {
        List<Cliente> clientes = pegarClientes();
        boolean removido = clientes.removeIf(c -> c.getId().equals(id));
        if (removido) {
            salvarClientesEmArquivo(clientes);
        }
        return removido;
    }


    public void adicionar(String nome, String cpf, String email, String telefone, String login, String senha) {
        validarCamposObrigatorios(nome, cpf, email, telefone);
        verificarCpfDuplicado(cpf);
        try {
            List<Cliente> clientes = pegarClientes();
            Long idUltimoCliente = clientes.getLast().getId();
            Cliente cliente = new Cliente(login, senha, ++idUltimoCliente, nome, cpf, email, telefone);
            clientes.add(cliente);
            salvarClientesEmArquivo(clientes);
        } catch (Exception e) {
            throw new ClienteException("Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    private void salvarClientesEmArquivo(List<Cliente> clientes) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, clientes);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar clientes no arquivo: " + e.getMessage());
        }
    }

    public List<Cliente> carregarClientesDoArquivo() {
        if (!arquivo.exists()) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(arquivo, new TypeReference<List<Cliente>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar clientes do arquivo: " + e.getMessage());
        }
    }

    public void atualizar(Long id, String nome, String cpf, String email, String telefone) {
        validarCamposObrigatorios(nome, cpf, email, telefone);
        Cliente cliente = buscarPorId(id);
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
    }


    public void excluir(Long id) {
        Cliente cliente = buscarPorId(id);
        pegarClientes().remove(cliente);
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
        boolean existe = pegarClientes().stream().anyMatch(c -> c.getCpf().equals(cpf));
        if (existe) {
            throw new ClienteException("Já existe um cliente com este CPF.");
        }
    }



}
