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

    private Connection conectar;

    public TransacaoDAO() {
        this.conectar = Conexao.conectar();
    }

    public TransacaoDAO(Connection conectar) {
        this.conectar = conectar;
    }
    
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

        String sql = "SELECT * FROM transacao WHERE usuario_id = ?";
        try (PreparedStatement stmt = conectar.prepareStatement(sql)) {

            stmt.setInt(1, usuarioId);
            try (ResultSet resultado = stmt.executeQuery()) {
                List<Transacao> transacoes = new ArrayList<>();

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

    @Override
    public void atualizar(Transacao transacao) throws SQLException {
        String sql = "UPDATE transacao SET eh_receita = ?, valor = ?, descricao = ?, data = ? WHERE id = ? AND usuario_id = ?";
        try (PreparedStatement stmt = conectar.prepareStatement(sql)) {

            stmt.setBoolean(1, transacao.isEhReceita());
            stmt.setDouble(2, transacao.getValor());
            stmt.setString(3, transacao.getDescricao());
            stmt.setDate(4, java.sql.Date.valueOf(transacao.getData()));
            stmt.setInt(5, transacao.getId());
            stmt.setInt(6, transacao.getUsuarioId());
            
            stmt.executeUpdate();
        }
    }

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
