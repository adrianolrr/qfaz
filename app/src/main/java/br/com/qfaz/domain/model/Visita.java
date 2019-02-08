package br.com.qfaz.domain.model;

public class Visita {

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    private String uid;

    public Visita(String uid, String local, String data, String horario) {
        this.uid = uid;
        this.local = local;
        this.data = data;
        this.horario = horario;
    }

    private String local;
    private String data;
    private String horario;
}
