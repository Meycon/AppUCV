package com.catolica.salesianoapp.ucv.model;

/**
 * Created by Meycon Augusto on 10/02/2018.
 */

public class Comunicado {

    private String idComunicado;
    private String materia;
    private String professor;
    private String data;
    private String titulo_descricao;
    private String descricao;
    private String imagem;

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getIdComunicado() {
        return idComunicado;
    }

    public void setIdComunicado(String idComunicado) {
        this.idComunicado = idComunicado;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitulo_descricao() {
        return titulo_descricao;
    }

    public void setTitulo_descricao(String titulo_descricao) {
        this.titulo_descricao = titulo_descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
