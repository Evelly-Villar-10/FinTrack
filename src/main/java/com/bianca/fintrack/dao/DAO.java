/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bianca.fintrack.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author bianca
 */
/*Interface genérica que define as operações básicas de acesso e manipulação de dados.
 *param <T> tipo de objeto que será manipulado pelo DAO */
public interface DAO<T> {

    void adicionar(T objeto) throws SQLException;

    List<T> listar(int usuarioId) throws SQLException;

    T buscarPorId(int id) throws SQLException;

    void atualizar(T objeto) throws SQLException;

    void deletar(int id, int usuarioId) throws SQLException;
}
