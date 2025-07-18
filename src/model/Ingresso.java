package model;

public class Ingresso {
    private Long id;
    private Sessao sessao;
    private String assento;
    private boolean meiaEntrada;
    private String documentoMeiaEntrada;
    private double valor;
    private String qrCode;
    private boolean cancelado = false;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Sessao getSessao() {
        return sessao;
    }
    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }
    public String getAssento() {
        return assento;
    }
    public void setAssento(String assento) {
        this.assento = assento;
    }
    public boolean isMeiaEntrada() {
        return meiaEntrada;
    }
    public void setMeiaEntrada(boolean meiaEntrada) {
        this.meiaEntrada = meiaEntrada;
    }
    public String getDocumentoMeiaEntrada() {
        return documentoMeiaEntrada;
    }
    public void setDocumentoMeiaEntrada(String documentoMeiaEntrada) {
        this.documentoMeiaEntrada = documentoMeiaEntrada;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public boolean isCancelado() {
        return cancelado;
    }
    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }
    public String getQrCode() {
        return qrCode;
    }
    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @Override
    public String toString() {
        return "Ingresso [" +
               "ID=" + id +
               ", Filme=" + sessao.getFilme().getTitulo() +
               ", Assento='" + assento + '\'' +
               ", Valor=R$ " + String.format("%.2f", valor) +
               ", QRCode='" + qrCode + '\'' +
               ']';
    }
}