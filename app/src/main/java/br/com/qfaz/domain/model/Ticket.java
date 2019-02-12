package br.com.qfaz.domain.model;

public class Ticket {


    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }


    public Ticket(String segmento, String descricao, double valor, int ticket) {
        this.segmento = segmento;
        this.descricao = descricao;
        this.valor = valor;
        this.ticket = ticket;
    }

    private String segmento;
    private String descricao;
    private double valor;
    private int ticket;
}
