/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.controller;

import com.bianca.fintrack.dao.TransacaoDAO;
import com.bianca.fintrack.exceptions.EntradaInvalidaException;
import com.bianca.fintrack.model.Transacao;
import com.bianca.fintrack.model.TransacaoMensal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author bianca
 */
public class FinTracker {

    private TransacaoDAO transacaoDao;

    public FinTracker(TransacaoDAO transacaoDao) {
        this.transacaoDao = transacaoDao;
    }

    public void adicionarTransacao(boolean ehReceita, double valor, String descricao, LocalDate data, int usuarioId) throws EntradaInvalidaException, SQLException {
        if (valor <= 0) {
            throw new EntradaInvalidaException("\nO valor deve ser maior que zero.");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new EntradaInvalidaException("\nA descrição não pode ser vazia.");
        }

        Transacao novaTransacao = new Transacao(ehReceita, valor, descricao, data, usuarioId);
        transacaoDao.adicionar(novaTransacao);
    }

    public void adicionarTransacaoMensal(boolean ehReceita, double valor, String descricao, LocalDate data, int diaRecorrencia, int usuarioId) throws EntradaInvalidaException, SQLException {
        if (valor <= 0) {
            throw new EntradaInvalidaException("\nO valor deve ser maior que zero.");
        }
        if (descricao == null || descricao.isBlank()) {
            throw new EntradaInvalidaException("\nA descrição não pode ser vazia.");
        }

        TransacaoMensal mensal = new TransacaoMensal(diaRecorrencia, ehReceita, valor, descricao, data, usuarioId);
        transacaoDao.adicionar(mensal);
    }

    public List<Transacao> listarTransacoes(int usuarioId) throws EntradaInvalidaException, SQLException {
        List<Transacao> transacoes = transacaoDao.listar(usuarioId);
        if (transacoes.isEmpty()) {
            throw new EntradaInvalidaException("\nA lista não tem transações.");
        } else {
            return transacoes;
        }
    }

    public void removerTransacao(int id, int usuarioId) throws EntradaInvalidaException, SQLException {
        List<Transacao> listaTransacoes = transacaoDao.listar(usuarioId);
        if (listaTransacoes.isEmpty()) {
            throw new EntradaInvalidaException("\nA lista não tem transações para ser removidas.");
        }
        for (int i = 0; i < listaTransacoes.size(); i++) {
            Transacao transacao = listaTransacoes.get(i);
            if (transacao.getId() == id) {
                transacaoDao.deletar(id, usuarioId);
                System.out.println("\nTransação removida com sucesso.");
                return;
            }
        }
        throw new EntradaInvalidaException("\nNenhuma transação com o ID " + id + " foi encontrada.");
    }

    public double calcularSaldoTotal(int usuarioId) throws EntradaInvalidaException, SQLException {
        double saldo = 0;
        List<Transacao> transacoes = transacaoDao.listar(usuarioId);
        if (transacoes.isEmpty()) {
            throw new EntradaInvalidaException("\nA lista não tem transações.");
        }
        for (Transacao t : transacoes) {
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
    public void adicionarTransacaoDia(List<? extends Transacao> listaDoDia) throws SQLException {
        for (Transacao t : listaDoDia) {
            transacaoDao.adicionar(t);
        }
    }

    /* metodo para retornar a lista de registros, já que o listartransaçoes so faz no console */
    public List<Transacao> buscarTransacoes(int usuarioId) throws SQLException {
        return transacaoDao.listar(usuarioId);
    }

    /*criacao de metodos para realizar realatorio*/
    public double calcularReceitasTotal(int usuarioId) throws EntradaInvalidaException, SQLException {
        double receitasTotal = 0;
        List<Transacao> transacoes = transacaoDao.listar(usuarioId);
        if (transacoes.isEmpty()) {
            throw new EntradaInvalidaException("\nA lista não tem transações.");
        }
        for (Transacao t : transacoes) {
            if (t.isEhReceita()) {
                receitasTotal += t.getValor();
            }
        }
        return receitasTotal;
    }

    public double calcularDespesasTotal(int usuarioId) throws EntradaInvalidaException, SQLException {
        double despesasTotal = 0;
        List<Transacao> transacoes = transacaoDao.listar(usuarioId);
        if (transacoes.isEmpty()) {
            throw new EntradaInvalidaException("\nA lista não tem transações.");
        }
        for (Transacao t : transacoes) {
            if (!t.isEhReceita()) {
                despesasTotal += t.getValor();
            }
        }
        return despesasTotal;
    }
    
    public void atualizarTransacao(Transacao transacao) throws SQLException, EntradaInvalidaException{
        if (transacao.getValor() <= 0) {
            throw new EntradaInvalidaException("\nO valor deve ser maior que zero.");
        }
        if (transacao.getDescricao() == null || transacao.getDescricao().isBlank()) {
            throw new EntradaInvalidaException("\nA descrição não pode ser vazia.");
        }

        transacaoDao.atualizar(transacao);
    }
    
}
