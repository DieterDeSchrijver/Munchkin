/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domein.DomeinController;
import gui.WelkomScherm_TaalKeuzeController;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Aaron Dejonghe
 */
public class StartUp_gui_Munchkin extends Application {
    
    @Override
    public void start(Stage primaryStage) throws SQLException  {

        WelkomScherm_TaalKeuzeController scherm = new WelkomScherm_TaalKeuzeController(new DomeinController());
        Scene scene = new Scene(scherm);
        
        primaryStage.setTitle("Welcomescreen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
