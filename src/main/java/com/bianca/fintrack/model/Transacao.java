/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.model;

import java.time.LocalDate;

/**
 *
 * @author bianca
 */
public class Transacao {
    private int id;
    private boolean ehReceita;
    private double valor;
    private String descricao; 
    private LocalDate data;

    public Transacao(int id, boolean ehReceita, double valor, String descricao) {
        this.id = id;
        this.ehReceita = ehReceita;
        this.valor = valor;
        this.descricao = descricao;
        this.data = LocalDate.now();
    }

    public Transacao(boolean ehReceita, double valor, String descricao) {
        this.ehReceita = ehReceita;
        this.valor = valor;
        this.descricao = descricao;
        this.data = LocalDate.now();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isEhReceita() {
        return ehReceita;
    }

    public void setEhReceita(boolean ehReceita) {
        this.ehReceita = ehReceita;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Transação{" + "id = " + id + "|" +
                " ehReceita = " + ehReceita + "|" +
                " valor = " + valor + "|" +
                " descricao = " + descricao + "|" +
                " data = " + data + '}';
    }

}
