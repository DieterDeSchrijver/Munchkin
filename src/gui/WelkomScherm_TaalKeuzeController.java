/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Aaron Dejonghe
 */
public class WelkomScherm_TaalKeuzeController extends VBox{

    @FXML
    private Button btnNederlands;
    @FXML
    private Button btnEngels;
    @FXML
    private Button btnFrans;
    
    private final DomeinController dc;
    private Locale locale;
    ResourceBundle labels;
    
    

    public WelkomScherm_TaalKeuzeController(DomeinController dc) {
        this.dc = dc;
        createBuild();
    }
    /**
     * Initializes the controller class.
     */
    @FXML
    private void btnNederlandsOnAction(ActionEvent event) {
        
        locale = new Locale("");
        
    }

    @FXML
    private void btnEngelsOnAction(ActionEvent event) {
        
        locale = new Locale("en");
    }

    @FXML
    private void btnFransOnAction(ActionEvent event) {
        
        locale = new Locale("fr");
    }

    private void createBuild() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("WelkomScherm_TaalKeuze.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        } catch (IOException ex) {
            
            //new RuntimeException(ex);
            System.err.println("Scherm kan niet geladen worden");
            System.err.println(ex.getMessage());
        
        }
    }
    
}
