/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack;

import com.bianca.fintrack.controller.FinTracker;
import com.bianca.fintrack.dao.TransacaoDAO;
import com.bianca.fintrack.dao.UsuarioDAO;
import com.bianca.fintrack.exceptions.EntradaInvalidaException;
import com.bianca.fintrack.model.FinTrackerInterface;
import com.bianca.fintrack.model.Transacao;
import com.bianca.fintrack.model.Usuario;
import com.bianca.fintrack.service.UsuarioService;
import com.bianca.fintrack.view.NovaTransacaoController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author bianca
 */
public class FinApp extends Application {

    /*criacao das variaveis que seram usadas, e os objetos compartilhados pela aplicação*/
    private static Scene scene;
    private static FinTracker fintracker;
    private static UsuarioService usuarioservice;
    private static Usuario usuariologado;

    @Override
    public void start(Stage stage) throws Exception {
        // Inicializa o acesso ao banco de dados e os serviços.
        TransacaoDAO transacaoDAO = new TransacaoDAO();
        fintracker = new FinTracker(transacaoDAO);

        usuarioservice = new UsuarioService(new UsuarioDAO());

        /*criacao da janela/tela, que começa pela inicial e ai abre com show*/
        scene = new Scene(loadFXML("/com/bianca/fintrack/view/tela_inicial"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Controle de Finanças");
        stage.show();
    }

    /*criacao do metodo que instancia um FXMLLoader, que é responsavel por ler os arquivos .fxml, cria os componentes delas,
    cria o controller daquela tela e retorna ela*/
    private static Parent loadFXML(String fxml) throws IOException, EntradaInvalidaException {
        FXMLLoader fxmlLoader = new FXMLLoader(FinApp.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();

        Object controller = fxmlLoader.getController();
        // Compartilha o FinTracker com as telas que implementam a interface.
        if (controller instanceof FinTrackerInterface) {
            ((FinTrackerInterface) controller).setFinTracker(fintracker);
        }

        return root;
    }

    // Troca a tela atual pelo FXML informado.
    public static void setRoot(String fxml) throws IOException, EntradaInvalidaException {
        scene.setRoot(loadFXML(fxml));
    }

    // Abre uma tela enviando a transação que será editada.
    public static void setRoot(String fxml, Transacao transacao) throws IOException, EntradaInvalidaException {
        FXMLLoader fxmlLoader = new FXMLLoader(FinApp.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();

        Object controller = fxmlLoader.getController();

        if (controller instanceof FinTrackerInterface) {
            ((FinTrackerInterface) controller).setFinTracker(fintracker);
        }

        if (controller instanceof NovaTransacaoController) {
            ((NovaTransacaoController) controller).setTransacaoEditando(transacao);
        }

        scene.setRoot(root);
    }

    //inicia a aplicação javaFX
    public static void main(String[] args) {
        launch();
    }

    /*get para poder ter acesso a usuarioservice */
    public static UsuarioService getUsuarioservice() {
        return usuarioservice;
    }

    public static Usuario getUsuariologado() {
        return usuariologado;
    }

    public static void setUsuariologado(Usuario usuariologado) {
        FinApp.usuariologado = usuariologado;
    }

}
