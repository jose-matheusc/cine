package service;

import model.Funcionario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FuncionarioService {
    private final List<Funcionario> funcionarios = new ArrayList<>();
    private Long proximoId = 1L;

    public FuncionarioService() {
        adicionar("João Silva", "Gerente");
        adicionar("Maria Oliveira", "Atendente de Bilheteria");
        adicionar("Carlos Pereira", "Projecionista");
        adicionar("Ana Costa", "Atendente da Bomboniere");
        adicionar("Pedro Souza", "Limpeza");
    }

    public void adicionar(String nome, String funcao) {
        if (nome == null || nome.isBlank() || funcao == null || funcao.isBlank()) {
            throw new RuntimeException("Nome e função são obrigatórios.");
        }
        Funcionario funcionario = new Funcionario(proximoId++, nome, funcao);
        funcionarios.add(funcionario);
    }

    public List<Funcionario> listar() {
        return funcionarios;
    }

    public Funcionario buscarPorId(Long id) {
        return funcionarios.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
    }

    public void demitir(Long id) {
        Funcionario funcionario = buscarPorId(id);
        funcionarios.remove(funcionario);
        System.out.println("✅ Funcionário demitido com sucesso!");
    }

    public void elegerFuncionarioDoMes(Long id) {
        Funcionario eleito = buscarPorId(id);
        
        funcionarios.forEach(f -> f.setEhFuncionarioDoMes(false));
        
        eleito.setEhFuncionarioDoMes(true);
        System.out.println("✅ " + eleito.getNome() + " foi eleito(a) o(a) funcionário(a) do mês!");
    }
}