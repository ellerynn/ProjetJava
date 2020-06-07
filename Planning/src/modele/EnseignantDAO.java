package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modele.Enseignant;
import modele.Utilisateur;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */

public class EnseignantDAO extends DAO<Enseignant> {
    /**
     * create
     * @param object L'enseignant à créer dans la BDD
     * @return Retourne l'enseignant trouvé
     */
    @Override
    public Enseignant create(Enseignant object) {
        try{
            
            if (!object.getCours().isEmpty())
            {
                //Si l'utilisateur n'existe pas, on le crée			
                if(object.getId() == 0){
                    UtilisateurDAO userDAO= new UtilisateurDAO();
                    object.copierUtilisateur(userDAO.create(object.getUtilisateur())); //On crée le site non existant dans la BDD et on le récup
                }
                //On insère les données de la nouvelle pour chaque cours (MATIERE)
                for (int i = 0 ; i < object.getCours().size() ;i++)
                {   //Si cours(MATIERE) non existant:
                    if(object.getCours().get(i).getId() == 0)
                    {   //On le crée et on écrase dans l'array
                        CoursDAO coursDAO = new CoursDAO();
                        object.getCours().set(i, coursDAO.create(object.getCours().get(i)));
                    }
                    ResultSet result = connect.createStatement()
                                             .executeQuery("SELECT * FROM enseignant WHERE ID_utilisateur = "+object.getId()+
                                                           " AND ID_cours = "+object.getCours().get(i).getId());
                    if (!result.first())
                    {
                       PreparedStatement requete = this.connect
                                               .prepareStatement(
                                                           "INSERT INTO enseignant (ID_utilisateur, ID_cours)"+
                                                           "VALUES(?, ?)"
                                               );
                       requete.setInt(1, object.getId());
                       requete.setInt(2, object.getCours().get(i).getId());
                       requete.executeUpdate(); 
                    }
                }
                    //On récupère tout les données lié à cette objet pour être sûr qu'on a tous
                    object = this.find(object.getId()); //Pas besoin de vérifier l'id car on l'a déjà grâce à la création de Utilisateur
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
            
        return object;
    }

    /**
     * delete
     * @param object Enseignant à supprimer de la BDD
     * @return Retourne un booléan indiquant si l'enseignant est supprimé ou pas
     */
    @Override
    public boolean delete(Enseignant object) {
        return false;
    }
    
    /**
     * update
     * @param object Enseignant à mettre à jours dans la BDD
     * @return Retourne l'enseignant qui a été mise à jours
     */
    @Override
    public Enseignant update(Enseignant object) {
        return object;
    }

    /**
     * find
     * trouver enseignant via id
     * @param id Id de l'enseignant
     * @return Retourne l'enseignant trouvé
     */
    @Override
    public Enseignant find(int id) {
        Enseignant enseignant = new Enseignant();      

        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();

            result = st.executeQuery("SELECT * FROM Utilisateur\n" +
"                                      LEFT JOIN enseignant ON utilisateur.ID = enseignant.ID_utilisateur\n" +
"                                      LEFT JOIN Cours ON Enseignant.ID_cours=Cours.ID\n" +
"                                      WHERE Enseignant.ID_utilisateur = "+id);
            
            if(result.first()) {
                //recuperation données utilisateur
                int cle = result.getInt("ID");//ID de groupe d'après la BDD quand on tape la requete dans phpmyadmin
                UtilisateurDAO userDAO = new UtilisateurDAO();
                Utilisateur user = userDAO.find(cle);
                enseignant.copierUtilisateur(user);//copie de utilisateur dans enseignant pour pouvoir les afficher

                result.beforeFirst(); // retourne à la première ligne
                CoursDAO cDAO = new CoursDAO();
                while(result.next()) {
                    enseignant.ajouterCours(cDAO.find(result.getInt("ID_cours")));
                }  
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return enseignant;
    }
    
    /**
     * trouver tous les enseignants
     * pour l'admin
     * @return Retourne l'ensemble des enseignants trouvés
     */
    public ArrayList<Enseignant> findAllTeacher()
    {
        ArrayList<Enseignant> enseignants = new ArrayList<>();
        try {
            ResultSet result=connect.createStatement().executeQuery("SELECT DISTINCT ID_utilisateur FROM Enseignant ORDER BY ID_utilisateur"); // Récup tout ensengnants
            
                while(result.next()) {
                    enseignants.add(find(result.getInt("ID_utilisateur")));
                }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return enseignants;
    }
    
    /**
     * Prend un String en paramètre et retourne une classe Enseignant
     * il permet d'obtenir l'enseignant en fonction de son prenom et nom dans un seul string
     * @param infos Le Prenom et nom de l'enseignant
     * @return Retourne l'enseignant trouvé
     */
    public Enseignant findByName(String infos){
        UtilisateurDAO userD= new UtilisateurDAO();
        int espace = infos.indexOf(" ");
        String prenom = infos.substring(0,espace);
        String nom = infos.substring(espace+1, infos.length());
        return find(userD.findByName(prenom, nom).getId());
    }
}