package dao;

import java.sql.Connection;
import java.sql.*;
import modele.Site;

public class SiteDAO extends DAO<Site> {
    @Override
    public Site create(Site object) {
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
}