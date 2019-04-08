package domein;


public class Curse extends KerkerKaart {

    private String naam;
    private String omschrijving;
    private String specialEffect;
    private boolean heeftEffectOpSpeler = true;
    private int verliesAantalLevels;

    public Curse(String naam, String omschrijving, String specialEffect,int verliesAantalLevels) {
        super(naam);
        this.omschrijving = omschrijving;
        this.specialEffect = specialEffect;
        this.verliesAantalLevels = verliesAantalLevels;
    }

    public boolean getHeeftEffectOpSpeler() {
        return heeftEffectOpSpeler;
    }
    
    @Override
    public String toString(){
        return String.format("%s\n"
                + "%s\n"
                + "%s\n", getNaam(), omschrijving, specialEffect);
    }

    public int getVerliesAantalLevels (){
        return verliesAantalLevels;
    }
}
