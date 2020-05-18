/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import modele.Developpeur;
import modele.Langage;

/**
 *
 * @author Emilie
 */

public class DeveloppeurDAO extends DAO<Developpeur> {

    @Override
    public Developpeur create(Developpeur obj) {

        try 
        {

            //Si le langage n'existe pas en base, on le créé			
            if(obj.getLangage().getId() == 0){
                    DAO<Langage> langageDAO = AbstractDAOFactory.getFactory(FactoryType.DAO_FACTORY)
                                                                  .getLangageDAO(); //Equivalent à new LangageDAO();
                    obj.setLangage(langageDAO.create(obj.getLangage()));
            }
            PreparedStatement prepare = this.connect
                                        .prepareStatement(
                                                    "INSERT INTO developpeur (dev_nom, dev_prenom, dev_lan_k)"+
                                                    "VALUES(?, ?, ?)"
                                        );
            prepare.setString(1, obj.getNom());
            prepare.setString(2, obj.getPrenom());
            prepare.setLong(3, obj.getLangage().getId());
            prepare.executeUpdate();
                        
            ResultSet result = this.connect //Cherche la derniere clée enregistrée
                        .createStatement(
                                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                    ResultSet.CONCUR_UPDATABLE
                        ).executeQuery(
                                    "SELECT MAX(dev_id) FROM developpeur"
                        );
            if (result.first()){
                obj = this.find(result.getLong("MAX(dev_id)")); //Probleme
            }	   
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Developpeur find(long id) {

        Developpeur dev = new Developpeur();
        try 
        {
            ResultSet result = this.connect
                            .createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY
                             ).executeQuery(
                                "SELECT * FROM developpeur WHERE dev_id = " + id
                             );
            if(result.first())
            {
                dev = new Developpeur(
                                            id, 
                                            result.getString("dev_nom"), 
                                            result.getString("dev_prenom"),
                                            new LangageDAO().find(result.getLong("dev_lan_k"))
                                    );
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
           return dev;

    }
    
    @Override
    public Developpeur update(Developpeur obj) {

        try
        {	
            DAO<Langage> langageDAO = AbstractDAOFactory.getFactory(FactoryType.DAO_FACTORY)
                                                                   .getLangageDAO(); //Equivalent à new LangageDAO();
            //Si le langage n'existe pas en base, on le créé			
            if(obj.getLangage().getId() == 0){				
                    obj.setLangage(langageDAO.create(obj.getLangage()));
            }
            //On met à jours l'objet Langage
            langageDAO.update(obj.getLangage());

            this.connect	
                .createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                        "UPDATE developpeur SET dev_nom = '" + obj.getNom() + "',"+
                        " dev_prenom = '" + obj.getPrenom() + "',"+
                        " dev_lan_k = '" + obj.getLangage().getId() + "'"+
                        " WHERE dev_id = " + obj.getId()
                 );

                obj = this.find(obj.getId());
        } catch (SQLException e) {
                e.printStackTrace();
        }

            return obj;
    }
	

    @Override
    public void delete(Developpeur obj) {
        try {

            this.connect	
                .createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                        "DELETE FROM developpeur WHERE dev_id = " + obj.getId()
                 );

        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
	
}