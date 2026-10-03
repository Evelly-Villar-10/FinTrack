/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.model;

/**
 *
 * @author bianca
 */
public class Usuario {

    private String nome, email, cpf, telefone, senha;
    private int id;

    public Usuario(String nome, String email, String cpf, String telefone, String senha, int id) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.senha = senha;
        this.id = id;
    }

    public Usuario(String nome, String email, String cpf, String telefone, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome=" + nome
                + ", email=" + email
                + ", cpf=" + cpf
                + ", telefone=" + telefone
                + ", id=" + id + '}';
    }

}
