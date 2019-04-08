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
public class KerkerConsumable extends KerkerKaart {

    private String naam;
    private int bonus;
    private String omschrijving;

    public KerkerConsumable(String naam,String omschrijving , int bonus) {
        super(naam);
        this.omschrijving = omschrijving;
        this.bonus = bonus;

    }

    public int getBonus() {
        return bonus;
    }
    
    @Override
    public String toString(){
        return String.format("%s\n"
                + "%s\n"
                + "bonus: %d\n", getNaam(), omschrijving, bonus);
    }

}
