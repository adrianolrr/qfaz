package br.com.qfaz.domain.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class HistoricoLocalizacao {


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }



    public HistoricoLocalizacao(String latitude, String longitude, String horario, String data) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.horario = horario;
        this.data = data;
    }

    private String latitude;
    private String longitude;
    private String horario;
    private String data;

    public HistoricoLocalizacao(){

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("latitude", latitude);
        result.put("longitude", longitude);
        result.put("data", data);
        result.put("horario", horario);

        return result;
    }
}
