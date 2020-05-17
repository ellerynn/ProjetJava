/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.*;
import modele.*;



public class EtudiantDAO extends DAO<Etudiant>{
  public EtudiantDAO(Connection conn) {
    super(conn);
  }
    @Override
    public boolean create(Etudiant object) {
        return false;
    }

    @Override
    public boolean delete(Etudiant object) {
        return false;
    }

    @Override
    public boolean update(Etudiant object) {
        return false;
    }
    
    public Etudiant find(int id) {
    Etudiant etudiant = new Etudiant();      

    try {
        Statement st;
        ResultSet result;
        //creation ordre SQL
        st = connect.createStatement();
            
      result = st.executeQuery("SELECT * FROM Utilisateur "+
                                "LEFT JOIN etudiant ON utilisateur.ID = etudiant.ID_utilisateur "+
                                "LEFT JOIN Groupe ON Etudiant.ID_groupe=Groupe.ID "+
                                "LEFT JOIN seance_groupes ON Etudiant.ID_groupe = seance_groupes.ID_groupe "+
                                "WHERE Etudiant.ID_utilisateur =" + id);
      if(result.first())
      {
          //recuperation données utilisateur
          int clee = result.getInt("ID");//ID de groupe d'après la BDD quand on tape la requete dans phpmyadmin
          etudiant.setNumero(result.getInt("Numero"));
          UtilisateurDAO userDAO = new UtilisateurDAO(connect);
          Utilisateur user;
          user = userDAO.find(clee);
          etudiant.copie(user);//copie de utilisateur dans enseignant pour pouvoir les afficher
          
          GroupeDAO dDAO = new GroupeDAO(connect);
          etudiant.setGroupe(dDAO.find(result.getInt("ID_groupe")));
          
          result.beforeFirst(); // retourne à la première ligne
          SeanceDAO sDAO = new SeanceDAO(connect);
          while(result.next()){
              if (result.getInt("ID_seance") != 0)
              {
                  etudiant.addSeances(sDAO.find(result.getInt("ID_seance")));
              }
          }
      }
                 
    }catch (SQLException e) {
      e.printStackTrace();
      System.out.println("pas trouvé");
    }
    return etudiant;
  }
}