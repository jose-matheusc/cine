package service;

import exception.IngressoException;
import model.Filme;
import model.Ingresso;
import model.Sessao;
import model.Sala;
import java.util.*;
import java.time.LocalDateTime;

public class IngressoService {

    private final List<Ingresso> ingressos = new ArrayList<>();
    private Long proximoIdIngresso = 1L;
    private final List<Sessao> sessoes = new ArrayList<>();
    private Long proximaSessaoId = 1L;

    private final FilmeService filmeService;
    private final SalaService salaService;

    public IngressoService(FilmeService filmeService, SalaService salaService) {
        this.filmeService = filmeService;
        this.salaService = salaService;
    }

    public Sessao cadastrarSessao(Long filmeId, Long salaId, LocalDateTime horario) {
        Filme filme = filmeService.buscarPorId(filmeId);
        Sala sala = salaService.buscarPorId(salaId);

        Sessao sessao = new Sessao(proximaSessaoId++, filme, sala, horario);
        sessoes.add(sessao);
        return sessao;
    }

    public Ingresso comprarIngresso(Long sessaoId, String assento, boolean meiaEntrada, String documento) {
        Sessao sessao = buscarSessaoPorId(sessaoId);
        Sala sala = sessao.getSala();

        if (!sala.verificarDisponibilidade(assento)) {
            throw new IngressoException("Assento " + assento + " já está ocupado para esta sessão.");
        }

        if (meiaEntrada && (documento == null || documento.isBlank())) {
            throw new IngressoException("Documento obrigatório para meia-entrada.");
        }
        
        sala.ocuparAssento(assento);

        Ingresso ingresso = new Ingresso();
        ingresso.setId(proximoIdIngresso++);
        ingresso.setSessao(sessao);
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
        
        LocalDateTime agora = LocalDateTime.now();
        if (agora.isAfter(ingresso.getSessao().getHorario().minusHours(2))) {
            throw new IngressoException("Cancelamento só é permitido até 2 horas antes da sessão.");
        }

        ingresso.getSessao().getSala().liberarAssento(ingresso.getAssento());
        ingresso.setCancelado(true);
        System.out.println("Ingresso cancelado. Reembolso processado.");
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
}