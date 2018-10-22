package com.catolica.salesianoapp.ucv.model;

/**
 * Created by Meycon Augusto on 03/02/2018.
 */

public class Nota {

    private String idnota;
    private String disciplina;
    private String teste;
    private String p1;
    private String td1;
    private String td2;
    private String p2;
    private String td3;
    private String td4;
    private String aco;

    public Nota(String disciplina, String teste, String p1, String td1, String td2, String p2, String td3, String td4, String aco) {
        this.disciplina = disciplina;
        this.teste = teste;
        this.p1 = p1;
        this.td1 = td1;
        this.td2 = td2;
        this.p2 = p2;
        this.td3 = td3;
        this.td4 = td4;
        this.aco = aco;
    }

    public Nota(){

    }


    public String getIdnota() {
        return idnota;
    }

    public void setIdnota(String idnota) {
        this.idnota = idnota;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getTeste() {
        return teste;
    }

    public void setTeste(String teste) {
        this.teste = teste;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getTd1() {
        return td1;
    }

    public void setTd1(String td1) {
        this.td1 = td1;
    }

    public String getTd2() {
        return td2;
    }

    public void setTd2(String td2) {
        this.td2 = td2;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getTd3() {
        return td3;
    }

    public void setTd3(String td3) {
        this.td3 = td3;
    }

    public String getTd4() {
        return td4;
    }

    public void setTd4(String td4) {
        this.td4 = td4;
    }

    public String getAco() {
        return aco;
    }

    public void setAco(String aco) {
        this.aco = aco;
    }


}
