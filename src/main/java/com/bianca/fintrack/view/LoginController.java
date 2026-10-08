/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.view;

import com.bianca.fintrack.FinApp;
import com.bianca.fintrack.exceptions.EntradaInvalidaException;
import com.bianca.fintrack.model.Usuario;
import com.bianca.fintrack.service.UsuarioService;
import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        try {
            UsuarioService usuarioservice = FinApp.getUsuarioservice();
            Usuario loginUsuario = usuarioservice.loginUsuario(email, senha);
            if (loginUsuario != null) {
                FinApp.setUsuariologado(loginUsuario);
                FinApp.setRoot("/com/bianca/fintrack/view/tela_principal");
            } else {
                System.out.println("\nErro ao logar. ");
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Falha no login");
                alerta.setHeaderText(null);
                alerta.setContentText("Email ou senha incorretos.");
                alerta.showAndWait();
            }
        } catch (SQLException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro no banco");
            alerta.setHeaderText(null);
            alerta.setContentText("Não foi possível acessa o banco de dados.");
            alerta.showAndWait();
            e.printStackTrace();
        }
    }

    @FXML
    public void onVoltar() throws IOException, EntradaInvalidaException {
        FinApp.setRoot("/com/bianca/fintrack/view/tela_inicial");
    }
}
