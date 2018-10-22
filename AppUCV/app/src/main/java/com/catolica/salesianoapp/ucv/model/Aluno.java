package com.catolica.salesianoapp.ucv.model;

/**
 * Created by Meycon Augusto on 01/02/2018.
 */

public class Aluno {

    private int idaluno;
    private String matricula;
    private String nome;
    private String senha;
    private String semestre;
    private String email;
    private String genero;
    private String imagem;
    private String cpf;
    private String curso;
    private String validade;

    public Aluno() {

    }

    public Aluno(int idaluno, String matricula, String nome, String email, String genero, String imagem, String cpf, String semestre) {
        this.idaluno = idaluno;
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.genero = genero;
        this.imagem = imagem;
        this.cpf = cpf;
        this.semestre = semestre;
    }


    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public int getIdaluno() {
        return idaluno;
    }

    public void setIdaluno(int idaluno) {
        this.idaluno = idaluno;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Aluno{" +
                "matricula=" + matricula +
                ", nome='" + nome + '\'' +
                '}';
    }
}
