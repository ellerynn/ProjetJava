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