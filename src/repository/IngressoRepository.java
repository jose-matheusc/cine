package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import model.Ingresso;

import java.util.List;

public class IngressoRepository extends JsonRepository<Ingresso> {
    public IngressoRepository() {
        super("src/bd/Ingressos.json", new TypeReference<List<Ingresso>>() {});
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
