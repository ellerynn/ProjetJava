/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modele.Langage;

/**
 *
 * @author Emilie
 */
public class LangageDAO extends DAO<Langage> {
    @Override
    public Langage create(Langage obj) {
        try {
                PreparedStatement prepare = this.connect
                                            .prepareStatement(
                                                "INSERT INTO langage (lan_nom) VALUES(?)"
                                            );
                prepare.setString(1, obj.getNom());
                prepare.executeUpdate();
                
                ResultSet result = this.connect //Cherche la derniere clée enregistrée
                                    .createStatement(
                                    		ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                    		ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                    		"SELECT MAX(lan_id) FROM langage"
                                    );
                if (result.first()){
                    obj = this.find(result.getLong("MAX(lan_id)")); //Probleme
                }	
                
    } catch (SQLException e) {
            e.printStackTrace();
    }
    return obj;
    }

    @Override
    public Langage find(long id) 
    {
        Langage lang = new Langage();
        try {
            ResultSet result = this.connect
                                    .createStatement(
                                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                ResultSet.CONCUR_UPDATABLE
                                             ).executeQuery(
                                                "SELECT * FROM langage WHERE lan_id = " + id
                                             );
            if(result.first()){
                lang = new Langage(
                                id, 
                                result.getString("lan_nom") 
                            );
        }
        } catch (SQLException e) {
                e.printStackTrace();
        }
       return lang;

    }
	
	
    @Override
    public Langage update(Langage obj) {
        try {

            this.connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE langage SET lan_nom = '" + obj.getNom() + "'"+
                    " WHERE lan_id = " + obj.getId()
                 );
                obj = this.find(obj.getId());
                
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return obj;
    }

    @Override
    public void delete(Langage obj) {
        try {

            this.connect
                    .createStatement(
                         ResultSet.TYPE_SCROLL_INSENSITIVE, 
                         ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                         "DELETE FROM langage WHERE lan_id = " + obj.getId()
                    );
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
}
