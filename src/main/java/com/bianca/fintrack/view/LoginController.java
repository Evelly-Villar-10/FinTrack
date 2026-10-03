/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.view;

import com.bianca.fintrack.FinApp;
import com.bianca.fintrack.exceptions.EntradaInvalidaException;
import com.bianca.fintrack.service.UsuarioService;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 *
 * @author bianca
 */
public class LoginController {

    @FXML
    private TextField campoEmail;

    @FXML
    private PasswordField campoSenha;

    @FXML
    public void onEntrar() throws IOException, EntradaInvalidaException {
        /*criação de variaveis para guarda o valor dos campos */
        String email = campoEmail.getText();
        String senha = campoSenha.getText();

        /*instancia um usuarioservice do finapp e acessa ele, depois cria uma variavel para guarda o retorno da chamada de função de login de usuarioservice */
        UsuarioService usuarioservice = FinApp.getUsuarioservice();
        boolean loginUsuario = usuarioservice.loginUsuario(email, senha);
        if (loginUsuario) {
            FinApp.setRoot("/com/bianca/fintrack/view/tela_principal");
        } else {
            System.out.println("\nErro ao entrar. ");
        }
    }
    
    @FXML
    public void onVoltar() throws IOException, EntradaInvalidaException{
        FinApp.setRoot("/com/bianca/fintrack/view/tela_inicial");
    }
}
