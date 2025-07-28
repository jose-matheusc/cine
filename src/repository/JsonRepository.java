package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class JsonRepository<T> {
    protected final ObjectMapper objectMapper = new ObjectMapper();
    private final File arquivo;
    private final TypeReference<List<T>> typeReference;

    protected JsonRepository(String caminhoArquivo, TypeReference<List<T>> typeReference) {
        this.arquivo = new File(caminhoArquivo);
        this.typeReference = typeReference;
    }

    public List<T> listar() {
        if (!arquivo.exists()) return new ArrayList<>();
        try {
            return objectMapper.readValue(arquivo, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o arquivo: " + e.getMessage(), e);
        }
    }

    public void salvar(List<T> dados) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, dados);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar no arquivo: " + e.getMessage(), e);
        }
    }

    protected File getArquivo() {
        return arquivo;
    }
}
