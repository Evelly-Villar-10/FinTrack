/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.view;

import com.bianca.fintrack.FinApp;
import com.bianca.fintrack.exceptions.EntradaInvalidaException;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author bianca
 */
public class TelaInicialController {
    @FXML
    public void onfazerLogin() throws IOException, EntradaInvalidaException {
        FinApp.setRoot("/com/bianca/fintrack/view/tela_login");
    }
    
    @FXML
    public void onfazerCadastro() throws IOException, EntradaInvalidaException {
        FinApp.setRoot("/com/bianca/fintrack/view/tela_cadastro");
    }
}
