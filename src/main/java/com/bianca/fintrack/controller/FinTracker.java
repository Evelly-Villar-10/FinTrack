/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.controller;

import com.bianca.fintrack.exceptions.EntradaInvalidaException;
import com.bianca.fintrack.model.RepositorioGenerico;
import com.bianca.fintrack.model.Transacao;
import com.bianca.fintrack.model.TransacaoMensal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author bianca
 */
public class FinTracker {

    /*contador pra começar em 1 as transaçoes */
    private int contadorId = 1;
    private RepositorioGenerico<Transacao> transacoes;

    public FinTracker(RepositorioGenerico<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

    public int getContadorId() {
        return contadorId;
    }

    public void adicionarTransacao(boolean ehReceita, double valor, String descricao, LocalDate data) throws EntradaInvalidaException {
        if (valor <= 0) {
            throw new EntradaInvalidaException("\nO valor deve ser maior que zero.");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new EntradaInvalidaException("\nA descrição não pode ser vazia.");
        }

        Transacao novaTransacao = new Transacao(contadorId, ehReceita, valor, descricao, data);
        transacoes.adicionarRegistros(novaTransacao);
        contadorId++;
    }

    public void adicionarTransacaoMensal(boolean ehReceita, double valor, String descricao, LocalDate data, int diaRecorrencia) throws EntradaInvalidaException {
        if (valor <= 0) {
            throw new EntradaInvalidaException("\nO valor deve ser maior que zero.");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new EntradaInvalidaException("\nA descrição não pode ser vazia.");
        }

        TransacaoMensal mensal = new TransacaoMensal(diaRecorrencia, contadorId, ehReceita, valor, descricao, data);
        transacoes.adicionarRegistros(mensal);
        contadorId++;
    }

    public void listarTransacoes() throws EntradaInvalidaException {
        if (transacoes.getRegistros().isEmpty()) {
            throw new EntradaInvalidaException("\nA lista não tem transações.");
        } else {
            for (Transacao t : transacoes.getRegistros()) {
                System.out.println(t);
            }
        }
    }

    public void removerTransacao(int id) throws EntradaInvalidaException {
        if (transacoes.getRegistros().isEmpty()) {
            throw new EntradaInvalidaException("\nA lista não tem transações para ser removidas.");
        }
        for (int i = 0; i < transacoes.getRegistros().size(); i++) {
            if (transacoes.getRegistros().get(i).getId() == id) {
                transacoes.removerRegistros(transacoes.getRegistros().get(i));
                System.out.println("\nTransação removida com sucesso.");
                return;
            }
        }
        throw new EntradaInvalidaException("\nNenhuma transação com o ID " + id + " foi encontrada.");
    }

    public double calcularSaldoTotal() throws EntradaInvalidaException {
        double saldo = 0;
        if (transacoes.getRegistros().isEmpty()) {
            throw new EntradaInvalidaException("\nA lista não tem transações.");
        }
        for (Transacao t : transacoes.getRegistros()) {
            if (t.isEhReceita()) {
                saldo += t.getValor();
            } else {
                saldo -= t.getValor();
            }
        }
        System.out.println("\nSaldo atual:" + saldo);
        return saldo;
    }
    
/*adiciona varias transaçoes referentes a um dia, pega a lista e percorre, ai muda o id pra o correto e depois adiciona nas transaçoes */
    public void adicionarTransacaoDia(List<? extends Transacao> listaDoDia) {
        for (Transacao t : listaDoDia) {
            t.setId(contadorId);
            transacoes.adicionarRegistros(t);
            contadorId++;
        }
    }

/* metodo para retornar a lista de registros, já que o listartransaçoes so faz no console */
    public List<Transacao> buscarTransacoes() {
        return transacoes.listarRegistros();
    }
/*criacao de metodos para realizar realatorio*/
    public double calcularReceitasTotal() throws EntradaInvalidaException {
        double receitasTotal = 0;
        if (transacoes.getRegistros().isEmpty()) {
            throw new EntradaInvalidaException("\nA lista não tem transações.");
        }
        for (Transacao t : transacoes.getRegistros()) {
            if (t.isEhReceita()) {
                receitasTotal += t.getValor();
            }
        }
        return receitasTotal;
    }

    public double calcularDespesasTotal() throws EntradaInvalidaException {
        double despesasTotal = 0;
        if (transacoes.getRegistros().isEmpty()) {
            throw new EntradaInvalidaException("\nA lista não tem transações.");
        }
        for (Transacao t : transacoes.getRegistros()) {
            if (!t.isEhReceita()) {
                despesasTotal += t.getValor();
            }
        }
        return despesasTotal;
    }
}
