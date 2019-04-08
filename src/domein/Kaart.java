package domein;


public class Kaart {

    private String naam;

    public Kaart(String naam) {
        this.naam = naam;
    }

    private void setNaam(String naam) {
        this.naam = naam;
    }

    public String getNaam() {
        return naam;
    }
    
    @Override
    public String toString(){
        return String.format("Naam: %s", naam);
    }


}
