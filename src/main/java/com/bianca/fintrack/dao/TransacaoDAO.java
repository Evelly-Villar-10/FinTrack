/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.dao;

import com.bianca.fintrack.database.Conexao;
import com.bianca.fintrack.model.Transacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bianca
 */
public class TransacaoDAO implements DAO<Transacao> {

    //cria atributo que guarda a conexão com o banco
    private Connection conectar;

    /*construtor sem parametros, o método Conexao.conectar() cria ou 
    * obtém a conexão com o banco configurado no projeto*/
    public TransacaoDAO() {
        this.conectar = Conexao.conectar();
    }

    // recebe uma conexão que já foi criada, opção para usar banco em memória
    public TransacaoDAO(Connection conectar) {
        this.conectar = conectar;
    }

    /*adiciona uma trnasação, o inset insere uma linha na tabela com os dados, 
    * depois o java prepara o comando usando a conexaão*/
    @Override
    public void adicionar(Transacao transacao) throws SQLException {
        String sql = "INSERT INTO transacao(eh_receita, valor, descricao, data, usuario_id) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conectar.prepareStatement(sql)) {

            // insere os dados da transação no seu respectivo campo
            stmt.setBoolean(1, transacao.isEhReceita());
            stmt.setDouble(2, transacao.getValor());
            stmt.setString(3, transacao.getDescricao());
            stmt.setDate(4, java.sql.Date.valueOf(transacao.getData()));     //esta convertendo o localdate, atraves do metodo da classe java.sql.date, pq o banco não reconhece localdate
            stmt.setInt(5, transacao.getUsuarioId());

            // executa a inserção
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Transacao> listar(int usuarioId) throws SQLException {

        /*comando que seleciona a tabela e filtra pelo parametro recebido, depois tem a preparação
        * do comando com o id sendo usado para consulta*/
        String sql = "SELECT * FROM transacao WHERE usuario_id = ?";
        try (PreparedStatement stmt = conectar.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            try (ResultSet resultado = stmt.executeQuery()) {        //execução da consulta 

                List<Transacao> transacoes = new ArrayList<>();      //cria uma lista que recebe os objetos transaçoes do banco

                /*percorre os resultados e pega cada campo e guarda em uma variável,
                * deposi cria a transação e adicionar e retorna a lista*/
                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    boolean ehReceita = resultado.getBoolean("eh_receita");
                    double valor = resultado.getDouble("valor");
                    String descricao = resultado.getString("descricao");
                    LocalDate data = resultado.getDate("data").toLocalDate();
                    int idUsuario = resultado.getInt("usuario_id");

                    Transacao transacao = new Transacao(id, ehReceita, valor, descricao, data, idUsuario);
                    transacoes.add(transacao);
                }
                return transacoes;
            }

        }
    }

    /*busca uma transaçao por id que vem como parametro e depois o objeto Transacao e retorna, se não 
    * for encontrado retorna null*/
    @Override
    public Transacao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM transacao WHERE id = ?";
        try (PreparedStatement stmt = conectar.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet resultado = stmt.executeQuery()) {
                if (resultado.next()) {
                    int id_encontrado = resultado.getInt("id");
                    boolean ehReceita = resultado.getBoolean("eh_receita");
                    double valor = resultado.getDouble("valor");
                    String descricao = resultado.getString("descricao");
                    LocalDate data = resultado.getDate("data").toLocalDate();
                    int id_usuario = resultado.getInt("usuario_id");

                    Transacao transacao = new Transacao(id_encontrado, ehReceita, valor, descricao, data, id_usuario);
                    return transacao;
                }
            }
        }
        return null;
    }

    /*recebe uma Transação com dados atualizados e o comando recebe o id da transação e o do usuario refrente */
    @Override
    public void atualizar(Transacao transacao) throws SQLException {
        String sql = "UPDATE transacao SET eh_receita = ?, valor = ?, descricao = ?, data = ? WHERE id = ? AND usuario_id = ?";
        try (PreparedStatement stmt = conectar.prepareStatement(sql)) {

            //preenche os parametros, menos o usuario id
            stmt.setBoolean(1, transacao.isEhReceita());
            stmt.setDouble(2, transacao.getValor());
            stmt.setString(3, transacao.getDescricao());
            stmt.setDate(4, java.sql.Date.valueOf(transacao.getData()));
            stmt.setInt(5, transacao.getId());
            stmt.setInt(6, transacao.getUsuarioId());

            stmt.executeUpdate();
        }
    }

    /*preenche o campo de id e de usuarioId e depois executa o delete*/
    @Override
    public void deletar(int id, int usuarioId) throws SQLException {
        String sql = "DELETE FROM transacao where id = ? AND usuario_id = ?";
        try (PreparedStatement stmt = conectar.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.setInt(2, usuarioId);
            stmt.executeUpdate();
        }
    }

}
