package dao;

import java.sql.Connection;
import java.sql.*;
import modele.*;

public class SeanceDAO extends DAO<Seance> {
    @Override
    public Seance create(Seance object) {
        return object;
    }

    @Override
    public boolean delete(Seance object) {
        return false;
    }

    @Override
    public Seance update(Seance object) {
        return object;
    }
    
    @Override
    public Seance find(int id) {
        Seance seance = new Seance();      
        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();

            //On récupère les champs des la table Séance
            result = st.executeQuery("SELECT * FROM Seance WHERE ID = "+id); // Pour récupérer les champs de séances

            if(result.first())
            {
                //recuperation données utilisateur
                seance.setId(result.getInt("ID"));
                seance.setSemaine(result.getInt("Semaine"));
                seance.setHeureDebut(result.getString("Heure_Debut"));
                seance.setHeureFin(result.getString("Heure_Fin"));
                seance.setEtat(result.getInt("Etat"));

                CoursDAO cDAO = new CoursDAO();
                TypeCoursDAO tDAO = new TypeCoursDAO();
                seance.setCours(cDAO.find(result.getInt("ID_cours")));
                seance.setTypeCours(tDAO.find(result.getInt("ID_type")));
            }

            //On recupère les enseignants de la séance
            ResultSet resultEnseignants =  st.executeQuery("SELECT * FROM Seance\n" + // Pour les enseignants de la séance
                                          "LEFT JOIN seance_enseignants ON seance.ID = seance_enseignants.ID_seance\n" +
                                          "LEFT JOIN enseignant ON seance_enseignants.ID_enseignant = enseignant.ID_utilisateur\n" +
                                          "WHERE Seance.ID ="+ id +" AND enseignant.ID_cours = seance.ID_cours");
            
            if (resultEnseignants.first())
            {

                EnseignantDAO eDAO = new EnseignantDAO();
                seance.ajouterEnseignant(eDAO.find(resultEnseignants.getInt("ID_enseignant")));
                
                while(resultEnseignants.next()) {
                    seance.ajouterEnseignant(eDAO.find(resultEnseignants.getInt("ID_enseignant")));
                }
            }

            //On récupère les salles de la séance
            ResultSet resultSalles =  st.executeQuery("SELECT * FROM Seance\n" +
                                                      "LEFT JOIN seance_salles ON seance.ID = seance_salles.ID_seance\n" +
                                                      "LEFT JOIN salle ON seance_salles.ID_salle = salle.ID\n" +
                                                      "WHERE Seance.ID = "+id);

            if (resultSalles.first())
            {
                SalleDAO sDAO = new SalleDAO();
                seance.ajouterSalle(sDAO.find(resultSalles.getInt("ID_salle")));
                
                while(resultSalles.next()) {
                    seance.ajouterSalle(sDAO.find(resultSalles.getInt("ID_salle")));
                }
            }

            //On récupère les groupes (d'étudiant) de la séance
            ResultSet resultGroupes =  st.executeQuery("SELECT * FROM Seance\n" +
                                                      "LEFT JOIN seance_groupes ON seance.ID = seance_groupes.ID_seance\n" +
                                                      "LEFT JOIN groupe ON seance_groupes.ID_groupe = groupe.ID\n" +
                                                      "WHERE Seance.ID = "+id);
            if (resultGroupes.first())
            {
                GroupeDAO gDAO = new GroupeDAO();
                seance.ajouterGroupe(gDAO.find(resultGroupes.getInt("ID_groupe")));
                
                while(resultGroupes.next()) {
                    seance.ajouterGroupe(gDAO.find(resultGroupes.getInt("ID_groupe")));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
        return seance;
    }
}