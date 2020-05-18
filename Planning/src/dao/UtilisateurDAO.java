package dao;

import java.sql.Connection;
import java.sql.*;
import modele.Utilisateur;

public class UtilisateurDAO extends DAO<Utilisateur> {
    
    @Override
    public Utilisateur create(Utilisateur object) {
        return object;
    }

    @Override
    public boolean delete(Utilisateur object) {
        return false;
    }

    @Override
    public Utilisateur update(Utilisateur object) {
        return object;
    }
    
    @Override
    public Utilisateur find(int id) {
        Utilisateur utilisateur = new Utilisateur();      
        String maRequete = "SELECT * FROM utilisateur WHERE ID = " + id;
        requeteFind(maRequete, utilisateur);
        return utilisateur;
    }
    
    public Utilisateur find(String email, String psw) {
        Utilisateur utilisateur = new Utilisateur();      
        String maRequete = "SELECT * FROM utilisateur WHERE Email = '" + email + "' AND Passwd = '" +psw+"'";
        requeteFind(maRequete, utilisateur);
        return utilisateur;  
    }
    
    public void requeteFind(String requete, Utilisateur utilisateur) {
        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();
            result = st.executeQuery(requete);
            if(result.first())
            {
                utilisateur.setId(result.getInt("ID"));
                utilisateur.setEmail(result.getString("Email"));
                utilisateur.setPassword(result.getString("Passwd"));
                utilisateur.setNom(result.getString("Nom"));
                utilisateur.setPrenom(result.getString("Prenom"));
                utilisateur.setDroit(result.getInt("Droit"));
            }              
        }
        catch (SQLException e) {
          e.printStackTrace();
          System.out.println("pas trouvé");
        }
    }
    public int nombreMax(){
        int nb = 0;
        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();
            result = st.executeQuery("SELECT COUNT(ID) FROM utilisateur");
            if(result.first())
            {
                nb = result.getInt("COUNT(ID)");
            }              
        }
        catch (SQLException e) {
          e.printStackTrace();
          System.out.println("pas trouvé");
        }
        return nb;
    }
}