package service;

import exception.IngressoException;
import model.Filme;
import model.Ingresso;
import model.Sessao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class IngressoService {

    private final List<Ingresso> ingressos = new ArrayList<>();
    private final List<Sessao> sessoes = new ArrayList<>();
    private Long proximoIdIngresso = 1L;
    private Long proximaSessaoId = 1L;

    // Dependência injetada: o serviço agora USA o FilmeService, mas não gerencia filmes.
    private final FilmeService filmeService;

    public IngressoService(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    // --- MÉTODOS DE SESSÃO ---

    public Sessao cadastrarSessao(Long filmeId, LocalDateTime horario) {
        // Busca o filme usando o serviço correto.
        Filme filme = filmeService.buscarPorId(filmeId);

        // A linha 73 original foi corrigida aqui.
        Sessao sessao = new Sessao(proximaSessaoId++, filme, horario);
        sessoes.add(sessao);
        return sessao;
    }

    public List<Sessao> listarSessoes() {
        return sessoes;
    }

    public Sessao buscarSessaoPorId(Long id) {
        return sessoes.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Sessão não encontrada."));
    }


    // --- MÉTODOS DE INGRESSO ---

    public Ingresso comprarIngresso(String filmeTitulo, LocalDateTime horarioSessao, String assento, boolean meiaEntrada, String documento) {
        if (filmeTitulo == null || filmeTitulo.isBlank()) {
            throw new IngressoException("Filme não pode ser vazio.");
        }
        if (assento == null || assento.isBlank()) {
            throw new IngressoException("Assento deve ser selecionado.");
        }
        if (meiaEntrada && (documento == null || documento.isBlank())) {
            throw new IngressoException("Documento obrigatório para meia-entrada.");
        }

        boolean assentoOcupado = ingressos.stream()
                .anyMatch(i -> i.getAssento().equals(assento) && i.getHorarioSessao().equals(horarioSessao) && !i.isCancelado());

        if (assentoOcupado) {
            throw new IngressoException("Assento já está ocupado.");
        }

        Ingresso ingresso = new Ingresso();
        ingresso.setId(proximoIdIngresso++);
        ingresso.setFilme(filmeTitulo);
        ingresso.setHorarioSessao(horarioSessao);
        ingresso.setAssento(assento);
        ingresso.setMeiaEntrada(meiaEntrada);
        ingresso.setDocumentoMeiaEntrada(documento);

        ingressos.add(ingresso);
        return ingresso;
    }

    public void cancelarIngresso(Long ingressoId) {
        Ingresso ingresso = ingressos.stream()
                .filter(i -> i.getId().equals(ingressoId))
                .findFirst()
                .orElseThrow(() -> new IngressoException("Ingresso não encontrado."));

        if (ingresso.isCancelado()) {
            throw new IngressoException("Ingresso já está cancelado.");
        }

        LocalDateTime agora = LocalDateTime.now();
        if (agora.isAfter(ingresso.getHorarioSessao().minusHours(2))) {
            throw new IngressoException("Cancelamento só é permitido até 2 horas antes da sessão.");
        }

        ingresso.setCancelado(true);
        System.out.println("Ingresso cancelado. Reembolso processado.");
    }
}