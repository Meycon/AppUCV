package com.catolica.salesianoapp.ucv.model;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Documentos implements Serializable {

    private final static long serialVersionUID = -8283979883378197619L;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nome")
    @Expose
    private String nome;
    @SerializedName("url")
    @Expose
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
