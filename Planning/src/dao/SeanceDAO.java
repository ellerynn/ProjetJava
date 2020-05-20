package dao;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import modele.*;

public class SeanceDAO extends DAO<Seance> {
    
    @Override
    public Seance create(Seance object) {
        try
        {
            if(object.getCours().getId() == 0) //Si un cours est crée mais que son ID est 0
            {
                CoursDAO coursDAO = new CoursDAO();
                object.setCours(coursDAO.create(object.getCours())); //On crée le cours dans la BDD, on récupère l'ID
            }
            if(object.getTypeCours().getId() == 0) //Si le type est nouveau, son ID est 0
            {
                TypeCoursDAO typeDAO = new TypeCoursDAO();
                object.setTypeCours(typeDAO.create(object.getTypeCours())); //On crée le type dans la BDD, on récupère l'ID
            }
            PreparedStatement requete = this.connect
                                .prepareStatement(
                                            "INSERT INTO seance (Semaine, Date, Heure_debut, Heure_fin, Etat, ID_cours, ID_type)"+
                                            "VALUES(?,?,?,?,?,?,?)"
                                    );
            requete.setInt(1,object.getSemaine());
            requete.setString(2,object.getDate());
            requete.setString(3,object.getHeureDebut());
            requete.setString(4,object.getHeureFin());
            requete.setInt(5,object.getEtat());
            requete.setInt(6,object.getCours().getId());
            requete.setInt(7,object.getTypeCours().getId());
            requete.executeUpdate();
            
            ResultSet result = this.connect.createStatement().executeQuery(
                                    		"SELECT MAX(ID) FROM seance"
                                                );
            if (result.first())
            {
                //On crée les données dans la table seance_enseignants
                for(Enseignant ens : object.getEnseignants()){
                    if (ens.getId() == 0) //Si cet enseignant est nouveau
                    {
                        EnseignantDAO ensDAO = new EnseignantDAO();
                        ens = ensDAO.create(ens); //Les données de la BDD, on le crée, on le récupère.
                    }
                    requete = this.connect.prepareStatement( //Création de la ligne dans la BDD
                                                "INSERT INTO seance_enseignants (ID_seance, ID_enseignant)"+
                                                " VALUES(?, ?)"
                                                );
                    requete.setInt(1, result.getInt("MAX(ID)"));
                    requete.setInt(2, ens.getId());
                    requete.executeUpdate();
                }
                //On crée les données dans la table seance_groupes
                for(Groupe grp : object.getGroupes()){
                    if(grp.getId() == 0) //Si le groupe est nv et qu'il n'est pas créé
                    {
                        GroupeDAO grpDAO = new GroupeDAO();
                        grp = grpDAO.create(grp); //On crée dans la BDD, on récupère
                    }
                    requete = this.connect.prepareStatement( //Création de la ligne dans la BDD
                                                "INSERT INTO seance_groupes (ID_seance, ID_groupe)"+
                                                " VALUES(?, ?)"
                                                );
                    requete.setInt(1, result.getInt("MAX(ID)"));
                    requete.setInt(2, grp.getId());
                    requete.executeUpdate();
                }
                //On crée les données dans la table seance_salles
                for(Salle salle : object.getSalles()){
                    if(salle.getId() == 0){ //Si la salle est nouvelle et qu'il n'est pas créé
                        SalleDAO salleDAO = new SalleDAO();
                        salle = salleDAO.create(salle); //On crée dans la BDD, on récupère
                    }
                    requete = this.connect.prepareStatement( //Création de la ligne dans la BDD
                                                "INSERT INTO seance_salles (ID_seance, ID_salle)"+
                                                " VALUES(?, ?)"
                                                );
                    requete.setInt(1, result.getInt("MAX(ID)"));
                    requete.setInt(2, salle.getId());
                    requete.executeUpdate();
                }
                object = this.find(result.getInt("MAX(ID)")); //On récupère TOUT pour être sûr de n'avoir rien oublier
            }
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
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
                seance.setDate(result.getString("Date"));
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
    
    public ArrayList<Seance> findSeancesByUserAndWeek(int id, int semaine){
        ArrayList<Seance> listSeancesbyWeek = new ArrayList<>();
        try{
        ResultSet result = connect.createStatement().executeQuery("SELECT Droit FROM utilisateur WHERE ID = "+ id);
        if(result.first())
        {
            String requete = new String();
            if (result.getInt("Droit") == 3){ //Professeur, trouver les séances de ce prof
                requete = "SELECT ID FROM Seance \n"
                        +"LEFT JOIN seance_enseignants SE ON SE.ID_seance = seance.ID \n"
                        +"WHERE Seance.Semaine = "+semaine+" AND SE.ID_enseignant = "+id +" ORDER BY Seance.Date, seance.Heure_debut";
            }
            if (result.getInt("Droit") == 4){ //Etudiant, trouver les séances de cet étudiant
                requete = "SELECT ID FROM Seance \n" +
                            "LEFT JOIN seance_groupes SG ON SG.ID_seance = seance.ID \n" +
                            "LEFT JOIN etudiant user ON user.ID_groupe = SG.ID_groupe \n" +
                            "WHERE Seance.Semaine = "+semaine+" AND user.ID_utilisateur = "+id+ 
                            " ORDER BY Seance.Date, seance.Heure_debut";
            }
            ResultSet resultSeances = connect.createStatement().executeQuery(requete);
            
            if(resultSeances.first()) //On regarde si une ligne existe
            {
                resultSeances.beforeFirst(); //On retourne à la première ligne car on sait jamais il y a pas plusieurs lignes
                while(resultSeances.next())  //On recupère les données de toute les lignes
                {
                    SeanceDAO sDAO = new SeanceDAO();
                    listSeancesbyWeek.add(sDAO.find(resultSeances.getInt("ID")));
                }
            }
        }
        }catch (SQLException e) {
                e.printStackTrace();
        }
        return listSeancesbyWeek;
    }
    
    public ArrayList<Seance> findSeancesByGroupForStudent(int id) {
        ArrayList<Seance> listSeances = new ArrayList<>();
        try{
            ResultSet resultSeances = connect.createStatement()
                                             .executeQuery("SELECT seance.ID FROM seance\n" +
                                                            "LEFT JOIN seance_groupes SG ON SG.ID_seance = seance.ID\n" +
                                                            "WHERE SG.ID_groupe = " + id + 
                                                            " ORDER BY Seance.Date, seance.Heure_debut");
            if(resultSeances.first()) //On regarde si une ligne existe
            {
                resultSeances.beforeFirst(); //On retourne à la première ligne car on sait jamais il y a pas plusieurs lignes
                while(resultSeances.next())  //On recupère les données de toute les lignes
                {
                    SeanceDAO sDAO = new SeanceDAO();
                    listSeances.add(sDAO.find(resultSeances.getInt("ID")));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Probleme sql seances by group");
        }
        return listSeances;
    }
    
    public ArrayList<Seance> findSeancesByClassForTeacher(int id) {
        ArrayList<Seance> listSeances = new ArrayList<>();
        try{
            ResultSet resultSeances = connect.createStatement()
                                             .executeQuery("SELECT seance.ID FROM seance\n" +
                                                            "LEFT JOIN enseignant SG ON SG.ID_cours = seance.ID_cours\n" +
                                                            "WHERE SG.ID_utilisateur = " + id + 
                                                            " ORDER BY Seance.Date, seance.Heure_debut");
            if(resultSeances.first()) //On regarde si une ligne existe
            {
                resultSeances.beforeFirst(); //On retourne à la première ligne car on sait jamais il y a pas plusieurs lignes
                while(resultSeances.next())  //On recupère les données de toute les lignes
                {
                    SeanceDAO sDAO = new SeanceDAO();
                    listSeances.add(sDAO.find(resultSeances.getInt("ID")));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Probleme sql seances by group");
        }
        return listSeances;
    }
    
    public ArrayList<Seance> findSeancesByGroupAndWeek(int id, int semaine)
    {
        ArrayList<Seance> listSeancesbyWeek = new ArrayList<>();
        try{
            ResultSet resultSeances = connect.createStatement()
                                             .executeQuery("SELECT seance.ID FROM seance\n" +
                                                            "LEFT JOIN seance_groupes SG ON SG.ID_seance = seance.ID\n" +
                                                            "WHERE seance.Semaine = "+semaine+" AND SG.ID_groupe = "+id+ 
                                                            " ORDER BY Seance.Date, seance.Heure_debut");
            if(resultSeances.first()) //On regarde si une ligne existe
            {
                resultSeances.beforeFirst(); //On retourne à la première ligne car on sait jamais il y a pas plusieurs lignes
                while(resultSeances.next())  //On recupère les données de toute les lignes
                {
                    SeanceDAO sDAO = new SeanceDAO();
                    listSeancesbyWeek.add(sDAO.find(resultSeances.getInt("ID")));
                }
            }
        }catch (SQLException e) {
                e.printStackTrace();
        }
        return listSeancesbyWeek;
    }
    public ArrayList<Seance> findSeancesByPromoAndWeek(int id, int semaine)
    {
        ArrayList<Seance> listSeancesbyWeek = new ArrayList<>();
        try{
            ResultSet resultSeances = connect.createStatement()
                                             .executeQuery("SELECT DISTINCT seance.ID FROM seance\n" +
                                                            "LEFT JOIN seance_groupes SG ON SG.ID_seance = seance.ID\n" +
                                                            "LEFT JOIN groupe G ON G.ID = SG.ID_groupe\n" +
                                                            "WHERE seance.Semaine = "+semaine+" AND G.ID_promotion = "+id+
                                                            " ORDER BY Seance.Date, seance.Heure_debut");
            if(resultSeances.first()) //On regarde si une ligne existe
            {
                resultSeances.beforeFirst(); //On retourne à la première ligne car on sait jamais il y a pas plusieurs lignes
                while(resultSeances.next())  //On recupère les données de toute les lignes
                {
                    SeanceDAO sDAO = new SeanceDAO();
                    listSeancesbyWeek.add(sDAO.find(resultSeances.getInt("ID")));
                }
            }
        }catch (SQLException e) {
                e.printStackTrace();
        }
        return listSeancesbyWeek;
    }
    public ArrayList<Seance> findSeancesBySalle(int id, int semaine)
    {
        ArrayList<Seance> listSeancesbyWeek = new ArrayList<>();
        try
        {
            ResultSet resultSeances = connect.createStatement()
                                             .executeQuery("SELECT seance.ID FROM seance\n" +
                                                            "LEFT JOIN seance_salles SS ON SS.ID_seance = seance.ID\n" +
                                                            "WHERE seance.Semaine = "+semaine+" AND SS.ID_salle = "+id+
                                                            " ORDER BY Seance.Date, seance.Heure_debut");
            if(resultSeances.first()) //On regarde si une ligne existe
            {
                resultSeances.beforeFirst(); //On retourne à la première ligne car on sait jamais il y a pas plusieurs lignes
                while(resultSeances.next())  //On recupère les données de toute les lignes
                {
                    SeanceDAO sDAO = new SeanceDAO();
                    listSeancesbyWeek.add(sDAO.find(resultSeances.getInt("ID")));
                }
            }
        }catch (SQLException e) {
                e.printStackTrace();
        }
        return listSeancesbyWeek;
    }
}