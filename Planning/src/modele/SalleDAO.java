package modele;

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
     * @param object Salle à créer dans la BDD
     * @return Retourne la salle créée
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
     * @param object Salle à supprimer de la BDD
     * @return Retourne si salle créer ou pas
     */
    @Override
    public boolean delete(Salle object) {
        return false;
    }

    /**
     * update
     * @param object Salle à mettre à jours dans la BDD
     * @return Salle qui a été mise à jours
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
     * @param id Id de la salle
     * @return Retourne la salle trouvée
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
     * @return Retourne toute les salles trouvés de la BDD
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
     * @param infos Données en String d'une salle (nom et nom du site)
     * @return Retourne la salle trouvée
     */
    public Salle findByName(String infos) {
        int espace = infos.indexOf(" ");
        
        if(espace != -1) {
            String salle = infos.substring(0,espace);
            String site = infos.substring(espace+1, infos.length());
            
            try {
                ResultSet result=connect.createStatement()
                                        .executeQuery("SELECT salle.ID FROM salle "
                                                    + "LEFT JOIN site ON site.ID = salle.ID_site "
                                                    + "WHERE salle.Nom = '"+salle+"' AND site.Nom = '"+site+"'"); // récup l'id de la salle
                if(result.first())
                    return find(result.getInt("salle.ID"));
                
                else { //Si les infos sont saisies dans le desordre = salle = site et site = salle
                    result=connect.createStatement()
                                        .executeQuery("SELECT salle.ID FROM salle "
                                                    + "LEFT JOIN site ON site.ID = salle.ID_site "
                                                    + "WHERE salle.Nom = '"+site+"' AND site.Nom = '"+salle+"'"); // récup l'id de la salle
                if(result.first())
                    return find(result.getInt("salle.ID"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                System.out.println("pas trouvé");
            }
            
        }
        return null;
    }
    
    /**
     * Méthode qui permet de savoir si une salle est libre ou pas dans un horaire
     * @param id_salle id de la salle
     * @param horaire horaire à vérifier
     * @param date Date à vérifier
     * @return boolean indiquant si cette salle libre ou pas
     */
    public Boolean estLibre(int id_salle, String horaire, String date) {
        try {
            ResultSet maRequete = this.connect.createStatement()
                    .executeQuery("SELECT * FROM seance\n" +
                                "LEFT JOIN seance_salles SS ON seance.ID = SS.ID_seance\n" +
                                "WHERE SS.ID_salle = "+id_salle+" AND seance.Date = '"+ date +"' "+
                                "AND (seance.Heure_fin > '"+horaire+"' AND seance.Heure_debut < '"+horaire+"') ");
            if(maRequete.first())
                return false; //Des séances sont éxistant dans le créneau 
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
        return true;
    }
}