package controleur;

import dao.CoursDAO;
import dao.EnseignantDAO;
import dao.EtudiantDAO;
import dao.GroupeDAO;
import dao.PromotionDAO;
import dao.SalleDAO;
import dao.SeanceDAO;
import dao.TypeCoursDAO;
import dao.UtilisateurDAO;
import java.awt.Component;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
        
        new JFreeChartTest();
    }

   //Ouverture interface graphique
  public void afficherGraphe() {
        SalleDAO sDAO = new SalleDAO();
        ArrayList<Salle> salles = recupAllSalles();
        ArrayList<String> s = new ArrayList<>();

        DefaultPieDataset pieDataset = new DefaultPieDataset();//eiffel 1
        DefaultPieDataset pieDataset2 = new DefaultPieDataset();//eiffel 2
        DefaultPieDataset pieDataset3 = new DefaultPieDataset();//eiffel 4
        
        for (int i = 0 ; i <salles.size(); i++){
            if(salles.get(i).getSite().getNom().equals("Eiffel 1"))
            pieDataset.setValue(salles.get(i).getNom()+",Capacité :"+salles.get(i).getCapacite(), new Integer(salles.get(i).getCapacite()));
            if(salles.get(i).getSite().getNom().equals("Eiffel 2"))
            pieDataset2.setValue(salles.get(i).getNom()+",Capacité :"+salles.get(i).getCapacite(), new Integer(salles.get(i).getCapacite()));
            if(salles.get(i).getSite().getNom().equals("Eiffel 4"))
            pieDataset3.setValue(salles.get(i).getNom()+",Capacité :"+salles.get(i).getCapacite(), new Integer(salles.get(i).getCapacite()));
        }

        JFreeChart chart = ChartFactory.createPieChart("Capacité des salles pour Eiffel 1", pieDataset, true, true, true);//eiffel 1
        JFreeChart chart2 = ChartFactory.createPieChart("Capacité des salles pour Eiffel 2", pieDataset2, true, true, true);//eiffeil 2
        JFreeChart chart3 = ChartFactory.createPieChart("Capacité des salles pour Eiffel 4", pieDataset3, true, true, true);//eiffel 4
        PiePlot P=(PiePlot)chart.getPlot();
        PiePlot P2=(PiePlot)chart2.getPlot();
        PiePlot P3=(PiePlot)chart3.getPlot();
        //P.setForegroundAlpha(TOP_ALIGNMENT);
        ChartFrame frame = new ChartFrame("Capacité des salles pour Eiffel 1", chart );
        ChartFrame frame2 = new ChartFrame("Capacité des salles pour Eiffel 2", chart2 );
        ChartFrame frame3 = new ChartFrame("Capacité des salles pour Eiffel 4", chart3 );
        

        frame.setVisible(true);
        frame.setSize(500,500);
        
        frame2.setVisible(true);
        frame2.setSize(500,500);
        
        frame3.setVisible(true);
        frame3.setSize(500,500);
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
        }
        
        return info;
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
            seances = sDAO.findSeancesOfUserByDate(en.getId(), debut, fin);
            
            //Ne doit pas voir les séances en cours de validation (etat = 1)
            for(int i=0;i<seances.size();i++) {
                for(int j=0;j<seances.get(i).size();j++) {
                    /*System.out.println("Taille seances = " + seances.size());
                    System.out.print("Etat = " + seances.get(i).getEtat() + " ");
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
                System.out.println(seances.get(i).get(a).getDate() + " de "+ seances.get(i).get(a).getHeureDebut() +" à "+ seances.get(i).get(a).getHeureFin() + " ("+duree +")");
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

    /**
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
     * @return tous les types de cours de la BDD
     */
    public ArrayList<TypeCours> recupAllTypes(){
        TypeCoursDAO tDAO =new TypeCoursDAO();
        return tDAO.findAllTypes();
    }
    
    /**
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
     * @return tous les cours de la BDD
     */
    public ArrayList<Cours> recupAllCours(){
        CoursDAO DAO =new CoursDAO();
        return DAO.findAllCours();
    }
    
    /**
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
     * @return toutes les salles de la BDD
     */
    public ArrayList<Salle> recupAllSalles(){
        SalleDAO DAO = new SalleDAO();
        return DAO.findAllSalles();
    }
    
    /**
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
     * @return tous les enseignants de la BDD
     */
    public ArrayList<Enseignant> recupAllEnseignants(){
        EnseignantDAO eDAO = new EnseignantDAO();
        return eDAO.findAllTeacher();
    }
    
    /**
     * @return un ArrayList de tous les enseignants de la BDD
     */
    public ArrayList<String> allEnseignantsToStrings(){
        ArrayList<Enseignant> ens = recupAllEnseignants();
        ArrayList<String> s = new ArrayList<>();
        
        for (int i = 0 ; i <ens.size(); i++)
            s.add(ens.get(i).getPrenom()+" "+ ens.get(i).getNom());
        return s;
    }
    
    /**
     * @return toutes les seances de la BDD
     */
    public ArrayList<Seance> recupAllSeances(){
        SeanceDAO dao = new SeanceDAO();
        return dao.findAllSeances();
    }
    
    /**
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
     * Demande d'ajout d'une séance par la vue vers le controleur en récupérant tout les données saisies par l'user
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
            
            ArrayList<Enseignant> enseignants = new ArrayList<>();              
            List list = (List)strings.get(6);                       
            for (Iterator it = list.iterator() ; it.hasNext(); ){
                enseignants.add(uDAO.findByName((String)it.next()));//Enseignants
            }
            
            ArrayList<Groupe> groupes = new ArrayList<>();
            list = (List)strings.get(7);
            for (Iterator it = list.iterator() ; it.hasNext(); ){
                groupes.add(gDAO.findByName((String)it.next()));//Groupes
            }
            
            ArrayList<Salle> salles = new ArrayList<>();
            list = (List)strings.get(8);
            for (Iterator it = list.iterator() ; it.hasNext(); ){
                salles.add(salleDAO.findByName((String)it.next()));//Salles
            }
            ajouterSeanceInModel(semaine,date,heureDebut,heureFin,etat,cours,type,groupes, enseignants,salles);
        }
    }
    
    /**
     * Le controleur demande au model l'ajout d'une séance en lui fournissant les données issues de la vue.
     * Le modèle renvoie des réponses vers le controleur pour savoir si tels données sont acceptables et si 
     * toutes les données saisies sont cohérentes.
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
}