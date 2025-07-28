package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.Sessao;

import java.util.List;

public class SessaoRepository extends JsonRepository<Sessao> {
    public SessaoRepository() {
        super("src/bd/Sessoes.json", new TypeReference<List<Sessao>>() {});
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
