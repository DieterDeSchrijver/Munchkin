/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author diete
 */
public class Speler {
    private int level;
    private String naam;
    private String geslacht;
    List<Kaart> hand;
    List<Kaart> zichtbareKaarten;
    private boolean helptBijGevecht;

    public Speler(String naam, String geslacht){
        controleerNaam(naam);
        controleerGeslacht(geslacht);
        setGeslacht(geslacht);
        setLevel(1);
        setNaam(naam);
        hand = new ArrayList<>();
        zichtbareKaarten = new ArrayList<>();
    }

    private void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    private void setNaam(String naam) {
        this.naam = naam;
    }
    
    private void controleerNaam(String naam){
        //minstens 6 tekens lang max 12
        if(naam.length() < 6 || naam.length() > 12){
            throw new IllegalArgumentException("exceptionKaraktersNaam");
        }
           
        if(!Pattern.matches("[a-zA-Z]+", naam) && !naam.contains("_") && !naam.contains("-")){
            throw new IllegalArgumentException("exceptionSpecialeKaraktersNaam");
        } 
    }
    private void controleerGeslacht(String geslacht){
        String geslachtKlein;
        geslachtKlein = geslacht.toLowerCase();
        if(!"man".equals(geslachtKlein) && !"vrouw".equals(geslachtKlein) && !"woman".equals(geslachtKlein) && !"homme".equals(geslachtKlein) && !"femme".equals(geslachtKlein)){
            throw new IllegalArgumentException("exceptionGeslacht");
        }
    }
    
    @Override
    public String toString(){
        return String.format("%s, %s, %d", naam, geslacht, level);
    }
    
    public String getNaam(){
        return naam;
    }
    
    public String toonHand(){
        String stringHand = "";
        for (Kaart kaart : hand) {
            stringHand += kaart.toString() + "\n";
        }
        return String.format("%s bezit volgende kaarten:\n\n"
                + "%s",naam,  stringHand);
    }
    
    public int getLevel(){
        return level;
    }
    
    public void speelBeurt() {
    }

    public void voegKaartToeAanHand(Kaart kaart){ //voegt 1 kaart toe aan hand
        hand.add(kaart);
    }

    public void neemKaartenInHand(List<Kaart> schatKaarten, List<Kaart> kerkerKaarten) { //voegt meerdere kaarten toe aan hand
        hand.addAll(schatKaarten);
        hand.addAll(kerkerKaarten);
    }

    public String getZichtbareKaarten() {
        StringBuilder string = new StringBuilder();
        for (Kaart kaart: this.zichtbareKaarten
             ) {
            string.append(kaart.toString());
        }
        return string.toString();
    }

    public String getGeslacht() {
        return geslacht;
    }

    public void voerEffectUit(Kaart bovensteKaart) {
        this.level -= ((Curse) bovensteKaart).getVerliesAantalLevels();
        if (this.level < 0)
            this.level = 0;
    }

    public boolean handSpelerOk() {
        if (hand.size() <= 5)
            return true;
        else return false;
    }

    public void setHelptBijGevecht(boolean helptBijGevecht) {
        this.helptBijGevecht = helptBijGevecht;
    }

    public boolean getHelptBijGevecht() {
        return helptBijGevecht;
    }
}
