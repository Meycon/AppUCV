package com.catolica.salesianoapp.ucv.model;

/**
 * Created by Meycon Augusto on 15/02/2018.
 */

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Estagio implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("cod_estagio")
    @Expose
    private Integer codEstagio;
    @SerializedName("vaga")
    @Expose
    private String vaga;
    @SerializedName("curso")
    @Expose
    private String curso;
    @SerializedName("imagem")
    @Expose
    private String imagem;
    private final static long serialVersionUID = 8997579885201014797L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodEstagio() {
        return codEstagio;
    }

    public void setCodEstagio(Integer codEstagio) {
        this.codEstagio = codEstagio;
    }

    public String getVaga() {
        return vaga;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}