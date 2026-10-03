/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.view;

import com.bianca.fintrack.FinApp;
import com.bianca.fintrack.controller.FinTracker;
import com.bianca.fintrack.exceptions.EntradaInvalidaException;
import com.bianca.fintrack.model.FinTrackerInterface;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author bianca
 */
public class RelatorioController implements FinTrackerInterface{
/*declaracao das veriaveis do fxml*/
    @FXML
    private Label saldovalor;
    
    @FXML
    private Label totalreceitas;
    
    @FXML
    private Label totaldespesas;
    
    @FXML
    private Label totaltransacoes;
    
    private FinTracker fintracker;
    
    public void initialize(){
        
    }
    
/*esse metodo coloca o objeto fintracker dentro do controller*/    
    @Override
    public void setFinTracker(FinTracker fintracker) throws EntradaInvalidaException {
        this.fintracker = fintracker;
        double saldo = fintracker.calcularSaldoTotal();
        saldovalor.setText(String.valueOf(saldo));    /*esta convertendo o tipo da variavel pra string e guardando na variavel do fxml*/
        
        double receitas = fintracker.calcularReceitasTotal();
        totalreceitas.setText(String.valueOf(receitas));
        
        double despesas = fintracker.calcularDespesasTotal();
        totaldespesas.setText(String.valueOf(despesas));
        
        int quantidade = fintracker.buscarTransacoes().size();
        totaltransacoes.setText(String.valueOf(quantidade));
    }
    
    @FXML
    public void oncriarNovaTransacao() throws IOException, EntradaInvalidaException {
        FinApp.setRoot("/com/bianca/fintrack/view/nova_transacao");
    }
    
    @FXML
    public void onirTelaPrincipal() throws IOException, EntradaInvalidaException {
        FinApp.setRoot("/com/bianca/fintrack/view/tela_principal");
    }
}
