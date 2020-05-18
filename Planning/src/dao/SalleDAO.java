package dao;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import modele.*;

public class SalleDAO extends DAO<Salle>{
    @Override
    public Salle create(Salle object) {
        return object;
    }

    @Override
    public boolean delete(Salle object) {
        return false;
    }

    @Override
    public Salle update(Salle object) {
        return object;
    }
    
    public Salle find(int id) {
        Salle salle = new Salle();      

        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();

            result = st.executeQuery("SELECT * FROM Salle\n" +
                                      "LEFT JOIN Site ON Salle.ID_site=Site.ID\n" +
                                      "WHERE Salle.ID = " + id);
            if(result.first())
            {
                salle.setId(result.getInt(1));//ID de groupe d'après la BDD quand on tape la requete dans phpmyadmin
                salle.setNom(result.getString("Nom"));
                salle.setCapacite(result.getInt("Capacite"));

                int cle = (result.getInt("ID_site")); //ID de promotion d'après la BDD quand on tape la requete dans phpmyadmin
                SiteDAO pDAO = new SiteDAO();
                salle.setSite(pDAO.find(cle));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        
        return salle;
    }
}