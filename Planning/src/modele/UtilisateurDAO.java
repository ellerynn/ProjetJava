package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modele.Utilisateur;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class UtilisateurDAO extends DAO<Utilisateur> {
    /**
     * create
     * @param object Utilisateur à créer dans la BDD
     * @return Retourne l'utilisateur créé dans la BDD
     */
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

    /**
     * delete
     * @param object Utilisateur à supprimer de la BDD
     * @return indique si supprimé ou pas
     */
    @Override
    public boolean delete(Utilisateur object) {
        return false;
    }

    /**
     * update
     * @param object Utilisateur à mettre à jours dans la BDD
     * @return Retourne l'utilisateur qui a été mise à jours
     */
    @Override
    public Utilisateur update(Utilisateur object) {
        return object;
    }
    
    /**
     * find
     * trouver utilisateur via id
     * @param id Id de l'utilisateur
     * @return Retourne l'utilisateur trouvé
     */
    @Override
    public Utilisateur find(int id) {
        Utilisateur utilisateur = new Utilisateur();      
        String maRequete = "SELECT * FROM utilisateur WHERE ID = " + id;
        requeteFind(maRequete, utilisateur);
        return utilisateur;
    }
      
    /**
     * Trouve l'utilisateur à partir de son mail et mot de passe
     * @param email string email de l'utilisateur
     * @param psw string mot de passe de l'utilisateur
     * @return utilisateur via email et password
     */
    public Utilisateur find(String email, String psw) {
        Utilisateur utilisateur = new Utilisateur();      
        String maRequete = "SELECT * FROM utilisateur WHERE Email = '" + email + "' AND Passwd = '" +psw+"'";
        requeteFind(maRequete, utilisateur);
        return utilisateur;  
    }
    
    /**
     * Trouve l'utilisateur à partir de prénom et nom
     * @param prenom string prenom de l'utilisateur
     * @param nom string nom de l'utilisateur
     * @return utilisateur via nom et prenom
     */
    public Utilisateur findByName(String prenom, String nom) {
        Utilisateur utilisateur = new Utilisateur();      
        String maRequete = "SELECT * FROM utilisateur WHERE Prenom = '" + prenom + "' AND Nom = '" + nom +"'";
        requeteFind(maRequete, utilisateur);
        return utilisateur;  
    }
    /**
     * Trouve l'utilisateur à partir d'une recherche
     * @param recherche donnée saisie à partir d'une barre de recherche
     * @return Retourne l'utilisateur trouvé
     */
    public Utilisateur findForSearch(String recherche) {
        Utilisateur utilisateur = new Utilisateur();      
        String maRequete = "SELECT * FROM utilisateur WHERE Prenom LIKE '" + recherche + "' OR Nom LIKE '" + recherche +"'";
        requeteFind(maRequete, utilisateur);
        return utilisateur;
    }
    
    /**
     * trouver tous les utilisateurs
     * @return Retourne tout les utilisateurs
     */
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
    
    /**
     * lance une requête SQ et evite des duplicatats
     * @param requete la requete sql
     * @param utilisateur l'utilisateur qui récupère les données trouvés
     */
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
    
    /**
     * Récupère le nombre maximum d'utilisateur dans la BDD
     * @return Retourne le nombre maximum
     */
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