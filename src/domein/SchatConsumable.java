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
public class SchatConsumable extends SchatKaart{
    private String omschrijving;
    private int value;
    private int levelUp;
    
    public SchatConsumable(String naam, String omschrijving, int value, int levelUp){
        super(naam);
        this.omschrijving = omschrijving;
        this.value = value;
        this.levelUp = levelUp;
    }
    
    @Override
    public String toString(){
        return String.format("%s\n"
                + "Value: %d\n" + "%s%n"
                + "%s\n", getNaam(), value, omschrijving, levelUp == 0? "": "");
    }
}
