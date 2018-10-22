package com.catolica.salesianoapp.ucv.model;

/**
 * Created by Meycon Augusto on 13/02/2018.
 */

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HistoricoEscolar implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("semestre")
    @Expose
    private String semestre;
    @SerializedName("materias")
    @Expose
    private List<Materia> materias = null;
    private final static long serialVersionUID = -6361503195365612826L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

}