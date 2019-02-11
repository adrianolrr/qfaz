package br.com.qfaz.domain.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Usuario {


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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<String> getPermissao() {
        return permissao;
    }

    public void setPermissao(List<String> permissao) {
        this.permissao = permissao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Visita> getListaVisita() {
        return listaVisita;
    }

    public void setListaVisita(List<Visita> listaVisita) {
        this.listaVisita = listaVisita;
    }

    public List<HistoricoLocalizacao> getListaHistoricoLocalizacao() {
        return listaHistoricoLocalizacao;
    }

    public void setLitsaHistoricoLocalizacao(List<HistoricoLocalizacao> litsaHistoricoLocalizacao) {
        this.listaHistoricoLocalizacao = litsaHistoricoLocalizacao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setListaHistoricoLocalizacao(List<HistoricoLocalizacao> listaHistoricoLocalizacao) {
        this.listaHistoricoLocalizacao = listaHistoricoLocalizacao;
    }
    public Usuario(String nome, String email,  String cnpj, String cpf, String codigo, List<String> permissao, Boolean status, List<Visita> listaVisita, List<HistoricoLocalizacao> litsaHistoricoLocalizacao) {
        this.nome = nome;
        this.email = email;
        this.cnpj = cnpj;
        this.permissao = permissao;
        this.status = status;
        this.listaVisita = listaVisita;
        this.listaHistoricoLocalizacao = litsaHistoricoLocalizacao;
    }

    private String nome;
    private String email;
    private String codigo;
    private String cnpj;



    private String cpf;
    private List<String> permissao;
    private Boolean status;
    private List<Visita> listaVisita;
    private List<HistoricoLocalizacao> listaHistoricoLocalizacao;

    public Usuario(){

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nome", nome);
        result.put("email", email);
        result.put("codigo", codigo);
        result.put("cpf", cpf);
        result.put("cnpj", cnpj);
        result.put("permissao", permissao);
        result.put("status", status);

        return result;
    }
}
