/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.*;
import modele.*;



public class SeanceDAO extends DAO<Seance>{
  public SeanceDAO(Connection conn) {
    super(conn);
  }
    @Override
    public boolean create(Seance object) {
        return false;
    }

    @Override
    public boolean delete(Seance object) {
        return false;
    }

    @Override
    public boolean update(Seance object) {
        return false;
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
          
          CoursDAO cDAO = new CoursDAO(connect);
          TypeCoursDAO tDAO = new TypeCoursDAO(connect);
          seance.setCours(cDAO.find(result.getInt("ID_cours")));
          seance.setTypeCours(tDAO.find(result.getInt("ID_type")));
          
      }
      
      //On recupère les enseignants de la séance
      ResultSet resultEnseignants =  st.executeQuery("SELECT * FROM Seance\n" + // Pour les enseignants de la séance
                                    "LEFT JOIN seance_enseigants ON seance.ID = seance_enseigants.ID_seance\n" +
                                    "LEFT JOIN enseignant ON seance_enseigants.ID_enseignant = enseignant.ID_utilisateur\n" +
                                    "WHERE Seance.ID ="+ id +" AND enseignant.ID_cours = seance.ID_cours");
      if (resultEnseignants.first())
      {
          
          EnseignantDAO eDAO = new EnseignantDAO(connect);
          seance.addEnseignant(eDAO.find(resultEnseignants.getInt("ID_enseignant")));
           while(resultEnseignants.next()){
               seance.addEnseignant(eDAO.find(resultEnseignants.getInt("ID_enseignant")));
            }
          
      }
      
      //On récupère les salles de la séance
      ResultSet resultSalles =  st.executeQuery("SELECT * FROM Seance\n" +
                                                "LEFT JOIN seance_salles ON seance.ID = seance_salles.ID_seance\n" +
                                                "LEFT JOIN salle ON seance_salles.ID_salle = salle.ID\n" +
                                                "WHERE Seance.ID = "+id);
      
      if (resultSalles.first())
      {
          SalleDAO sDAO = new SalleDAO(connect);
          seance.addSalle(sDAO.find(resultSalles.getInt("ID_salle")));
           while(resultSalles.next()){
               seance.addSalle(sDAO.find(resultSalles.getInt("ID_salle")));
            }
      }
      
      //On récupère les groupes (d'étudiant) de la séance
      ResultSet resultGroupes =  st.executeQuery("SELECT * FROM Seance\n" +
                                                "LEFT JOIN seance_groupes ON seance.ID = seance_groupes.ID_seance\n" +
                                                "LEFT JOIN groupe ON seance_groupes.ID_groupe = groupe.ID\n" +
                                                "WHERE Seance.ID = "+id);
      if (resultGroupes.first())
      {
          GroupeDAO gDAO = new GroupeDAO(connect);
          seance.addGroupe(gDAO.find(resultGroupes.getInt("ID_groupe")));
           while(resultGroupes.next()){
               seance.addGroupe(gDAO.find(resultGroupes.getInt("ID_groupe")));
            }
      }

    }catch (SQLException e) {
      e.printStackTrace();
      System.out.println("pas trouvé");
    }
    return seance;
  }
}