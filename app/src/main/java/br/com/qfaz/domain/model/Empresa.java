package br.com.qfaz.domain.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Empresa {


    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCodigoempresa() {
        return codigoempresa;
    }

    public void setCodigoempresa(String codigoempresa) {
        this.codigoempresa = codigoempresa;
    }

    public Empresa(String cnpj, String razao, String nome, String cep, String endereco, String numero, String codigoempresa) {
        this.cnpj = cnpj;
        this.razao = razao;
        this.nome = nome;
        this.cep = cep;
        this.endereco = endereco;
        this.numero = numero;
        this.codigoempresa = codigoempresa;
    }

    public Empresa(){

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("cnpj", cnpj);
        result.put("razao", razao);
        result.put("nome", nome);
        result.put("cep", cep);
        result.put("endereco", endereco);
        result.put("numero", numero);
        result.put("codigoempresa", codigoempresa);

        return result;
    }

    private String razao;
    private String nome;
    private String cep;
    private String endereco;
    private String numero;
    private String cnpj;
    private String codigoempresa;




}