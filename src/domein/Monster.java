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
public class Monster extends KerkerKaart {

    private String naam;
    private int level;
    private int reward;
    private String badStuff;
    private String specialEffect;

    public Monster(String naam, int level, String badStuff, int reward, String specialEffect) {
        super(naam);
        this.level = level;
        this.badStuff = badStuff;
        this.reward = reward;
        this.specialEffect = specialEffect;
    }
    
    public String toString(){
        return String.format("%s\n"
                + "Level: %d\n"
                + "BadStuff: %s\n"
                + "Reward: %d %s\n"
                + "Special Effect: %s\n", getNaam(), level, badStuff, reward, reward == 1? "schatkaart" : "schatkaarten", specialEffect == null? "Geen": specialEffect);
    }

}
