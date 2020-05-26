package dao;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import modele.Site;

public class SiteDAO extends DAO<Site> {
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

    @Override
    public boolean delete(Site object) {
        return false;
    }

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
    /*methodes en plus pour ADMINISTRATEUR*/
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
}