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
public class Equipment extends SchatKaart{
    private String omschrijving, type;
    private int bonus, value;
    
    
    public Equipment(String naam, String omschrijving, int bonus, String type, int value){
        super(naam);
        this.omschrijving = omschrijving;
        this.bonus = bonus;
        this.type = type;
        this.value = value;
    }
    
    @Override
    public String toString(){
        return String.format("%s\n"
                + "%s\n"
                + "%s\n"
                + "Bonus: %d, Value: %d\n", getNaam(), omschrijving == null ? "Geen omschrijving beschikbaar" : omschrijving, type, bonus, value);
    }
    
}
