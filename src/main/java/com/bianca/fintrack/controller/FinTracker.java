/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.controller;

import com.bianca.fintrack.exceptions.EntradaInvalidaException;
import com.bianca.fintrack.model.RepositorioGenerico;
import com.bianca.fintrack.model.Transacao;
import com.bianca.fintrack.model.TransacaoMensal;
import java.util.List;

/**
 *
 * @author bianca
 */
public class FinTracker {
    private int contadorId = 1;
    private RepositorioGenerico<Transacao> transacoes;

    public FinTracker(RepositorioGenerico<Transacao> transacoes) {
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
        transacoes.adicionarRegistros(novaTransacao);
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
        transacoes.adicionarRegistros(mensal);
        contadorId++;
    }
    
    public void listarTransacoes() throws EntradaInvalidaException{
       if(transacoes.getRegistros().isEmpty()){
        throw new EntradaInvalidaException("\nA lista não tem transações.");    
       }else{
           for(Transacao t : transacoes.getRegistros()){
               System.out.println(t);
           }
       }
    }
    
    public void removerTransacao(int id) throws EntradaInvalidaException{
      if(transacoes.getRegistros().isEmpty()){
        throw new EntradaInvalidaException("\nA lista não tem transações para ser removidas.");    
       }
    for(int i = 0; i < transacoes.getRegistros().size(); i++){
            if(transacoes.getRegistros().get(i).getId() == id){
                transacoes.removerRegistros(transacoes.getRegistros().get(i));
                System.out.println("\nTransação removida com sucesso.");
                return;
            }
        } 
        throw new EntradaInvalidaException("\nNenhuma transação com o ID " + id + " foi encontrada.");  
    }
    
    public void calcularSaldoTotal() throws EntradaInvalidaException{
      double saldo = 0;
      if(transacoes.getRegistros().isEmpty()){
        throw new EntradaInvalidaException("\nA lista não tem transações.");    
       }
      for(Transacao t : transacoes.getRegistros()){
          if(t.isEhReceita()){
            saldo += t.getValor();  
          }else{
              saldo -= t.getValor();
          }
      }
        System.out.println("\nSaldo atual:" + saldo);
    }
    
    public void adicionarTransacaoDia(List<? extends Transacao> listaDoDia){
        for(Transacao t : listaDoDia){
            t.setId(contadorId);
            transacoes.adicionarRegistros(t);
            contadorId++;
        }
    }
}
