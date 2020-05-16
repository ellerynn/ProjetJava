/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.*;
import modele.Site;


public class SiteDAO extends DAO<Site>{
  public SiteDAO(Connection conn) {
    super(conn);
  }
    @Override
    public boolean create(Site object) {
        return false;
    }

    @Override
    public boolean delete(Site object) {
        return false;
    }

    @Override
    public boolean update(Site object) {
        return false;
    }
    
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
                 
    }catch (SQLException e) {
      e.printStackTrace();
      System.out.println("pas trouvé");
    }
    return site;
  }
}