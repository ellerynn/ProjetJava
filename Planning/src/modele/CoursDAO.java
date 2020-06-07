package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modele.Cours;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */

public class CoursDAO extends DAO<Cours> {
    /**
     * create
     * @param object Cours à créer dans la BDD
     * @return Retourne le cours créé
     */
    @Override
    public Cours create(Cours object) {
        try {
            //On insère les données dans la BDD
            PreparedStatement requete = this.connect
                                            .prepareStatement(
                                                "INSERT INTO cours (Nom) VALUES(?)"
                                            );
            requete.setString(1, object.getNom());
            requete.executeUpdate();
            
            //On récupère l'id
            ResultSet result = connect.createStatement().executeQuery("SELECT MAX(ID) FROM cours");
            if (result.first()) {
                //On récupère tout les données lié à cette objet pour être sûr qu'on a tous
                object = this.find(result.getInt("MAX(ID)")); //On récupère les nouvelles données
            }
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * delete
     * @param object Cours à supprimer dans la BDD
     * @return Retourne un booléan pour indiquer si le cours a été supprimé
     */
    @Override
    public boolean delete(Cours object) {
        return false;
    }

    /**
     * update
     * @param object Cours à mettre à jours dans la BDD
     * @return Retourne le cours qui a été mise à jours
     */
    @Override
    public Cours update(Cours object) {
        try {

            this.connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE cours SET Nom = '" + object.getNom() + "'"+
                    " WHERE ID = " + object.getId()
                 );
                object = this.find(object.getId());
                
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return object;
    }
    
    /**
     * find
     * trouver cours via ID
     * @param id Id du cours à trouver dans la BDD
     * @return Retourne le cours qui a été trouvé dans la BDD
     */
    @Override
    public Cours find(int id) {
        Cours cours = new Cours();      

        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();

            result = st.executeQuery("SELECT * FROM cours WHERE ID = " + id);
            if(result.first())
            {
                cours.setId(result.getInt("ID"));
                cours.setNom(result.getString("Nom"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return cours;
    }
    
    /**
     * trouver tous les cours
     * pour l'admin
     * @return Retourne la liste des cours de la BDD
     */
    public ArrayList<Cours> findAllCours()
    {
        ArrayList<Cours> cours = new ArrayList<>();
        try {
            ResultSet result=connect.createStatement().executeQuery("SELECT DISTINCT ID FROM cours ORDER BY ID"); // Récup tout cours
            
                while(result.next()) {
                    cours.add(find(result.getInt("ID")));
                }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return cours;
    }
    /**
     * Prend un String en paramètre et retourne une classe Cours 
     * il permet d'obtenir le cours en fonction de son nom 
     * @param infos Nom du cours
     * @return 
     */
    public Cours findByName(String infos){
        try {
            ResultSet result=connect.createStatement()
                                    .executeQuery("SELECT ID FROM cours "
                                                + "WHERE Nom = '"+infos+"'"); // récup l'id du type
            if(result.first())
                return find(result.getInt("ID"));
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return null;
    }
}