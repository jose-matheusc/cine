package model;

public class Produto {
    private Long id;
    private String nome;
    private double preco;
    private int estoque;

    public Produto(Long id, String nome, double preco, int estoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }
    public int getEstoque() { return estoque; }
    public void reduzirEstoque(int quantidade) { if (this.estoque >= quantidade) { this.estoque -= quantidade; } }

    @Override
    public String toString() {
        return "Produto [ID=" + id + ", Nome='" + nome + "']";
    }
}