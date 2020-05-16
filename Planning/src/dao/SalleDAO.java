/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.*;
import modele.*;



public class SalleDAO extends DAO<Salle>{
  public SalleDAO(Connection conn) {
    super(conn);
  }
    @Override
    public boolean create(Salle object) {
        return false;
    }

    @Override
    public boolean delete(Salle object) {
        return false;
    }

    @Override
    public boolean update(Salle object) {
        return false;
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
                                "WHERE Salle.ID = "+id);
      if(result.first())
      {
          salle.setId(result.getInt(1));//ID de groupe d'après la BDD quand on tape la requete dans phpmyadmin
          salle.setNom(result.getString("Nom"));
          salle.setCapacite(result.getInt("Capacite"));
          
          int cle = (result.getInt("ID_site")); //ID de promotion d'après la BDD quand on tape la requete dans phpmyadmin
          Site site;
          SiteDAO pDAO = new SiteDAO(connect);
          site = pDAO.find(cle);
          salle.setSite(site);
      }
                 
    }catch (SQLException e) {
      e.printStackTrace();
      System.out.println("pas trouvé");
    }
    return salle;
  }
}