/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author diete
 */
public class Stapel {

    private List<Kaart> kaarten;

    public Stapel(List<Kaart> kaarten) {
        this.kaarten = kaarten;
    }

    public Kaart trekBovensteKaart() {
        Kaart kaart = kaarten.get(0);
        kaarten.remove(0);
        return kaart;

    }

    public void schudKaarten() {
        Collections.shuffle(kaarten);
    }

    public List<Kaart> geefBovensteKaarten(int aantal){
        List<Kaart> terugTeGevenKaarten = new ArrayList<>();
        for (int i = 0; i < aantal; i++) {
            terugTeGevenKaarten.add(kaarten.get(0));
            kaarten.remove(0);
        }
        return terugTeGevenKaarten;
    }

    public void voegKaartOnderaanToe(Kaart bovensteKaart) {
        kaarten.add(bovensteKaart);
    }
}
