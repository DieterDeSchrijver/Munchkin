//package domein;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class SpelerRepository {
//    
//    private Speler eersteSpeler;
//    List<Speler> spelers = new ArrayList<>();
//
//    public SpelerRepository() {
//
//    }
//
//    public String overzichtSpelers() {
//        String output = "";
//        for (Speler speler : spelers) {
//            output += String.format("%s\n", speler.toString());
//        }
//        return output;
//    }
//
//    public void voegSpelerToe(Speler speler) {
//        spelers.add(speler);
//    }
//
//    public void controleerNaamUniek(String naam) {
//        for (Speler speler : spelers) {
//            if (naam.toLowerCase().equals(speler.getNaam().toLowerCase())) {
//                throw new IllegalArgumentException("Deze naam is niet uniek!");
//            }
//        }
//    }
//    
//    public Speler bepaalEersteSpeler() {
//        Speler eersteSpeler;
//        eersteSpeler = spelers.get(0);
//        for (Speler speler : spelers) {
//            if(speler.getNaam().length() < eersteSpeler.getNaam().length()){
//                eersteSpeler = speler;              
//            }else if(speler.getNaam().length() == eersteSpeler.getNaam().length()) {
//                    if(speler.getNaam().compareToIgnoreCase(eersteSpeler.getNaam()) > 0){
//                        eersteSpeler = speler;                        
//                    }
//            }
//        }
//        this.eersteSpeler = eersteSpeler;
//        return eersteSpeler;
//    }
//    
//    public void ordenSpelers(){
//        List<Speler> toRemove = new ArrayList<Speler>();
//        for (Speler speler : spelers) {
//            if(speler.getNaam().equals(eersteSpeler.getNaam())){
//                toRemove.add(speler);
//            }
//        }
//        spelers.removeAll(toRemove);
//        spelers.add(0, eersteSpeler);
//    }
//
//}
