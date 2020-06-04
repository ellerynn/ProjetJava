package controleur;

import dao.CoursDAO;
import dao.EnseignantDAO;
import dao.EtudiantDAO;
import dao.GroupeDAO;
import dao.PromotionDAO;
import dao.SalleDAO;
import dao.SeanceDAO;
import dao.SiteDAO;
import dao.TypeCoursDAO;
import dao.UtilisateurDAO;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import modele.Cours;
import modele.Enseignant;
import modele.Etudiant;
import modele.Groupe;
import modele.Promotion;
import modele.Salle;
import modele.Seance;
import modele.TypeCours;
import modele.Utilisateur;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import vue.Fenetre;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class Controle {
    private Fenetre fenetre;
    
    /**
     * constructeur
     */
    public Controle() {
        fenetre = new Fenetre(this); //Initialisation JFrame
        fenetre.setVisible(true); //Ouverture JFrame
    }
    
    /**
     * main
     * simple ouverture du controle
     * @param args
     */
    public static void main(String[] args) {       
        //Ouverture interface graphique        
        new Controle();
    }
  
    /**
     * @param email
     * @param password
     * @return true si on trouve dans la BDD un utilisateur correspondant a la saisie de connexion
     */
    public Boolean demandeConnexion(String email, String password) {
        Utilisateur utilisateur = recupUtilisateur(email, password);       
        return !(utilisateur.getEmail().isEmpty() && utilisateur.getPassword().isEmpty()); 
    }
    
    /**
     * utilisé pour set le titre de la frame
     * @param email
     * @param password
     * @return une chaine de caractère avec prenom et nom de l'utilisateur courrant
     */
    public String utilisateurCourant(String email, String password) {
        Utilisateur utilisateur = recupUtilisateur(email, password);
        return utilisateur.getPrenom() + " " + utilisateur.getNom();
    }
    
    /**
     * utilisé pour créer des listeners que seuls les admins ont
     * @param email
     * @param password
     * @return true si l'utilisateur est un admin
     */
    public Boolean admin(String email, String password) {
        Utilisateur u = recupUtilisateur(email, password);
        return u.getDroit() == 1;
    }
    
    /**
     * @param email
     * @param password
     * @return un utilisateur à partir de son email et password
     */
    public Utilisateur recupUtilisateur(String email, String password) {
        UtilisateurDAO uDAO = new UtilisateurDAO();
        return uDAO.find(email,password);
    }
    
    /**
     * @return tous les utilisateurs de la BDD dans un ArrayList d'utilisateurs
     */
    public ArrayList<Utilisateur> recupUtilisateurs() {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        return utilisateurDAO.find();
    }
    
    /**
     * @return tous les groupes de la BDD
     */
    public ArrayList<Groupe> recupGroupes() {
        GroupeDAO gDAO = new GroupeDAO();
        return gDAO.find();
    }    
    
    /**
     * @param utilisateur
     * @return les données étudiantes de l'utilisateur
     */
    public Etudiant recupEtudiant(Utilisateur utilisateur) {
        EtudiantDAO eDAO = new EtudiantDAO();
        return eDAO.find(utilisateur.getId());
    }

    /**
     * @param utilisateur
     * @return les données enseignantes de l'utilisateur
     */
    public Enseignant recupEnseignant(Utilisateur utilisateur) {
        EnseignantDAO eDAO = new EnseignantDAO();
        return eDAO.find(utilisateur.getId());
    }
    
    /**
     * Permet d'obtenir tout les Types de la BDD
     * @return tous les types de cours de la BDD
     */
    public ArrayList<TypeCours> recupAllTypes(){
        TypeCoursDAO tDAO =new TypeCoursDAO();
        return tDAO.findAllTypes();
    }
    
    /**
     * Permet d'obtenir tout les cours de la BDD
     * @return tous les cours de la BDD
     */
    public ArrayList<Cours> recupAllCours(){
        CoursDAO DAO =new CoursDAO();
        return DAO.findAllCours();
    }
    
    /**
     * Permet d'obtenir tout les salles de la BDD
     * @return toutes les salles de la BDD
     */
    public ArrayList<Salle> recupAllSalles(){
        SalleDAO DAO = new SalleDAO();
        return DAO.findAllSalles();
    }
    
    /**
     * Permet d'obtenir tout les séances de la BDD
     * @return toutes les seances de la BDD
     */
    public ArrayList<Seance> recupAllSeances(){
        SeanceDAO dao = new SeanceDAO();
        return dao.findAllSeances();
    }

    /**
     * utilisé pour set le titre de la frame
     * @param email
     * @param password
     * @return certaines données selon profil
     */
    public String recupInfo(String email, String password) {
        Utilisateur u = recupUtilisateur(email, password);
        String info = new String();
        
        //Si etudiant -> Promo
        if(u.getDroit() == 4) {
            Etudiant e = recupEtudiant(u);
            info = e.getGroupe().getPromotion().getNom();
        }
        //Si enseignant -> "Enseignant"
        if(u.getDroit() == 3) {
            info = "Enseignant";
        }
        //Si référent pédagogique -> "Enseignant - Référent pédagogique"
        if(u.getDroit() == 2) {
            info = "Enseignant - Référent pédagogique";
            fenetre.rendreVisible();
        }
        //Si admin -> "Administrateur"
        if(u.getDroit() == 1) {
            info = "Administrateur";
            fenetre.getEdt().addOngletServicePlanification();
            fenetre.rendreVisible();
        }
        
        return info;
    }
     
    /**
     * Permet d'obtenir tout les prenoms et noms des utilisateurs
     * utilisé pour la recherche du référent par exemple
     * @return un ArrayList de tous les utilisateurs de la BDD
     */
    public ArrayList<String> allUsersToStrings() {
        ArrayList<Utilisateur> utilisateurs = recupUtilisateurs();
        ArrayList<String> preNom = new ArrayList<>();
        
        for(int i=0;i<utilisateurs.size();i++)
            preNom.add(utilisateurs.get(i).getPrenom() + " " + utilisateurs.get(i).getNom());
         
        return preNom;
    }

    /**
     * Permet d'obtenir tout les groupes par nom et nom de la promotion
     * utilisé pour la recherche du référent par exemple
     * @return un ArrayList de tous les groupes de la BDD
     */
    public ArrayList<String> allGroupsToStrings() {
        ArrayList<Groupe> groupes = recupGroupes();
        ArrayList<String> gp = new ArrayList<>();
        
        for(int i=0;i<groupes.size();i++)
            gp.add(groupes.get(i).getNom() + " " + groupes.get(i).getPromotion().getNom());
         
        return gp;
    }
    
    /**
     * Permet d'obtenir tout les noms des types
     * @return un ArrayList de tous les types de cours de la BDD
     */
    public ArrayList<String> allTypeToStrings(){
        ArrayList<TypeCours> types = recupAllTypes();
        ArrayList<String> tp = new ArrayList<>();
        
        for (int i = 0 ; i <types.size(); i++)
            tp.add(types.get(i).getNom());
        return tp;
    }
    
    /**
     * Permet d'obtenir tout les nom des cours de la BDD
     * @return un ArrayList de tous les cours de la BD
     */
    public ArrayList<String> allCoursToStrings(){
        ArrayList<Cours> cours = recupAllCours();
        ArrayList<String> c = new ArrayList<>();
        
        for (int i = 0 ; i <cours.size(); i++)
            c.add(cours.get(i).getNom());
        return c;
    }
    
    /**
     * Permet d'obtenir tout les noms des salles et leur site
     * @return un ArrayList de tous les salles de la BDD
     */
    public ArrayList<String> allSallesToStrings(){
        ArrayList<Salle> salles = recupAllSalles();
        ArrayList<String> s = new ArrayList<>();
        
        for (int i = 0 ; i <salles.size(); i++)
            s.add(salles.get(i).getNom()+" "+ salles.get(i).getSite().getNom());
        return s;
    }
    
    /**
     * Permet d'obtenir tout les eneignants par prénom et nom
     * @return un ArrayList de tous les enseignants de la BDD
     */
    public ArrayList<String> allEnseignantsToStrings(){
        EnseignantDAO eDAO = new EnseignantDAO();
        ArrayList<Enseignant> ens = eDAO.findAllTeacher();
        ArrayList<String> s = new ArrayList<>();
        
        for (int i = 0 ; i <ens.size(); i++)
            s.add(ens.get(i).getPrenom()+" "+ ens.get(i).getNom());
        return s;
    }
    
    /**
     *
     * @return un ArrayList de String (nom des sites)
     */
    public ArrayList<String> allSitesToStrings() {
        SiteDAO sDAO = new SiteDAO();
        return sDAO.allSitesToString();
    }
    
    /**
     * Permet d'obtenir tout les informations des toutes les séance sous forme d'une phrase pour chaque séance
     * @return un ArrayList de toutes les seances de la BDD
     */
    public ArrayList<String> allSeancesToStrings(){
        ArrayList<Seance> seances = recupAllSeances();
        ArrayList<String> string = new ArrayList<>();
        for (int i = 0 ; i < seances.size() ; i++)
        {
            ArrayList<String> temp = seances.get(i).toArrayListOfString();
            String msg = "Seance N°"+seances.get(i).getId()+" "+seances.get(i).getSemaine()+" "+seances.get(i).getDate()+" "+seances.get(i).getHeureDebut()+"-"+seances.get(i).getHeureFin()+" ";
            if(seances.get(i).getEtat() != 2) //si pas valide, car temp est de taille 6
                msg+=temp.get(0)+" "+temp.get(1)+" "+temp.get(2)+" "+temp.get(3)+" "+temp.get(4)+" "+temp.get(5);
            else //Si valide temp est de taille 5
                msg+= temp.get(0)+" "+temp.get(1)+" "+temp.get(2)+" "+temp.get(3)+" "+temp.get(4);
            
            string.add(msg);
        }
        
        return string;
    }

    /**
     * creations des graphes dans Home
     * @param email
     * @param password
     */
    public void creationGraphe(String email, String password) {
/////////////////////////////////////////PREMIER PANEL
////////////////////////////////////////
///////////////////////////////////////
        //GRAPHES SITES
        //////////DEBUT DECLARATION
        ArrayList<Salle> salles = recupAllSalles();
        ArrayList<String> sites = allSitesToStrings();
        ArrayList<ChartPanel> c = new ArrayList<>();
       
        //GRAPHES SEANCES -> combien de cours restant sur l'annee scolaire en cours
        String debut = fenetre.calculAnneeScolaire().substring(0, 4) + "-09-01";
        int pos = fenetre.calculAnneeScolaire().indexOf("/");
        
        Calendar cal = Calendar.getInstance();//date du jour
        int annee = cal.get(Calendar.YEAR) ;
        int mois = cal.get(Calendar.MONTH)+1;
        int jour = cal.get(Calendar.DAY_OF_MONTH);
        int heure = cal.get(Calendar.HOUR_OF_DAY);//heure du jour
        int minute = cal.get(Calendar.MINUTE);//heure du jour
        String debut2 = fenetre.calculAnneeScolaire().substring(pos+1) + "-0"+mois+"-0"+jour;//debut2 pour cours restant
        String fin = fenetre.calculAnneeScolaire().substring(pos+1) + "-08-01";
        //Pour l'utilisateur connecté
        SeanceDAO sDAO = new SeanceDAO();
        Utilisateur u = recupUtilisateur(email, password);
        ArrayList<ChartPanel> t = new ArrayList();
        //ArrayList<ArrayList<Seance>> seancesheure = sDAO.findSeancesOfUserByDate(1, debut, fin);//heure
        ArrayList<ArrayList<Seance>> seancesheure = sDAO.findAllSeancesByDate(debut, fin);//heure

        ArrayList<Cours> cours = recupAllCours();
        int calculheuretotal = 0; //heure
        int comparaison = 0;
        
        /////////FIN DECLARATION
        
        DefaultPieDataset pieDataset = new DefaultPieDataset();//eiffel 1
        
        for (int i = 0 ; i <salles.size(); i++){
            if(salles.get(i).getSite().getNom().equals("Eiffel 1"))
            pieDataset.setValue("Site: "+salles.get(i).getSite().getNom()+","+salles.get(i).getNom()+",Capacité :"+salles.get(i).getCapacite(), new Integer(salles.get(i).getCapacite()));
           }

        DefaultPieDataset pieDataset2 = new DefaultPieDataset();//eiffel 1
        DefaultPieDataset pieDataset5 = new DefaultPieDataset();//heure
        
        for (int i = 0 ; i <salles.size(); i++){
            if(salles.get(i).getSite().getNom().equals("Eiffel 2"))
            pieDataset.setValue("Site: "+salles.get(i).getSite().getNom()+","+salles.get(i).getNom()+",Capacité :"+salles.get(i).getCapacite(), new Integer(salles.get(i).getCapacite()));
        }
        
        for (int i = 0 ; i <seancesheure.size(); i++){
            for(int j=0;j<seancesheure.get(i).size();j++){
            if(calculheuretotal!=60){
                comparaison += Integer.parseInt(seancesheure.get(i).get(0).calculDuree().substring(0,1));
                calculheuretotal += Integer.parseInt(seancesheure.get(i).get(0).calculDuree().substring(2));
                if(calculheuretotal==60){
                     comparaison += 1 ;
                     calculheuretotal = 0;
                }
            }    
            }
            if (i+1 < seancesheure.size() ){
                if(!seancesheure.get(i+1).get(0).getCours().getNom().equals(seancesheure.get(i).get(0).getCours().getNom())){
                    if(calculheuretotal!=0)
                        pieDataset5.setValue(" Nombre d'Heure total du cours "+ seancesheure.get(i).get(0).getCours().getNom()+" est: "+ comparaison + "h"+calculheuretotal, new Integer(comparaison));
                    if(calculheuretotal==0)
                        pieDataset5.setValue(" Nombre d'Heure total du cours "+ seancesheure.get(i).get(0).getCours().getNom()+" est: "+ comparaison+"h", new Integer(comparaison));
                    comparaison=0;
                    calculheuretotal=0;
                }   
            }
            if(i == seancesheure.size()-1){
               if(calculheuretotal!=0)
                   pieDataset5.setValue(" Nombre d'Heure total du cours "+ seancesheure.get(i).get(0).getCours().getNom()+" est: "+ comparaison + "h"+calculheuretotal, new Integer(comparaison));
               if(calculheuretotal==0)
                   pieDataset5.setValue(" Nombre d'Heure total du cours "+ seancesheure.get(i).get(0).getCours().getNom()+" est: "+ comparaison+"h", new Integer(comparaison));
               comparaison=0;
               calculheuretotal=0;
            }  
                
            }     

/////////////////////////////////////////DEUXIEME PANEL
////////////////////////////////////////
///////////////////////////////////////

        JFreeChart chart = ChartFactory.createPieChart("Capacité des salles pour tous les sites", pieDataset, true, true, true);//eiffel 1
        PiePlot P=(PiePlot)chart.getPlot();
        ChartPanel p = new ChartPanel(chart);
        c.add(p); 
        
        JFreeChart chart5 = ChartFactory.createPieChart("Nombres d'heures total d'un cours dans un semestre", pieDataset5, true, true, true);//eiffel 1
        PiePlot P5=(PiePlot)chart5.getPlot();
        ChartPanel p5 = new ChartPanel(chart5);
        c.add(p5);
        
        if(u.getDroit() != 1) {
            ArrayList<ArrayList<Seance>> seances = sDAO.findSeancesOfUserByDate(u.getId(), debut, fin);//cours total
            ArrayList<ArrayList<Seance>> seances2 = sDAO.findSeancesOfUserByDate(u.getId(), debut2, fin);//cours restant

            int compteur = 0;//cours total
            int compteur2=0;//cours restant
            
            DefaultPieDataset pieDataset3 = new DefaultPieDataset();//cours total
            DefaultPieDataset pieDataset4 = new DefaultPieDataset();//cours restant
            
            int blindage=0;
            int pas_afficher=0;//variable qui permet de na pas afficher la seance si la dat est la meme et si lheure n'est pas superieur
            
            for(int i=0;i<seances.size();i++) {//graphe seance 1
                for(int j=0;j<seances.get(i).size();j++) {
                        compteur++; //Compte le nombre de seances pour un cours
                }
                if (i+1 < seances.size() ){
                    if(!seances.get(i+1).get(0).getCours().getNom().equals(seances.get(i).get(0).getCours().getNom())){
                        //On peut prendre j = 0 car pour tout j il aura le même nom
                        pieDataset3.setValue("Séance : "+seances.get(i).get(0).getCours().getNom()+" Nombre de séances: " + compteur, new Integer(compteur));
                        compteur=0;
                    }
                }
                if(i == seances.size()-1){
                    pieDataset3.setValue("Séance : "+seances.get(i).get(0).getCours().getNom()+" Nombre de séances: " + compteur, new Integer(compteur));
                    compteur=0;
                } 
            }
            for(int l=0;l<seances2.size();l++) { //graphe seance 2
                String dateblindage=seances.get(l).get(0).getDate();
                int heureblindage=Integer.parseInt(seances.get(l).get(0).getHeureDebut().substring(0,2));
                    for(int j=0;j<seances2.get(l).size();j++) {
                        if(debut2.equals(dateblindage)){
                            if(heureblindage > heure){
                                compteur2++;
                            }else 
                                pas_afficher++; 
                        }
                        else 
                            compteur2++; //Compte le nombre de seances pour un cours
                    }
                    if (l+1 < seances2.size() ){
                        if(!seances2.get(l+1).get(0).getCours().getNom().equals(seances2.get(l).get(0).getCours().getNom())){
                            //On peut prendre j = 0 car pour tout j il aura le même nom
                            if(pas_afficher==0){
                                pieDataset4.setValue("Séance : "+seances2.get(l).get(0).getCours().getNom()+" Nombre de séances: " + compteur2, new Integer(compteur2));
                                compteur2=0;
                            }else
                                pas_afficher=0;
                        }
                    }
                    if(l == seances2.size()-1){
                        if(pas_afficher==0){
                            pieDataset4.setValue("Séance : "+seances2.get(l).get(0).getCours().getNom()+" Nombre de séances: " + compteur2, new Integer(compteur2));
                            compteur2=0;
                        }else
                            pas_afficher=0;
                    }      
            }

            JFreeChart chart2 = ChartFactory.createPieChart("Nombres de séances par cours", pieDataset3, true, true, true);
            PiePlot P3 =(PiePlot)chart2.getPlot();
            ChartPanel p3 = new ChartPanel(chart2);
            t.add(p3);
            
            JFreeChart chart4 = ChartFactory.createPieChart("Nombres de séances par cours restant de l'année", pieDataset4, true, true, true);
            PiePlot P4 =(PiePlot)chart4.getPlot();
            ChartPanel p4 = new ChartPanel(chart4);
            t.add(p4);
            
        }
        else {
            ArrayList<Seance> seances = recupAllSeances();
            int compteur = 0;
            int compteur2 = 0;//cours restant
            int blindage_date = 0;
            
            DefaultPieDataset pieDataset53 = new DefaultPieDataset();
            DefaultPieDataset pieDataset54 = new DefaultPieDataset();//couurs restant
            
            for(int j=0;j<seances.size();j++) {
                compteur++; //Compte le nombre de seances pour un cours
                pieDataset.setValue(seances.get(j).getCours().getNom(), new Integer(compteur));
                
                blindage_date = Integer.parseInt(seances.get(j).getDate());
                System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC" + blindage_date);
                if((seances.get(j).getDate()).equals(cal))
                {
                    
                }
            }
            JFreeChart chart53 = ChartFactory.createPieChart("Nombres de séances par cours", pieDataset53, true, true, true);
            PiePlot P53 =(PiePlot)chart53.getPlot();
            ChartPanel p53 = new ChartPanel(chart53);
            t.add(p53);
        } 
        fenetre.ajouterGraphes(c, t);
    } 
    
    /**
     *
     * @param recherche
     * @param semaine
     */
    public void rechercheUtilisateur(String recherche, int semaine) {
        //Saisi d'un nom et prenom, peut etre pas complets
        int pos = recherche.indexOf(" ");
        UtilisateurDAO uDAO = new UtilisateurDAO();
        Utilisateur u = new Utilisateur();
        if(pos != -1) {
            String prenom = recherche.substring(0, pos-1);
            String nom = recherche.substring(pos+1);
            u = uDAO.findByName(prenom, nom);
        }
        //Saisi d'un nom ou prenom, peut etre pas complet
        else {
            u = uDAO.findForSearch(recherche);
        }
        
        if(u.getEmail() != null && u.getPassword() != null) {
            seancesEdt(semaine, u.getEmail(), u.getPassword());
        }
            
    }
    
    /**
     * Calcul l'heure de fin à partir de l'heure de début
     * @param debut
     * @return l'heure de fin en ajoutant 1h30 à l'heure de début d'une séance
     */
    public String calculHeureFin(String debut)
    {
        Calendar cal = Calendar.getInstance();
        cal.set(2020,01,01, Integer.parseInt(debut.substring(0,2)), Integer.parseInt(debut.substring(3,5)),0); //Date au pif
        cal.add(Calendar.HOUR_OF_DAY, 1);
        cal.add(Calendar.MINUTE, 30);
        Date date = cal.getTime();
        String fin = date.toString().substring(11,19); //On récupère la position de l'heure
        return fin;
    }
    
    /**
     * Demande d'ajout d'une séance par la vue vers le controleur en récupérant tout les données nécessaires saisies par l'user
     * @param strings 
     */
    public void demandeAddSeance(ArrayList<Object> strings)
    {   
        SalleDAO salleDAO = new SalleDAO();
        GroupeDAO gDAO = new GroupeDAO();
        EnseignantDAO uDAO = new EnseignantDAO();
        TypeCoursDAO tDAO = new TypeCoursDAO();
        CoursDAO cDAO = new CoursDAO();
        if(strings.size() != 9)
            System.out.println("Que "+strings.size()+" Champs sur 9 obligatoires sont remplis");
        else{
            int semaine = Integer.parseInt((String)strings.get(0));//Semaine
            String heureDebut = (String)strings.get(1);//Heure de début
            String heureFin = calculHeureFin(heureDebut);//Heure de fin
            String date = (String)strings.get(2);//Date
            int etat = Integer.parseInt((String)strings.get(3));//Etat
            Cours cours = cDAO.findByName((String)strings.get(4));//Cours
            TypeCours type = tDAO.findByName((String)strings.get(5));//Type
            
            List list;
            ArrayList<Enseignant> enseignants = new ArrayList<>();    
            if(strings.get(6) != null)
            {
                list = (List)strings.get(6);                       
                for (Iterator it = list.iterator() ; it.hasNext(); ){
                    enseignants.add(uDAO.findByName((String)it.next()));//Enseignants
                }
            }
            
            ArrayList<Groupe> groupes = new ArrayList<>();
            if(strings.get(7) != null)
            {
                list = (List)strings.get(7);
                for (Iterator it = list.iterator() ; it.hasNext(); ){
                    groupes.add(gDAO.findByName((String)it.next()));//Groupes
                }
            }
            
            ArrayList<Salle> salles = new ArrayList<>();
            if(strings.get(8) != null)
            {
                list = (List)strings.get(8);
                for (Iterator it = list.iterator() ; it.hasNext(); ){
                    salles.add(salleDAO.findByName((String)it.next()));//Salles
                }
            }
            ajouterSeanceInModel(semaine,date,heureDebut,heureFin,etat,cours,type,groupes, enseignants,salles);
        }
    }
    
    /**
     * Le controleur demande au model l'ajout d'une séance en lui fournissant les données issues de la vue
     * le modèle renvoie des réponses vers le controleur pour savoir si tels données sont acceptables et si 
     * toutes les données saisies sont cohérentes
     * @param Semaine
     * @param Date
     * @param Heure_Debut
     * @param Heure_Fin
     * @param Etat
     * @param cours
     * @param type
     * @param groupes
     * @param enseignants
     * @param salles 
     */
    public void ajouterSeanceInModel(int Semaine, String Date, String Heure_Debut, String Heure_Fin, int Etat, Cours cours,TypeCours type, ArrayList<Groupe> groupes, ArrayList<Enseignant> enseignants, ArrayList<Salle> salles) 
    {   
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance = new Seance(Semaine, Heure_Debut, Heure_Fin,Date, Etat, cours, type); //instanciation de la nvlle seance avec les premiers données
        boolean okForCreate = true; //On considère au début que tout est ok pour créer cette séance dans la BDD
        //On ajoute les salles en accord avec leur créneau dispo/Duplication sans vérif la capa car rien à vérifier au début vu qu'aucun grp n'ai encore add dans séance
        for (int i = 0 ; i < salles.size();i++)
        {
            if (sDAO.canIAjouterSalleSeance(seance, salles.get(i).getId()))
               seance.ajouterSalle(salles.get(i)); 
            else{
                okForCreate = false;
                i = 100; // Dès qu'il y a un false on arrête tout
            }
        }
        if(okForCreate) //Si les vérifs des salles sont bon, on continue
        {
            for (int i = 0; i <Math.max(groupes.size(), enseignants.size()); i++) // Pour éviter 2 for
            {
                if (i< groupes.size())
                {
                    //Si groupes non duplication/pbl crénaux /Pas de pbl de place, tout est bon
                    if (sDAO.canIAjoutGroupeSeance(seance, groupes.get(i).getId()))
                        seance.ajouterGroupe(groupes.get(i));
                    else{
                        okForCreate = false;
                        i = 1000; //On arrete tout
                    }
                }
                if (i<enseignants.size())
                {   //Si enseignants non duplication/pbl crénaux tout est bon
                    if ( sDAO.canIAjouterEnseignantSeance(seance, enseignants.get(i).getId()))
                        seance.ajouterEnseignant(enseignants.get(i));
                    else{
                        okForCreate = false;
                        i = 1000; //On arrete tout
                    }
                }
            }
        }
        if (okForCreate) //Si tout les conditions sont réunis, on create, si il y a eu un faux, on ne create pas.
        {
            seance = sDAO.create(seance);
            System.out.println("Ajouter avec succes");
            majAllSeances();
        }else{
            System.out.println("La seance n'a pas été ajouté");
        }
    }
    
    /**
     * Mise à jour au niveau visuel si toutes les données sont cohérentss
     */
    public void majAllSeances()
    {   //Du controleur à la vue
        fenetre.remplirListSeances();
    }
   
    /**
     * Demande de la vue pour savoir quelles sont les informations qui doivent être séléctionner dans le vue dans l'ongletGererCoursSP
     * en fournissant au controleur l'id de cette séance
     * @param id_seance
     * @return 
     */
    public ArrayList<Object> demandeInfosSelectedSeance(int id_seance)
    {   //de la vue au controleur
        ArrayList<Object> strings = new ArrayList<>();
        SeanceDAO sDAO = new SeanceDAO();
        //Obtention données de modèle pour répondre à la requete de la vue
        Seance seance = sDAO.find(id_seance);
        
        strings.add(seance.getDate()+" "+seance.getHeureDebut());
        strings.add(seance.getEtat());
        strings.add(seance.getCours().getNom());
        strings.add(seance.getTypeCours().getNom());
        
        ArrayList<String> e = new ArrayList<>();
        ArrayList<String> g = new ArrayList<>();
        ArrayList<String> s = new ArrayList<>();
        for (int i = 0 ; i < Math.max(seance.getEnseignants().size(),Math.max(seance.getGroupes().size(),seance.getSalles().size()));i++)
        {
            if(i < seance.getEnseignants().size())
                e.add(seance.getEnseignants().get(i).getPrenom()+ " "+seance.getEnseignants().get(i).getNom());
            if(i< seance.getGroupes().size())
                g.add(seance.getGroupes().get(i).getNom()+" "+seance.getGroupes().get(i).getPromotion().getNom());
            if(i<seance.getSalles().size())
                s.add(seance.getSalles().get(i).getNom()+ " "+seance.getSalles().get(i).getSite().getNom());
        }
        strings.add(e);
        strings.add(g);
        strings.add(s);        
        
        //On retourne les données reçus à la vue
        return strings;
    }
    
    /**
     * Requête de la vue pour modifier une séance en fournissant toutes les infos nécessaires saisies par l'user 
     * @param idSeance
     * @param strings 
     */
    public void demandeModifSeance(int idSeance, ArrayList<Object> strings)
    {   //Contenu du strings:
        // 0: Semaine, 1: Heure de début, 2: Date, 3: Etat, 4: Cours, 5: Type, 6:Enseignants, 7:Groupes, 8: Salles
        if(strings.size() == 9)//Ceux qui ne sont pas rempli sont déclaré à null, donc on a toujours = 9
        {
            SeanceDAO seanceDAO = new SeanceDAO();
            Seance seance = seanceDAO.find(idSeance);
            boolean okEtat = true;
            boolean okEnseignants = true;
            boolean okGroupesSalles = true;
            
            //Changement de date localement (si pas changé, ça ne fait que l'écraser)
            seance.setSemaine(Integer.parseInt((String)strings.get(0)));
            String heureDebut = (String)strings.get(1);
            seance.setHeureDebut(heureDebut);
            seance.setDate((String)strings.get(2));
            seance.setHeureFin(calculHeureFin(heureDebut));
            //Etape 1 Verification si tout les données entrées sont cohérents
            okEtat = verifAndSetSeanceEtat(seance,(String)strings.get(3),((ArrayList<String>)strings.get(8)).size(),((ArrayList<String>)strings.get(6)).size());
            okEnseignants = verifSeanceEnseignants(seance,(ArrayList<String>)strings.get(6));
            //Ensemble salles et groupes car chacun ont bsn de voir les capa de l'autre
            okGroupesSalles = verifSeanceGroupesEtSalles(seance,(ArrayList<String>)strings.get(7),(ArrayList<String>)strings.get(8)); 
            
            //Etape 2 Changement de données
            if(okEtat && okEnseignants && okGroupesSalles)
            {  
                System.out.println("Verification RAS, modification des données OK pous séance : "+idSeance);
                //Changement de données dans la variable seance (localement) et dans celui BDD
                //Etat est déjà set localement
                setSeanceCoursNom(seance,(String)strings.get(4));
                setSeanceCoursType(seance,(String)strings.get(5));
                setSeanceEnseignants(seance,(ArrayList<String>)strings.get(6));
                setSeanceGroupes(seance,(ArrayList<String>)strings.get(7));
                setSeanceSalles(seance, (ArrayList<String>)strings.get(8));
                seance = seanceDAO.update(seance); //Pour ceux qui on été changé juste localement
            }
        }
        else{
            System.out.println("Un champ n'est pas saisie correctement");
        }
        majAllSeances();
    }

    /**
     * Vérification/Changement d'état pour une séance sans le changer dans la BDD
     * @param seance
     * @param tailleGroupe
     * @param tailleEnseignant
     * @param choix 
     * @return  
     */
    public boolean verifAndSetSeanceEtat(Seance seance, String choix, int tailleGroupe, int tailleEnseignant)
    {
        switch(choix)
        {
            case "1":
            {
                seance.setEtat(1);
                return true;
            }
            case "2":
            {
                //LES CONDITIONS
                if(tailleEnseignant != 0 && tailleGroupe != 0){
                    seance.setEtat(2);
                    return true;
                }
                else{System.out.println("On ne peux pas valider cette séance car il faut au minimum un enseignant et un groupe");} 
                break;
            }
            case "3":
            {
                seance.setEtat(3);
                return true;
            }
        }
        return false;
    }

    /**
     * Verification des edt des enseignants selectionnés pour une séance donnée
     * @param seance
     * @param namesSelected
     * @return 
     */
    public boolean verifSeanceEnseignants(Seance seance, ArrayList<String> namesSelected){
        EnseignantDAO eDAO = new EnseignantDAO();
        SeanceDAO sDAO = new SeanceDAO();
        for(int i = 0 ; i < namesSelected.size();i++)
        {
            Enseignant tampon = eDAO.findByName(namesSelected.get(i));
            if(sDAO.isTeacherNotFreeForThisSeance(tampon.getId(), seance))
            {
                System.out.println("pbl edt du prof : "+tampon.getPrenom()+ " "+tampon.getNom());
                return false;
            }
        }
        //Si aucune erreur de créneau n'est rencontré pour ces enseignants, (si on est encore dans la méthode), on continue
        return true;
    }

    /**
     * Verification des edt des groupes et salles selectionnés pour une séance donnée
     * @param seance
     * @param groupesSelected
     * @param sallesSelected
     * @return 
     */
    public boolean verifSeanceGroupesEtSalles(Seance seance, ArrayList<String> groupesSelected, ArrayList<String> sallesSelected)
    {
        SalleDAO salleDAO = new SalleDAO();
        GroupeDAO grpDAO = new GroupeDAO();
        SeanceDAO sDAO = new SeanceDAO();
        
        boolean okForGrps  = false; //On part de base que tout est faux
        boolean okForSalles = false;//On part de base que tout est faux
        
        //Vérif créneau groupes + calcule capa total groupes
        int capaciteGroupe = 0;
        for (int i = 0 ; i < groupesSelected.size() ;i++)
        {   //Vérification du créneau de chaque groupe
            Groupe tampon = grpDAO.findByName(groupesSelected.get(i));
            capaciteGroupe += sDAO.find_capacite_groupes_total(tampon.getId(), 0); //CapacitéTotal += capacité de chaque groupe
            if(sDAO.isGroupNotFreeForThisSeance(tampon.getId(), seance)) //Si un groupe n'est pas libre
            {
                System.out.println("pbl edt du groupe : "+tampon.getNom()+ " "+tampon.getPromotion().getNom());
                return false; //Faux d'office
            }
        }
        //Verif créneau salles + calcule capa total salles
        int capaciteSalle = 0;
        for (int i = 0 ; i < sallesSelected.size(); i++)
        {   //Vérification du créneau de chaque salle
            Salle tampon = salleDAO.findByName(sallesSelected.get(i));
            capaciteSalle += tampon.getCapacite(); //On calcule la capacité total (Pour plus tard si tout est bon)
            if(sDAO.isSalleNotFreeForThisSeance(tampon.getId(), seance)) //Une salle n'est pas libre
            {
                System.out.println("pbl edt de la salle : "+tampon.getNom()+ " "+tampon.getSite().getNom());
                return false; //Faux d'office
            }
        }
        //Si tout est bon nv horaire (si on a pas quitté la méthode)
        //Cas groupes
        if(sallesSelected.isEmpty()) //Si aucune salle n'est selectionné, pas bsn de vérif capa groupe et salle
        {
            okForGrps = true;
        }else{ //Si des salles sont déjà affectés à cette séance, on est oblgés de vérifier la capacité
            if(capaciteGroupe <= capaciteSalle) //Capa groupe doit être <= capa salles
            {
                okForGrps = true;
            }else{
                System.out.println("Ensemble de groupe trop grand");
            }
        }
        //Cas salles
        if(groupesSelected.isEmpty()) 
        {   
            okForSalles = true;
        }else{ //Si des groupes sont dans cette séance
            if(capaciteSalle >= capaciteGroupe) //On vérifie si toute les salles peuvent supporter le nb
            {   //Si les salles supportent les groupes
                okForSalles = true;
            }
            else
                System.out.print("Capacité insuffisante");
        }
        if(okForGrps && okForSalles) //Si tout est Ok
        {
            return true;
        }
        return false;
    }

    /**
     * Requête de MAJ du nom de cours d'une séance donnée dans la BDD
     * @param seance
     * @param cours 
     */
    public void setSeanceCoursNom(Seance seance, String cours) {
        CoursDAO cDAO = new CoursDAO();
        SeanceDAO sDAO = new SeanceDAO();
        seance.setCours(cDAO.findByName(cours));
    }

    /**
     * Requête de MAJ du type de cours d'une séance donnée dans la BDD
     * @param seance
     * @param type 
     */
    public void setSeanceCoursType(Seance seance, String type) {
        TypeCoursDAO tDAO = new TypeCoursDAO();
        SeanceDAO sDAO = new SeanceDAO();
        seance.setTypeCours(tDAO.findByName(type));
    }

    /**
     * Requête de MAJ des enseignants d'une séance donnée dans la BDD
     * @param seance
     * @param namesSelected 
     */
    public void setSeanceEnseignants(Seance seance,ArrayList<String> namesSelected)
    {
        EnseignantDAO eDAO = new EnseignantDAO();
        SeanceDAO sDAO = new SeanceDAO();
        //On supprime tout, pour éviter tout doublons
            for (int i = 0 ; i < seance.getEnseignants().size() ; i++)
                sDAO.DeleteInJonction(seance.getId(), seance.getEnseignants().get(i).getId(), 1);
            if(!namesSelected.isEmpty()) //Si c'est pas vide, on re/ajoute tous les enseignants saisies pas l'user
                for (int i = 0 ; i < namesSelected.size() ; i++)
                    sDAO.insertInJonction(seance.getId(), eDAO.findByName(namesSelected.get(i)).getId(), 1);
    }

    /**
     * Requête de MAJ des groupes d'une séance donnée dans la BDD
     * @param seance
     * @param groupesSelected 
     */
    public void setSeanceGroupes(Seance seance,ArrayList<String> groupesSelected)
    {
        GroupeDAO grpDAO = new GroupeDAO();
        SeanceDAO sDAO = new SeanceDAO();
        for (int i = 0; i < seance.getGroupes().size() ; i++)
            sDAO.DeleteInJonction(seance.getId(), seance.getGroupes().get(i).getId(), 2);//supprime tout les groupes de la séance
        for (int i = 0; i < groupesSelected.size() ; i++)
            sDAO.insertInJonction(seance.getId(), grpDAO.findByName(groupesSelected.get(i)).getId(), 2);//On ajoute tt les groupes selectionnées
     
    }
    
    /**
     * Requête de MAJ des salles d'une séance donnée dans la BDD
     * @param seance
     * @param sallesSelected 
     */
    public void setSeanceSalles(Seance seance,ArrayList<String> sallesSelected)
    {
        SalleDAO salleDAO = new SalleDAO();
        SeanceDAO sDAO = new SeanceDAO();
        for (int i = 0 ; i < seance.getSalles().size() ; i++) {
            sDAO.DeleteInJonction(seance.getId(), seance.getSalles().get(i).getId(), 3);//Suppression de toute les salles de cette séance dans la BDD 
        }
        for (int i = 0 ; i < sallesSelected.size() ; i++) {
            sDAO.insertInJonction(seance.getId(), salleDAO.findByName(sallesSelected.get(i)).getId(), 3); //On ajoute tout ce qui a été select

        }    
    }
    
    /**
     * affichage de l'emploi du temps dans l'onglet Cours
     * @param semaine
     * @param prenom
     * @param nom
     */
    public void majSeancesEdt(int semaine, String prenom, String nom) {
        UtilisateurDAO uDAO = new UtilisateurDAO();
        Utilisateur u = uDAO.findByName(prenom, nom);
        //System.out.println("dans maj seances edt semaine prenom nom" + u.getPrenom() + " " + u.getNom());
        seancesEdt(semaine, u.getEmail(), u.getPassword());
    }
    
    /**
     * affichage de l'emploi du temps dans l'onglet Cours
     * @param semaine
     * @param prenom
     * @param nom
     */
    public void majSeancesListe(int semaine, String prenom, String nom) {
        UtilisateurDAO uDAO = new UtilisateurDAO();
        Utilisateur u = uDAO.findByName(prenom, nom);
        //System.out.println("dans maj seances edt semaine prenom nom" + u.getPrenom() + " " + u.getNom());
        seancesListe(semaine, u.getEmail(), u.getPassword());
    }
    
    /**
     * recup et affichage des séances sur une semaine
     * @param semaine
     * @param email
     * @param password
     */
    public void seancesListe(int semaine, String email, String password) {
        System.out.println("\nSEANCES LISTE - On veut afficher les seances de " + email);
        SeanceDAO sDAO = new SeanceDAO();
        Etudiant et = new Etudiant();
        Enseignant en = new Enseignant();
        ArrayList<Seance> seances = new ArrayList<>(); //Conteneur de seances
        String strSeances; //Conteneur des string relative a une seance
        //On récupère l'utilisateur
        Utilisateur u = recupUtilisateur(email, password);
        
        //On récupère les données de l'utilisateurs selon son profil (étudiant, enseignant dont référent)
        if(u.getDroit() == 3 || u.getDroit() == 2) {
            System.out.println("enseignant");
            //On ne récupère que les séances de la semaine courante
            en = recupEnseignant(u);
            en.setSeances(sDAO.findSeancesByUserAndWeek(en.getId(), semaine));
            seances = en.getSeances();
            
            if(u.getDroit() == 3) {
                //Ne doit pas voir les séances en cours de validation (etat = 1)
                for(int i=0;i<seances.size();i++) {
                    /*System.out.println("Taille seances = " + seances.size());
                    System.out.print("Etat = " + seances.get(i).getEtat() + " ");
                    System.out.println(seances.get(i).toString());
                    System.out.println("Tour de boucle" + i);*/
                    if(seances.get(i).getEtat() == 1) {
                        seances.remove(i); //Effacer la séance
                        System.out.println("Cette séance est en cours de validation, on ne l'affiche pas");
                        i--; //On retourne une case en arrière
                    }
                }
            }
        }
        
        if(u.getDroit() == 4) {
            et = recupEtudiant(u);
            et.setSeances(sDAO.findSeancesByUserAndWeek(et.getId(), semaine));
            seances = et.getSeances();
            
            //Ne doit pas voir les séances en cours de validation (etat = 1)
            for(int i=0;i<seances.size();i++) {
                /*System.out.println("Taille seances = " + seances.size());
                System.out.print("Etat = " + seances.get(i).getEtat() + " ");
                System.out.println(seances.get(i).toString());
                System.out.println("Tour de boucle" + i);*/
                if(seances.get(i).getEtat() == 1) {
                    seances.remove(i); //Effacer la séance
                    System.out.println("Cette séance est en cours de validation, on ne l'affiche pas");
                    i--; //On retourne une case en arrière
                }
            }
        }
                      
        for(int j=seances.size()-1;j>-1;j--) { //Pour toutes les seances            
            String dateBDD = seances.get(j).getDate();
            System.out.println(dateBDD); //AAAA-MM-JJ
                
            //Convertir la dateBDD en jour
            String jourBDD = dateBDD.substring(8, 10); 
            System.out.println(jourBDD);
            
            //LIGNE DEBUT
            for(int i=0;i<fenetre.getListeCours().getRowCount();i++) { 
                String date = fenetre.getListeCours().getValueAt(i, 0).toString(); 
                String jourEdt = date.substring(5, 7);
                if(jourEdt.endsWith(" ")) {
                    jourEdt = "0" + jourEdt.substring(0, 1);
                }
                                    
                if(jourEdt.equals(jourBDD)) { //Si l'heure correspond, récupérer la ligne
                    System.out.println("Ces deux jour sont pareils : " + jourBDD + " et " + jourEdt);
                    strSeances = seances.get(j).getHeureDebut() + " à " + seances.get(j).getHeureFin() + " " + seances.get(j).toString();
                    Vector os = null;
                    ((DefaultTableModel) fenetre.getListeCours().getModel()).insertRow(i+1, os);
                    fenetre.getListeCours().setValueAt(strSeances, i+1, 0);
                    i++;
                }
            }
        } 
    }
    
    /**
     * recup et affichage des séances sur une semaine
     * @param semaine
     * @param email
     * @param password
     */
    public void seancesEdt(int semaine, String email, String password) {
        //System.out.println("\nSEANCES EDT - On veut afficher les seances de " + email);
        SeanceDAO sDAO = new SeanceDAO();
        Etudiant et = new Etudiant();
        Enseignant en = new Enseignant();
        ArrayList<Seance> seances = new ArrayList<>(); //Conteneur de seances
        ArrayList<String> strSeances; //Conteneur des string relative a une seance
        //On récupère l'utilisateur
        Utilisateur u = recupUtilisateur(email, password);
        
        //On récupère les données de l'utilisateurs selon son profil (étudiant, enseignant dont référent)
        if(u.getDroit() == 3 || u.getDroit() == 2) {
            //System.out.println("enseignant");
            //On ne récupère que les séances de la semaine courante
            en = recupEnseignant(u);
            en.setSeances(sDAO.findSeancesByUserAndWeek(en.getId(), semaine));
            seances = en.getSeances();
            
            if(u.getDroit() == 3) {
                //Ne doit pas voir les séances en cours de validation (etat = 1)
                for(int i=0;i<seances.size();i++) {
                    /*System.out.println("Taille seances = " + seances.size());
                    System.out.print("Etat = " + seances.get(i).getEtat() + " ");
                    System.out.println(seances.get(i).toString());
                    System.out.println("Tour de boucle" + i);*/
                    if(seances.get(i).getEtat() == 1) {
                        seances.remove(i); //Effacer la séance
                        System.out.println("Cette séance est en cours de validation, on ne l'affiche pas");
                        i--; //On retourne une case en arrière
                    }
                }
            }
        }
        
        if(u.getDroit() == 4) {
            et = recupEtudiant(u);
            et.setSeances(sDAO.findSeancesByUserAndWeek(et.getId(), semaine));
            seances = et.getSeances();
            
            //Ne doit pas voir les séances en cours de validation (etat = 1)
            for(int i=0;i<seances.size();i++) {
                /*System.out.println("Taille seances = " + seances.size());
                System.out.print("Etat = " + seances.get(i).getEtat() + " ");
                System.out.println(seances.get(i).toString());
                System.out.println("Tour de boucle" + i);*/
                if(seances.get(i).getEtat() == 1) {
                    seances.remove(i); //Effacer la séance
                    //System.out.println("Cette séance est en cours de validation, on ne l'affiche pas");
                    i--; //On retourne une case en arrière
                }
            }
        }
        
        /*for(int i=0;i<seances.size();i++) {
                System.out.println("Taille seances = " + seances.size());
                System.out.print("Etat = " + seances.get(i).getEtat() + " ");
                System.out.println(seances.get(i).toString());
                System.out.println("Tour de boucle" + i);
        }*/
       
        //Trouver la ligne -> HEURE - Récuperer string, comparer a ce qui est dans seances de l'utilisateur
        int ligne1 = 0; int ligne2 = 0; int colonne = 0; 
        
        for(int j=0;j<seances.size();j++) { //Pour toutes les seances
            String heure1 = seances.get(j).getHeureDebut();
            //System.out.println("Heure début de la seance " + heure1);
            
            String heure2 = seances.get(j).getHeureFin();
            //System.out.println("Heure fin de la seance " + heure2);
            
            //Convertir l'heure1BDD en heureEdt : 12:00:00 -> 12h00
            String heure1BDD = heure1.substring(0, 2) + "h" + heure1.substring(3, 5); 
            //System.out.println(heure1BDD);
            
            //Convertir l'heure2BDD en heureEdt : 12:00:00 -> 12h00
            String heure2BDD = heure2.substring(0, 2) + "h" + heure2.substring(3, 5); 
            //System.out.println(heure2BDD);
            
            String date = seances.get(j).getDate();
            //System.out.println(date); //AAAA-MM-JJ
                
            //Convertir la dateBDD en jour
            String jourBDD = date.substring(8, 10); 
            //System.out.println(jourBDD);
            
            //LIGNE DEBUT
            for(int i=0;i<fenetre.getEdtCours().getRowCount();i++) { 
                String heureEdt = fenetre.getEdtCours().getValueAt(i, 0).toString(); //08h00 dans EDT -> 08:00:00 dans BDD
                
                if(heureEdt.equals(heure1BDD)) { //Si l'heure correspond, récupérer la ligne
                    //System.out.println("DEBUT - Ces deux heures sont pareilles : " + heure1BDD + " et " + heureEdt);
                    ligne1 = i;
                }
            }
            
            //LIGNE FIN
            for(int i=0;i<fenetre.getEdtCours().getRowCount();i++) { 
                String heureEdt = fenetre.getEdtCours().getValueAt(i, 0).toString(); //08h00 dans EDT -> 08:00:00 dans BDD
                
                if(heureEdt.equals(heure2BDD)) { //Si l'heure correspond, récupérer la ligne
                    //System.out.println("FIN - Ces deux heures sont pareilles : " + heure2BDD + " et " + heureEdt);
                    ligne2 = i;
                }
            }
            
            for(int i=0;i<fenetre.getEdtCours().getColumnCount();i++) { //Pour chaque ligne
                String entete = fenetre.getEdtCours().getModel().getColumnName(i);
                String jourEdt = entete.substring(5, 7);
                if(jourEdt.endsWith(" ")) {
                    jourEdt = "0" + jourEdt.substring(0, 1);
                }
                                    
                if(jourEdt.equals(jourBDD)) { //Si l'heure correspond, récupérer la ligne
                    //System.out.println("Ces deux jour sont pareils : " + jourBDD + " et " + jourEdt);
                    colonne = i;
                }
            }
            
            //Un cours dure forcément 1h30 = 6 cases
            strSeances = seances.get(j).toArrayListOfString();
            
            //System.out.println("strSeances :");
            
            //System.out.println(strSeances);
            //RAPPEL cf. méthode dans Seance.java
            //seance[0] = etat ; seance[1] = intitulé du cours; seance[2] = enseignants ; 
            //seance[3] = groupes; seance[4] = salles; seance[5] = type du cours;
            //!! SI LA SEANCE EST VALIDEE = 5 CASES
            
            if(colonne != 0)  {
                //System.out.println("dans le if colonne");
                for(int i=0;i<strSeances.size();i++) {
                    fenetre.getEdtCours().setValueAt(strSeances.get(i), ligne1+i, colonne);
                    //System.out.println("ajout de " + strSeances.get(i) + " l." + (ligne1+i) + " c." + colonne);
                    
                    //Si la séance est validée
                    if(!strSeances.get(0).equals("ANNULEE") && !strSeances.get(0).equals("EN COURS DE VALIDATION")) {
                        fenetre.getEdtCours().setValueAt("    ", ligne1+5, colonne);
                    } 
                }
            }
        } 
    }
    
    /**
     * affichage de l'edt d'une seule journee dans l'onglet Home
     * @param date
     * @param email
     * @param password
     */
    public void seancesEdt(String date, String email, String password) {
        //System.out.println("On veut afficher les seances de " + email);
        SeanceDAO sDAO = new SeanceDAO();
        Etudiant et = new Etudiant();
        Enseignant en = new Enseignant();
        ArrayList<Seance> seances = new ArrayList<>(); //Conteneur de seances
        ArrayList<String> strSeances; //Conteneur des string relative a une seance
        //On récupère l'utilisateur
        Utilisateur u = recupUtilisateur(email, password);
        //Avant d'appeler le seanceDAO, il faut convertir la Strind date en plusieurs int
        int jour = 0, mois = 0, annee = 0;
        
        int pos = date.indexOf(' '); //Premier espace -> après le jour
        jour = Integer.parseInt(date.substring(0, pos)); //On récup le jour
        //System.out.println("jour " + jour);
        
        date = date.substring(pos+1); //On récupère la string de base sans le jour ni l'espace
        //System.out.println("tampon " + date);
        
        pos = date.indexOf(' '); //Premier espace -> après le jour
        String month = date.substring(0, pos); //On récup le mois
        //System.out.println("mois " + month);
        
        switch(month) {
            case "janvier" :
                mois = 1;
                break;
            case "février" :
                mois = 2;
                break;
            case "mars" :
                mois = 3;
                break;
            case "avril" :
                mois = 4;
                break;
            case "mai" :
                mois = 5;
                break;
            case "juin" :
                mois = 6;
                break;
            case "juillet" :
                mois = 7;
                break;
            case "août" :
                mois = 8;
                break;
            case "septembre" :
                mois = 9;
                break;
            case "octobre" :
                mois = 10;
                break;
            case "novembre" :
                mois = 11;
                break;
            case "décembre" :
                mois = 12;
                break;
        }
        
        //System.out.println("mois " + mois);
        
        annee = Integer.parseInt(date.substring(pos+1)); //L'annee
        //System.out.println("annee " + annee);
        
        //On récupère les données de l'utilisateurs selon son profil (étudiant, enseignant dont référent)
        if(u.getDroit() == 3 || u.getDroit() == 2) {
            //On ne récupère que les séances de la semaine courante
            en = recupEnseignant(u);
            en.setSeances(sDAO.findSeancesByUserAndDay(en.getId(), jour, mois, annee));
            seances = en.getSeances();
        }
        
        if(u.getDroit() == 4) {
            et = recupEtudiant(u);
            et.setSeances(sDAO.findSeancesByUserAndDay(et.getId(), jour, mois, annee));
            seances = et.getSeances();
            
            //Ne doit pas voir les séances en cours de validation (etat = 1)
            for(int i=0;i<seances.size();i++) {
                
                if(seances.get(i).getEtat() == 1) {
                    seances.remove(i); //Effacer la séance
                    //System.out.println("Cette séance est en cours de validation, on ne l'affiche pas");
                    i--; //On retourne une case en arrière
                }
            }
        }
                
        //Trouver la ligne -> HEURE - Récuperer string, comparer a ce qui est dans seances de l'utilisateur
        int ligne1 = 0, ligne2 = 0; 
        
        for(int j=0;j<seances.size();j++) { //Pour toutes les seances
            String heure1 = seances.get(j).getHeureDebut();
            //System.out.println("Heure début de la seance " + heure1);
            
            String heure2 = seances.get(j).getHeureFin();
            //System.out.println("Heure fin de la seance " + heure2);
            
            //Convertir l'heure1BDD en heureEdt : 12:00:00 -> 12h00
            String heure1BDD = heure1.substring(0, 2) + "h" + heure1.substring(3, 5); 
            //System.out.println(heure1BDD);
            
            //Convertir l'heure2BDD en heureEdt : 12:00:00 -> 12h00
            String heure2BDD = heure2.substring(0, 2) + "h" + heure2.substring(3, 5); 
            //System.out.println(heure2BDD);
                        
            //LIGNE DEBUT
            for(int i=0;i<fenetre.getEdtHome().getRowCount();i++) { 
                String heureEdt = fenetre.getEdtHome().getValueAt(i, 0).toString(); //08h00 dans EDT -> 08:00:00 dans BDD
                
                if(heureEdt.equals(heure1BDD)) { //Si l'heure correspond, récupérer la ligne
                    //System.out.println("DEBUT - Ces deux heures sont pareilles : " + heure1BDD + " et " + heureEdt);
                    ligne1 = i;
                }
            }
            
            //LIGNE FIN
            for(int i=0;i<fenetre.getEdtHome().getRowCount();i++) { 
                String heureEdt = fenetre.getEdtHome().getValueAt(i, 0).toString(); //08h00 dans EDT -> 08:00:00 dans BDD
                
                if(heureEdt.equals(heure2BDD)) { //Si l'heure correspond, récupérer la ligne
                    //System.out.println("FIN - Ces deux heures sont pareilles : " + heure2BDD + " et " + heureEdt);
                    ligne2 = i;
                }
            }
            
            //Un cours dure forcément 1h30 = 6 cases
            strSeances = seances.get(j).toArrayListOfString();
            //RAPPEL cf. méthode dans Seance.java
            //seance[0] = etat ; seance[1] = intitulé du cours; seance[2] = enseignants ; 
            //seance[3] = groupes; seance[4] = salles; seance[5] = type du cours;
            //!! SI LA SEANCE EST VALIDEE = 5 CASES
            
            for(int i=0;i<strSeances.size();i++) {
                fenetre.getEdtHome().setValueAt(strSeances.get(i), ligne1+i, 1);

                //Si la séance est validée
                if(!strSeances.get(0).equals("ANNULEE") && !strSeances.get(0).equals("EN COURS DE VALIDATION")) {
                    fenetre.getEdtHome().setValueAt("    ", ligne1+5, 1);
                } 
            }
        } 
    }
    
    /**
     * affichage de l'edt d'un groupe pour référent
     * @param semaine
     * @param recherche
     */
    public void majSeancesEdt(int semaine, String recherche) {
        //Récupérer le groupe et la promo
        String groupe = recherche.substring(0, 4);
        String promo = recherche.substring(5, 9);

        //System.out.println("recherche : " + groupe + " " + promo);
        
        //Trouver un etudiant qui appartient a ce groupe
        //Trouver l'id de la promo
        PromotionDAO pDAO = new PromotionDAO();
        Promotion p = pDAO.findByName(promo);
        
        //System.out.println("promo recup " + p.getNom());
        
        //Trouver l'id du groupe
        GroupeDAO gDAO = new GroupeDAO();
        Groupe g = gDAO.findByNameAndPromo(groupe, p.getId());
        
        //System.out.println("groupe recup " + g.getNom() + "de la promo " + g.getPromotion().getNom());
        
        //Trouver etudiant by group -> email, password
        EtudiantDAO eDAO = new EtudiantDAO();
        Etudiant e = eDAO.findByGroup(g.getId());
        
        //System.out.println("etudiant recup " + e.getEmail() + " " + e.getPassword());
        
        seancesEdt(semaine, e.getEmail(), e.getPassword());
    }
    
    /**
     * affichage de toutes les séances de l'année scolaire en cours
     * @param email
     * @param password
     */
    public void seancesRecap(String email, String password) {
        SeanceDAO sDAO = new SeanceDAO();
        Etudiant et = new Etudiant();
        Enseignant en = new Enseignant();
        ArrayList<ArrayList<Seance>> seances = new ArrayList<>(); //Conteneur de seances
        //à Chaque .get(i) se trouve une liste de séance de même matière et même groupes
        //à chaque .get(i).get(j) se trouve les séances de même matière et de même groupes rangé par date et heure
        ArrayList<String> strSeances; //Conteneur des string relative a une seance
        //On récupère l'utilisateur
        Utilisateur u = recupUtilisateur(email, password);
        
        //System.out.println("\n" + fenetre.calculAnneeScolaire());
        String debut = fenetre.calculAnneeScolaire().substring(0, 4) + "-09-01";
        int pos = fenetre.calculAnneeScolaire().indexOf("/");
        String fin = fenetre.calculAnneeScolaire().substring(pos+1) + "-08-01";
        //System.out.println("\n" + d + "/" + f);
        
        //On récupère les données de l'utilisateurs selon son profil (étudiant, enseignant dont référent)
        if(u.getDroit() == 3 || u.getDroit() == 2) {
            en = recupEnseignant(u);
            seances = sDAO.findSeancesOfUserByDate(en.getId(), debut, fin);
        }
        
        if(u.getDroit() == 4) {
            et = recupEtudiant(u);
            seances = sDAO.findSeancesOfUserByDate(et.getId(), debut, fin);
            
            //Ne doit pas voir les séances en cours de validation (etat = 1)
            for(int i=0;i<seances.size();i++) {
                for(int j=0;j<seances.get(i).size();j++) {
                    /*System.out.println("Taille seances = " + seances.size());
                    System.out.print("Etat = " + seances.get(i).get(j).getEtat() + " ");
                    System.out.println(seances.get(i).toString());
                    System.out.println("Tour de boucle" + i);*/
                    if(seances.get(i).get(j).getEtat() == 1) {
                        seances.get(i).remove(j); //Effacer la séance
                        //System.out.println("Cette séance est en cours de validation, on ne l'affiche pas");
                        j--; //On retourne une case en arrière
                    }
                }
            }
        }
        
        ((DefaultTableModel) fenetre.getRecapCours().getModel()).setRowCount(seances.size());
        
        for (int i = 0 ; i < seances.size() ; i++)
        {
            //fenetre.getEdtCours().setValueAt(strSeances.get(i), ligne1+i, colonne);
            //System.out.println("******************************************");
            //System.out.println("Matiere - Public:");
            String nom = seances.get(i).get(0).getCours().getNom();
            
            for (int a = 0; a<seances.get(i).get(0).getGroupes().size(); a++)
            {
                nom += " "+seances.get(i).get(0).getGroupes().get(a).getNom();
                nom += " - "+seances.get(i).get(0).getGroupes().get(a).getPromotion().getNom();
            }
            
            for(int j=0;j<seances.get(i).size();j++) {
                //TAMPON POUR JTREE
                nom += "[" + seances.get(i).get(j).getDate() + " " + seances.get(i).get(j).getHeureDebut() + " " + seances.get(i).get(j).getHeureFin() + "]";
            }
            
            System.out.println(nom);
            /*for (int a = 0; a<seances.get(i).get(0).getGroupes().size(); a++)
            {
                nom += " "+seances.get(i).get(0).getGroupes().get(a).getNom();
                nom += " - "+seances.get(i).get(0).getGroupes().get(a).getPromotion().getNom();
            }*/
            
            fenetre.getRecapCours().setValueAt(nom, i, 0);
            
            //System.out.println(nom);
            //System.out.println("Premiere séance:");
            String premiereSeance = seances.get(i).get(0).getDate() + " de "+ seances.get(i).get(0).getHeureDebut() +" à "+ seances.get(i).get(0).getHeureFin();
            fenetre.getRecapCours().setValueAt(premiereSeance, i, 1);
            //System.out.println(premiereSeance);
            
            //System.out.println("Dernière séance:");
            int dernier = seances.get(i).size()-1;
            String derniereSeance = seances.get(i).get(dernier).getDate() + " de "+ seances.get(i).get(dernier).getHeureDebut() +" à "+ seances.get(i).get(dernier).getHeureFin(); 
            fenetre.getRecapCours().setValueAt(derniereSeance, i, 2);
            //System.out.println(derniereSeance);
            
            //System.out.println("SI ON CLIQUE SUR L'ICONE PLUS DE DETAIL:");
            int heure = 0;
            int minute = 0;
            String duree = new String();
            for (int a = 0 ; a < seances.get(i).size() ; a++)
            {
                duree = seances.get(i).get(a).calculDuree(); //On récupère la durée de la séance i
                //System.out.println(seances.get(i).get(a).getDate() + " de "+ seances.get(i).get(a).getHeureDebut() +" à "+ seances.get(i).get(a).getHeureFin() + " ("+duree +")");
                int p; //Première occurence de la lettre h de la durée
                p = duree.indexOf('h');
                heure += Integer.parseInt(duree.substring(0,p)); //On somme tout les heures
                minute += Integer.parseInt(duree.substring(p+1,duree.length())); //On somme tout les minutes
            }
            duree = seances.get(i).get(0).orderingHour(heure+"h"+minute); //On appel n'importe que séance, nous souhaitons juste ranger les heures/minutes
            //System.out.println("Durée globale:");
            //System.out.println(duree);
            fenetre.getRecapCours().setValueAt(duree, i, 3);
            
            //System.out.println("Nb");
            String nb = String.valueOf(dernier+1);
            fenetre.getRecapCours().setValueAt(nb, i, 4);
        }      
    }
    
    /**
     * redimensionner la hauteur des lignes d'un tableau (ici recap)
     */
    public void basicRowHeights() {
        for (int i = 0; i < fenetre.getRecapCours().getRowCount(); i++)
        {
            int iHeight = fenetre.getRecapCours().getRowHeight();

            for (int j = 0; j < fenetre.getRecapCours().getColumnCount(); j++)
            {
                Component comp = fenetre.getRecapCours().prepareRenderer(fenetre.getRecapCours().getCellRenderer(i, j), i, j);
                iHeight = Math.min(iHeight, comp.getPreferredSize().height);
            }

            fenetre.getRecapCours().setRowHeight(i, iHeight);
        }
    }
    
    /**
     * cf. fonction précédente
     */
    public void updateRowHeights()
    {       
        int column = fenetre.getRecapCours().getSelectedColumn();
        int row = fenetre.getRecapCours().getSelectedRow();
        int rowHeight = fenetre.getRecapCours().getRowHeight();
        Component comp = fenetre.getRecapCours().prepareRenderer(fenetre.getRecapCours().getCellRenderer(row, column), row, column);
        rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
        fenetre.getRecapCours().setRowHeight(row, rowHeight);
    }
  
}