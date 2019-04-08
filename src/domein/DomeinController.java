/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author diete
 */
public class DomeinController {

    private Spel spel;
    private KaartRepository kaartRepo;


    public DomeinController() throws SQLException {
        kaartRepo = new KaartRepository();
    }

    public void geefAantalSpelers(int aantalSpelers) {
        List<Kaart> kaarten = kaartRepo.geefAlleKaarten();
        spel = new Spel(aantalSpelers, kaarten);
    }

    public void voegSpelerToe(String naam, String geslacht) {
        spel.voegSpelerToe(naam, geslacht);
    }

    public String overzichtSpelers() {
        return spel.overzichtSpelers();
    }

    public void speelSpel() {
    }

    public int getAantalSpelersSpel() {
        return spel.getAantalSpelers();
    }

    public boolean isSpelAfgelopen() {
        return spel.isSpelAfgelopen();
    }

    public List<String> bepaalVolgordeSpelers() {
        List<String> volgordeSpelersspeler = new ArrayList<>() {
        };
        for (Speler speler : spel.bepaalVolgordeSpelers()
        ) {
            volgordeSpelersspeler.add(speler.getNaam());
        }
        spel.updateSpelerAanDeBeurt();
        return volgordeSpelersspeler;

    }

    public void updateBeurt() {
        spel.resetHulpSpelers();
        spel.updateSpelerAanDeBeurt();
    }

    public void updateSpelerDieHelpt(){
        spel.updateSpelerDieHelpt();
    }

    public String getNaamWinnaar() {
        return spel.getNaamWinnaar();
    }

    public String getNaamSpelerAanDeBeurt() {
        return spel.getNaamSpelerAanDeBeurt();
    }

    public String getNaamSpeler(int speler) {
        return spel.getNaamSpeler(speler);
    }

    public int getLevelSpeler(int speler) {
       return spel.getLevelSpeler(speler);
    }

    public String getZichtbareKaartenSpeler(int speler) {
        return spel.getZichtbareKaartenSpeler(speler);
    }

    public String getGeslachtSpeler(int speler) {
        return spel.getGeslachtSpeler(speler);
    }

    public void sluitSpelAf() {
        System.exit(0);
    }

    public void slaSpelOp() {
    }

    public String trekBovensteKaart() {
        return spel.trekBovensteKaart();
    }

    public boolean isBovensteKaartMonster() {
        return spel.isBovensteKaartMonster();
    }

    public boolean isBovensteKaartCurse() {
        return spel.isBovensteKaartCurse();
    }

    public void bovensteKaartIsCurse() {
        spel.bovensteKaartIsCurse();
    }

    public void bovensteKaartIsConsumable() {
        spel.bovensteKaartIsConsumable();
    }

    public int getVerliesAantalLevelsCurse() {
        return spel.verliesAantalLevelsCurse();
    }

    public boolean handSpelerOk() {
        return spel.handSpelerOk();
    }

    public void registreerKeuzeHulp(int keuzeHulp) {
        spel.registreerKeuzeHulp(keuzeHulp);
    }

    public boolean registreerSpeelKaart(int keuzeSpeelKaart) {
        return spel.registreerKeuzeSpeelSpel(keuzeSpeelKaart);
    }

    public boolean isKeuzeSpeelKaart() {
        return spel.isKeuzeSpeelKaart();
    }

    public int getSpelerAanDeBeurt() {
        return spel.getSpelerAanDeBeurt();
    }

    public boolean iedereenHeeftGeholpen() {
        return spel.iedereenHeeftGeholpen();
    }

    public String getNaamSpelerDieHelpt() {
        return spel.getNaamSpelerDieHelpt();
    }

    public boolean wiltHulp() {
        return spel.wiltHulp();
    }

    public void spelerHelptBijGevecht() {
        spel.spelerHelptBijGevecht();
    }

    public String[][] toonOverzichtSpelersGevecht() {
        return spel.toonOverzichtSpelersGevecht();
    }
}
