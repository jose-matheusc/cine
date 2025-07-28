package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import model.Filme;

import java.util.List;

public class FilmeRepository extends JsonRepository<Filme> {
    public FilmeRepository() {
        super("src/bd/Filmes.json", new TypeReference<List<Filme>>() {});
    }
}
