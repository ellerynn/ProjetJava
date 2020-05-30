package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modele.Groupe;
import modele.Promotion;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class GroupeDAO extends DAO<Groupe> {
    /**
     * create
     * @param object
     * @return
     */
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

    /**
     * delete
     * @param object
     * @return
     */
    @Override
    public boolean delete(Groupe object) {
        return false;
    }
    
    /**
     * update
     * @param object
     * @return
     */
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
    
    /**
     * find
     * trouver groupe via id
     * @param id
     * @return
     */
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
    
    /**
     * pour éviter le duplicatat de code
     * @param requete
     * @param groupe
     */
    public void requeteFind(String requete, Groupe groupe) {
        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();
            result = st.executeQuery(requete);
            
            if(result.first())
            {
                groupe.setId(result.getInt("ID"));
                groupe.setNom(result.getString("Nom"));
                
                PromotionDAO pDAO = new PromotionDAO();
                Promotion p = pDAO.find(groupe.getPromotion().getId());
                
                groupe.setPromotion(p);
            }    
            
            else
                System.out.println("Aucun utilisateur");
        }
        catch (SQLException e) {
          e.printStackTrace();
          System.out.println("pas trouvé");
        }
    }
    
    /**
     * trouver groupe via nom du groupe et id promo
     * @param groupe
     * @param promo
     * @return
     */
    public Groupe findByNameAndPromo(String groupe, int promo) {
        Groupe g = new Groupe();      
        String maRequete = "SELECT * FROM groupe WHERE Nom = '" + groupe + "' AND ID_promotion = " + promo;
        g.getPromotion().setId(promo);
        requeteFind(maRequete, g);
        return g;  
    }
    
    /**
     * trouver tous les groupes 
     * @return
     */
    public ArrayList<Groupe> find() {
        ArrayList<Groupe> groupes = new ArrayList<>();
        Groupe group;
        
        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();
            
            //On récupère les champs des la table Séance
            result = st.executeQuery("SELECT * FROM groupe"); // Pour récupérer les champs de groupe
            
            if(result.first())
            {
                result.beforeFirst();
                while(result.next()) {
                    GroupeDAO gDAO = new GroupeDAO();
                    group = gDAO.find(result.getInt("ID"));
                      /*                  
                    PromotionDAO pDAO = new PromotionDAO();
                    Promotion promo = pDAO.find(result.getInt("ID_promotion"));
                    group.setPromotion(promo);
                                        */
                    groupes.add(group);
                }
            }
            
            /*for(Groupe s : groupes)
                System.out.println("groupe " + s.getNom() + " promo " + s.getPromotion().getNom());*/
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé dans find all utilisateurs");
        }
               
        return groupes;
    }
    /**
     * Prend un String en paramètre et une classe Groupe, 
     * il permet d'obtenir un groupe en fonction du nom du groupe et de sa promotion
     * @param infos
     * @return 
     */
    public Groupe findByName(String infos){
        int espace = infos.indexOf(" ");
        String td = infos.substring(0,espace);
        String promo = infos.substring(espace+1, infos.length());
        try {
            ResultSet result=connect.createStatement()
                                    .executeQuery("SELECT groupe.ID FROM groupe "
                                                + "LEFT JOIN promotion P ON P.ID = groupe.ID_promotion "
                                                + "WHERE groupe.Nom = '"+td+"' AND P.Nom = '"+promo+"'"); // récup l'id du groupe
            if(result.first())
                return find(result.getInt("groupe.ID"));
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return null;
    }
}