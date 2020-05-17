/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.*;
import modele.Promotion;


public class PromotionDAO extends DAO<Promotion> {
    public PromotionDAO(Connection conn) {
      super(conn);
    }
    
    @Override
    public boolean create(Promotion object) {
        return false;
    }

    @Override
    public boolean delete(Promotion object) {
        return false;
    }

    @Override
    public boolean update(Promotion object) {
        return false;
    }
    
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
}