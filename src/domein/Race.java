/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author diete
 */
public class Race extends KerkerKaart {
    private String naam, specialEffect;
    
    public Race(String naam, String specialEffect){
        super(naam);
        this.specialEffect = specialEffect;
    }
    
    public String toString(){
        return String.format("%s\n"
                + "Special Effect: %s\n", getNaam(), specialEffect);
    }
}
