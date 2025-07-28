package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import model.Cliente;

import java.util.List;

public class ClienteRepository extends JsonRepository<Cliente> {
    public ClienteRepository() {
        super("src/bd/Cliente.json", new TypeReference<List<Cliente>>() {});
    }
}
