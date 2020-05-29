package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modele.Salle;
import modele.Site;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class SalleDAO extends DAO<Salle>{
    /**
     * create
     * @param object
     * @return
     */
    @Override
    public Salle create(Salle object) {
        try{
            //Si le site de la salle n'existe pas, on le crée			
            if(object.getSite().getId() == 0){
                SiteDAO siteDAO = new SiteDAO();
                object.setSite(siteDAO.create(object.getSite())); //On crée le site non existant dans la BDD et on le récup
            }
            //On insère les données de la nouvelle salle
            PreparedStatement requete = this.connect
                                        .prepareStatement(
                                                    "INSERT INTO salle (Nom, Capacite, ID_site)"+
                                                    "VALUES(?, ?, ?)"
                                        );
            requete.setString(1, object.getNom());
            requete.setInt(2, object.getCapacite());
            requete.setInt(3, object.getSite().getId());
            requete.executeUpdate();
            
            //On cherche la dernière clée enregistré dans la BDD pour la récupérer
            ResultSet result = connect.createStatement().executeQuery("SELECT MAX(ID) FROM salle");
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
    public boolean delete(Salle object) {
        return false;
    }

    /**
     * update
     * @param object
     * @return
     */
    @Override
    public Salle update(Salle object) {
        try {
 
                this.connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE salle SET Nom = '" + object.getNom() + "'"+
                    " , Capacite = '" + object.getCapacite() + "'"+
                    " WHERE ID = " + object.getId()
                 );
            object = this.find(object.getId());
                
            DAO<Site> siteDAO = new SiteDAO();
            Site sit = object.getSite();
            sit = siteDAO.find(sit.getId());
            siteDAO.update(sit);
                   
            
                
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return object;
    }
    
    /**
     * find
     * trouver salle via id
     * @param id
     * @return
     */
    @Override
    public Salle find(int id) {
        Salle salle = new Salle();      

        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();

            result = st.executeQuery("SELECT * FROM Salle\n" +
                                      "LEFT JOIN Site ON Salle.ID_site=Site.ID\n" +
                                      "WHERE Salle.ID = " + id);
            if(result.first())
            {
                salle.setId(result.getInt(1));//ID de groupe d'après la BDD quand on tape la requete dans phpmyadmin
                salle.setNom(result.getString("Nom"));
                salle.setCapacite(result.getInt("Capacite"));

                int cle = (result.getInt("ID_site")); //ID de promotion d'après la BDD quand on tape la requete dans phpmyadmin
                SiteDAO pDAO = new SiteDAO();
                salle.setSite(pDAO.find(cle));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        
        return salle;
    }
    
    /**
     * trouver toutes les salles
     * pour admin
     * @return
     */
    public ArrayList<Salle> findAllSalles()
    {
        ArrayList<Salle> salles = new ArrayList<>();
        try {
            ResultSet result=connect.createStatement().executeQuery("SELECT DISTINCT ID FROM salle ORDER BY ID"); // Récup tout salles
            
                while(result.next()) {
                    salles.add(find(result.getInt("ID")));
                }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return salles;
    }
    /**
     * Prend un String en paramètre et retourne une classe Salle 
     * il permet d'obtenir une salle en fonction du nom de la salle et du site, 
     * si rien n'est trouvé, il retourne 0
     * @param infos
     * @return 
     */
    public Salle findByName(String infos){
        int espace = infos.indexOf(" ");
        String salle = infos.substring(0,espace);
        String site = infos.substring(espace+1, infos.length());
        try {
            ResultSet result=connect.createStatement()
                                    .executeQuery("SELECT salle.ID FROM salle, site "
                                                + "WHERE salle.Nom = '"+salle+"' AND site.Nom = '"+site+"'"); // récup l'id de la salle
            if(result.first())
                return find(result.getInt("salle.ID"));
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return null;
    }
}