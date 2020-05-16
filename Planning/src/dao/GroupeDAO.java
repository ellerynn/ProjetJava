/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.*;
import modele.Groupe;
import modele.Promotion;


public class GroupeDAO extends DAO<Groupe>{
  public GroupeDAO(Connection conn) {
    super(conn);
  }
    @Override
    public boolean create(Groupe object) {
        return false;
    }

    @Override
    public boolean delete(Groupe object) {
        return false;
    }

    @Override
    public boolean update(Groupe object) {
        return false;
    }
    
    public Groupe find(int id) {
    Groupe groupe = new Groupe();      

    try {
        Statement st;
        ResultSet result;
        //creation ordre SQL
        st = connect.createStatement();
            
      result = st.executeQuery("SELECT * FROM groupe\n" +
                                "LEFT JOIN promotion ON groupe.ID_promotion=promotion.ID\n" +
                                "WHERE groupe.ID = "+id);
      if(result.first())
      {
          groupe.setId(result.getInt(1));//ID de groupe d'après la BDD quand on tape la requete dans phpmyadmin
          groupe.setNom(result.getString("Nom"));
          
          int cle = result.getInt(4); //ID de promotion d'après la BDD quand on tape la requete dans phpmyadmin
          Promotion promo;
          PromotionDAO pDAO = new PromotionDAO(connect);
          promo = pDAO.find(cle);
          groupe.setPromotion(promo);
      }
                 
    }catch (SQLException e) {
      e.printStackTrace();
      System.out.println("pas trouvé");
    }
    return groupe;
  }
}