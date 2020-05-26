package dao;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import modele.*;

public class EtudiantDAO extends DAO<Etudiant> {
    @Override
    public Etudiant create(Etudiant object) {
        try{
            //Si l'utilisateur n'existe pas, on le crée			
            if(object.getId() == 0){
                UtilisateurDAO userDAO= new UtilisateurDAO();
                object.copierUtilisateur(userDAO.create(object.getUtilisateur())); //On crée le site non existant dans la BDD et on le récup
            }
            //Si le groupe n'existe pas
            if(object.getGroupe().getId() == 0){
                GroupeDAO gDAO = new GroupeDAO();
                object.setGroupe(gDAO.create(object.getGroupe()));
            }
            
            //On insère les données de la nouvelle salle
            PreparedStatement requete = this.connect
                                        .prepareStatement(
                                                    "INSERT INTO etudiant (ID_utilisateur, Numero, ID_groupe)"+
                                                    "VALUES(?, ?, ?)"
                                        );
            requete.setInt(1, object.getId());
            requete.setInt(2, object.getNumero());
            requete.setInt(3, object.getGroupe().getId());
            requete.executeUpdate();
            
            //On cherche la dernière clée enregistré dans la BDD pour la récupérer
            ResultSet result = connect.createStatement().executeQuery("SELECT MAX(ID_utilisateur) FROM etudiant");
            if (result.first())
            {
                //On récupère tout les données lié à cette objet pour être sûr qu'on a tous
                object = this.find(result.getInt("MAX(ID_utilisateur)"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public boolean delete(Etudiant object) {
        return false;
    }

    @Override
    public Etudiant update(Etudiant object) {
        return object;
    }
    
    @Override
    public Etudiant find(int id) {
        Etudiant etudiant = new Etudiant();      

        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();

            result = st.executeQuery("SELECT * FROM Utilisateur\n" +
                                    "LEFT JOIN etudiant ON utilisateur.ID = etudiant.ID_utilisateur\n" +
                                    "LEFT JOIN Groupe ON Etudiant.ID_groupe=Groupe.ID\n" +
                                    "LEFT JOIN seance_groupes ON Etudiant.ID_groupe = seance_groupes.ID_groupe\n" +
                                    "LEFT JOIN seance ON seance.ID = seance_groupes.ID_seance\n" +
                                    "WHERE Etudiant.ID_utilisateur = " + id +
                                    " ORDER BY Seance.Date, seance.Heure_debut");
            
            if(result.first()) {
                //recuperation données utilisateur
                int cle = result.getInt("ID");//ID de groupe d'après la BDD quand on tape la requete dans phpmyadmin
                etudiant.setNumero(result.getInt("Numero"));
                UtilisateurDAO userDAO = new UtilisateurDAO();
                Utilisateur user = userDAO.find(cle);
                etudiant.copierUtilisateur(user); //copie de utilisateur dans enseignant pour pouvoir les afficher

                GroupeDAO dDAO = new GroupeDAO();
                etudiant.setGroupe(dDAO.find(result.getInt("ID_groupe")));

                //Pour l'instant, pas de RECUP COMPLETE DES SEANCES
                /*result.beforeFirst(); // retourne à la première ligne
                SeanceDAO sDAO = new SeanceDAO();
                while(result.next()) {
                    if (result.getInt("ID_seance") != 0)
                        etudiant.ajouterSeance(sDAO.find(result.getInt("ID_seance")));
                }*/
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Etudiant pas trouvé");
        }
        return etudiant;
    }
    
    public Etudiant findByGroup(int id) {
        Etudiant etudiant = new Etudiant();      

        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();

            result = st.executeQuery("SELECT * FROM Etudiant WHERE ID_groupe = " + id);
            
            if(result.first()) {
                //recuperation données utilisateur
                int cle = result.getInt("ID_utilisateur"); //ID étudiant
                etudiant.setNumero(result.getInt("Numero")); //Pas très utile mais bon
                
                UtilisateurDAO userDAO = new UtilisateurDAO();
                Utilisateur user = userDAO.find(cle);
                etudiant.copierUtilisateur(user); //copie de utilisateur dans enseignant pour pouvoir recup mail et password
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Etudiant pas trouvé");
        }
        return etudiant;
    }
    /*methodes en plus pour ADMINISTRATEUR*/
    public ArrayList<Etudiant> findAllStudents()
    {
        ArrayList<Etudiant> etudiants = new ArrayList<>();
        try {
            ResultSet result=connect.createStatement().executeQuery("SELECT DISTINCT ID_utilisateur FROM etudiant ORDER BY ID_utilisateur"); // Récup tout ensengnants
            
                while(result.next()) {
                    etudiants.add(find(result.getInt("ID_utilisateur")));
                }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return etudiants;
    }
}