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
                case 3:
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
    public void DeleteInJonction(int idSeance, int idAutre, int table)
    {
        try{
            switch(table)
            {
                case 1: //add un enseignant
                {
                    connect.createStatement().executeUpdate(
                           "DELETE FROM seance_enseignants WHERE ID_seance = "+idSeance+" AND ID_enseignant = "+ idAutre
                    );
                    break;
                }
                    
                case 2: //add un groupe 
                {
                    connect.createStatement().executeUpdate(
                           "DELETE FROM seance_groupes WHERE ID_seance = "+idSeance+" AND ID_groupe = "+ idAutre
                    );
                    break;
                }
                case 3:
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
