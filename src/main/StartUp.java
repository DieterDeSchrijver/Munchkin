/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import domein.DomeinController;
import ui.Applicatie;

import java.sql.SQLException;

/**
 *
 * @author diete
 */
public class StartUp {
    public static void main(String[] args) throws SQLException, InterruptedException {
        DomeinController dc = new DomeinController();
        Applicatie app = new Applicatie(dc);
        app.start();
    }
    
}

