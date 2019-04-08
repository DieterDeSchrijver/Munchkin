/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Aaron Dejonghe
 */
public class SpelersAanmakenController extends GridPane {

    /**
     * Initializes the controller class.
     */
    
    private final DomeinController dc;
    private Locale locale;
    ResourceBundle labels;

    public SpelersAanmakenController(DomeinController dc) {
        this.dc = dc;
        createBuild();
    }
    
    private void createBuild() {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("SpelersAanmaken.fxml"));
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
