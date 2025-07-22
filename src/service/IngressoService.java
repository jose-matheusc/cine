package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.Ingresso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngressoService {

    private final ObjectMapper objectMapper;
    private final File arquivo = new File("src/repository/Ingressos.json");

    public IngressoService() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public void adicionar(Ingresso ingresso) {
        List<Ingresso> ingressos = listar();
        long novoId = ingressos.stream()
                .mapToLong(i -> i.getId() == null ? 0 : i.getId())
                .max()
                .orElse(0) + 1;
        ingresso.setId(novoId);
        ingressos.add(ingresso);
        salvar(ingressos);
    }

    public List<Ingresso> listar() {
        try {
            if (!arquivo.exists()) return new ArrayList<>();
            Ingresso[] ingressosArray = objectMapper.readValue(arquivo, Ingresso[].class);
            return new ArrayList<>(Arrays.asList(ingressosArray));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler ingressos: " + e.getMessage(), e);
        }
    }

    public boolean cancelar(Long id) {
        List<Ingresso> ingressos = listar();
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getId().equals(id) && !ingresso.isCancelado()) {
                ingresso.setCancelado(true);
                salvar(ingressos);
                return true;
            }
        }
        return false;
    }

    private void salvar(List<Ingresso> ingressos) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, ingressos);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar ingressos: " + e.getMessage(), e);
        }
    }
}
