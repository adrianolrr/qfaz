package br.com.qfaz.domain.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Visita {

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
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

    public Visita(String uid, Local local, String data, String horario) {
        this.uid = uid;
        this.local = local;
        this.data = data;
        this.horario = horario;
    }

    public Visita(){

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("local", local);
        result.put("horario", horario);
        result.put("data", data);

        return result;
    }

    private String uid;
    private Local local;
    private String data;
    private String horario;
}
