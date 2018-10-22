package com.catolica.salesianoapp.ucv.model;

/**
 * Created by Meycon Augusto on 13/02/2018.
 */

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Materia implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("disciplina")
    @Expose
    private String disciplina;
    @SerializedName("situacao")
    @Expose
    private String situacao;
    @SerializedName("notaFinal")
    @Expose
    private String notaFinal;
    private final static long serialVersionUID = -6883425441883565500L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(String notaFinal) {
        this.notaFinal = notaFinal;
    }

}
