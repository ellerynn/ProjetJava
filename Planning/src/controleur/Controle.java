package controleur;

import modele.CoursDAO;
import modele.EnseignantDAO;
import modele.EtudiantDAO;
import modele.GroupeDAO;
import modele.PromotionDAO;
import modele.SalleDAO;
import modele.SeanceDAO;
import modele.SiteDAO;
import modele.TypeCoursDAO;
import modele.UtilisateurDAO;
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
     * Demande de connexion de la part de l'utilisateur en fournissant les données saisies
     * @param email email de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * @return true si on trouve dans la BDD un utilisateur correspondant a la saisie de connexion
     */
    public Boolean demandeConnexion(String email, String password) {
        Utilisateur utilisateur = recupUtilisateur(email, password);       
        return !(utilisateur.getEmail().isEmpty() && utilisateur.getPassword().isEmpty()); 
    }
    
    /**
     * utilisé pour set le titre de la frame
     * @param email email de l'utilisateur connecté
     * @param password mot de passe de l'utilisateur connecté
     * @return une chaine de caractère avec prenom et nom de l'utilisateur courrant
     */
    public String utilisateurCourant(String email, String password) {
        Utilisateur utilisateur = recupUtilisateur(email, password);
        return utilisateur.getPrenom() + " " + utilisateur.getNom();
    }
    
    /**
     * utilisé pour créer des listeners que seuls les admins ont
     * @param email email de l'admin
     * @param password mot de passe de l'admin
     * @return true si l'utilisateur est un admin
     */
    public Boolean admin(String email, String password) {
        Utilisateur u = recupUtilisateur(email, password);
        return u.getDroit() == 1;
    }
    
    /**
     * Méthode qui cherche l'utilisateur en fonction du mail et du mot de passe
     * @param email email de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * @return un utilisateur à partir de son email et password
     */
    public Utilisateur recupUtilisateur(String email, String password) {
        UtilisateurDAO uDAO = new UtilisateurDAO();
        return uDAO.find(email,password);
    }
    
    /**
     * Méthode qui récupère l'ensemble des utilisateurs de la BDD
     * @return tous les utilisateurs de la BDD dans un ArrayList d'utilisateurs
     */
    public ArrayList<Utilisateur> recupUtilisateurs() {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        return utilisateurDAO.find();
    }
    
    /**
     * Méthode qui récupère l'ensemble des promotions de la BDD
     * @return toutes les promos de la BDD dans un ArrayList de promos
     */
    public ArrayList<Promotion> recupPromos() {
        PromotionDAO pDAO = new PromotionDAO();
        return pDAO.findAllPromo();
    }
    
    /**
     * Méthode qui récupère l'ensemble des groupes de la BDD
     * @return tous les groupes de la BDD
     */
    public ArrayList<Groupe> recupGroupes() {
        GroupeDAO gDAO = new GroupeDAO();
        return gDAO.find();
    }    
    
    /**
     * Méthode qui permet de savoir si un utilisateur donnée est un étudiant et le retourne
     * @param utilisateur Utilisateur en question
     * @return les données étudiantes de l'utilisateur
     */
    public Etudiant recupEtudiant(Utilisateur utilisateur) {
        EtudiantDAO eDAO = new EtudiantDAO();
        return eDAO.find(utilisateur.getId());
    }

    /**
     * Méthode qui permet de savoir si un utilisateur donnée est un enseignant et le retourne
     * @param utilisateur Utilisateur en question
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
     * Permet de convertir certaines information de l'utilisateur sous forme de String
     * utilisé pour set le titre de la frame
     * @param email Email de l'utilisateur
     * @param password Mot de passe de l'utilisateur
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
     * Permet d'obtenir toutes les promos par nom 
     * utilisé pour la recherche du référent par exemple
     * @return un ArrayList de toutes les promos de la BDD
     */
    public ArrayList<String> allPromosToStrings() {
        ArrayList<Promotion> promos = recupPromos();
        ArrayList<String> p = new ArrayList<>();
        
        for(int i=0;i<promos.size();i++)
            p.add(promos.get(i).getNom());
         
        return p;
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
     * Permet d'obtenir tout les sites sous forme de String
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
     * @param email email de l'utilisateur
     * @param password mot de passe de l'utilisateur
     */
    public void creationGraphe(String email, String password) {
        //Premier panel = deux graphes
        ArrayList<Salle> salles = recupAllSalles();
        ArrayList<String> sites = allSitesToStrings();
        ArrayList<ChartPanel> c = new ArrayList<>();
       
        //GRAPHES SEANCES -> combien de cours restant sur l'annee scolaire en cours
        String debut = fenetre.calculAnneeScolaire().substring(0, 4) + "-09-01";
        int pos = fenetre.calculAnneeScolaire().indexOf("/");
        
        Calendar cal = Calendar.getInstance();//date du jour
        int mois = cal.get(Calendar.MONTH)+1;
        int jour = cal.get(Calendar.DAY_OF_MONTH);
        int heure = cal.get(Calendar.HOUR_OF_DAY);//heure du jour
        String debut2 = fenetre.calculAnneeScolaire().substring(pos+1) + "-0"+mois+"-0"+jour;//debut2 pour cours restant
        String fin = fenetre.calculAnneeScolaire().substring(pos+1) + "-08-01";
        //Pour l'utilisateur connecté
        SeanceDAO sDAO = new SeanceDAO();
        Utilisateur u = recupUtilisateur(email, password);
        ArrayList<ChartPanel> t = new ArrayList();
        ArrayList<ArrayList<Seance>> seancesheure = sDAO.findAllSeancesByDate(debut, fin);//heure
        int calculheuretotal = 0; //heure
        int comparaison = 0;
        
        ArrayList<ArrayList<Seance>> seances;
        ArrayList<ArrayList<Seance>> seances2;
        
        DefaultPieDataset pieDataset = new DefaultPieDataset();//salles
        DefaultPieDataset pieDataset5 = new DefaultPieDataset();//heure
        
        for (int i = 0 ; i <salles.size(); i++){ //GRAPHE 1
            pieDataset.setValue("Site: "+salles.get(i).getSite().getNom()+","+salles.get(i).getNom()+",Capacité :"+salles.get(i).getCapacite(), new Integer(salles.get(i).getCapacite()));
           }

        
        for (int i = 0 ; i <seancesheure.size(); i++){ //GRAPHE 2
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

        //Second panel = deux graphes
        JFreeChart chart = ChartFactory.createPieChart("Capacité des salles pour tous les sites", pieDataset, true, true, true);//eiffel 1
        PiePlot P=(PiePlot)chart.getPlot();
        ChartPanel p = new ChartPanel(chart);
        c.add(p); 
        
        JFreeChart chart5 = ChartFactory.createPieChart("Nombres d'heures total d'un cours dans un semestre", pieDataset5, true, true, true);//eiffel 1
        PiePlot P5=(PiePlot)chart5.getPlot();
        ChartPanel p5 = new ChartPanel(chart5);
        c.add(p5);

        if(u.getDroit() != 1) {
            seances = sDAO.findSeancesOfUserByDate(u.getId(), debut, fin);//cours total
            seances2 = sDAO.findSeancesOfUserByDate(u.getId(), debut2, fin);//cours restant
        }
        else {
            seances = sDAO.findAllSeancesByDate(debut, fin);//cours total
            seances2 = sDAO.findAllSeancesByDate(debut2, fin);//cours restant
        }
        
        int compteur = 0;//cours total
        int compteur2=0;//cours restant

        DefaultPieDataset pieDataset3 = new DefaultPieDataset();//cours total
        DefaultPieDataset pieDataset4 = new DefaultPieDataset();//cours restant

        int pas_afficher=0;//variable qui permet de na pas afficher la seance si la dat est la meme et si lheure n'est pas superieur

        for(int i=0;i<seances.size();i++) {//graphe 3
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
        
        for(int l=0;l<seances2.size();l++) { //graphe 4
            String dateblindage=seances2.get(l).get(0).getDate();
            int heureblindage=Integer.parseInt(seances2.get(l).get(0).getHeureDebut().substring(0,2));
                for(int j=0;j<seances2.get(l).size();j++) {
                    if(debut2.equals(dateblindage)){
                        if(heureblindage > heure){
                            compteur2++;
                        }
                        else 
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
                    }
                    else
                        pas_afficher=0;
                }      
        }

        if(!seances.isEmpty()) {
            JFreeChart chart2 = ChartFactory.createPieChart("Nombres de séances par cours", pieDataset3, true, true, true);
            PiePlot P3 =(PiePlot)chart2.getPlot();
            ChartPanel p3 = new ChartPanel(chart2);
            t.add(p3);
        }
        if(!seances2.isEmpty()) {
            JFreeChart chart4 = ChartFactory.createPieChart("Nombres de séances par cours restant de l'année", pieDataset4, true, true, true);
            PiePlot P4 =(PiePlot)chart4.getPlot();
            ChartPanel p4 = new ChartPanel(chart4);
            t.add(p4);
        }
        
        fenetre.ajouterGraphes(c, t);
    } 
    
    /**
     * Recherche si un user réalisée à travers la barre de recherche existe ou pas si oui, on retourne le nom, si non on retourne un null
     * @param recherche informations d'un utilisateur à chercher : prenom et nom
     * @return Retourne un String avec le prenom et nom de l'user si trouver, sinon null
     */
    public String rechercheUtilisateur(String recherche) {
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
        
        if(u.getId() != 0) {
            //On a trouvé la personne, on selectionne par défaut dans le menu des users dans l'onglet cours
            //ça enclenche le listener du menu user, qui va update la séance de ce user
            return u.getPrenom()+" "+u.getNom();
        }
        else
            return null;
    }
    
    /**
     * Recherche si une salle réalisée à travers la barre de recherche existe ou pas
     * @param recherche informations d'une salle à chercher :nom de la salle et site de la salle
     * @return Retourn le nom de la salle et le site de la salle si salle trouver sinon, null
     */
    public String rechercheSalle(String recherche) {
        SalleDAO s2DAO = new SalleDAO();
        Salle s2 = s2DAO.findByName(recherche);
        if(s2 != null)
            return s2.getNom()+ " "+ s2.getSite().getNom();
        return null;
    }
    
    /**
     * Calcul l'heure de fin à partir de l'heure de début
     * @param debut Heure de début
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
     * @param strings Données d'une séance à ajouter
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
     * @param Semaine Semaine d'une séance
     * @param Date date d'une séance 
     * @param Heure_Debut heure de début d'une séance
     * @param Heure_Fin heure de fin d'une séance
     * @param Etat etat d'une séance
     * @param cours cours d'une séance
     * @param type type d'une séance
     * @param groupes les gorupes d'une séance
     * @param enseignants les enseignant d'une séance
     * @param salles les salles d'une séance
     */
    public void ajouterSeanceInModel(int Semaine, String Date, String Heure_Debut, String Heure_Fin, int Etat, Cours cours,TypeCours type, ArrayList<Groupe> groupes, ArrayList<Enseignant> enseignants, ArrayList<Salle> salles) 
    {   
        //MODIFIER
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance = new Seance(Semaine, Heure_Debut, Heure_Fin,Date, 3, cours, type); //instanciation de la nvlle seance avec les premiers données
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
        okForCreate = verifAndSetSeanceEtat(seance,""+Etat,seance.getGroupes().size(),seance.getEnseignants().size(),seance.getSalles().size());
        if (okForCreate) //Si tout les conditions sont réunis, on create, si il y a eu un faux, on ne create pas.
        {
            seance = sDAO.create(seance);
            System.out.println("Ajouter avec succes");
            majAllSeances();
        }
        else {
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
     * @param id_seance id de la séance
     * @return ArrayList Retourne l'ensemble des infos d'une séance à être sélectionner si l'admin clique sur une séance dans la liste
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
     * @param idSeance id de la séance
     * @param strings Données d'une séance à modifier
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
            setSeanceCoursNom(seance,(String)strings.get(4));
            setSeanceCoursType(seance,(String)strings.get(5));
            //Etape 1 Verification si tout les données entrées sont cohérents
            okEtat = verifAndSetSeanceEtat(seance,(String)strings.get(3),((ArrayList<String>)strings.get(7)).size(),((ArrayList<String>)strings.get(6)).size(),((ArrayList<String>)strings.get(7)).size());
            okEnseignants = verifSeanceEnseignants(seance,(ArrayList<String>)strings.get(6));
            //Ensemble salles et groupes car chacun ont bsn de voir les capa de l'autre
            okGroupesSalles = verifSeanceGroupesEtSalles(seance,(ArrayList<String>)strings.get(7),(ArrayList<String>)strings.get(8)); 
            
            //Etape 2 Changement de données
            if(okEtat && okEnseignants && okGroupesSalles)
            {  
                //Changement de données dans la variable seance (localement) et dans celui BDD
                //Etat est déjà set localement
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
     * @param seance Seance en question
     * @param choix la valeur de l'état souhaité pour la séance
     * @param tailleGroupe Nombre de groupes
     * @param tailleEnseignant Nombre d'enseignants
     * @param tailleSalles Nombre de salles
     * @return Retourne un booléan indiquant si tout est bon pour changer d'était dans la BDD
     */
    public boolean verifAndSetSeanceEtat(Seance seance, String choix, int tailleGroupe, int tailleEnseignant, int tailleSalles)
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
                if(tailleEnseignant != 0 && tailleGroupe != 0 && tailleSalles != 0){
                    seance.setEtat(2);
                    return true;
                }
                else{System.out.println("On ne peux pas valider cette séance car il faut au minimum un enseignant et un groupe et une salle");} 
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
     * @param seance Seance en question
     * @param namesSelected Les infos des enseignants sélectionnés pour cette séance
     * @return Retourne un boolean indiquant si tout les ensignants d'une séance sont bon pour être mise à jours dans la BDD
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
     * @param seance Seance en question
     * @param groupesSelected Les infos des groupes sélectionnés pour cette séance
     * @param sallesSelected Les infos des salles sélectionnés pour cette séance
     * @return Retourne un boolean indiquant si et les groupes et les salles selectionnées d'une séance sont bon pour être mise à jours dans la BDD 
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
        if(sallesSelected.isEmpty()) { //Si aucune salle n'est selectionné, pas bsn de vérif capa groupe et salle
            okForGrps = true;
        }
        else { //Si des salles sont déjà affectés à cette séance, on est oblgés de vérifier la capacité
            if(capaciteGroupe <= capaciteSalle) { //Capa groupe doit être <= capa salles
                okForGrps = true;
            }
            else {
                System.out.println("Ensemble de groupe trop grand");
            }
        }
        //Cas salles
        if(groupesSelected.isEmpty()) {   
            okForSalles = true;
        }
        else if(!sallesSelected.isEmpty()) { //Si des groupes sont dans cette séance
            if(capaciteSalle >= capaciteGroupe) { //On vérifie si toute les salles peuvent supporter le nb
               //Si les salles supportent les groupes
                okForSalles = true;
            }
            else
                System.out.print("Capacité insuffisante");
        }
        if(sallesSelected.isEmpty()) {
            okForSalles = true; //Si salle vide jusqu'en bas, alors c'est Ok, pas bsn pour groupe car il est vérif avant salle
        }
        if(okForGrps && okForSalles) { //Si tout est Ok  
            return true;
        }
        return false;
    }

    /**
     * Requête de MAJ du nom de cours d'une séance donnée dans la BDD
     * @param seance Seance en question
     * @param cours Cours en question
     */
    public void setSeanceCoursNom(Seance seance, String cours) {
        CoursDAO cDAO = new CoursDAO();
        seance.setCours(cDAO.findByName(cours));
    }

    /**
     * Requête de MAJ du type de cours d'une séance donnée dans la BDD
     * @param seance Seance en question
     * @param type type de cours en question
     */
    public void setSeanceCoursType(Seance seance, String type) {
        TypeCoursDAO tDAO = new TypeCoursDAO();
        seance.setTypeCours(tDAO.findByName(type));
    }

    /**
     * Requête de MAJ des enseignants d'une séance donnée dans la BDD
     * @param seance Seance en question
     * @param namesSelected les enseignants sous forme de prenom et nom en question
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
     * @param seance Seance en question
     * @param groupesSelected les groupes sous forme de String en question
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
     * @param seance Seance en question
     * @param sallesSelected Les salles sous forme de String en question
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
     * Méthode qui affiche à la vue, les séances d'une promo sur une semaine donnée
     * @param semaine Semaine en question
     * @param promo la promotion en question
     */
    public void majSeancesPromo(int semaine, String promo) {
        PromotionDAO pDAO = new PromotionDAO();
        Promotion p = pDAO.findByName(promo);
        ArrayList<Seance> seances = new ArrayList<>();
        if(p != null) {
            SeanceDAO sDAO = new SeanceDAO();
            seances = sDAO.findSeancesByPromoAndWeek(p.getId(), semaine);
        }
        
        String strSeances; //Conteneur des string relative a une seance
            
        for(int j=seances.size()-1;j>-1;j--) { //Pour toutes les seances            
            String dateBDD = seances.get(j).getDate();
                
            //Convertir la dateBDD en jour
            String jourBDD = dateBDD.substring(8, 10); 
            
            //LIGNE DEBUT
            for(int i=0;i<fenetre.getListeCours().getRowCount();i++) { 
                String date = fenetre.getListeCours().getValueAt(i, 0).toString(); 
                String jourEdt = date.substring(5, 7);
                if(jourEdt.endsWith(" ")) {
                    jourEdt = "0" + jourEdt.substring(0, 1);
                }
                                    
                if(jourEdt.equals(jourBDD)) { //Si l'heure correspond, récupérer la ligne
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
     * affichage de l'emploi du temps d'un utilisateur dans l'onglet Cours
     * @param semaine la semaine en question
     * @param prenom prenom de l'utilisateur en question
     * @param nom nom de l'utilisateur en question
     */
    public void majSeancesEdt(int semaine, String prenom, String nom) {
        UtilisateurDAO uDAO = new UtilisateurDAO();
        Utilisateur u = uDAO.findByName(prenom, nom);
        seancesEdt(semaine, u.getEmail(), u.getPassword());
    }
    
    /**
     * affichage de l'emploi du temps d'une salle dans l'onglet Cours
     * @param semaine la semaine en question
     * @param infos les infos de la salle sous forme de string en question
     */
    public void majSeancesSalles(int semaine, String infos) {
        SalleDAO s2DAO = new SalleDAO();
        Salle s2 = s2DAO.findByName(infos);
        if(s2 != null) //Si la salle est trouvé
            seancesSalles(semaine, s2); //Partie affichage de l'edt dans la vue
    }
    
    /**
     * Méthode qui affiche les séances d'une salle sur une semaine dans la vue sous forme d'une liste
     * @param semaine la semaine en question
     * @param infos les infos de la salle sous forme de string en question
     */
    public void majSallesListe(int semaine, String infos) {
        SeanceDAO sDAO = new SeanceDAO();
        SalleDAO s2DAO = new SalleDAO();
        Salle s = s2DAO.findByName(infos);
        ArrayList<Seance> seances = new ArrayList();
        seances = sDAO.findSeancesBySalle(s.getId(), semaine); //100% trouvé la salle, le rechercher par barre de recherche est vérifié dans rechercheSalle
        String strSeances; //Conteneur des string relative a une seance
        
        for(int i=0;i<seances.size();i++) {
            if(seances.get(i).getEtat() == 1) {
                seances.remove(i); //Effacer la séance car en cours de validation
                i--; //On retourne une case en arrière
            }
        }
                      
        for(int j=seances.size()-1;j>-1;j--) { //Pour toutes les seances            
            String dateBDD = seances.get(j).getDate();
                
            //Convertir la dateBDD en jour
            String jourBDD = dateBDD.substring(8, 10); 
            
            //LIGNE DEBUT
            for(int i=0;i<fenetre.getListeCours().getRowCount();i++) {
                String date = fenetre.getListeCours().getValueAt(i, 0).toString(); 
                String jourEdt = date.substring(5, 7);
                if(jourEdt.endsWith(" ")) {
                    jourEdt = "0" + jourEdt.substring(0, 1);
                }
                                    
                if(jourEdt.equals(jourBDD)) { //Si l'heure correspond, récupérer la ligne
                    strSeances = seances.get(j).getHeureDebut() + " à " + seances.get(j).getHeureFin() + " " + seances.get(j).toString();
                    Vector os = null;
                    ((DefaultTableModel) fenetre.getListeSalles().getModel()).insertRow(i+1, os);
                    fenetre.getListeSalles().setValueAt(strSeances, i+1, 0);
                    i++;
                }
            }
        } 
    }
    
    /**
     * affichage de l'emploi du temps de l'utilisateur sous forme de liste dans l'onglet Cours
     * @param semaine la semaine en question
     * @param prenom Le prenom de l'utilisateur en question
     * @param nom le nom de l'utilisateur en question
     */
    public void majSeancesListe(int semaine, String prenom, String nom) {
        UtilisateurDAO uDAO = new UtilisateurDAO();
        Utilisateur u = uDAO.findByName(prenom, nom);
        seancesListe(semaine, u.getEmail(), u.getPassword());
    }
    
    /**
     * recup et affichage des séances d'un utilisateur sur une semaine sous forme de liste
     * @param semaine La semaine en question
     * @param email l'email de l'utilisateur
     * @param password le mot de passe de l'utilisateur
     */
    public void seancesListe(int semaine, String email, String password) {
        SeanceDAO sDAO = new SeanceDAO();
        Etudiant et = new Etudiant();
        Enseignant en = new Enseignant();
        ArrayList<Seance> seances = new ArrayList<>(); //Conteneur de seances
        String strSeances; //Conteneur des string relative a une seance
        //On récupère l'utilisateur
        Utilisateur u = recupUtilisateur(email, password);
        ArrayList<String> mesInfos = fenetre.recupMesInfos();//Infos de l'user actuelle
        Utilisateur actuelle = recupUtilisateur(mesInfos.get(0),mesInfos.get(1));
        
        //Si la personne connecté n'est pas un admin ni un réfénrent, on applique les blindages
        if (actuelle.getDroit() != 1 && actuelle.getDroit() != 2)
        {
            //On récupère les données de l'utilisateurs selon son profil (étudiant, enseignant dont référent)
            if(u.getDroit() == 3 || u.getDroit() == 2) {
                //On ne récupère que les séances de la semaine courante
                en = recupEnseignant(u);
                en.setSeances(sDAO.findSeancesByUserAndWeek(en.getId(), semaine));
                seances = en.getSeances();

                if(u.getDroit() == 3) {
                    //Ne doit pas voir les séances en cours de validation (etat = 1)
                    for(int i=0;i<seances.size();i++) {
                        if(seances.get(i).getEtat() == 1) {
                            seances.remove(i); //Effacer la séance
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
                    if(seances.get(i).getEtat() == 1) {
                        seances.remove(i); //Effacer la séance
                        i--; //On retourne une case en arrière
                    }
                }
            }
        }
        else { //On affiche TOUUT de la personne en question ou de celui selectionné
            seances = sDAO.findSeancesByUserAndWeek(u.getId(), semaine);
        }       
        for(int j=seances.size()-1;j>-1;j--) { //Pour toutes les seances            
            String dateBDD = seances.get(j).getDate();
                
            //Convertir la dateBDD en jour
            String jourBDD = dateBDD.substring(8, 10); 
            
            //LIGNE DEBUT
            for(int i=0;i<fenetre.getListeCours().getRowCount();i++) { 
                String date = fenetre.getListeCours().getValueAt(i, 0).toString(); 
                String jourEdt = date.substring(5, 7);
                if(jourEdt.endsWith(" ")) {
                    jourEdt = "0" + jourEdt.substring(0, 1);
                }
                                    
                if(jourEdt.equals(jourBDD)) { //Si l'heure correspond, récupérer la ligne
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
     * Méthode qui affiche les séances d'une salle sous forme de grille
     * @param semaine la semaine de l'utilisateur
     * @param salle la salle de l'utilisateur
     */
    public void seancesSalles(int semaine, Salle salle) {
        SeanceDAO sDAO = new SeanceDAO();
        ArrayList<Seance> seances = sDAO.findSeancesBySalle(salle.getId(), semaine);
        ArrayList<String> strSeances = new ArrayList<>();
        
        for(int i=0;i<seances.size();i++) {
            if(seances.get(i).getEtat() == 1 || seances.get(i).getEtat() == 3) {
                seances.remove(i); //Effacer la séance
                i--; //On retourne une case en arrière
            }
        }
        
        //Trouver la ligne -> HEURE - Récuperer string, comparer a ce qui est dans seances de l'utilisateur
        int ligne1 = 0; int ligne2 = 0; int colonne = 0; 
        
        for(int j=0;j<seances.size();j++) { //Pour toutes les seances
            int couleur = (int)(Math.random()*6); //*6 entre 0 et 5
            fenetre.getEdt().getGrilleSalles().setAlea(couleur);
            
            String heure1 = seances.get(j).getHeureDebut();
            String heure2 = seances.get(j).getHeureFin();
            
            //Convertir l'heure1BDD en heureEdt : 12:00:00 -> 12h00
            String heure1BDD = heure1.substring(0, 2) + "h" + heure1.substring(3, 5); 
            
            //Convertir l'heure2BDD en heureEdt : 12:00:00 -> 12h00
            String heure2BDD = heure2.substring(0, 2) + "h" + heure2.substring(3, 5); 
            String date = seances.get(j).getDate();
                
            //Convertir la dateBDD en jour
            String jourBDD = date.substring(8, 10); 
            
            //LIGNE DEBUT
            for(int i=0;i<fenetre.getEdtSalles().getRowCount();i++) { 
                String heureEdt = fenetre.getEdtSalles().getValueAt(i, 0).toString(); //08h00 dans EDT -> 08:00:00 dans BDD
                
                if(heureEdt.equals(heure1BDD)) { //Si l'heure correspond, récupérer la ligne
                    ligne1 = i;
                }
            }
            
            //LIGNE FIN
            for(int i=0;i<fenetre.getEdtSalles().getRowCount();i++) { 
                String heureEdt = fenetre.getEdtCours().getValueAt(i, 0).toString(); //08h00 dans EDT -> 08:00:00 dans BDD
                
                if(heureEdt.equals(heure2BDD)) { //Si l'heure correspond, récupérer la ligne
                    ligne2 = i;
                }
            }
            
            for(int i=0;i<fenetre.getEdtCours().getColumnCount();i++) { //Pour chaque ligne
                String entete = fenetre.getEdtSalles().getModel().getColumnName(i);
                String jourEdt = entete.substring(5, 7);
                if(jourEdt.endsWith(" ")) {
                    jourEdt = "0" + jourEdt.substring(0, 1);
                }
                                    
                if(jourEdt.equals(jourBDD)) { //Si l'heure correspond, récupérer la ligne
                    colonne = i;strSeances = seances.get(j).toArrayListOfString();
                    break;
                }
                
                if(!jourEdt.equals(jourBDD)) {
                    colonne = 0;
                }
            }
            
            //RAPPEL cf. méthode dans Seance.java
            //seance[0] = etat ; seance[1] = intitulé du cours; seance[2] = enseignants ; 
            //seance[3] = groupes; seance[4] = salles; seance[5] = type du cours;
            //!! SI LA SEANCE EST VALIDEE = 5 CASES
            
            if(colonne != 0)  {    
                for(int i=0;i<strSeances.size();i++) {
                    fenetre.getEdtSalles().setValueAt(strSeances.get(i), ligne1+i, colonne);
                    
                    //Si la séance est validée
                    if(!strSeances.get(0).equals("ANNULEE") && !strSeances.get(0).equals("EN COURS DE VALIDATION")) {
                        fenetre.getEdtSalles().setValueAt("    ", ligne1+5, colonne);
                    } 
                }
            }
        } 
    }
    
    /**
     * recup et affichage des séances d'un utilisateur sur une semaine sous forme de grille
     * @param semaine la semaine en question
     * @param email l'email de l'utilisateur en question
     * @param password le mot de passe de l'utilisateur en question
     */
    public void seancesEdt(int semaine, String email, String password) {
        SeanceDAO sDAO = new SeanceDAO();
        Etudiant et = new Etudiant();
        Enseignant en = new Enseignant();
        ArrayList<Seance> seances = new ArrayList<>(); //Conteneur de seances
        ArrayList<String> strSeances = new ArrayList<>(); //Conteneur des string relative a une seance
        //On récupère l'utilisateur
        Utilisateur u = recupUtilisateur(email, password);
        ArrayList<String> mesInfos = fenetre.recupMesInfos();//Infos de l'user actuelle
        Utilisateur actuelle = recupUtilisateur(mesInfos.get(0),mesInfos.get(1));
        
        //Si la personne connecté n'est pas un admin ni un réfénrent, on applique les blindages
        if (actuelle.getDroit() != 1 && actuelle.getDroit() != 2)
        {  
            //On récupère les données de l'utilisateurs selon son profil (étudiant, enseignant dont référent)
            if(u.getDroit() == 3 || u.getDroit() == 2) {
                //On ne récupère que les séances de la semaine courante
                en = recupEnseignant(u);
                en.setSeances(sDAO.findSeancesByUserAndWeek(en.getId(), semaine));
                seances = en.getSeances();

                if(u.getDroit() == 3) {
                    //Ne doit pas voir les séances en cours de validation (etat = 1) ni annulé (3)
                    for(int i=0;i<seances.size();i++) {
                        if(seances.get(i).getEtat() == 1) {
                            seances.remove(i); //Effacer la séance
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
                    if(seances.get(i).getEtat() ==  1) {
                        seances.remove(i); //Effacer la séance
                        i--; //On retourne une case en arrière
                    }
                }
            }
        }
        else //On affiche TOUUT de la personne en question ou de celui selectionné
        {
            seances = sDAO.findSeancesByUserAndWeek(u.getId(), semaine);
        }
        //Trouver la ligne -> HEURE - Récuperer string, comparer a ce qui est dans seances de l'utilisateur
        int ligne1 = 0; int ligne2 = 0; int colonne = 0; 
        
        for(int j=0;j<seances.size();j++) { //Pour toutes les seances
            int couleur = (int)(Math.random()*6); //*6 entre 0 et 5
            fenetre.getEdt().getGrilleCours().setAlea(couleur);
                                  
            String heure1 = seances.get(j).getHeureDebut();
            
            String heure2 = seances.get(j).getHeureFin();
            
            //Convertir l'heure1BDD en heureEdt : 12:00:00 -> 12h00
            String heure1BDD = heure1.substring(0, 2) + "h" + heure1.substring(3, 5); 
            
            //Convertir l'heure2BDD en heureEdt : 12:00:00 -> 12h00
            String heure2BDD = heure2.substring(0, 2) + "h" + heure2.substring(3, 5); 
            
            String date = seances.get(j).getDate();
                
            //Convertir la dateBDD en jour
            String jourBDD = date.substring(8, 10); 
            
            //LIGNE DEBUT
            for(int i=0;i<fenetre.getEdtCours().getRowCount();i++) { 
                String heureEdt = fenetre.getEdtCours().getValueAt(i, 0).toString(); //08h00 dans EDT -> 08:00:00 dans BDD
                
                if(heureEdt.equals(heure1BDD)) { //Si l'heure correspond, récupérer la ligne
                    ligne1 = i;
                }
            }
            
            //LIGNE FIN
            for(int i=0;i<fenetre.getEdtCours().getRowCount();i++) { 
                String heureEdt = fenetre.getEdtCours().getValueAt(i, 0).toString(); //08h00 dans EDT -> 08:00:00 dans BDD
                
                if(heureEdt.equals(heure2BDD)) { //Si l'heure correspond, récupérer la ligne
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
                    colonne = i;
                    //Un cours dure forcément 1h30 = 6 cases
                    strSeances = seances.get(j).toArrayListOfString();
                    break;
                }
                
                if(!jourEdt.equals(jourBDD)) {
                    colonne = 0;
                }
            }
                     
            //RAPPEL cf. méthode dans Seance.java
            //seance[0] = etat ; seance[1] = intitulé du cours; seance[2] = enseignants ; 
            //seance[3] = groupes; seance[4] = salles; seance[5] = type du cours;
            //!! SI LA SEANCE EST VALIDEE = 5 CASES
            
            if(colonne != 0)  {               
                for(int i=0;i<strSeances.size();i++) {
                    fenetre.getEdtCours().setValueAt(strSeances.get(i), ligne1+i, colonne);
                    
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
     * @param date la date en question
     * @param email l'email de l'utilisateur en question
     * @param password le mot de passe de l'utilisateur en question
     */
    public void seancesEdt(String date, String email, String password) {
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
        
        date = date.substring(pos+1); //On récupère la string de base sans le jour ni l'espace
        
        pos = date.indexOf(' '); //Premier espace -> après le jour
        String month = date.substring(0, pos); //On récup le mois
        
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
        
        annee = Integer.parseInt(date.substring(pos+1)); //L'annee
        
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
                    i--; //On retourne une case en arrière
                }
            }
        }
                
        //Trouver la ligne -> HEURE - Récuperer string, comparer a ce qui est dans seances de l'utilisateur
        int ligne1 = 0, ligne2 = 0; 
        
        for(int j=0;j<seances.size();j++) { //Pour toutes les seances
            int couleur = (int)(Math.random()*6); //*6 entre 0 et 5
            fenetre.getEdt().getGrilleHome().setAlea(couleur);
                
            String heure1 = seances.get(j).getHeureDebut();
            
            String heure2 = seances.get(j).getHeureFin();
            
            //Convertir l'heure1BDD en heureEdt : 12:00:00 -> 12h00
            String heure1BDD = heure1.substring(0, 2) + "h" + heure1.substring(3, 5); 
            
            //Convertir l'heure2BDD en heureEdt : 12:00:00 -> 12h00
            String heure2BDD = heure2.substring(0, 2) + "h" + heure2.substring(3, 5); 
                        
            //LIGNE DEBUT
            for(int i=0;i<fenetre.getEdtHome().getRowCount();i++) { 
                String heureEdt = fenetre.getEdtHome().getValueAt(i, 0).toString(); //08h00 dans EDT -> 08:00:00 dans BDD
                
                if(heureEdt.equals(heure1BDD)) { //Si l'heure correspond, récupérer la ligne
                    ligne1 = i;
                }
            }
            
            //LIGNE FIN
            for(int i=0;i<fenetre.getEdtHome().getRowCount();i++) { 
                String heureEdt = fenetre.getEdtHome().getValueAt(i, 0).toString(); //08h00 dans EDT -> 08:00:00 dans BDD
                
                if(heureEdt.equals(heure2BDD)) { //Si l'heure correspond, récupérer la ligne
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
     * affichage de l'edt d'un groupe sous forme de grille pour référent, admin
     * @param semaine la semaine en question
     * @param recherche données du groupe en guestion sous forme de String
     */
    public void majSeancesEdt(int semaine, String recherche) {
        //Récupérer le groupe et la promo
        String groupe = recherche.substring(0, 4);
        String promo = recherche.substring(5, 9);
       
        //Trouver un etudiant qui appartient a ce groupe
        //Trouver l'id de la promo
        PromotionDAO pDAO = new PromotionDAO();
        Promotion p = pDAO.findByName(promo);
        
        //Trouver l'id du groupe
        GroupeDAO gDAO = new GroupeDAO();
        Groupe g = gDAO.findByNameAndPromo(groupe, p.getId());
                
        //Trouver etudiant by group -> email, password
        EtudiantDAO eDAO = new EtudiantDAO();
        Etudiant e = eDAO.findByGroup(g.getId());
                
        seancesEdt(semaine, e.getEmail(), e.getPassword());
    }
    /**
     * affichage de l'edt d'un groupe sous forme de liste pour référent, admin
     * @param semaine la semaine en question
     * @param recherche données du groupe en guestion sous forme de String
     */
    public void majSeancesListe(int semaine, String recherche){
        //Récupérer le groupe et la promo
        String groupe = recherche.substring(0, 4);
        String promo = recherche.substring(5, 9);
        
        //Trouver un etudiant qui appartient a ce groupe
        //Trouver l'id de la promo
        PromotionDAO pDAO = new PromotionDAO();
        Promotion p = pDAO.findByName(promo);
                
        //Trouver l'id du groupe
        GroupeDAO gDAO = new GroupeDAO();
        Groupe g = gDAO.findByNameAndPromo(groupe, p.getId());
                
        //Trouver etudiant by group -> email, password
        EtudiantDAO eDAO = new EtudiantDAO();
        Etudiant e = eDAO.findByGroup(g.getId());
                
        seancesListe(semaine, e.getEmail(), e.getPassword());
    }
    
    /**
     * affichage de toutes les séances de l'année scolaire en cours
     * @param email l'email de l'utilisateur en question
     * @param password le mot de passe de l'utilisateur en question
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
        
        String debut = fenetre.calculAnneeScolaire().substring(0, 4) + "-09-01";
        int pos = fenetre.calculAnneeScolaire().indexOf("/");
        String fin = fenetre.calculAnneeScolaire().substring(pos+1) + "-08-01";
        
        String periode = "  du " + debut + " au " + fin + ".";
        fenetre.getEdt().getPeriode().setText(periode);
        
        if(u.getDroit() == 1) { //Pour un admin, on recupère toutes les séances de tout le monde
            seances = sDAO.findAllSeancesByDate(debut, fin);
        }
        
        //On récupère les données de l'utilisateurs selon son profil (étudiant, enseignant dont référent)
        if(u.getDroit() == 3 || u.getDroit() == 2) {
            en = recupEnseignant(u);
            seances = sDAO.findSeancesOfUserByDate(en.getId(), debut, fin);
        }
        
        if(u.getDroit() == 4) {
            et = recupEtudiant(u);
            seances = sDAO.findSeancesOfUserByDate(et.getId(), debut, fin);
        }
        
        ((DefaultTableModel) fenetre.getRecapCours().getModel()).setRowCount(seances.size());
        
        for (int i = 0 ; i < seances.size() ; i++)
        {
            String nom = seances.get(i).get(0).getCours().getNom();
            
            if(u.getDroit() == 1) {
                ArrayList<Enseignant> enseignants = seances.get(i).get(0).getEnseignants();
                for(int j=0;j<enseignants.size();j++)
                    nom += ", " + enseignants.get(j).getPrenom() + " " + enseignants.get(j).getNom();
            }
            
            for (int a = 0; a<seances.get(i).get(0).getGroupes().size(); a++)
            {
                nom += " "+seances.get(i).get(0).getGroupes().get(a).getNom();
                nom += " - "+seances.get(i).get(0).getGroupes().get(a).getPromotion().getNom();
            }
            
            for(int j=0;j<seances.get(i).size();j++) {
                //TAMPON POUR JTREE
                nom += "[" + seances.get(i).get(j).getDate() + " " + seances.get(i).get(j).getHeureDebut() + " " + seances.get(i).get(j).getHeureFin() + "]";
            }
            
            fenetre.getRecapCours().setValueAt(nom, i, 0);
            
            String premiereSeance = seances.get(i).get(0).getDate() + " de "+ seances.get(i).get(0).getHeureDebut() +" à "+ seances.get(i).get(0).getHeureFin();
            fenetre.getRecapCours().setValueAt(premiereSeance, i, 1);
            int dernier = seances.get(i).size()-1;
            String derniereSeance = seances.get(i).get(dernier).getDate() + " de "+ seances.get(i).get(dernier).getHeureDebut() +" à "+ seances.get(i).get(dernier).getHeureFin(); 
            fenetre.getRecapCours().setValueAt(derniereSeance, i, 2);
            
            int heure = 0;
            int minute = 0;
            String duree = new String();
            for (int a = 0 ; a < seances.get(i).size() ; a++)
            {
                duree = seances.get(i).get(a).calculDuree(); //On récupère la durée de la séance i
                int p; //Première occurence de la lettre h de la durée
                p = duree.indexOf('h');
                heure += Integer.parseInt(duree.substring(0,p)); //On somme tout les heures
                minute += Integer.parseInt(duree.substring(p+1,duree.length())); //On somme tout les minutes
            }
            duree = seances.get(i).get(0).orderingHour(heure+"h"+minute); //On appel n'importe que séance, nous souhaitons juste ranger les heures/minutes
            fenetre.getRecapCours().setValueAt(duree, i, 3);
            
            String nb = String.valueOf(dernier+1);
            fenetre.getRecapCours().setValueAt(nb, i, 4);
        }      
    }
    
    /**
     * affichage de toutes les salles libres de ajd a ajd + 1 mois
     * @param recherche données de la salle sous forme de String
     */
    public void sallesLibres(String recherche) {
        SalleDAO sDAO = new SalleDAO();
        Salle s = sDAO.findByName(recherche);
        if(s != null) {
            String debut = new String();
            String[] horaires = {"08:00:00","08:15:00","08:30:00","08:45:00", 
                                "09:00:00","09:15:00","09:30:00","09:45:00", 
                                "10:00:00","10:15:00","10:30:00","10:45:00", 
                                "11:00:00","11:15:00","11:30:00","11:45:00", 
                                "12:00:00","12:15:00","12:30:00","12:45:00", 
                                "13:00:00","13:15:00","13:30:00","13:45:00", 
                                "14:00:00","14:15:00","14:30:00","14:45:00", 
                                "15:00:00","15:15:00","15:30:00","15:45:00", 
                                "16:00:00","16:15:00","16:30:00","16:45:00", 
                                "17:00:00","17:15:00","17:30:00","17:45:00", 
                                "18:00:00","18:15:00","18:30:00","18:45:00", 
                                "19:00:00","19:15:00","19:30:00","19:45:00", 
                                "20:00:00","20:15:00","20:30:00","20:45:00"};

            int jour = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            int mois = Calendar.getInstance().get(Calendar.MONTH)+1;
            int annee = Calendar.getInstance().get(Calendar.YEAR);
            int tampon = mois;

            String d = new String();
            String m = new String();
            if(jour<10)
                d = "0" + jour;
            else d = String.valueOf(jour);

            if(tampon<10)
                m = "0" + tampon;
            else m = String.valueOf(tampon);

            debut = annee + "-" + m + "-" + d;

            int pos = fenetre.calculAnneeScolaire().indexOf("/");
            String fin = new String();

            if(tampon == 12) {
                annee += 1;
                tampon = 1;
            }
            else {
                tampon += 1;
            }

            if(tampon<10)
                m = "0" + tampon;
            else m = String.valueOf(tampon);

            fin = annee + "-" + m + "-" + d;

            String periode = "  du " + debut + " au " + fin + ".";
            fenetre.getEdt().getPeriode2().setText(periode);
            fenetre.getEdt().getPeriode2().setVisible(true);
            int anneeFev = Integer.parseInt(fenetre.calculAnneeScolaire().substring(pos+1));

            String date = new String();
            ArrayList<String> joursRestants = new ArrayList<>();
            while(!date.equals(fin)) {
                Boolean nextMonth = false;
                if(jour == 30 && (mois == 4 || mois == 6 || mois == 9 || mois == 11)) {
                    nextMonth = true;
                }
                else if(jour == 31 && (mois == 1 || mois == 3 || mois == 5 || mois == 7 || mois == 8 || mois == 10 || mois == 12)) {
                    nextMonth = true;
                }
                else if(anneeBissextile(anneeFev)) {
                    if(jour == 29 && mois == 2) {
                        nextMonth = true;
                    }
                }
                else if(jour == 28 && mois == 2) {
                    nextMonth = true;
                }

                if(nextMonth && mois != 12) {
                    mois++;
                    jour = 1;
                }
                else {
                    jour++;
                }

                if(annee != anneeFev && mois == 12 && nextMonth) {
                    annee += 1;
                    mois = 1;
                    jour = 1;
                }

                String day = new String();
                String month = new String();
                if(jour<10)
                    day = "0" + jour;
                else day = String.valueOf(jour);

                if(mois<10)
                    month = "0" + mois;
                else month = String.valueOf(mois);

                date = annee + "-" + month + "-" + day;
                joursRestants.add(date);
            }        

            ((DefaultTableModel) fenetre.getLibres().getModel()).setRowCount(joursRestants.size());

            String cap = s.getCapacite() + " pers.";

            for (int i = 0 ; i < joursRestants.size() ; i++)
            {
                fenetre.getLibres().setValueAt("Le " + joursRestants.get(i), i, 0);

                String h = new String();
                ArrayList<String> htamp = new ArrayList<>();
                for(int j=0;j<horaires.length;j++) {
                    htamp.add(horaires[j]);
                }
                //String[] htamp = horaires;
                //Quand salle occupée, on remplace l'horaire par X
                for(int j=0;j<htamp.size();j++) {
                    if(!sDAO.estLibre(s.getId(), htamp.get(j), joursRestants.get(i))) {
                        htamp.set(j, "X");
                    }
                }

                StringBuilder tamp = new StringBuilder();
                for(int j=0;j<htamp.size();j++) {
                    if(!"X".equals(htamp.get(j))) {
                        if(tamp.indexOf("/") == -1 && tamp.indexOf("F") == -1)
                            tamp.insert(0, "De " + htamp.get(j) + " /");
                        else if(tamp.indexOf("F") != -1) {
                            int f = tamp.indexOf("F");
                            tamp.deleteCharAt(f);
                            tamp.insert(f, "; De " + htamp.get(j) + " /");
                        }

                        if(j == htamp.size()-1) {
                            tamp.insert(tamp.indexOf("/"), " à " + htamp.get(j));
                            tamp.deleteCharAt(tamp.length()-1);
                            h = tamp.toString();
                            tamp.delete(0, tamp.length());
                        }
                    }
                    else {
                        if(tamp.indexOf("/") != -1) {
                            if(htamp.get(j-1) == "X" && htamp.get(j) == "X")
                                j++;
                            tamp.insert(tamp.indexOf("/"), " à " + htamp.get(j-1) + "F");
                            tamp.deleteCharAt(tamp.length()-1);
                        }
                    }
                }

                fenetre.getLibres().setValueAt(h, i, 1);
                fenetre.getLibres().setValueAt(cap, i, 2);
            }      
        }
    }
    
    /**
     * retourne true si l'annee est bissextile
     * @param annee l'année en question
     * @return
     */
    public Boolean anneeBissextile(int annee) {
        if(annee%4 == 0) { 
            if(annee%100 == 0) { 
                if(annee%400 == 0)
                    return true;
                else 
                    return false;
            }
            else 
                return true;
        }
        return false;
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
     * redimensionner la hauteur des lignes d'un tableau (ici recap)
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
    
    /**
     * Méthode qui trouve l'utilisateur à partir d'un String 
     * @param recherche données de l'utilisateur sous forme de String
     */
    public void majRecapRecherche(String recherche) {
        int pos = recherche.indexOf(" ");
        String prenom = recherche.substring(0, pos);
        String nom = recherche.substring(pos+1);
        
        UtilisateurDAO uDAO = new UtilisateurDAO();
        Utilisateur u = uDAO.findByName(prenom, nom);
        
        seancesRecap(u.getEmail(), u.getPassword());
    }
  
    /**
     * Méthode qui trouve l'utilisateur à partir d'un String 
     * @param recherche données de l'utilisateur sous forme de String
     */
    public void majRecapRechercheBarre(String recherche) {
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
        
        if(u.getId() != 0)
        {
            seancesRecap(u.getEmail(), u.getPassword());
        }
    }
    /**
     * La vue fournie les données utiles pour créer l'intitulé d'un cours 
     * au controleur, le Modèle le crée, le controleur renvoie les données 
     * à mettre à jour visuellement  
     * @param name la matiàre à créer
     */
    public void createMatiere(String name)
    {
        CoursDAO cDAO = new CoursDAO();
        Cours monCours = new Cours();
        monCours.setNom(name);
        monCours = cDAO.create(monCours);
        System.out.println("matière ajouté avec succès");
        fenetre.remplirComboCours();
    }
}