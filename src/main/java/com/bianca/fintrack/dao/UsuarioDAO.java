/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.dao;

import com.bianca.fintrack.database.Conexao;
import com.bianca.fintrack.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author bianca
 */
public class UsuarioDAO implements DAO<Usuario>{

    // classe que cadastra o usuario com o banco de dados
@Override
    public void adicionar(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO usuario(nome, email, cpf, telefone, senha) VALUES(?, ?, ?, ?, ?)";
        try (Connection conectar = Conexao.conectar(); PreparedStatement stmt = conectar.prepareStatement(sql)) {

            // insere os dados do usuario no seu respectivo campo
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getCpf());
            stmt.setString(4, usuario.getTelefone());
            stmt.setString(5, usuario.getSenha());
            // executa a inserção
            stmt.executeUpdate();
        }
    }

    public Usuario procurarEmail(String email) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE email = ?";
        try (Connection conectar = Conexao.conectar(); PreparedStatement stmt = conectar.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                String nome = resultado.getString("nome");
                String email_encontrado = resultado.getString("email");
                String cpf = resultado.getString("cpf");
                String telefone = resultado.getString("telefone");
                String senha = resultado.getString("senha");
                int id = resultado.getInt("id");
                
                Usuario usuario = new Usuario(nome, email_encontrado, cpf, telefone, senha, id);
                return usuario;
            }
        }
        return null;
    }

    @Override
    public Usuario buscarPorId(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void atualizar(Usuario objeto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Usuario> listar(int usuarioId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deletar(int id, int usuarioId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
