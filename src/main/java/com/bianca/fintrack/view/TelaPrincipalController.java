/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack.view;

import com.bianca.fintrack.FinApp;
import com.bianca.fintrack.controller.FinTracker;
import com.bianca.fintrack.exceptions.EntradaInvalidaException;
import com.bianca.fintrack.model.FinTrackerInterface;
import com.bianca.fintrack.model.Transacao;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author bianca
 */
public class TelaPrincipalController implements FinTrackerInterface {

    /* criacao de atributos com tipos e nomes do fxml */
    @FXML
    private TableView<Transacao> tabela;

    @FXML
    private TableColumn<Transacao, LocalDate> colData;

    @FXML
    private TableColumn<Transacao, String> colDescricao;

    @FXML
    private TableColumn<Transacao, Double> colValor;

    @FXML
    private TableColumn<Transacao, Boolean> colTipo;

    @FXML
    private Button butaoAdicionar;

    @FXML
    private Button butaoRelatorio;

    /* é preciso um atributo do tipo da classe fintracker, para usa-lo como objeto e 
fazer manipulaçoes com ele */
    private FinTracker fintracker;

    /* quer a tabela já vindo cheia quando a tela abre, por isso usa initialize, 
pra quando o FXMLLoader chamar o .fxml já preeencher */
    public void initialize() {
        /*configuraçao para cada coluna pegar o seu respectivo atributo*/
        colData.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getData())
        );

        colDescricao.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getDescricao())
        );

        colValor.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getValor())
        );

        colTipo.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().isEhReceita())
        );
    }

    /*recebe o fintracker de finapp e usa ele pra pra preencher a tabela*/
    public void setFinTracker(FinTracker fintracker) {
        this.fintracker = fintracker;

        carregarTransacoes();
    }

    private void carregarTransacoes() {
        /*criaçao d uma lista do tipo transacao para guarda a busca que esse metodo do fintracker faz*/
        int usuarioId = FinApp.getUsuariologado().getId();
        List<Transacao> lista;
        try {
            lista = fintracker.buscarTransacoes(usuarioId);
            /*transforma em observablelist*/
            ObservableList<Transacao> lista_nova = FXCollections.observableArrayList(lista);

            tabela.setItems(lista_nova);

        } catch (SQLException ex) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro no banco");
            alerta.setHeaderText(null);
            alerta.setContentText("Não foi possível carregar as transações.");
            alerta.showAndWait();
            ex.printStackTrace();
        }
    }

    @FXML
    public void oncriarNovaTransacao() throws IOException, EntradaInvalidaException {
        FinApp.setRoot("/com/bianca/fintrack/view/nova_transacao");
    }

    @FXML
    public void onabrirRelatorio() throws IOException, EntradaInvalidaException {
        FinApp.setRoot("/com/bianca/fintrack/view/relatorio");
    }

    @FXML
    public void onAtualizar() throws IOException, EntradaInvalidaException {
        Transacao selecionada = tabela.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText(null);
            alerta.setContentText("Nenhuma transação selecionada.");
            alerta.showAndWait();
            return;
        }
        
        FinApp.setRoot("/com/bianca/fintrack/view/nova_transacao", selecionada);
    }

    @FXML
    public void onExcluir() throws EntradaInvalidaException, SQLException {
        Transacao selecionada = tabela.getSelectionModel().getSelectedItem();
        if (selecionada == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Erro");
            alerta.setHeaderText(null);
            alerta.setContentText("Nenhuma transação selecionada.");
            alerta.showAndWait();
            return;
        }
        int id = selecionada.getId();
        int usuarioId = selecionada.getUsuarioId();

        fintracker.removerTransacao(id, usuarioId);
        carregarTransacoes();
    }
}
