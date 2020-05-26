package dao;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import modele.Enseignant;
import modele.Etudiant;
import modele.Utilisateur;

public class UtilisateurDAO extends DAO<Utilisateur> {
    
    @Override
    public Utilisateur create(Utilisateur object) {
        try{
            //On insère les nouvelles données dans la BDD
            PreparedStatement requete = this.connect
                                            .prepareStatement(
                                                "INSERT INTO utilisateur (Email, Passwd, Nom, Prenom, Droit) VALUES(?,?,?,?,?)"
                                            );
            requete.setString(1, object.getEmail());
            requete.setString(2, object.getPassword());
            requete.setString(3, object.getNom());
            requete.setString(4, object.getPrenom());
            requete.setInt(5, object.getDroit());
            requete.executeUpdate();
            
            //On récupère l'Id
             ResultSet result = connect.createStatement().executeQuery("SELECT MAX(ID) FROM utilisateur");
             if (result.first())
             {
                 //On récupère tout les données lié à cette objet pour être sûr qu'on a tous
                 object = this.find(result.getInt("MAX(ID)"));
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    
    public Utilisateur findByName(String prenom, String nom) {
        Utilisateur utilisateur = new Utilisateur();      
        String maRequete = "SELECT * FROM utilisateur WHERE Prenom LIKE '%" + prenom + "%' AND Nom LIKE '%" + nom +"%'";
        requeteFind(maRequete, utilisateur);
        return utilisateur;  
    }
    
    public ArrayList<Utilisateur> find() {
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        Utilisateur user = new Utilisateur();
        
        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();
            
            //On récupère les champs des la table utilisateur
            result = st.executeQuery("SELECT * FROM Utilisateur"); // Pour récupérer les champs de user
            
            if(result.first())
            {
                result.beforeFirst();
                while(result.next()) {
                    UtilisateurDAO uDAO = new UtilisateurDAO();
                    user = uDAO.find(result.getInt("ID"));
                    
                    utilisateurs.add(user);
                }
                
                //for(int i=0;i<utilisateurs.size();i++)
                    //System.out.println("Ajout : " + utilisateurs.get(i).getId() + " " + utilisateurs.get(i).getPrenom() + " " + utilisateurs.get(i).getNom());
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé dans find all utilisateurs");
        }
               
        return utilisateurs;
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
            
            else
                System.out.println("Aucun utilisateur");
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
          System.out.println("Utilisateur pas trouvé");
        }
        return nb;
    }
}