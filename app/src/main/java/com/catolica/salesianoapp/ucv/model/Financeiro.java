package com.catolica.salesianoapp.ucv.model;

/**
 * Created by Meycon Augusto on 09/02/2018.
 */

public class Financeiro {

    private String idboleto;
    private String vencimento;
    private String valor_bruto;
    private String semestre;
    private String codigo_barra;
    private String situacao;

    public Financeiro(){

    }

    public String getIdboleto() {
        return idboleto;
    }

    public void setIdboleto(String idboleto) {
        this.idboleto = idboleto;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public String getValor_bruto() {
        return valor_bruto;
    }

    public void setValor_bruto(String valor_bruto) {
        this.valor_bruto = valor_bruto;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getCodigo_barra() {
        return codigo_barra;
    }

    public void setCodigo_barra(String codigo_barra) {
        this.codigo_barra = codigo_barra;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
