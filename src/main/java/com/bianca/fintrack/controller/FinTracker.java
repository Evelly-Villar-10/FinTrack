/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.controller;

import com.bianca.fintrack.exceptions.EntradaInvalidaException;
import com.bianca.fintrack.model.Transacao;
import com.bianca.fintrack.model.TransacaoMensal;
import java.util.ArrayList;

/**
 *
 * @author bianca
 */
public class FinTracker {
    private int contadorId = 1;
    private ArrayList<Transacao> transacoes;

    public FinTracker(ArrayList<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    public int getContadorId() {
        return contadorId;
    }

    public void adicionarTransacao(boolean ehReceita, double valor, String descricao) throws EntradaInvalidaException{
        if(valor <= 0){
        throw new EntradaInvalidaException("\nO valor deve ser maior que zero."); 
        }
        if(descricao == null || descricao.isBlank()){
        throw new EntradaInvalidaException("\nA descrição não pode ser vazia.");   
        }
      
        Transacao novaTransacao = new Transacao(contadorId, ehReceita, valor, descricao);
        transacoes.add(novaTransacao);
        contadorId++;
    } 
    
    public void adicionarTransacaoMensal(boolean ehReceita, double valor, String descricao, int diaRecorrencia) throws EntradaInvalidaException{
        if(valor <= 0){
        throw new EntradaInvalidaException("\nO valor deve ser maior que zero."); 
        }
        if(descricao == null || descricao.isBlank()){
        throw new EntradaInvalidaException("\nA descrição não pode ser vazia.");   
        }
        
        TransacaoMensal mensal = new TransacaoMensal(diaRecorrencia, contadorId, ehReceita, valor, descricao);
        transacoes.add(mensal);
        contadorId++;
    }
    
    public void listarTransacoes() throws EntradaInvalidaException{
       if(transacoes.isEmpty()){
        throw new EntradaInvalidaException("\nA lista não tem transações.");    
       }else{
           for(Transacao t : transacoes){
               System.out.println(t);
           }
       }
    }
    
    public void removerTransacao(int id) throws EntradaInvalidaException{
      if(transacoes.isEmpty()){
        throw new EntradaInvalidaException("\nA lista não tem transações para ser removidas.");    
       }
    for(int i = 0; i < transacoes.size(); i++){
            if(transacoes.get(i).getId() == id){
                transacoes.remove(i);
                System.out.println("\nTransação removida com sucesso.");
                return;
            }
        } 
        throw new EntradaInvalidaException("\nNenhuma transação com o ID " + id + " foi encontrada.");  
    }
    
    public void calcularSaldoTotal() throws EntradaInvalidaException{
      double saldo = 0;
      if(transacoes.isEmpty()){
        throw new EntradaInvalidaException("\nA lista não tem transações.");    
       }
      for(Transacao t : transacoes){
          if(t.isEhReceita()){
            saldo += t.getValor();  
          }else{
              saldo -= t.getValor();
          }
      }
        System.out.println("\nSaldo atual:" + saldo);
    }
}
