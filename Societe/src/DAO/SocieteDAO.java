/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.*;
import java.util.ArrayList;
import modele.Developpeur;
import modele.Societe;

/**
 *
 * @author Emilie
 */
public class SocieteDAO extends DAO<Societe> {

	
    @Override
    public Societe create(Societe obj) {
        try{

            PreparedStatement prepare = this.connect
                                .prepareStatement(
                                            "INSERT INTO societe (soc_nom)"+
                                            "VALUES(?)"
                                    );
            prepare.setString(1, obj.getNom());
            prepare.executeUpdate();
            
            ResultSet result = this.connect //Cherche la derniere clée enregistrée
                                    .createStatement(
                                    		ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                    		ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                    		"SELECT MAX(soc_id) FROM societe"
                                    );
            if (result.first())
            {
                //Maintenant, nous devons créer les liens vers les développeurs
                //Si le développeur n'existe pas en base, on le créé
                for(Developpeur dev : obj.getListDeveloppeur()){
                        if(dev.getId() == 0){
                                DAO<Developpeur> developpeurDAO = AbstractDAOFactory.getFactory(FactoryType.DAO_FACTORY)
                                                                             .getDeveloppeurDAO(); //Equivalent à new developpeurDAO();
                                dev = developpeurDAO.create(dev);
                        }
                        PreparedStatement prepare2 = this.connect
                                    .prepareStatement(
                                                "INSERT INTO j_soc_dev (jsd_soc_k, jsd_dev_k)"+
                                                " VALUES(?, ?)"
                                        );
                        prepare2.setLong(1, result.getLong("MAX(soc_id)")); //Probleme
                        prepare2.setLong(2, dev.getId());
                        prepare2.executeUpdate();
                        obj = this.find(result.getLong("MAX(soc_id)"));
                }
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return obj;
    }

    @Override
    public Societe find(long id) {
            Societe societe = new Societe();                

    try {
            ResultSet result = this .connect
                                    .createStatement(
                                         ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                         ResultSet.CONCUR_UPDATABLE
                                    ).executeQuery(
                                        "select * from societe "+
                                        " left join j_soc_dev on jsd_soc_k = soc_id AND soc_id = "+ id +
                                        " inner join developpeur on jsd_dev_k = dev_id"
                                    );

            if(result.first()){
                DeveloppeurDAO devDao = new DeveloppeurDAO();
                ArrayList<Developpeur> listDeveloppeur = new ArrayList<Developpeur>();
                result.beforeFirst();
                //while(result.next() && result.getLong("jsd_dev_k") != 0) Probleme
                while (result.next())
                    listDeveloppeur.add(devDao.find(result.getLong("jsd_dev_k")));

                result.first();
                societe = new Societe(id, result.getString("soc_nom"), listDeveloppeur);
            }
    } catch (SQLException e) {
            e.printStackTrace();
    }
    return societe;

    }
    @Override
    public Societe update(Societe obj) {

        try{
            //On met à jour une partie de societe
            PreparedStatement prepare = this .connect
                                        .prepareStatement(
                                        "UPDATE societe SET soc_nom = '"+ obj.getNom() +"'"+
                                        " WHERE soc_id = " + obj.getId()
                                );

            prepare.executeUpdate();
            
            //On met à jours la liste des développeurs au cas ou
            //Maintenant, nous devons créer les liens vers les développeurs
            //Si le développeur n'existe pas en base, on le créé
            for(Developpeur dev : obj.getListDeveloppeur())
            {
                    DAO<Developpeur> developpeurDAO = AbstractDAOFactory.getFactory(FactoryType.DAO_FACTORY)
                                                                        .getDeveloppeurDAO(); //Equivalent new developpeurDAO()

                    //Si l'objet n'existe pas, on le créé avec sa jointure
                    if(dev.getId() == 0){
                            dev = developpeurDAO.create(dev);
                            PreparedStatement prepare2 = this .connect
                                                        .prepareStatement(
                                                        "INSERT INTO j_soc_dev (jsd_soc_k, jsd_dev_k)"+
                                                        "VALUES(?, ?)"
                                        );
                            prepare2.setLong(1, obj.getId());
                            prepare2.setLong(2, dev.getId());
                            prepare2.executeUpdate();
                    }
                    else{
                            developpeurDAO.update(dev);
                    }

            }
            obj = this.find(obj.getId());
        }catch(SQLException e){
                e.printStackTrace();
        }

        return obj;

    }

    @Override
    public void delete(Societe obj) {
        try{
                this.connect
                    .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                    "DELETE FROM j_soc_dev WHERE jsd_soc_k = " + obj.getId()
                    );
                
                this.connect
                    .createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE
                    ).executeUpdate(
                    "DELETE FROM societe WHERE soc_id = " + obj.getId()
                    );
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
	
}
