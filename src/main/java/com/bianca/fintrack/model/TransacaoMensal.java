/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.model;

/**
 *
 * @author bianca
 */
public class TransacaoMensal extends Transacao{
    private int diaRecorrencia; 

    public TransacaoMensal(int diaRecorrencia, int id, boolean ehReceita, double valor, String descricao) {
        super(id, ehReceita, valor, descricao);
        this.diaRecorrencia = diaRecorrencia;
    }

    public int getDiaRecorrencia() {
        return diaRecorrencia;
    }

    public void setDiaRecorrencia(int diaRecorrencia) {
        this.diaRecorrencia = diaRecorrencia;
    }

    @Override
    public String toString() {
        return super.toString() + "diaRecorrência=" + diaRecorrencia;
    }
    
}
