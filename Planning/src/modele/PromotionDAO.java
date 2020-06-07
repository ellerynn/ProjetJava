package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modele.Promotion;
import java.util.ArrayList;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class PromotionDAO extends DAO<Promotion> {
    /**
     * create
     * @param object Promotion à créer dans la BDD
     * @return Retourne la promotion créé
     */
    @Override
    public Promotion create(Promotion object) {
        try{
            //On insère les données dans la BDD
            PreparedStatement requete = this.connect
                                            .prepareStatement(
                                                "INSERT INTO promotion (Nom) VALUES(?)"
                                            );
            requete.setString(1, object.getNom());
            requete.executeUpdate();
            
            //On récupère l'id de la BDD
            ResultSet result = connect.createStatement().executeQuery("SELECT MAX(ID) FROM promotion");
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
     * @param object Promotion à supprimer de la BDD
     * @return Retourne un boolean indiquant si supprimer ou pas de la BDD
     */
    @Override
    public boolean delete(Promotion object) {
        return false;
    }
    
    /**
     * upadte
     * @param object Promotion à mettre à jours dans la BDD
     * @return Retourne la promotion qui a été mise à jours
     */
    @Override
    public Promotion update(Promotion object) {
        try {

            this.connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE promotion SET Nom = '" + object.getNom() + "'"+
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
     * trouver promo via id
     * @param id Id de la promotion à trouver
     * @return Retourne la promotion trouvé
     */
    @Override
    public Promotion find(int id) {
        Promotion promotion = new Promotion();      

        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();

            result = st.executeQuery("SELECT * FROM promotion WHERE ID = " + id);
            if(result.first())
            {
                promotion.setId(result.getInt("ID"));
                promotion.setNom(result.getString("Nom"));
            }
        }
        catch (SQLException e) {
          e.printStackTrace();
          System.out.println("pas trouvé");
        }
        return promotion;
      }
    
    /**
     * trouver promo via nom
     * @param promo Infos en String de la promotion à trouver
     * @return Retourne la promotion trouvée
     */
    public Promotion findByName(String promo) {
        Promotion promotion = new Promotion();      

        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();

            result = st.executeQuery("SELECT * FROM promotion WHERE Nom = '" + promo + "'");
            if(result.first())
            {
                promotion.setId(result.getInt("ID"));
                promotion.setNom(result.getString("Nom"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return promotion;
    }
    
    /**
     * trouver toutes les promo
     * pour l'admin
     * @return Retourne tout les promotions de la BDD
     */
    public ArrayList<Promotion> findAllPromo()
    {
        ArrayList<Promotion> promos = new ArrayList<>();
        try {
            ResultSet result=connect.createStatement().executeQuery("SELECT DISTINCT ID FROM Promotion ORDER BY ID"); // Récup tout promo
            
                while(result.next()) {
                    promos.add(find(result.getInt("ID")));
                }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return promos;
    }
}