/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.service;

import com.bianca.fintrack.dao.UsuarioDAO;
import com.bianca.fintrack.exceptions.EntradaInvalidaException;
import com.bianca.fintrack.model.RepositorioGenerico;
import com.bianca.fintrack.model.Usuario;
import java.sql.SQLException;

/**
 *
 * @author bianca
 */
public class UsuarioService {

    /*guarda no repositorio */
    private RepositorioGenerico<Usuario> usuarios;

    public UsuarioService(RepositorioGenerico<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    private UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuario) {
        this.usuarioDAO = usuario;
    }
    
    /*faz o cadastro dos usuarios, verifica todos os campos */
    public void cadastrarUsuario(String nome, String email, String cpf, String telefone, String senha) throws EntradaInvalidaException, SQLException{
        if (nome == null || nome.isBlank()) {
            throw new EntradaInvalidaException("\nO nome não pode ser vazio.");
        }

        if (email == null || email.isBlank()) {
            throw new EntradaInvalidaException("\nE-mail não informado. Preencha o campo. ");
        }
        /*usa regex para determinar um formato válido e depois checa */
        String regex = "^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.matches(regex)) {
            throw new EntradaInvalidaException("\nE-mail inválido. Preencha o campo corretamente. ");
        }
        if (emailExiste(email)) {
            throw new EntradaInvalidaException("\nE-mail já existe. ");
        }
        if (cpf == null || cpf.isBlank()) {
            throw new EntradaInvalidaException("\nO cpf não pode ser vazio. ");
        }
        if (telefone == null || telefone.isBlank()) {
            throw new EntradaInvalidaException("\nO telefone não pode ser vazio. ");
        }
        if (senha == null || senha.isBlank()) {
            throw new EntradaInvalidaException("\nA senha não pode ser vazia. ");
        }
        if (senha.length() < 6) {
            throw new EntradaInvalidaException("\nA senha deve ter no mínimo 6 caracteres. ");
        }

        /*instância um usuario e depois adiciona ele no banco */
        Usuario usuario = new Usuario(nome, email, cpf, telefone, senha);
        usuarioDAO.adicionar(usuario);

    }

    /*função para verificar se o email já existe, com o metodo do dao, se existir retorna verdadeiro se não retonra falso */
    public boolean emailExiste(String email) throws SQLException {
        Usuario achado = usuarioDAO.procurarEmail(email);
            if (achado != null) {
                return true;
            }
        return false;
    }

    /*função para procurar o email e o usuario referente a ele*/
    public Usuario buscarPorEmail(String email) throws SQLException {
        Usuario usuario_encontrado = usuarioDAO.procurarEmail(email);
        return usuario_encontrado;
    }

    /*função para logar o usuario, pega o usuario que teve seu email validado e verifica com sua senha se bate */
    public Usuario loginUsuario(String email, String senha) throws SQLException {
        Usuario usuarioEncontrado = buscarPorEmail(email);         /*criação de variável pra guarda retorno da função */
        if (usuarioEncontrado == null) {
            System.out.println("\nErro, email não encontrado. ");
            return null;
        } else if (senha.equals(usuarioEncontrado.getSenha())) {
            System.out.println("\nLogin realizado com sucesso! ");
            return usuarioEncontrado;
        } else {
            System.out.println("\nSenha incorreta. Tente novamente. ");
            return null;
        }
    }
}
