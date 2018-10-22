package com.catolica.salesianoapp.ucv.model;

/**
 * Created by Meycon Augusto - // Informações que viram do BD.
 */

public class Noticia {

    private int id;
    private String idCategoria;
    private String categoria;
    private String idNoticia;
    private String tituloNoticia;
    private String descricaoNoticia;
    private String imgNoticia;
    private String imgCategoria;
    private String dataNoticia;

    public Noticia() {

    }

    public Noticia(String tituloNoticia, String imgNoticia, String dataNoticia) {
        this.tituloNoticia = tituloNoticia;
        this.imgNoticia = imgNoticia;
        this.dataNoticia = dataNoticia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImgCategoria() {
        return imgCategoria;
    }

    public void setImgCategoria(String imgCategoria) {
        this.imgCategoria = imgCategoria;
    }

    public String getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(String idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getTituloNoticia() {
        return tituloNoticia;
    }

    public void setTituloNoticia(String tituloNoticia) {
        this.tituloNoticia = tituloNoticia;
    }

    public String getDescricaoNoticia() {
        return descricaoNoticia;
    }

    public void setDescricaoNoticia(String descricaoNoticia) {
        this.descricaoNoticia = descricaoNoticia;
    }

    public String getImgNoticia() {
        return imgNoticia;
    }

    public void setImgNoticia(String imgNoticia) {
        this.imgNoticia = imgNoticia;
    }

    public String getDataNoticia() {
        return dataNoticia;
    }

    public void setDataNoticia(String dataNoticia) {
        this.dataNoticia = dataNoticia;
    }
}
