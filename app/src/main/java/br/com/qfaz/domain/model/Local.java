package br.com.qfaz.domain.model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Local {


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

    public Local(String cnpj, String razao, String nome, String cep, String endereco, String numero, String latitude, String longitude, String cidade, String estado, String bairro) {
        this.cnpj = cnpj;
        this.razao = razao;
        this.nome = nome;
        this.cep = cep;
        this.endereco = endereco;
        this.numero = numero;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cidade = cidade;
        this.estado = estado;
        this.bairro = bairro;
    }

    public Local(){

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
        result.put("latitude", longitude);
        result.put("longitude", longitude);
        result.put("cidade", cidade);
        result.put("estado", estado);
        result.put("bairro", bairro);

        return result;
    }

    private String razao;
    private String nome;
    private String cep;
    private String endereco;
    private String numero;
    private String cnpj;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    private String cidade;
    private String estado;
    private String bairro;
    private String latitude;
    private String longitude;




}
