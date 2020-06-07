package modele;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class SeanceDAO extends DAO<Seance> {
    /**
     * @param object
     * @return une nouvelle seance
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
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * @param object
     * @return false
     */
    @Override
    public boolean delete(Seance object) {
        return false;
    }
    
    /**
     * @param object
     * @return une seance mise a jour
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
            
            //Pour appelé les updates des autres classes
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
    
    /**
     * find
     * @param id
     * @return seance via id
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
            ResultSet resultSalles =  st.executeQuery("SELECT * FROM seance_salles WHERE ID_seance = "+ id);
            if (resultSalles.first()){
                SalleDAO sDAO = new SalleDAO();
                seance.ajouterSalle(sDAO.find(resultSalles.getInt("ID_salle")));
                
                while(resultSalles.next()) {
                    seance.ajouterSalle(sDAO.find(resultSalles.getInt("ID_salle")));
                }
            }

            //On récupère les groupes (d'étudiant) de la séance
            ResultSet resultGroupes =  st.executeQuery("SELECT * FROM seance_groupes WHERE ID_seance = " + id);
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
    
    /**
     * @param id
     * @param semaine
     * @return seance via id utilisateur et semaine
     */
    public ArrayList<Seance> findSeancesByUserAndWeek(int id, int semaine){
        ArrayList<Seance> listSeancesbyWeek = new ArrayList<>();
        
        try {
            ResultSet result = connect.createStatement().executeQuery("SELECT Droit FROM utilisateur WHERE ID = "+ id);
            if(result.first())
            {
                if(result.getInt("Droit") != 1)
                {
                    String requete = new String();
                    if (result.getInt("Droit") == 3 || result.getInt("Droit") == 2){ //Professeur, trouver les séances de ce prof
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
        }
        catch (SQLException e) {
                e.printStackTrace();
        }
        return listSeancesbyWeek;
    }
    
    /**
     * @param id
     * @param jour
     * @param mois
     * @param annee
     * @return seance via utilisateur et jour
     */
    public ArrayList<Seance> findSeancesByUserAndDay(int id, int jour, int mois, int annee){
        ArrayList<Seance> listSeancesbyDay = new ArrayList<>();
        
        String month = new String();
        if(mois<10)
            month = "0" + String.valueOf(mois);
        else month = String.valueOf(mois);
        
        String day = new String();
        if(jour<10)
            day = "0" + String.valueOf(jour);
        else day = String.valueOf(jour);
        
        String dateBDD = annee + "-" + month + "-" + jour; //aaaa-mm-jj
        System.out.println("date BDD " + dateBDD);
        
        try {
            ResultSet result = connect.createStatement().executeQuery("SELECT Droit FROM utilisateur WHERE ID = "+ id);
            if(result.first())
            {
                String requete = new String();
                if (result.getInt("Droit") == 3 || result.getInt("Droit") == 2){ //Professeur, trouver les séances de ce prof
                    requete = "SELECT ID FROM Seance \n"
                            +"LEFT JOIN seance_enseignants SE ON SE.ID_seance = seance.ID \n"
                            +"WHERE Seance.Date = '" + dateBDD + "' AND SE.ID_enseignant = " + id + " ORDER BY Seance.Date, seance.Heure_debut";
                }
                if (result.getInt("Droit") == 4){ //Etudiant, trouver les séances de cet étudiant
                    requete = "SELECT ID FROM Seance \n" +
                                "LEFT JOIN seance_groupes SG ON SG.ID_seance = seance.ID \n" +
                                "LEFT JOIN etudiant user ON user.ID_groupe = SG.ID_groupe \n" +
                                "WHERE Seance.Date = '" + dateBDD + "' AND user.ID_utilisateur = " + id + 
                                " ORDER BY Seance.Date, seance.Heure_debut";
                }
                ResultSet resultSeances = connect.createStatement().executeQuery(requete);

                if(resultSeances.first()) //On regarde si une ligne existe
                {
                    resultSeances.beforeFirst(); //On retourne à la première ligne car on sait jamais il y a pas plusieurs lignes
                    while(resultSeances.next())  //On recupère les données de toute les lignes
                    {
                        SeanceDAO sDAO = new SeanceDAO();
                        listSeancesbyDay.add(sDAO.find(resultSeances.getInt("ID")));
                    }
                }
            }
        }
        catch (SQLException e) {
                e.printStackTrace();
        }
        return listSeancesbyDay;
    }
    
    /**
     * @param id
     * @param semaine
     * @return seance via id groupe et semaine
     */
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
    
    /**
     * @param id
     * @param semaine
     * @return seance via id promo et semaine
     */
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
    
    /**
     * @param id
     * @param semaine
     * @return seance via id salle et semaine
     */
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
       
    /**
     * @param id
     * @param debut
     * @param fin
     * @return toutes les seances d'un utilisateur dans l'ordre
     */
    public ArrayList<ArrayList<Seance>> findSeancesOfUserByDate(int id, String debut, String fin) //Le récapitulatif de la personne d'ID id en fonction de 2 dates
    {
        //Explication de cette array d'array:
        //à Chaque .get(i) se trouve une liste de séance de même matière et même groupes
        //à chaque .get(i).get(j) se trouve les séances de même matière et de même groupes rangé par date et heure
        ArrayList<ArrayList<Seance>> seancesOrdered = new ArrayList<>();
        ArrayList<Seance> unFourreTout = new ArrayList<>();
        System.out.println("id " + id);
        try
        {
            ResultSet result = connect.createStatement().executeQuery("SELECT Droit FROM utilisateur WHERE ID = "+ id);
            if(result.first())
            {
                String requete = new String();
                if (result.getInt("Droit") == 3 || result.getInt("Droit") == 2 || result.getInt("Droit") == 1) { //Professeur, trouver les séances de ce prof
                    requete = "SELECT Seance.ID FROM Seance\n" +
                              "LEFT JOIN seance_enseignants SE ON SE.ID_seance = Seance.ID " +
                              "LEFT JOIN cours ON cours.ID = Seance.ID_cours " +
                              "WHERE SE.ID_enseignant = " + id + " "+
                              "AND Seance.Date >= '" + debut + "' "+
                              "AND Seance.Date <= '" + fin + "' " +
                              "AND Seance.Etat = 2 " +
                              "ORDER BY cours.Nom, Date, Heure_debut";
                }
                if (result.getInt("Droit") == 4) { //Etudiant, trouver les séances de cet étudiant
                    requete = "SELECT Seance.ID FROM Seance " +
                              "LEFT JOIN seance_groupes SG ON SG.ID_seance = Seance.ID " +
                              "LEFT JOIN cours ON cours.ID = Seance.ID_cours " +
                              "LEFT JOIN etudiant SE ON SE.ID_groupe = SG.ID_groupe WHERE SE.ID_utilisateur = " + id + " " +
                              "AND Seance.Date >= '" + debut + "' " +
                              "AND Seance.Date <= '" + fin + "' " +
                              "AND Seance.Etat = 2 " +
                              "ORDER BY cours.Nom, Date, Heure_debut";
                }
                ResultSet resultSeances = connect.createStatement().executeQuery(requete);
            
                //Selectionne les id des séances de la personne rangé par matière ->Date ->Heure de début
                if(resultSeances.first()) //On regarde si une ligne existe
                {
                    resultSeances.beforeFirst(); //On retourne à la première ligne car on sait jamais il y a pas plusieurs lignes
                    while(resultSeances.next())  //On recupère les données de toute les lignes
                    {
                        SeanceDAO sDAO = new SeanceDAO();
                        unFourreTout.add(sDAO.find(resultSeances.getInt("Seance.ID")));
                    }
                }
            
               //On trie
                ArrayList<Object> toCompare = new ArrayList<>();
                while(!unFourreTout.isEmpty())//Chaque séance de la postion 0 de unFourreTout va chercher et récupérer ses semblables
                {
                    
                    toCompare.add(unFourreTout.get(0).getCours().getNom()); //On prend la nom de la matière de la séance à la position 0
                    toCompare.add(unFourreTout.get(0).getGroupes()); //On prend les groupes de la séance à la position 0
                    
                    ArrayList<Seance> SeancesSameCourseAndGroupes= rec1(unFourreTout,0,toCompare); //appel fct réccursive pour trouver
                    
                    toCompare.clear(); //On efface pour faire un nouveau add
                    
                    seancesOrdered.add(SeancesSameCourseAndGroupes); //On récupère la liste dans une liste de liste de séance
                }
            }
        }
        catch (SQLException e) {
                e.printStackTrace();
        }
        return seancesOrdered;
    }
    
     public ArrayList<ArrayList<Seance>> findAllSeancesByDate( String debut, String fin) //Le récapitulatif de la personne d'ID id en fonction de 2 dates
    {
        //Explication de cette array d'array:
        //à Chaque .get(i) se trouve une liste de séance de même matière et même groupes
        //à chaque .get(i).get(j) se trouve les séances de même matière et de même groupes rangé par date et heure
        ArrayList<ArrayList<Seance>> seancesOrdered = new ArrayList<>();
        ArrayList<Seance> unFourreTout = new ArrayList<>();
        try
        {
            ResultSet result = connect.createStatement().executeQuery("SELECT * FROM seance");
            if(result.first())
            {
                String requete = new String();
                requete = "SELECT Seance.ID FROM Seance " +
                          "LEFT JOIN cours ON cours.ID = Seance.ID_cours " +
                          "WHERE Seance.Date >= '" + debut + "' " +
                          "AND Seance.Date <= '" + fin + "' " +
                          "AND Seance.Etat = 2 " +
                          "ORDER BY cours.Nom, Date, Heure_debut";

                ResultSet resultSeances = connect.createStatement().executeQuery(requete);
            
                //Selectionne les id des séances de la personne rangé par matière ->Date ->Heure de début
                if(resultSeances.first()) //On regarde si une ligne existe
                {
                    resultSeances.beforeFirst(); //On retourne à la première ligne car on sait jamais il y a pas plusieurs lignes
                    while(resultSeances.next())  //On recupère les données de toute les lignes
                    {
                        SeanceDAO sDAO = new SeanceDAO();
                        unFourreTout.add(sDAO.find(resultSeances.getInt("Seance.ID")));
                    }
                }
            
               //On trie
                ArrayList<Object> toCompare = new ArrayList<>();
                while(!unFourreTout.isEmpty())//Chaque séance de la postion 0 de unFourreTout va chercher et récupérer ses semblables
                {     
                    toCompare.add(unFourreTout.get(0).getCours().getNom()); //On prend la nom de la matière de la séance à la position 0
                    toCompare.add(unFourreTout.get(0).getGroupes()); //On prend les groupes de la séance à la position 0
                    
                    ArrayList<Seance> SeancesSameCourseAndGroupes= rec1(unFourreTout,0,toCompare); //appel fct réccursive pour trouver
                    
                    toCompare.clear(); //On efface pour faire un nouveau add
                    
                    seancesOrdered.add(SeancesSameCourseAndGroupes); //On récupère la liste dans une liste de liste de séance
                }
            }
        }
        catch (SQLException e) {
                e.printStackTrace();
        }
        return seancesOrdered;
    }

    
    
    /**
     * @param fourreTout
     * @param indice
     * @param toCompare
     * @return un array liste avec que les séances d'une matière avec les mêmes Groupes
     */
    public ArrayList<Seance> rec1 (ArrayList<Seance> fourreTout, int indice, ArrayList<Object> toCompare)
    {
        ArrayList<Seance> identique = new ArrayList<>();
        rec2(fourreTout,identique,indice,toCompare);
        return identique;
    }
    
    /**
     * @param fourreTout
     * @param identique
     * @param indice
     * @param toCompare
     */
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
    
    /**
     * @param liste
     * @return le nombre total d'heures des seances
     */
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
    
    /**
     * trouver toutes les seances
     * pour admin
     * @return
     */
    public ArrayList<Seance> findAllSeances()
    {
        ArrayList<Seance> seances = new ArrayList<>();
        try{
             ResultSet resultSeances = connect.createStatement()
                                             .executeQuery("SELECT ID FROM seance ORDER BY Date, Heure_debut");
             while(resultSeances.next())
             {
                 seances.add(find(resultSeances.getInt("ID")));
             }
        }catch (SQLException e) {
                e.printStackTrace();
        }
        return seances;
    }
    
    /**
     * vérifie si le prof qu'on veut add est dispo et non en doublon
     * @param seance
     * @param id_enseignant
     * @return 
     */
    public boolean canIAjouterEnseignantSeance(Seance seance, int id_enseignant) {
        boolean youCan = false;
        SeanceDAO sDAO = new SeanceDAO();
        if(!seance.isThisTeacherInThisSeance(id_enseignant)){ // Si pas dans la séance
            if(sDAO.isTeacherNotFreeForThisSeance(id_enseignant, seance)){//METHODE N°14 DANS SEANCEDAO
                System.out.println("Impossible d'ajouter car cet enseignant est occupé à ce créneau");
            }else{
                    youCan = true;
                    System.out.println("Possible d'add cet enseignant ! ");
            }         
        }
        else {
            System.out.println("Cet enseignant est déjà dans cet séance");
        }
        return youCan;
    }

    /**
     * vérifie si la salle a add est dispo et non doublon
     * @param seance
     * @param id_salle
     * @return
     */
    public boolean canIAjouterSalleSeance(Seance seance, int id_salle) {
        boolean youCan = false;
        SeanceDAO sDAO = new SeanceDAO();
        
        if(!seance.isThisRoomInThisSeance(id_salle)){ // Si cet salle n'est pas dans la séance
            if(sDAO.isSalleNotFreeForThisSeance(id_salle, seance)){//METHODE N°14 DANS SEANCEDAO
                System.out.println("Impossible d'ajouter car cet salle est occupé à ce créneau");
            }else{
                    youCan = true;
                    System.out.println("Possible d'add cet salle ! ");
            }         
        }else{
            System.out.println("Cet salle est déjà dans cet séance");
        }
        return youCan;
    }

    /**
     * vérifie si on peut add un groupe (dispo/non doublon/capa)
     * @param seance
     * @param id_groupe
     * @return
     */
    public boolean canIAjoutGroupeSeance(Seance seance, int id_groupe) {
        boolean youCan = false;
        SeanceDAO sDAO = new SeanceDAO();
        
        if (!seance.isThisGroupInThisSeance(id_groupe)){ //Le groupe n'est pas dans cette séance
            if(sDAO.isGroupNotFreeForThisSeance(id_groupe, seance)){//METHODE N°13 DANS SEANCEDAO
                System.out.println("Impossible de rajouter ce groupe car un cours est deja attitre dans ce créneaux");
            }else{
                int capGroupes = 0;
                for (int i = 0 ; i < seance.getGroupes().size() ; i++)
                {//On récup capa des groupes déjà dans cette séance
                    capGroupes += sDAO.find_capacite_groupes_total(seance.getGroupes().get(i).getId(),0); //on considère seance non existant (pour pvr use cette même méthode pour 2 MAJ sans recréer)
                }
                capGroupes += sDAO.find_capacite_groupes_total(id_groupe,0); //Le groupe qu'on veut add
                if(seance.getSalles().isEmpty() || (seance.placeInTotal() >= capGroupes)){//METHODE N°16 DANS SEANCEDAO
                
                    youCan = true;
                    System.out.println("Possible d'add ce groupe ! ");
                }else{System.out.println("Le nombre d'eleves dans le groupe depasse la capacité maximale de la seance");}
            }         
        }else{
            System.out.println("Ce groupe est déjà dans cette séance");
        }
        return youCan;
    }
 
    /**
     * retourne true si un groupe a deja une seance prevue
     * @param id_groupe
     * @param seance
     * @return
     */
    public Boolean isGroupNotFreeForThisSeance (int id_groupe, Seance seance ){
        try
        {
            ResultSet maRequete = this.connect.createStatement()
                    .executeQuery("SELECT * FROM seance\n" +
                                "LEFT JOIN seance_groupes SG ON seance.ID = SG.ID_seance\n" +
                                "WHERE SG.ID_groupe = "+id_groupe+" AND seance.Date = '"+ seance.getDate()+"' "+
                                "AND ((seance.Heure_fin >= '"+seance.getHeureDebut()+"' AND seance.Heure_fin <= '"+seance.getHeureFin()+"') " +
                                "OR (seance.Heure_debut >= '"+seance.getHeureDebut()+"' AND seance.Heure_debut <= '"+seance.getHeureFin()+"')) "
                              + "AND seance.ID != "+seance.getId());//Pour éviter qu'il regarde aussi dans la seance lui même
            if(maRequete.first())
                return true;//Des séances sont éxistant dans le créneau 
            else
                return false; //Ok, feu vert
        }
        catch (SQLException e) 
        {
          e.printStackTrace();
        }
        return false;
    }
 
    /**
     * retourne true si un enseignant a deja une seance prevue
     * @param id_enseignant
     * @param seance
     * @return
     */
    public Boolean isTeacherNotFreeForThisSeance (int id_enseignant, Seance seance ){
        try
        {
            ResultSet maRequete = this.connect.createStatement()
                    .executeQuery("SELECT * FROM seance\n" +
                                "LEFT JOIN seance_enseignants SE ON seance.ID = SE.ID_seance\n" +
                                "WHERE SE.ID_enseignant = "+id_enseignant+" AND seance.Date = '"+ seance.getDate()+"' "+
                                "AND ((seance.Heure_fin >= '"+seance.getHeureDebut()+"' AND seance.Heure_fin <= '"+seance.getHeureFin()+"') " +
                                "OR (seance.Heure_debut >= '"+seance.getHeureDebut()+"' AND seance.Heure_debut <= '"+seance.getHeureFin()+"')) "
                                + "AND seance.ID != "+seance.getId());//Pour éviter qu'il regarde aussi dans la seance lui même
            if(maRequete.first())
                return true;//Des séances sont éxistant dans le créneau 
            else
                return false; //Ok, feu vert
        }
        catch (SQLException e) 
        {
          e.printStackTrace();
        }
        return false;
    }
    
    /**
     * @param id_salle
     * @param seance
     * @return true si une salle a deja une seance prevue
     */
    public Boolean isSalleNotFreeForThisSeance (int id_salle, Seance seance ){
        try
        {
            ResultSet maRequete = this.connect.createStatement()
                    .executeQuery("SELECT * FROM seance\n" +
                                "LEFT JOIN seance_salles SS ON seance.ID = SS.ID_seance\n" +
                                "WHERE SS.ID_salle = "+id_salle+" AND seance.Date = '"+ seance.getDate()+"' "+
                                "AND ((seance.Heure_fin >= '"+seance.getHeureDebut()+"' AND seance.Heure_fin <= '"+seance.getHeureFin()+"') " +
                                "OR (seance.Heure_debut >= '"+seance.getHeureDebut()+"' AND seance.Heure_debut <= '"+seance.getHeureFin()+"'))"
                               + " AND seance.ID != "+seance.getId()); //Pour éviter qu'il regarde aussi dans la seance lui même
            if(maRequete.first())
                return true;//Des séances sont éxistant dans le créneau 
            else
                return false; //Ok, feu vert
        }
        catch (SQLException e) 
        {
          e.printStackTrace();
        }
        return false;
    }

    /**
     * ?????
     * @param id_groupe
     * @param id_seance
     * @return
     */

    public int find_capacite_groupes_total(int id_groupe, int id_seance ){
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

    /**
     * ajouter un enesignant/groupe/salle
     * @param idSeance
     * @param idAutre
     * @param table
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
    
    /**
     * supprimer un enseignant/groupe/salle
     * @param idSeance
     * @param idAutre
     * @param table
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
}
