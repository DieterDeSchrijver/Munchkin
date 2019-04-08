package persistentie;

import domein.Curse;
import domein.Equipment;
import domein.Kaart;
import domein.KerkerConsumable;
import domein.Monster;
import domein.Race;
import domein.SchatConsumable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KaartMapper {
    public List<Kaart> geefAlleKaarten(){
        List<Kaart> kaarten = new ArrayList<>();
        
        
        //toevoegen Equipment kaarten
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g18.Equipment WHERE isActief = 1");
                ResultSet rs = query.executeQuery()) {
            
            while(rs.next())
            {
                String naam = rs.getString("naam");
                String omschrijving = rs.getString("omschrijving");
                int bonus = rs.getInt("bonus");
                String type = rs.getString("type");
                int value = rs.getInt("value");
                
                kaarten.add(new Equipment(naam, omschrijving, bonus, type, value));  
            }                
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        
        //toevoegen SchatConsumable kaarten
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g18.SchatConsumable WHERE isActief = 1");
                ResultSet rs = query.executeQuery()) {
            
            while(rs.next())
            {
                String naam = rs.getString("naam");
                String omschrijving = rs.getString("omschrijving");
                int value = rs.getInt("value");
                int levelUp = rs.getInt("levelUp");
                
                kaarten.add(new SchatConsumable(naam, omschrijving, value, levelUp));  
            }                
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        
        //toevoegen KerkerConsumable kaarten
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g18.KerkerConsumable WHERE isActief = 1");
                ResultSet rs = query.executeQuery()) {
            
            while(rs.next())
            {
                String naam = rs.getString("naam");
                String omschrijving = rs.getString("omschrijving");
                int bonus = rs.getInt("bonus");
                
                kaarten.add(new KerkerConsumable(naam, omschrijving, bonus));  
            }                
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        
        //toevoegen Curse kaarten
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g18.Curse WHERE isActief = 1");
                ResultSet rs = query.executeQuery()) {
            
            while(rs.next())
            {
                String naam = rs.getString("naam");
                String omschrijving = rs.getString("omschrijving");
                String specialEffect = rs.getString("specialEffect");
                int verliesAantalLevels = rs.getInt("verliesAantalLevels");
                
                kaarten.add(new Curse(naam, omschrijving, specialEffect, verliesAantalLevels));
            }                
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        
        //toevoegen Race kaarten
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g18.Race WHERE isActief = 1");
                ResultSet rs = query.executeQuery()) {
            
            while(rs.next())
            {
                String naam = rs.getString("naam");
                String specialEffect = rs.getString("specialEffect");
                
                kaarten.add(new Race(naam, specialEffect));  
            }                
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        //toevoegen Monster kaarten
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
        PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g18.Monster WHERE isActief = 1");
                ResultSet rs = query.executeQuery()) {
            
            while(rs.next())
            {
                String naam = rs.getString("naam");
                int levelMonster = rs.getInt("levelMonster");
                String badStuff = rs.getString("badStuff");
                int reward = rs.getInt("reward");
                String specialEffect = rs.getString("specialEffect");
                
                kaarten.add(new Monster(naam, levelMonster, badStuff, reward, specialEffect));  
            }                
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        
        return kaarten;
    }
    
}
            