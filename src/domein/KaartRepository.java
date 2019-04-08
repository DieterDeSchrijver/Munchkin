/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistentie.KaartMapper;

/**
 *
 * @author diete
 */
public class KaartRepository {

    private final KaartMapper mapper;
    private List<Kaart> kaarten;

    public KaartRepository() throws SQLException {
        mapper = new KaartMapper();
        kaarten = mapper.geefAlleKaarten();
    }

    public List<Kaart> getKaarten() {
        return kaarten;
    }

    List<Kaart> geefAlleKaarten() {
        return this.kaarten;

    }
}
