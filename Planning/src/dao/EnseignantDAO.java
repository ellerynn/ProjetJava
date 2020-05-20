/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.*;
import modele.*;


public class EnseignantDAO extends DAO<Enseignant> {
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

    @Override
    public boolean delete(Enseignant object) {
        return false;
    }

    @Override
    public Enseignant update(Enseignant object) {
        return object;
    }

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

            //A REUTILISER CAR BOUCLE INFINI AVEC SEANCE DAO, DONC FAIRE CE DANS LE CONTROLEUR
            
            
            /*ResultSet resultSeances = st.executeQuery("SELECT ID_seance FROM Seance_enseignants WHERE ID_enseignant = "+id);
            
"SELECT * FROM Utilisateur\n" +
"LEFT JOIN enseignant ON utilisateur.ID = enseignant.ID_utilisateur\n" +
"LEFT JOIN Cours ON Enseignant.ID_cours=Cours.ID\n" +
"LEFT JOIN seance_enseignants ON Enseignant.ID_utilisateur = seance_enseignants.ID_enseignant "+
"WHERE Enseignant.ID_utilisateur = " + id
            
            if(resultSeances.first()) {
                if (resultSeances.getInt("ID_seance") != 0) {
                    SeanceDAO sDAO = new SeanceDAO(connect);
                    enseignant.addSeances(sDAO.find(resultSeances.getInt("ID_seance")));
                    while(resultSeances.next()) {
                        enseignant.addSeances(sDAO.find(resultSeances.getInt("ID_seance")));
                    } 
                }
            }*/

        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return enseignant;
    }
}