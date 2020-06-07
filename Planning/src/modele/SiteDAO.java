package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modele.Site;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class SiteDAO extends DAO<Site> {

    /**
     * create
     * @param object Site à créer dans la BDD
     * @return Retourne le site créé
     */
    @Override
    public Site create(Site object) {
        try{
            //On insère les données dans la BDD
            PreparedStatement requete = this.connect
                                            .prepareStatement(
                                                "INSERT INTO site (Nom) VALUES(?)"
                                            );
            requete.setString(1, object.getNom());
            requete.executeUpdate();
            
            //On recupère l'id
            ResultSet result = connect.createStatement().executeQuery("SELECT MAX(ID) FROM site");
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
     * @param object Site à supprimer de la BDD
     * @return Retourne si supprimé ou pas
     */
    @Override
    public boolean delete(Site object) {
        return false;
    }

    /**
     * update
     * @param object Site à mettre à jours dans la BDD
     * @return Retourne le site qui a été mise à jours
     */
    @Override
    public Site update(Site object) {
        try {

            this.connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE site SET Nom = '" + object.getNom() + "'"+
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
     * trouver un site via id
     * @param id Id du site
     * @return Retourne le site trouvé
     */
    @Override
    public Site find(int id) {
        Site site = new Site();      

        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();

            result = st.executeQuery("SELECT * FROM site WHERE ID = " + id);
            if(result.first())
            {
                site.setId(result.getInt("ID"));
                site.setNom(result.getString("Nom"));
            }
        }
        catch (SQLException e) {
          e.printStackTrace();
          System.out.println("pas trouvé");
        }
        
        return site;
    }
    
    /**
     * récupère tout les sites de la BDD
     * @return un ArrayList de Sites
     */
    public ArrayList<Site> findAllSites() //NE SERA PEUT ETRE JAMAIS USE (car salles possèdent dj l'info du site)
    {
        ArrayList<Site> sites = new ArrayList<>();
        try {
            ResultSet result=connect.createStatement().executeQuery("SELECT DISTINCT ID FROM site ORDER BY ID"); // Récup tout types
            
                while(result.next()) {
                    sites.add(find(result.getInt("ID")));
                }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return sites;
    }
    /**
     * Méthode qui récupère tout les noms de sites
     * @return Retourne tout les noms de sites 
     */
    public ArrayList<String> allSitesToString() {
        ArrayList<String> sites = new ArrayList<>();
        try {
            ResultSet result=connect.createStatement().executeQuery("SELECT DISTINCT ID FROM site ORDER BY ID"); // Récup tout types
            
                while(result.next()) {
                    sites.add(find(result.getInt("ID")).getNom());
                }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return sites;
    }
}