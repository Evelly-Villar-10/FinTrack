/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bianca.fintrack;

import com.bianca.fintrack.controller.FinTracker;
import com.bianca.fintrack.exceptions.EntradaInvalidaException;
import com.bianca.fintrack.model.FinTrackerInterface;
import com.bianca.fintrack.model.RepositorioGenerico;
import com.bianca.fintrack.model.Transacao;
import com.bianca.fintrack.model.Usuario;
import com.bianca.fintrack.service.UsuarioService;
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
public class FinApp extends Application{
/*criacao das variaveis que seram usadas, sendo criadas um repositorio e um fintracker para ser instanciado*/
    private static Scene scene;
    private static RepositorioGenerico<Transacao> repositorioTransacoes;
    private static FinTracker fintracker;
    private static RepositorioGenerico<Usuario> repositorioUsuarios;
    private static UsuarioService usuarioservice;
   
    @Override
    public void start(Stage stage) throws Exception {
/*criaçao de um repositorio vazio, pq a classe fintracker lida com um repositorio generico, 
então temos q passar esse como parametro na hora de instanciar o objeto fintracker */
        repositorioTransacoes = new RepositorioGenerico<>();
        fintracker = new FinTracker(repositorioTransacoes);

        repositorioUsuarios = new RepositorioGenerico<>();
        usuarioservice = new UsuarioService(repositorioUsuarios);
        
/*criacao da cena/tela, que começa pela principal e ai abre com show*/        
        scene = new Scene(loadFXML("/com/bianca/fintrack/view/tela_inicial"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Controle de Finanças");
        stage.show();
    }

/*criacao do metodo que instancia um FXMLLoader, que é responsavel por ler os .fxml, cria os componentes delas,
    cria o controller daquela tela e retorna ela*/   
    private static Parent loadFXML(String fxml) throws IOException, EntradaInvalidaException{
        FXMLLoader fxmlLoader = new FXMLLoader(FinApp.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        
        Object controller = fxmlLoader.getController();
        if(controller instanceof FinTrackerInterface){
            ((FinTrackerInterface)controller).setFinTracker(fintracker);
        }
        
        return root;
    }
    
    public static void setRoot(String fxml) throws IOException, EntradaInvalidaException {
        scene.setRoot(loadFXML(fxml));
    }
    
    public static void main(String[] args){
        launch();
    }

    /*get para poder ter acesso a usuarioservice */
    public static UsuarioService getUsuarioservice() {
        return usuarioservice;
    }
    
}
