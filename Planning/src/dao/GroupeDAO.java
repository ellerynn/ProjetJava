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


public class GroupeDAO extends DAO<Groupe> {
    @Override
    public Groupe create(Groupe object) {
        try{
            //Si la promotion n'existe pas, on le crée			
            if(object.getPromotion().getId() == 0){
                PromotionDAO promoDAO = new PromotionDAO();
                object.setPromotion(promoDAO.create(object.getPromotion())); //On crée le site non existant dans la BDD et on le récup
            }
            //On insère les données du nouveau groupe
            PreparedStatement requete = this.connect
                                        .prepareStatement(
                                                    "INSERT INTO groupe (Nom, ID_promotion)"+
                                                    "VALUES(?, ?)"
                                        );
            requete.setString(1, object.getNom());
            requete.setInt(2, object.getPromotion().getId());
            requete.executeUpdate();
            
            //On cherche la dernière clée enregistré dans la BDD pour la récupérer
            ResultSet result = connect.createStatement().executeQuery("SELECT MAX(ID) FROM groupe");
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
    public boolean delete(Groupe object) {
        return false;
    }

     @Override
    public Groupe update(Groupe object) {
        try {
 
                this.connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE groupe SET Nom = '" + object.getNom() + "'"+
                    " WHERE ID = " + object.getId()
                 );
            object = this.find(object.getId());
                
            DAO<Promotion> promotionDAO = new PromotionDAO();
            Promotion pro = object.getPromotion();
            pro = promotionDAO.find(pro.getId());
            promotionDAO.update(pro);
                  
            
                
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return object;
    }
    
    @Override
    public Groupe find(int id) {
        Groupe groupe = new Groupe();      
        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();

            result = st.executeQuery("SELECT * FROM groupe " +
                                     "LEFT JOIN promotion ON groupe.ID_promotion=promotion.ID\n" +
                                      "WHERE groupe.ID = " + id);
            
            if(result.first())
            {
                groupe.setId(result.getInt("ID")); //ID de groupe d'après la BDD quand on tape la requete dans phpmyadmin
                groupe.setNom(result.getString("Nom"));

                int cle = result.getInt("ID_promotion"); //ID de promotion d'après la BDD quand on tape la requete dans phpmyadmin
                
                PromotionDAO pDAO = new PromotionDAO();
                groupe.setPromotion(pDAO.find(cle));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        
        return groupe;
    }
}