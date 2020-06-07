package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modele.TypeCours;
import java.util.ArrayList;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class TypeCoursDAO extends DAO<TypeCours> {
    /**
     * create
     * @param object Type de cours à créer dans la BDD
     * @return Retourne le type de cours créé
     */
    @Override
    public TypeCours create(TypeCours object) {
        try{
            //On insère les nouvelles données dans la BDD
            PreparedStatement requete = this.connect
                                            .prepareStatement(
                                                "INSERT INTO type_cours (Nom) VALUES(?)"
                                            );
            requete.setString(1, object.getNom());
            requete.executeUpdate();
            
            //On récupère l'Id
             ResultSet result = connect.createStatement().executeQuery("SELECT MAX(ID) FROM type_cours");
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
     * @param object Type de cours à supprimer dans la BDD
     * @return indique si supprimé ou pas
     */
    @Override
    public boolean delete(TypeCours object) {
        return false;
    }

    /**
     * update
     * @param object Type de couurs à mettre à jours dans la BDD
     * @return Retourne le type de cours qui a été mise à jours
     */
    @Override
    public TypeCours update(TypeCours object) {
        try {

            this.connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE type_cours SET Nom = '" + object.getNom() + "'"+
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
     * trouver type cours via id
     * @param id Id du type de cours
     * @return Retourne le type de cours trouvé
     */
    @Override
    public TypeCours find(int id) {
        TypeCours typecours = new TypeCours();      

        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();

            result = st.executeQuery("SELECT * FROM type_cours WHERE ID = " + id);
            if(result.first())
            {
                typecours.setId(result.getInt("ID"));
                typecours.setNom(result.getString("Nom"));
            }
        }
        catch (SQLException e) {
          e.printStackTrace();
          System.out.println("pas trouvé");
        }
        return typecours;
    }
    
    /**
     * trouver tous les types de cours
     * pour l'admin
     * @return Retourne tout les type de cours de la BDD
     */
    public ArrayList<TypeCours> findAllTypes()
    {
        ArrayList<TypeCours> tcours = new ArrayList<>();
        try {
            ResultSet result=connect.createStatement().executeQuery("SELECT DISTINCT ID FROM type_cours ORDER BY ID"); // Récup tout types
            
                while(result.next()) {
                    tcours.add(find(result.getInt("ID")));
                }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return tcours;
    }
    /**
     * Prend un String en paramètre et retourne une classe Type, 
     * il permet d'obtenir le type d'une cours en fonction de son nom, 
     * si rien n'est trouvé, il retourne 0
     * @param infos Nom du type de cours
     * @return Retourne le type de cours trouvé
     */
    public TypeCours findByName(String infos){
        try {
            ResultSet result=connect.createStatement()
                                    .executeQuery("SELECT type_cours.ID FROM type_cours "
                                                + "WHERE type_cours.Nom = '"+infos+"'"); // récup l'id du type
            if(result.first())
                return find(result.getInt("type_cours.ID"));
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return null;
    }
}