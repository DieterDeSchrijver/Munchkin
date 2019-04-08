/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domein.DomeinController;

import java.util.*;

/**
 * @author diete
 */
public class Applicatie {

    private final DomeinController dc;
    private Locale locale;
    private Scanner sc = new Scanner(System.in);
    private ResourceBundle labels;


    public Applicatie(DomeinController dc) {
        this.dc = dc;

    }

    public void start() throws InterruptedException {

        //flags
        boolean spelCorrectFlag = true;
        boolean speelSpelFlag = true;
        boolean keuzeTaalFlag = true;

        do {
            try {
                toonMenuTaal(); //toont menu om taal te kiezen
                instellenTaal(geefKeuzeTaal()); // vraagt keuze taal en stelt taal in
                keuzeTaalFlag = false;

            } catch (Exception e) {
                System.err.println("\nKies het nummer van je taal (1, 2 of 3)\n"
                        + "Choose the number of your language (1, 2 or 3)\n"
                        + "Choisissez le numéro de votre langue (1, 2 ou 3)\n");
                System.err.flush();
                Thread.sleep(50);
                sc.nextLine();
            }
        } while (keuzeTaalFlag);

        labels = ResourceBundle.getBundle("i18n.MyBundle", locale);

        System.out.println(labels.getString("welkomwoord"));
        System.out.println(labels.getString("aanmakenSpel"));


        while (spelCorrectFlag) {
            try {
                System.out.println(labels.getString("vraagHoeveelSpelers"));
                int aantalSpelers = sc.nextInt();

                dc.geefAantalSpelers(aantalSpelers); //spel aanmaken

                spelCorrectFlag = false; //stopt lus

            } catch (IllegalArgumentException e) {
                System.err.println();
                System.err.println(labels.getString("exceptionAantalSpelers"));
                System.err.flush();
                Thread.sleep(50);
                sc.nextLine();
            } catch (InputMismatchException f) {
                System.err.println();
                System.err.println(labels.getString("exceptionNumeriekeWaarde"));
                System.err.println();
                System.err.flush();
                Thread.sleep(50);
                sc.nextLine();
            }

        }

        for (int i = 1; i <= dc.getAantalSpelersSpel(); i++) {

            System.out.println();  //invoer naam en geslacht speler
            System.out.println(labels.getString("speler") + i);
            System.out.println(labels.getString("geefNaam"));
            String naam = sc.next();
            System.out.println(labels.getString("geefGeslacht"));
            String geslacht = sc.next();


            try {
                dc.voegSpelerToe(naam, geslacht); //voegt speler toe aan spel
            } catch (IllegalArgumentException e) {
                --i;
                System.err.println();
                System.err.println(labels.getString(e.getMessage()));
                System.err.flush();
                Thread.sleep(50);
                sc.nextLine();

            }
        }

        System.out.println(); //overzicht spelers
        System.out.println(labels.getString("overzicht") + ": ");
        System.out.println(dc.overzichtSpelers());
        System.out.println();

        System.out.println(labels.getString("volgordeSpelers") + ": ");
        for (String naam : dc.bepaalVolgordeSpelers() //bepaalt volgorde spelers en print deze
        ) {
            System.out.println(naam);
        }

        while (!dc.isSpelAfgelopen()) { //Lus speelSpel
            int keuze = 0;
            do {
                try {
                    System.out.println(toonMenuSpeelSpel()); //toont menu speelSpelkeuze = 0;
                    keuze = sc.nextInt(); 
                    if (keuze > 0 && keuze < 4){
                        speelSpelFlag = false;
                    } else {
                        System.err.println("\n" + labels.getString("exceptionGeenJuisteOptie"));
                        System.err.flush();
                        Thread.sleep(50);
                        sc.nextLine();
                    }
                    

                } catch (Exception e) {
                    System.err.println("\n" + labels.getString("exceptionGeenJuisteOptie"));
                    System.err.flush();
                    Thread.sleep(50);
                    sc.nextLine();
                }
            } while (speelSpelFlag);


            switch (keuze) {
                case 1:
                    speelBeurt();
                    dc.updateBeurt();
                    break;
                case 2:
                    slaSpelOp();
                    break;
                case 3:
                    sluitSpelAf();

                default:
                    throw new IllegalArgumentException();
            }

        }
        System.out.printf("%s " + labels.getString("isGewonnen"), dc.getNaamWinnaar());

    }

    private void sluitSpelAf() { // sluit spel af
        System.out.println("\n" + labels.getString("spelWordtAfgesloten")  + "\n");
        dc.sluitSpelAf();
    }

    private void slaSpelOp() { //sla spel op
        System.out.println("\n" + labels.getString("spelWordtOpgeslaan") + "\n");
        dc.slaSpelOp();
    }

    private void speelBeurt() throws InterruptedException {
        toonOverzichtSpel(); //toont spel overzicht van alle spelers
        promptEnterKey(); // enter om verder te gaan
        toonBovensteKaart();

        if (dc.isBovensteKaartMonster()) { //kijkt of het een monster is
            System.out.println(labels.getString("isMonster"));
            voorbereidenGevecht();
            VechtMonster();
        } else {
            if (dc.isBovensteKaartCurse()) { //kijkt of een curse kaart is
                dc.bovensteKaartIsCurse();
                System.out.println(labels.getString("jeVerliest") + dc.getVerliesAantalLevelsCurse() + labels.getString("levels"));
            } else {
                dc.bovensteKaartIsConsumable();
                System.out.println(labels.getString("isConsumable"));
            }
        }

        boolean keuzeBeheerKaartenFlag = true;
        do{
            try{
                vraagBeheerKaarten();
                keuzeBeheerKaartenFlag = false;
            } catch (Exception e){
                System.err.println("\n" + labels.getString("exceptionBeheerKaarten"));
                System.err.flush();
                Thread.sleep(50);
                sc.nextLine();

            }
        } while (keuzeBeheerKaartenFlag);

        toonOverzichtSpel();
    }

    private void vraagBeheerKaarten() {
        do {
            while(keuzeBeheerKaarten()){
                beheerKaarten();
            }
            if (!dc.handSpelerOk())
                System.err.println("\n" + labels.getString("handSpelerOK"));

        } while (!dc.handSpelerOk());

    }

    private void beheerKaarten() {
        System.out.println("beheren kaartenn");
    }

    private boolean keuzeBeheerKaarten() {
        System.out.println(labels.getString("keuzeBeheerKaarten"));
        return sc.nextInt() == 1; //EXCEPTION HANDLING VEREIST
    }

    private void VechtMonster() {
        //UC vechtMonster
    }

    private void voorbereidenGevecht() {
        dc.registreerKeuzeHulp(keuzeHulp());
        lusSpeelKaart();
        if (dc.wiltHulp()){
            while (!dc.iedereenHeeftGeholpen()) {
                tegenSpelerHelptJa();
                dc.updateSpelerDieHelpt();
            }
        } else {
            while (!dc.iedereenHeeftGeholpen()) {
                tegenSpelerHelptNee();
                dc.updateSpelerDieHelpt();
            }
        }
        lusSpeelKaart();
        toonSpelSituatieBeknopt();
    }

    private void lusSpeelKaart(){
        while (dc.registreerSpeelKaart(keuzeSpeelKaart())){
            speelKaart(dc.getSpelerAanDeBeurt());
        }
    }

    private void tegenSpelerHelptNee() { // wanneer speler aan de beurt geen hulp wilt
        int keuze = keuzeHelpenNee();
        if (keuze == 1)
            spelerWerktTegen();
        else if (keuze == 2)
            spelerDoetNiets();
    }

    private void tegenSpelerHelptJa() { //Wanneer speler aan de beurt hulp wilt
        int keuze = keuzeHelpenJa();
        if (keuze == 1)
            spelerHelpt();
        else if (keuze == 2)
            spelerWerktTegen();
        else if (keuze == 3)
            spelerDoetNiets();
    }

    private void spelerDoetNiets() {
        System.out.println("Je beslist om niets te doen...");
    }

    private void spelerWerktTegen() {
        System.out.println("Je beslist om tegen te werken...");
        //speelt kaart ...
    }

    private void spelerHelpt() {
        dc.spelerHelptBijGevecht();
        System.out.println("Je beslist om te helpen...");
        // speelt kaart ...
    }

    private int keuzeHelpenJa() {
        System.out.println();
        System.out.println(dc.getNaamSpelerDieHelpt() + ": kies uit het menu.\n");
        System.out.print("1) Helpen door kaart(en) te spelen die de spelerzijde positief beïnvloeden of de monsterzijde negatief beïnvloeden\n" +
                "2) Tegenwerken door kaart(en) te spelen die de spelerzijde negatief beïnvloeden of de monsterzijde positief beïnvloeden\n" +
                "3) Niets doen\n");
        return sc.nextInt();
    }

    private int keuzeHelpenNee() {
        System.out.println();
        System.out.println(dc.getNaamSpelerDieHelpt() + ": kies uit het menu.\n");
        System.out.print("1) Tegenwerken door kaart(en) te spelen die de spelerzijde negatief beïnvloeden of de monsterzijde positief beïnvloeden\n" +
                "2) Niets doen\n");
        return sc.nextInt();
    }

    private void speelKaart(int spelerAanDeBeurt) {
        //UC Speel Kaart
    }

    private int keuzeSpeelKaart() {
        System.out.println();
        System.out.println(labels.getString("keuzeSpeelSpel"));
        return sc.nextInt();
    }

    private int keuzeHulp() {
        System.out.println();
        System.out.println(labels.getString("keuzeHulpMenu"));
        return sc.nextInt();
    }

    private void toonBovensteKaart() {
        System.out.println(labels.getString("deBovensteKaartIs") + ": \n");
        System.out.println(dc.trekBovensteKaart());
    }

    private int geefKeuzeTaal() {
        return sc.nextInt();
    }

    private void toonMenuTaal() {
        System.out.println("1) Kies Nederlands\n"
                + "2) Choose English\n"
                + "3) Choisir le français\n");
    }

    private String toonMenuSpeelSpel() {
        System.out.println();
        return labels.getString("kiesMenu");
    }

    private void instellenTaal(int keuze) {
        switch (keuze) {
            case 1: {
                locale = new Locale("");
                break;
            }
            case 2: {
                locale = new Locale("en");
                break;
            }
            case 3: {
                locale = new Locale("fr");
                break;
            }
            default:
                throw new IllegalArgumentException();
        }
    }

    private void toonOverzichtSpel() {
        System.out.println("----------" + labels.getString("spelOverzicht") + "----------");
        System.out.printf(labels.getString("hetIsAan") + ": %s\n", dc.getNaamSpelerAanDeBeurt());
        System.out.println("---------------------------------");
        for (int i = 0; i < dc.getAantalSpelersSpel(); i++) {
            toonOverzichtSpeler(i);
        }
    }

    private void toonOverzichtSpeler(int speler) {
        System.out.println();
        System.out.printf(labels.getString("naam") + ": %s\n", dc.getNaamSpeler(speler)); //toont naam speler
        System.out.printf(labels.getString("level") + ": %d\n", dc.getLevelSpeler(speler)); //toont level speler
        System.out.printf(labels.getString("geslacht") + ": %s\n", dc.getGeslachtSpeler(speler)); //toont geslacht speler
        System.out.println();
        System.out.printf(labels.getString("zichtbareKaarten") + ": %s\n", dc.getZichtbareKaartenSpeler(speler).equals("") ? labels.getString("geenZichtbareKaarten") : dc.getZichtbareKaartenSpeler(speler)); //toont de speler zijn zichtbare kaarten
        System.out.println("--------------------");
    }

    private void promptEnterKey() {
        System.out.println(labels.getString("drukEnterOmDoorTeGaan"));
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    private void toonSpelSituatieBeknopt(){
        String[] titelOverzichtSpelersGevecht = new String[]{"Naam", "Geslacht", "Niveau", "Helpt?"};
        String[][] tabel = dc.toonOverzichtSpelersGevecht();

        System.out.println("\nBeknopte Spelsituatie:");
        System.out.println("Het te vechten monster: \n");
        dc.trekBovensteKaart();

        //details gevechtsniveaus

        //details spelers
        System.out.println();

        for (int i = 0; i < 4; i++) {
            System.out.format("%-15s", titelOverzichtSpelersGevecht[i]);

        }
        System.out.println();

        for (int t = 0; t < dc.getAantalSpelersSpel(); t++) {
            for (int i=0;i < 4; i++) {
                System.out.format("%-15s", tabel[t][i]);
            }
            System.out.println();
        }
    }

}
