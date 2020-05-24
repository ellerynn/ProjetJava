package dao;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import modele.*;

public class SeanceDAO extends DAO<Seance> {
    /*
    * CREATE METHODE QUI PERMET DE CREER UNE SEANCE
    * METHODE N°1 DE BASE DAO
    */
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
    /*
    * METHODE UPDATE QUI PERMET DE UPDATE UNE SEANCE
    * METHODE N°2 DE BASE DAO
    */

     @Override
    public Seance update(Seance object) {
        try {
                this.connect	
                 .createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE
                 ).executeUpdate(
                    "UPDATE seance SET Semaine = '" + object.getSemaine() + "'"+
                    ", Date = '" + object.getDate() + "'"+
                    ", Heure_Debut = '" + object.getHeureDebut() + "'"+
                    ", Heure_Fin = '" + object.getHeureFin() + "'"+
                    ", Etat = '" + object.getEtat() + "'"+
                    ", ID_cours = '" + object.getCours().getId() + "'"+
                    ", ID_type = '" + object.getTypeCours().getId() + "'"+
                    " WHERE ID = " + object.getId()
                 );
            object = this.find(object.getId());
                
            DAO<Cours> coursDAO = new CoursDAO();
            Cours cou = object.getCours();
            cou = coursDAO.find(cou.getId());
            coursDAO.update(cou);
            
            DAO<TypeCours> typescoursDAO = new TypeCoursDAO();
            TypeCours tp = object.getTypeCours();
            tp = typescoursDAO.find(tp.getId());
            typescoursDAO.update(tp);   
                  
                
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return object;
    }
    
    /*
    *FIND METHODE QUI PERMET DE TROUVER UNE SEANCE
    * METHODE N°3 DE BASE DAO
    */
    
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
            ResultSet resultEnseignants =  st.executeQuery("SELECT * FROM seance_enseignants WHERE ID_seance = " + id);
            
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
            if (resultSalles.first()){
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
            if (resultGroupes.first()){
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
/*
    * FIND_SEANCE_GROUPE METHODE BOOLEAN QUI PERMET DE RECUPERER UN TRUE OU UN FALSE DE LA TABLE SEANCE_GROUPES POUR FAIRE LA VERIFICATION DE LA MAJ SI L'ID EXISTE
    * METHODE N°4 POUR MODULE MAJ N°8  AJOUT D'UN GROUPE A UNE SEANCE
*/
    public Boolean find_seance_groupe(int id_seance, int id_groupe) {
        try{
            ResultSet maRequete = this.connect.createStatement().executeQuery("SELECT * FROM seance_groupes WHERE ID_seance = '" + id_seance + "'"+ 
                           " AND ID_groupe = " + id_groupe );
            if(maRequete.first())
                return true;
            else
                return false; 
        }
        catch (SQLException e){
          e.printStackTrace();
        }
        return false;
    }
    
/*
    * FIND_SEANCE_ENSEIGNANT METHODE BOOLEAN QUI PERMET DE RECUPERER UN TRUE OU UN FALSE DE LA TABLE SEANCE_ENEIGNANTS POUR FAIRE LA VERIFICATION DE LA MAJ SI L'ID EXISTE
    * METHODE N°5 POUR MODULE MAJ N°7 MODIFIER LE NOM DU COURS, MODULE MAJ N°1 AFFECTER UN ENSEIGNANT A UNE SEANCE
*/
    public Boolean find_seance_enseignant(int id_seance, int id_enseignant) {
        try{
            ResultSet maRequete = this.connect.createStatement().executeQuery("SELECT * FROM seance_enseignants WHERE ID_seance = '" + id_seance + "'"+ 
                           " AND ID_enseignant = " + id_enseignant );
            if(maRequete.first())
                return true;
            else
                return false; 
        }
        catch (SQLException e){
          e.printStackTrace();
        }
        return false;
    }
/*
    * FIND_SEANCE_SALLE METHODE BOOLEAN QUI PERMET DE RECUPERER UN TRUE OU UN FALSE DE LA TABLE SEANCE_SALLES POUR FAIRE LA VERIFICATION DE LA MAJ SI L'ID EXISTE
    * METHODE N°6 POUR MODULE MAJ N°4 affecter une salle a une seance
*/
    public Boolean find_seance_salle(int id_seance, int id_salle) {
        try{
            ResultSet maRequete = this.connect.createStatement().executeQuery("SELECT * FROM seance_salles WHERE ID_seance = '" + id_seance + "'"+ 
                           " AND ID_salle = " + id_salle );
            if(maRequete.first())
                return true;
            else
                return false; 
        }
        catch (SQLException e){
          e.printStackTrace();
        }
        return false;
    }
    /*
    * FIND_SEANCE_AFFECTER_ENSEIGNANT METHODE BOOLEAN QUI PERMET DE RECUPERER UN TRUE OU UN FALSE POUR SAVOIR SI UN ENSEIGNANT A DEJA ETE AFFECTER A LA SEANCE
    *CAR ON PEUT AFFECTER QUE SI UNE SEANCE N'A PAS D'ENSEIGNANT
    * METHODE N°7 POUR MODULE MAJ N°1 AFFECTER UN ENSEIGNANT A UNE SEANCE
*/
    public Boolean find_seance_affecter_enseignant(int id_seance) {
        try{
            ResultSet maRequete = this.connect.createStatement().executeQuery("SELECT * FROM seance_enseignants " +
                                                                             "LEFT JOIN seance ON seance.ID = seance_enseignants.ID_seance " 
                                                                             + " WHERE seance.ID = " + id_seance );
            if(maRequete.first())
                return true;
            else
                return false; 
        }
        catch (SQLException e){
          e.printStackTrace();
        }
        return false;
    }
    
/*
    * FIND_SEANCE_AFFECTER_SALLE METHODE BOOLEAN QUI PERMET DE RECUPERER UN TRUE OU UN FALSE POUR SAVOIR SI UNE SALLE A DEJA ETE AFFECTER A LA SEANCE
    *CAR ON PEUT AFFECTER QUE SI UNE SEANCE N'A PAS DE SALLE
    * METHODE N°8 POUR MODULE MAJ N°4 AFFECTER UNE SALLE A UNE SEANCE
*/
    public Boolean find_seance_affecter_salle(int id_seance) {
        try{
            ResultSet maRequete = this.connect.createStatement().executeQuery("SELECT * FROM seance_salles " +
                                                                             "LEFT JOIN seance ON seance.ID = seance_salles.ID_seance " 
                                                                             + " WHERE seance.ID = " + id_seance );
            if(maRequete.first())
                return true;
            else
                return false; 
        }
        catch (SQLException e){
          e.printStackTrace();
        }
        return false;
    }
    
/*
    * FIND_SEANCE_GROUPE_VALIDER METHODE BOOLEAN QUI PERMET DE RECUPERER UN TRUE OU UN FALSE POUR SAVOIR SI UN GROUPE EXISTE DANS LA TABLE SEANCE_GROUPES
    * METHODE N°9 POUR MODULE MAJ N°10 VALIDER UNE SEANCE ET POUR MODULE MAJ N°11 ENLEVER UN GROUPE D'UNE SEANCE
*/
    
    public Boolean find_seance_groupe_valider(int id_seance) {
        try{
            ResultSet maRequete = this.connect.createStatement().executeQuery("SELECT * FROM seance_groupes WHERE ID_seance = " + id_seance );
            if(maRequete.first()){
                return true;
            }else
                return false; 
        }
        catch (SQLException e){
          e.printStackTrace();
        }
        return false;
    }
    
/*
    * FIND_SEANCE_ENSEIGNANT_VALIDER METHODE BOOLEAN QUI PERMET DE RECUPERER UN TRUE OU UN FALSE POUR SAVOIR SI UN ENSEIGNANT EXISTE DANS LA TABLE SEANCE_ENSEIGNANTS
    * METHODE N°10 POUR MODULE MAJ N°10 VALIDER UNE SEANCE, N°11 ENLEVER ENSEIGNANT
*/
        
    public Boolean find_seance_enseignant_valider(int id_seance) {
        int nombre = 0;
        try
        {
            ResultSet maRequete = this.connect.createStatement().executeQuery("SELECT * FROM seance_enseignants WHERE ID_seance = " + id_seance );
            if(maRequete.first())
            {
                maRequete.beforeFirst(); // retourne à la première ligne
                while(maRequete.next()) {
                    nombre++;
                }  
                return true;
            }
            else
                return false; 
        }
        catch (SQLException e) 
        {
          e.printStackTrace();
        }
        return false;
    }
/*
    * FIND_SEANCE_GROUPE_ENLEVER_BLINDAGE METHODE INT QUI PERMET DE RECUPERER UN ENTIER QUI COMPTE LE NOMBRE DE GROUPES PRESENTS DANS LA SEANCE DANS LA TABLE SEANCE_GROUPES
    * METHODE POUR LA SUPPRESSION D'UN GROUPE D'UNE SEANCE
    * METHODE N°11 POUR MODULE MAJ N°11 ENLEVER UN GROUPE D'UNE SEANCE
*/
    
    public int find_seance_groupe_enlever_blindage(int id_seance) {
        int nombre = 0;//utile pour blindage, minimum 1 groupe dans la table seance_groupe, dons si superieur a 1, donc on va stocker le nombre de seance
        try{
            ResultSet maRequete = this.connect.createStatement().executeQuery("SELECT * FROM seance_groupes WHERE ID_seance = " + id_seance );
            if(maRequete.first()){
                maRequete.beforeFirst(); // retourne à la première ligne
                while(maRequete.next()) {
                    nombre++;
                }  
            }
        }
        catch (SQLException e){
          e.printStackTrace();
        }
        return nombre;
    }
/*
    * FIND_SEANCE_ENSEIGNANT_ENLEVER_BLINDAGE METHODE INT QUI PERMET DE RECUPERER UN ENTIER QUI COMPTE LE NOMBRE D'ENSEIGNANT PRESENTS DANS LA SEANCE DANS LA TABLE SEANCE_ENSEIGNANTS
    * METHODE POUR LA SUPPRESSION D'UN ENSEIGNANT D'UNE SEANCE
    * METHODE N°12 POUR MODULE MAJ N°11 ENLEVER UN ENSEIGNANT D'UNE SEANCE
*/
    public int find_seance_enseignant_enlever_blindage(int id_seance) {
        int nombre = 0;//utile pour blindage, minimum 1 groupe dans la table seance_groupe, dons si superieur a 1, donc on va stocker le nombre de seance
        try
        {
            ResultSet maRequete = this.connect.createStatement().executeQuery("SELECT * FROM seance_enseignants WHERE ID_seance = " + id_seance );
            if(maRequete.first())
            {
                maRequete.beforeFirst(); // retourne à la première ligne
                while(maRequete.next()) {
                    nombre++;
                }  
            }
        }
        catch (SQLException e) 
        {
          e.printStackTrace();
        }
        return nombre;
    }
/*
    * FIND_SEANCE_GROUPE_CRENEAU_GROUPE METHODE BOOLEAN QUI RETURN TRUE SI UN GROUPE A DEJA UNE SEANCE DE PREVU SUR L'HORAIRE DE LA SEANCE QU'IL VOULAIT AJOUTER
    * METHODE BLINDAGE CRENEAU POUR GROUPE
    * METHODE N°13 POUR MODULE MAJ N°8  AJOUT D'UN GROUPE A UNE SEANCE
*/
    
    public Boolean find_seance_creneau_groupe (int id_groupe, Seance seance ){
        try
        {
            ResultSet maRequete = this.connect.createStatement()
                    .executeQuery("SELECT * FROM seance\n" +
                                "LEFT JOIN seance_groupes SG ON seance.ID = SG.ID_seance\n" +
                                "WHERE SG.ID_groupe = "+id_groupe+" AND seance.Date = '"+ seance.getDate()+"' "+
                                "AND ((seance.Heure_fin >= '"+seance.getHeureDebut()+"' AND seance.Heure_fin <= '"+seance.getHeureFin()+"') " +
                                "OR (seance.Heure_debut >= '"+seance.getHeureDebut()+"' AND seance.Heure_debut <= '"+seance.getHeureFin()+"'))");
            if(maRequete.first())
                return true;
                //return true; //Des séances sont éxistant dans le créneau 
            else
                return false; //Ok, feu vert
        }
        catch (SQLException e) 
        {
          e.printStackTrace();
        }
        return false;
    }
 /*
    * FIND_SEANCE_GROUPE_CRENAEAU_ENSEIGNANT METHODE BOOLEAN QUI RETURN TRUE SI UN ENSEIGNANT A DEJA UNE SEANCE DE PREVU SUR L'HORAIRE DE LA SEANCE QU'IL VOULAIT AJOUTER
    * METHODE BLINDAGE CRENEAU POUR ENSEIGNANT
    * METHODE N°14 POUR MODULE MAJ N°7 MODIFIER LE NOM DU COURS, MODULE MAJ N°1 AFFECTER UN ENSEIGNANT A UNE SEANCE
*/
    public Boolean find_seance_creneau_enseignant (int id_enseignant, Seance seance ){
        try
        {
            ResultSet maRequete = this.connect.createStatement()
                    .executeQuery("SELECT * FROM seance\n" +
                                "LEFT JOIN seance_enseignants SE ON seance.ID = SE.ID_seance\n" +
                                "WHERE SE.ID_enseignant = "+id_enseignant+" AND seance.Date = '"+ seance.getDate()+"' "+
                                "AND ((seance.Heure_fin >= '"+seance.getHeureDebut()+"' AND seance.Heure_fin <= '"+seance.getHeureFin()+"') " +
                                "OR (seance.Heure_debut >= '"+seance.getHeureDebut()+"' AND seance.Heure_debut <= '"+seance.getHeureFin()+"'))");
            if(maRequete.first())
                return true;
                //return true; //Des séances sont éxistant dans le créneau 
            else
                return false; //Ok, feu vert
        }
        catch (SQLException e) 
        {
          e.printStackTrace();
        }
        return false;
    }
/*
    * FIND_SEANCE_GROUPE_CRENAEAU_SEANCE METHODE BOOLEAN QUI RETURN TRUE SI UNE SALLE A DEJA UNE SEANCE DE PREVU SUR L'HORAIRE DE LA SEANCE QU'IL VOULAIT AJOUTER
    * METHODE BLINDAGE CRENEAU POUR SALLE
    * METHODE N°15 POUR MODULE MAJ N°4 AFFECTER UNE SALLE A UNE SEANCE
*/
    public Boolean find_seance_creneau_salle (int id_salle, Seance seance ){
        try
        {
            ResultSet maRequete = this.connect.createStatement()
                    .executeQuery("SELECT * FROM seance\n" +
                                "LEFT JOIN seance_salles SS ON seance.ID = SS.ID_seance\n" +
                                "WHERE SS.ID_salle = "+id_salle+" AND seance.Date = '"+ seance.getDate()+"' "+
                                "AND ((seance.Heure_fin >= '"+seance.getHeureDebut()+"' AND seance.Heure_fin <= '"+seance.getHeureFin()+"') " +
                                "OR (seance.Heure_debut >= '"+seance.getHeureDebut()+"' AND seance.Heure_debut <= '"+seance.getHeureFin()+"'))");
            if(maRequete.first())
                return true;
                //return true; //Des séances sont éxistant dans le créneau 
            else
                return false; //Ok, feu vert
        }
        catch (SQLException e) 
        {
          e.printStackTrace();
        }
        return false;
    }
/*
    * FIND_CAPACITE_GROUPE_TOTAL METHODE INT QUI PERMET DE RECUPER LA CAPACITE TOTAL D'ELEVES DANS LES GROUPES QUI SERONT PRESENT DANS LA SEANCE
    * METHODE BLINDAGE CAPCAITE GROUPE
    * METHODE N°16 POUR MODULE MAJ N°8  AJOUT D'UN GROUPE A UNE SEANCE
*/
    public int find_capacite_groupe_total(int id_groupe, int id_seance ){
        int cap = 0;
        try
        {
            ResultSet maRequete = this.connect.createStatement()
                    .executeQuery("SELECT DISTINCT ID_utilisateur FROM etudiant\n" +
                                    "LEFT JOIN seance_groupes SG ON SG.ID_groupe = etudiant.ID_groupe\n" +
                                    "WHERE SG.ID_seance = "+id_seance+" OR etudiant.ID_groupe = "+id_groupe);
            while(maRequete.next())
            {
                cap++;
            }
        }
        catch (SQLException e) 
        {
          e.printStackTrace();
        }
        return cap;
    }
/*
    * FIND_CAPACITE_SALLE_TOTAL METHODE INT QUI PERMET DE RECUPER LA CAPACITE TOTAL DES SALLES ET DES GROUPES QUI SERONT PRESENT DANS LA SEANCE
    * METHODE BLINDAGE CAPCAITE GROUPE ET SALLE
    * METHODE N°17 POUR MODULE MAJ N°4 AFFECTER UNE SALLE A UNE SEANCE
*/
    public int find_capacite_salle_total(int id_salle, int id_seance ){
        int cap = 0;
        try
        {
            ResultSet maRequete = this.connect.createStatement()
                    .executeQuery("SELECT DISTINCT ID_utilisateur FROM etudiant\n" +
                                    "LEFT JOIN seance_groupes SG ON SG.ID_groupe = etudiant.ID_groupe\n" +
                                    "LEFT JOIN seance_salles SS ON SS.ID_seance = SG.ID_seance\n" +
                                    "WHERE SG.ID_seance = "+id_seance+" OR SS.ID_salle = "+id_salle);
            while(maRequete.next())
            {
                cap++;
            }
        }
        catch (SQLException e) 
        {
          e.printStackTrace();
        }
        return cap;
    }
/*
    * REQUETE REQUETEFIND QUI PERMET DE RETROUVER UNE SEANCE A PARTIR D'UNE REQUETE SQL EN PARAMETRE, CETTE METHODE EST APPELE DANS UNE AUTRE METHODE
    * METHODE N°17 
*/
    public void requeteFind(String requete, Seance seance) {
        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();
            result = st.executeQuery(requete);
            
            if(!result.first())
            {
                
                seance.setId_seance(result.getInt("ID_seance"));
                seance.setId_groupe(result.getInt("ID_groupe"));
            }    
            
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
    }
    
    /*
    * INSERTINJONCTION METHODE QUI PERMET D'AJOUTER UN ENSEIGNANT, UN GROUPE OU UN SALLE RESPECTIVEMENT DANS LES TABLES
    * SEANCE_ENSEIGNANTS, SEANCE_GROUPES OU SEANCE_SALLES
    * METHODE N°18 
    */
   
    public void insertInJonction(int idSeance, int idAutre, int table)
    {
        try{
            switch(table)
            {
                case 1: //add un enseignant
                {
                    PreparedStatement requete = this.connect
                                    .prepareStatement(
                                                "INSERT INTO seance_enseignants (ID_seance,ID_enseignant)"+
                                                "VALUES(?,?)"
                                        );
                    requete.setInt(1,idSeance);
                    requete.setInt(2,idAutre);
                    requete.executeUpdate();
                    break;
                }
                    
                case 2: //add un groupe 
                {
                    PreparedStatement requete = this.connect
                                    .prepareStatement(
                                                "INSERT INTO seance_groupes (ID_seance,ID_groupe)"+
                                                "VALUES(?,?)"
                                        );
                    requete.setInt(1,idSeance);
                    requete.setInt(2,idAutre);
                    requete.executeUpdate();
                    break;
                }
                case 3://add une salle
                {
                    PreparedStatement requete = this.connect
                                    .prepareStatement(
                                                "INSERT INTO seance_salles (ID_seance,ID_salle)"+
                                                "VALUES(?,?)"
                                        );
                    requete.setInt(1,idSeance);
                    requete.setInt(2,idAutre);
                    requete.executeUpdate();
                    break;
                }
                default:
                    System.out.println("Non add");
                    break;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
    }
    /*
    * INSERTSEANCE METHODE QUI PERMET D'AJOUTER UNE SEANCE, BLINDAGE SERA FAIT AU NIVEAU DE LA FENETRE
    * METHODE N°19 MODULE MAJ N° 6 AJOUTER UNE SEANCE
    */   
    public void insertSeance(int Semaine, int Date, String Heure_Debut, String Heure_Fin, int Etat, int ID_cours,int ID_type)
    {
        try{

                    PreparedStatement requete = this.connect
                                    .prepareStatement(
                                                "INSERT INTO seance (Semaine,Date,Heure_Debut,Heure_Fin,Etat,ID_cours,ID_type)"+
                                                "VALUES(?,?,?,?,?,?,?)"
                                        );
                    requete.setInt(1,Semaine);
                    requete.setInt(2,Date);
                    requete.setString(3,Heure_Debut);
                    requete.setString(4,Heure_Fin);
                    requete.setInt(5,Etat);
                    requete.setInt(6,ID_cours);
                    requete.setInt(7,ID_type);
                    requete.executeUpdate();           
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
    }
    /*
    *DELETEINJONCTION METHODE QUI PERMET DE SUPPRIMER UN ENSEIGNANT, UN GROUPE OU UN SALLE RESPECTIVEMENT DANS LES TABLES
    * SEANCE_ENSEIGNANTS, SEANCE_GROUPES OU SEANCE_SALLES
    * METHODE N°20
    */
    public void DeleteInJonction(int idSeance, int idAutre, int table)
    {
        try{
            switch(table)
            {
                case 1: //DELETE un enseignant
                {
                    connect.createStatement().executeUpdate(
                           "DELETE FROM seance_enseignants WHERE ID_seance = "+idSeance+" AND ID_enseignant = "+ idAutre
                    );
                    break;
                }
                    
                case 2: //DELETE un groupe 
                {
                    connect.createStatement().executeUpdate(
                           "DELETE FROM seance_groupes WHERE ID_seance = "+idSeance+" AND ID_groupe = "+ idAutre
                    );
                    break;
                }
                case 3: //DELETE une salle
                {
                    connect.createStatement().executeUpdate(
                           "DELETE FROM seance_salles WHERE ID_seance = "+idSeance+" AND ID_salle = "+ idAutre
                    );
                    break;
                }
                default:
                    System.out.println("Non delete");
                    break;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé");
        }
    }
    
    public ArrayList<Seance> findSeancesByUserAndWeek(int id, int semaine){
        ArrayList<Seance> listSeancesbyWeek = new ArrayList<>();
        
        try {
            ResultSet result = connect.createStatement().executeQuery("SELECT Droit FROM utilisateur WHERE ID = "+ id);
            if(result.first())
            {
                String requete = new String();
                if (result.getInt("Droit") == 3){ //Professeur, trouver les séances de ce prof
                    requete = "SELECT ID FROM Seance \n"
                            +"LEFT JOIN seance_enseignants SE ON SE.ID_seance = seance.ID \n"
                            +"WHERE Seance.Semaine = " + semaine + " AND SE.ID_enseignant = " + id + " ORDER BY Seance.Date, seance.Heure_debut";
                }
                if (result.getInt("Droit") == 4){ //Etudiant, trouver les séances de cet étudiant
                    requete = "SELECT ID FROM Seance \n" +
                                "LEFT JOIN seance_groupes SG ON SG.ID_seance = seance.ID \n" +
                                "LEFT JOIN etudiant user ON user.ID_groupe = SG.ID_groupe \n" +
                                "WHERE Seance.Semaine = " + semaine + " AND user.ID_utilisateur = " + id + 
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
        }
        catch (SQLException e) {
                e.printStackTrace();
        }
        return listSeancesbyWeek;
    }
    
    public ArrayList<Seance> findSeanceByClassForTeacher(int id) {
        ArrayList<Seance> seances = new ArrayList();
        Seance seance = new Seance(); 
        
        try {
            Statement st;
            ResultSet result;
            //creation ordre SQL
            st = connect.createStatement();

            //On récupère les champs des la table Séance
            result = st.executeQuery("SELECT * FROM Seance WHERE ID_cours = " + id); // Pour récupérer les champs de séances

            if(result.first())
            {     
                result.beforeFirst();
                while(result.next()) { 
                    SeanceDAO sDAO = new SeanceDAO(); 
                    //recuperation données utilisateur
                    seance = sDAO.find(result.getInt("ID"));
                    
                    seances.add(seance);
                } 
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("pas trouvé dans find seance by class");
        }
        
        return seances; 
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
    
    //Récupère tout l'edt de l'utilisateur rangé
     public ArrayList<ArrayList<Seance>> findSeancesOfUserByDate(int id, String debut, String fin) //Le récapitulatif de la personne d'ID id en fonction de 2 dates
    {
        //Explication de cette array d'array:
        //à Chaque .get(i) se trouve une liste de séance de même matière et même groupes
        //à chaque .get(i).get(j) se trouve les séances de même matière et de même groupes rangé par date et heure
        ArrayList<ArrayList<Seance>> seancesOrdered = new ArrayList<>();
        
        ArrayList<Seance> unFourreTout = new ArrayList<>();
        try
        {
            ResultSet resultSeances = connect.createStatement()
                                             .executeQuery("SELECT Seance.ID FROM Seance\n" +
                                                            "LEFT JOIN seance_enseignants SE ON SE.ID_seance = Seance.ID\n" +
                                                            "LEFT JOIN cours ON cours.ID = Seance.ID_cours\n" +
                                                            "WHERE SE.ID_enseignant = "+id+ " "+
                                                            "AND Seance.Date >= '"+debut+ "' "+
                                                            "AND Seance.Date <= '"+fin+ "' " +
                                                            "ORDER BY cours.Nom, Date, Heure_debut");
            
            //Selectionne les id des séances de la personne rangé par matière ->Date ->Heure de début
            if(resultSeances.first()) //On regarde si une ligne existe
            {
                resultSeances.beforeFirst(); //On retourne à la première ligne car on sait jamais il y a pas plusieurs lignes
                while(resultSeances.next())  //On recupère les données de toute les lignes
                {
                    unFourreTout.add(this.find(resultSeances.getInt("Seance.ID")));
                }
            }
            
            //On trie
            ArrayList<Object> toCompare = new ArrayList<>();
            while(!unFourreTout.isEmpty())//Chaque séance de la postion 0 de unFourreTout va chercher et récupérer ses semblables
            {
                System.out.println(unFourreTout.get(0).getCours().getNom());
                toCompare.add(unFourreTout.get(0).getCours().getNom()); //On prend la nom de la matière de la séance à la position 0
                toCompare.add(unFourreTout.get(0).getGroupes()); //On prend les groupes de la séance à la position 0
                ArrayList<Seance> SeancesSameCourseAndGroupes= rec1(unFourreTout,0,toCompare); //appel fct réccursive pour trouver
                toCompare.clear(); //On efface pour faire un nouveau add
                seancesOrdered.add(SeancesSameCourseAndGroupes); //On récupère la liste dans une liste de liste de séance
                
                for (int i = 0 ; i < SeancesSameCourseAndGroupes.size() ; i++)
                {
                    System.out.println(SeancesSameCourseAndGroupes.get(i).getId());
                }
            }
            
        }catch (SQLException e) {
                e.printStackTrace();
        }
        return seancesOrdered;
    }
    //Renvoie un array liste avec que les séances d'une matière avec les mêmes Groupes
    public ArrayList<Seance> rec1 (ArrayList<Seance> fourreTout, int indice, ArrayList<Object> toCompare)
    {
        ArrayList<Seance> identique = new ArrayList<>();
        rec2(fourreTout,identique,indice,toCompare);
        return identique;
    }
    public void rec2 (ArrayList<Seance> fourreTout ,ArrayList<Seance> identique, int indice, ArrayList<Object> toCompare)
    {
        if (indice < fourreTout.size()) //Si pas encore vers la fin
        {
            String matiere = fourreTout.get(indice).getCours().getNom();
            String matiereToCompare = (String)toCompare.get(0);
            ArrayList<Groupe> groupes= fourreTout.get(indice).getGroupes();
            ArrayList<Groupe> groupesToCompare = new ArrayList<>();
            groupesToCompare= (ArrayList<Groupe>)toCompare.get(1);
            boolean sameValues = true;
            if (matiere.equals(matiereToCompare)) 
            {
                if (groupes.size() == groupesToCompare.size())
                {
                    for(int i = 0 ; i < groupes.size() ; i++)
                    {
                        if(groupes.get(i).getId() != groupesToCompare.get(i).getId())
                        {   //
                            sameValues = false;
                            i = groupes.size(); //Pour terminer for
                        }
                    }
                }
                else
                {
                    sameValues = false;
                }
            }
            else{
                sameValues = false;
            }
            
            if(sameValues)
            {
                identique.add(fourreTout.get(indice)); //Ajouter
                fourreTout.remove(indice);//Supprimer du général
                rec2(fourreTout,identique,indice,toCompare); //Répéter
            }
            if (indice+1 < fourreTout.size() && !sameValues){   //Si pas encore vers la fin
                rec2(fourreTout,identique,indice+1,toCompare);
            }
        }
    }
    public String heureTotalSeances(ArrayList<Seance> liste)
    {
        int heureDebut = 0;
        int minuteDebut = 0;
        int heureFin = 0;
        int minuteFin = 0;
        for (int i = 0 ; i <liste.size() ; i++)
        {
           //On récupère les segments.
           String tronqHeureDebut = liste.get(i).getHeureDebut().substring(0, 2);
           String tronqMinuteDebut = liste.get(i).getHeureDebut().substring(3, 5);
           String tronqHeureFin = liste.get(i).getHeureFin().substring(0,2);
           String tronqMinuteFin = liste.get(i).getHeureFin().substring(3, 5);
           
           //On les convertie en int pour pouvoir le calculer la durée de cette séance
           heureDebut += Integer.parseInt(tronqHeureDebut);
           minuteDebut += Integer.parseInt(tronqMinuteDebut);
           heureFin += Integer.parseInt(tronqHeureFin);
           minuteFin += Integer.parseInt(tronqMinuteFin);
        }
        //La différence de chaque heure et chaque minute
        int heure = heureFin - heureDebut;
        int minute = minuteFin - minuteDebut;
        Seance s = new Seance();
        String heureTotal = s.orderingHour(heure+"h"+minute);
        return heureTotal;
    }
}
