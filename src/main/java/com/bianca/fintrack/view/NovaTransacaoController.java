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
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author bianca
 */
public class NovaTransacaoController implements FinTrackerInterface {

    /*criacao de variaveis do fxml */
    @FXML
    private RadioButton butaoMais;

    @FXML
    private RadioButton butaoMenos;

    @FXML
    private TextField campoValor;

    @FXML
    private TextArea campoDescricao;

    @FXML
    private DatePicker campoData;

    @FXML
    private Label mensagem;

    private FinTracker fintracker;
    private Transacao transacaoEditando;

    /*esse metodo prepara a tela pra quando ela abrir*/
    public void initialize() {
        /*coloca a data atual no campo de digitar a data*/
        campoData.setValue(LocalDate.now());

        /*aqui cria um "cuidador" dos botoes, que ao clicar em um desativa o outro, para ser multuamente exclusivos*/
        ToggleGroup grupo = new ToggleGroup();
        butaoMais.setToggleGroup(grupo);
        butaoMenos.setToggleGroup(grupo);

        /*faz começar sempre em receita */
        butaoMais.setSelected(true);
    }

    /*esse butão limpa os campos, volta a data atual, desmarca os botoes, retorna o foco para o valor e limpa a mensagem de conclusão*/
    @FXML
    public void onlimpardados() {
        campoValor.clear();
        campoDescricao.clear();
        campoData.setValue(LocalDate.now());
        butaoMais.setSelected(true);
        butaoMenos.setSelected(false);
        campoValor.requestFocus();
        mensagem.setText(" ");
    }

    /*esse metodo pega tudo que o usuario escreveu e guarda em uma variavel(com seus respectivos tipos)
que é passada como parametro na hora de criar a nova transacao e limpa os campos após a inserção*/
    @FXML
    public void onsalvardados() throws SQLException, IOException {
        boolean ehReceita = butaoMais.isSelected();
        double valor;

        if (campoValor.getText().isBlank()) {
            /*verifica se o campo esta vazio e fecha caso esteja */
            mensagem.setText("O campo está vazio. Digite um valor. ");
            return;
        }
        String textoValor = campoValor.getText().replace(",", ".");
        /*variavél para trocar a , por ponto, no parseDouble */

        try {
            valor = Double.parseDouble(textoValor);
        } catch (NumberFormatException e) {
            mensagem.setText("Digite um número válido. ");
            return;
        }

        String descricao = campoDescricao.getText();
        LocalDate data = campoData.getValue();
        if (data == null) {
            /*Trata a exceção pra sempre ter uma data*/
            mensagem.setText("Selecione uma data. ");
            return;
        }

        //pega o id do usuário logado para relaciona com a transação
        int usuarioId = FinApp.getUsuariologado().getId();
        try {
            if (transacaoEditando == null) {
                fintracker.adicionarTransacao(ehReceita, valor, descricao, data, usuarioId);
                onlimpardados();
                mensagem.setText("transação salva com sucesso! ");
            } else {
                transacaoEditando.setEhReceita(ehReceita);
                transacaoEditando.setValor(valor);
                transacaoEditando.setDescricao(descricao);
                transacaoEditando.setData(data);

                fintracker.atualizarTransacao(transacaoEditando);
                onlimpardados();
                mensagem.setText("transação atualizada com sucesso! ");
                FinApp.setRoot("/com/bianca/fintrack/view/tela_principal");
            }
        } catch (EntradaInvalidaException e) {
            mensagem.setText(e.getMessage());
        }
    }

    /*esse metodo coloca o objeto fintracker dentro do controller*/
    public void setFinTracker(FinTracker fintracker) {
        this.fintracker = fintracker;
    }

    public void setTransacaoEditando(Transacao transacao) {
        this.transacaoEditando = transacao;

        campoValor.setText(String.valueOf(transacao.getValor()));
        campoDescricao.setText(transacao.getDescricao());
        campoData.setValue(transacao.getData());

        if (transacao.isEhReceita()) {
            butaoMais.setSelected(true);
        } else {
            butaoMenos.setSelected(true);
        }
    }

    @FXML
    public void onirTelaPrincipal() throws IOException, EntradaInvalidaException {
        FinApp.setRoot("/com/bianca/fintrack/view/tela_principal");
    }

    @FXML
    public void onabrirRelatorio() throws IOException, EntradaInvalidaException {
        FinApp.setRoot("/com/bianca/fintrack/view/relatorio");
    }

}
