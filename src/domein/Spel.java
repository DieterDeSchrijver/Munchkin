/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author diete
 */
public class Spel {

    private Speler spelerAanDeBeurt;
    private int aantalSpelers;
    private int beurt;
    private Stapel schatKaarten;
    private Stapel kerkerKaarten;
    private Speler speler;
    private Speler eersteSpeler;
    private List<Speler> spelers = new ArrayList<>();
    private Speler winnaar;
    private Kaart bovensteKaart;
    private boolean keuzeHulp;
    private boolean keuzeSpeelKaart;
    private Speler spelerDieHelpt;
    private int beurtHulp;

    public Spel(int aantalSpelers, List<Kaart> kaarten) {
        controleerAantalSpelers(aantalSpelers);
        setAantalSpelers(aantalSpelers);
        List<Kaart> alleKerkerKaarten = new ArrayList<>();
        List<Kaart> alleSchatKaarten = new ArrayList<>();
        for (Kaart k : kaarten) {
            if (k instanceof KerkerKaart) {
                alleKerkerKaarten.add(k);
            } else {
                alleSchatKaarten.add(k);
            }
            this.kerkerKaarten = new Stapel(alleKerkerKaarten);
            this.schatKaarten = new Stapel(alleSchatKaarten);
        }
        schudKaarten();
    }

    private void setAantalSpelers(int aantalSpelers) {
        this.aantalSpelers = aantalSpelers;
    }

    public int getAantalSpelers() {
        return aantalSpelers;
    }

    public void voegSpelerToe(String naam, String geslacht) {
        controleerNaamUniek(naam);
        speler = new Speler(naam, geslacht);
        spelers.add(speler);
        List<Kaart> schatKaarten = this.schatKaarten.geefBovensteKaarten(2);
        List<Kaart> kerkerKaarten = this.kerkerKaarten.geefBovensteKaarten(2);
        speler.neemKaartenInHand(schatKaarten, kerkerKaarten);
    }

    private void controleerAantalSpelers(int aantalSpelers) {
        if (aantalSpelers < 3 || aantalSpelers > 6) {
            throw new IllegalArgumentException("exceptionAantalSpelers");
        }
    }

    public String overzichtSpelers() {
        StringBuilder output = new StringBuilder();
        for (Speler speler : spelers) {
            output.append(String.format("%s\n", speler.toString()));
        }
        return output.toString();
    }

    public void eersteSpeler() {
        eersteSpeler = bepaalEersteSpeler();
        ordenSpelers();
    }

    public Speler bepaalEersteSpeler() {
        Speler eersteSpeler;
        eersteSpeler = spelers.get(0);
        for (Speler speler : spelers) {
            if (speler.getNaam().length() < eersteSpeler.getNaam().length()) {
                eersteSpeler = speler;
            } else if (speler.getNaam().length() == eersteSpeler.getNaam().length()) {
                if (speler.getNaam().compareToIgnoreCase(eersteSpeler.getNaam()) > 0) {
                    eersteSpeler = speler;
                }
            }
        }
        this.eersteSpeler = eersteSpeler;
        return eersteSpeler;
    }

    public void ordenSpelers() {
        List<Speler> toRemove = new ArrayList<>();
        for (Speler speler : spelers) {
            if (speler.getNaam().equals(eersteSpeler.getNaam())) {
                toRemove.add(speler);
            }
        }
        spelers.removeAll(toRemove);
        spelers.add(0, eersteSpeler);
    }

    public void controleerNaamUniek(String naam) {
        for (Speler speler : spelers) {
            if (naam.toLowerCase().equals(speler.getNaam().toLowerCase())) {
                throw new IllegalArgumentException(("exceptionUniekeNaam"));
            }
        }
    }

    public String overzichtHandSpelers() {
        StringBuilder overzichtHandSpelers = new StringBuilder();

        for (Speler speler : spelers) {
            overzichtHandSpelers.append(speler.toonHand()).append("\n");
        }

        return overzichtHandSpelers.toString();
    }


    private void beheerKaarten() {
        //UC beheerKaarten
    }


    private void voorbereidenGevecht() {
        //UC voorbereidenGevecht
    }

    private void VechtMonster() {
        //UC vechtMonster
    }

    public boolean isSpelAfgelopen() {

        for (Speler speler : spelers) {
            if (speler.getLevel() == 10) {
                winnaar = speler;
                return true;
            }
        }
        return false;
    }

    public void resetHulpSpelers(){
        for (Speler speler:
                spelers) {
            speler.setHelptBijGevecht(false);
        }
    }

    public void updateSpelerAanDeBeurt() {
        beurt += 1;
        if (beurt == aantalSpelers) {
            beurt = 0;
        }
        beurtHulp = beurt + 1;
        spelerAanDeBeurt = spelers.get(beurt);
        if (beurtHulp == aantalSpelers)
            beurtHulp = 0;
        spelerDieHelpt = spelers.get(beurtHulp);
    }

    public void updateSpelerDieHelpt(){
        beurtHulp += 1;
        if (beurtHulp == aantalSpelers)
            beurtHulp = 0;
        spelerDieHelpt = spelers.get(beurtHulp);
    }

    public int getSpelerAanDeBeurt() {
        return beurt;
    }

    public void schudKaarten() {
        kerkerKaarten.schudKaarten();
        schatKaarten.schudKaarten();
    }

    public List<Speler> bepaalVolgordeSpelers() {
        bepaalEersteSpeler();
        ordenSpelers();
        return spelers;
    }

    public String getNaamWinnaar() {
        return winnaar.getNaam();
    }

    public String getNaamSpelerAanDeBeurt() {
        return spelerAanDeBeurt.getNaam();
    }

    public String getNaamSpeler(int speler) {
        return spelers.get(speler).getNaam();
    }

    public int getLevelSpeler(int speler) {
        return spelers.get(speler).getLevel();
    }

    public String getZichtbareKaartenSpeler(int speler) {
        return spelers.get(speler).getZichtbareKaarten();
    }

    public String getGeslachtSpeler(int speler) {
        return spelers.get(speler).getGeslacht();
    }

    public String trekBovensteKaart() {
        bovensteKaart = kerkerKaarten.trekBovensteKaart();

        return bovensteKaart.toString();
    }

    public boolean isBovensteKaartMonster() {
        return bovensteKaart instanceof Monster;
    }


    public void bovensteKaartIsCurse() {
        spelerAanDeBeurt.voerEffectUit(bovensteKaart);
        kerkerKaarten.voegKaartOnderaanToe(bovensteKaart);
    }

    public boolean isBovensteKaartCurse() {
        return bovensteKaart instanceof Curse;
    }

    public void bovensteKaartIsConsumable() {
        spelerAanDeBeurt.voegKaartToeAanHand(bovensteKaart);
    }

    public int verliesAantalLevelsCurse() {
        return ((Curse) bovensteKaart).getVerliesAantalLevels();
    }

    public boolean handSpelerOk() {
       return spelerAanDeBeurt.handSpelerOk();
    }

    public void registreerKeuzeHulp(int keuzeHulp) {
        this.keuzeHulp = keuzeHulp == 1;
    }

    public boolean registreerKeuzeSpeelSpel(int keuzeSpeelKaart) {
        this.keuzeSpeelKaart = keuzeSpeelKaart == 1;
        return this.keuzeSpeelKaart;
    }

    public boolean isKeuzeSpeelKaart() {
        return keuzeSpeelKaart;
    }

    public boolean iedereenHeeftGeholpen() {
        return spelerAanDeBeurt == spelerDieHelpt;
    }

    public String getNaamSpelerDieHelpt() {
        return spelerDieHelpt.getNaam();
    }

    public boolean wiltHulp() {
        return keuzeHulp;
    }

    public void spelerHelptBijGevecht() {
        spelerDieHelpt.setHelptBijGevecht(true);
    }

    public String[][] toonOverzichtSpelersGevecht() {
        String[][] overzichtSpelersGevecht = new String[aantalSpelers][4];
        for (int i = 0; i < aantalSpelers; i++) {
            overzichtSpelersGevecht[i][0] = spelers.get(i).getNaam();
            overzichtSpelersGevecht[i][1] = spelers.get(i).getGeslacht();
            overzichtSpelersGevecht[i][2] = String.valueOf(spelers.get(i).getLevel());
            overzichtSpelersGevecht[i][3] = String.valueOf(spelers.get(i).getHelptBijGevecht());
        }
        return overzichtSpelersGevecht;
    }
}
