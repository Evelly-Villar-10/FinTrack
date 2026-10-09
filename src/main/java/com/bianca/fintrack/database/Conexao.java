/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bianca
 */
public class Conexao {

    // classe que conecta com o banco de dados
    public static Connection conectar() {
        //cria a variável da classe connection que armazena a conexão do banco
        Connection conectar = null;
        try {
            // O caminho começa com (jdbc:sqlite:), que é o drive do banco de dados e o banco, seguido do local do arquivo
            String url = "jdbc:sqlite:database/fintrack.db";

            conectar = DriverManager.getConnection(url);   //aqui a conexão acontece com o drive do sqlite
            System.out.println("Conexão com o SQLite estabelecida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
        return conectar;
    }

    /*faz a conexão com o banco em memória*/
    public static Connection conectarMemoria() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite::memory:");
    }
}
