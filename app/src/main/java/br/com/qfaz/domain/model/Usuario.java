package br.com.qfaz.domain.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Usuario {


    public String getCodigoempresa() {
        return codigoempresa;
    }

    public void setCodigoempresa(String codigoempresa) {
        this.codigoempresa = codigoempresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario(String codigoempresa, String nome, String email, String telefone, String status, Visita visita, HistoricoLocalizacao historicoLocalizacao) {
        this.codigoempresa = codigoempresa;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.status = status;
        this.visita = visita;
        this.historicoLocalizacao = (List<HistoricoLocalizacao>) historicoLocalizacao;
    }
    
    public Usuario(){

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("codigoempresa", codigoempresa);
        result.put("nome", nome);
        result.put("email", email);
        result.put("telefone", telefone);
        result.put("status", status);

        return result;
    }
    

    private String codigoempresa;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String status;
    private Visita visita;

    public List<HistoricoLocalizacao> getHistoricoLocalizacao() {
        return historicoLocalizacao;
    }

    public void setHistoricoLocalizacao(List<HistoricoLocalizacao> historicoLocalizacao) {
        this.historicoLocalizacao = historicoLocalizacao;
    }

    private List<HistoricoLocalizacao> historicoLocalizacao;


    public Visita getVisita() {
        return visita;
    }

    public void setVisita(Visita visita) {
        this.visita = visita;
    }




}
