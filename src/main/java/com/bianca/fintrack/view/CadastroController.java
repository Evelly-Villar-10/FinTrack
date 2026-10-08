/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.view;

import com.bianca.fintrack.FinApp;
import com.bianca.fintrack.exceptions.EntradaInvalidaException;
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
public class CadastroController {

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoEmail;

    @FXML
    private TextField campoCpf;

    @FXML
    private TextField campoTelefone;

    @FXML
    private PasswordField campoSenha;

    @FXML
    public void onfazerCadastro() throws IOException{
        /*criação de variaveis para guarda o valor dos campos */
        String Nome = campoNome.getText();
        String Email = campoEmail.getText();
        String CPF = campoCpf.getText();
        String Telefone = campoTelefone.getText();
        String Senha = campoSenha.getText();

        /*instancia um usuarioservice do finapp e acessa ele, depois chama função de cadastro do usuarioservice */
        try {
            UsuarioService usuarioservice = FinApp.getUsuarioservice();
            usuarioservice.cadastrarUsuario(Nome, Email, CPF, Telefone, Senha);
            System.out.println("\nCadastro realizado. ");
            FinApp.setRoot("/com/bianca/fintrack/view/tela_login");
            
        } catch(EntradaInvalidaException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Alerta");
            alerta.setHeaderText(null);
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
        } catch(SQLException e){
            e.printStackTrace();
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Alerta");
            alerta.setHeaderText(null);
            alerta.setContentText("Não foi possível salvar o cadastro.");
            alerta.showAndWait();
        }
    }

    @FXML
    public void onvoltar() throws IOException, EntradaInvalidaException {
        FinApp.setRoot("/com/bianca/fintrack/view/tela_inicial");
    }
}
