/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.*;
import modele.Cours;

public class CoursDAO extends DAO<Cours> {
    @Override
    public Cours create(Cours object) {
        return object;
    }

    @Override
    public boolean delete(Cours object) {
        return false;
    }

    @Override
    public Cours update(Cours object) {
        try {

            this.connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE cours SET Nom = '" + object.getNom() + "'"+
                    " WHERE ID = " + object.getId()
                 );
                object = this.find(object.getId());
                
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return object;
    }
    
    @Override
    public Cours find(int id) {
        Cours cours = new Cours();      

        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();

            result = st.executeQuery("SELECT * FROM cours WHERE ID = " + id);
            if(result.first())
            {
                cours.setId(result.getInt("ID"));
                cours.setNom(result.getString("Nom"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return cours;
    }
}