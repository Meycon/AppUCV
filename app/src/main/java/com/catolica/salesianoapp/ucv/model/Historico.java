package com.catolica.salesianoapp.ucv.model;

/**
 * Created by Meycon Augusto on 13/02/2018.
 */

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Historico implements Serializable {

    private final static long serialVersionUID = -8804112436098776331L;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("semestre")
    @Expose
    private String semestre;
    @SerializedName("mat1")
    @Expose
    private String mat1;
    @SerializedName("mat2")
    @Expose
    private String mat2;
    @SerializedName("mat3")
    @Expose
    private String mat3;
    @SerializedName("mat4")
    @Expose
    private String mat4;
    @SerializedName("mat5")
    @Expose
    private String mat5;
    @SerializedName("mat6")
    @Expose
    private String mat6;
    @SerializedName("mat7")
    @Expose
    private String mat7;
    @SerializedName("nota1")
    @Expose
    private String nota1;
    @SerializedName("nota2")
    @Expose
    private String nota2;
    @SerializedName("nota3")
    @Expose
    private String nota3;
    @SerializedName("nota4")
    @Expose
    private String nota4;
    @SerializedName("nota5")
    @Expose
    private String nota5;
    @SerializedName("nota6")
    @Expose
    private String nota6;
    @SerializedName("nota7")
    @Expose
    private String nota7;
    @SerializedName("mediasemestre")
    @Expose
    private String mediasemestre;

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

    public String getMat1() {
        return mat1;
    }

    public void setMat1(String mat1) {
        this.mat1 = mat1;
    }

    public String getMat2() {
        return mat2;
    }

    public void setMat2(String mat2) {
        this.mat2 = mat2;
    }

    public String getMat3() {
        return mat3;
    }

    public void setMat3(String mat3) {
        this.mat3 = mat3;
    }

    public String getMat4() {
        return mat4;
    }

    public void setMat4(String mat4) {
        this.mat4 = mat4;
    }

    public String getMat5() {
        return mat5;
    }

    public void setMat5(String mat5) {
        this.mat5 = mat5;
    }

    public String getMat6() {
        return mat6;
    }

    public void setMat6(String mat6) {
        this.mat6 = mat6;
    }

    public String getMat7() {
        return mat7;
    }

    public void setMat7(String mat7) {
        this.mat7 = mat7;
    }

    public String getNota1() {
        return nota1;
    }

    public void setNota1(String nota1) {
        this.nota1 = nota1;
    }

    public String getNota2() {
        return nota2;
    }

    public void setNota2(String nota2) {
        this.nota2 = nota2;
    }

    public String getNota3() {
        return nota3;
    }

    public void setNota3(String nota3) {
        this.nota3 = nota3;
    }

    public String getNota4() {
        return nota4;
    }

    public void setNota4(String nota4) {
        this.nota4 = nota4;
    }

    public String getNota5() {
        return nota5;
    }

    public void setNota5(String nota5) {
        this.nota5 = nota5;
    }

    public String getNota6() {
        return nota6;
    }

    public void setNota6(String nota6) {
        this.nota6 = nota6;
    }

    public String getNota7() {
        return nota7;
    }

    public void setNota7(String nota7) {
        this.nota7 = nota7;
    }

    public String getMediasemestre() {
        return mediasemestre;
    }

    public void setMediasemestre(String mediasemestre) {
        this.mediasemestre = mediasemestre;
    }
}
