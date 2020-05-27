package controleur;

import vue.*;
import java.sql.*;

import modele.*;
import dao.*;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Controle {
    private Fenetre fenetre;
    
    //Constructeur
    public Controle() {
        fenetre = new Fenetre(this);
        fenetre.setVisible(true);
    }
    
    public static void main(String[] args) { 
       
        //Ouverture interface graphique
        java.awt.EventQueue.invokeLater(new Runnable() {
           public void run() {
                new JFreeChartTest().setVisible(true);
            }
        });
        
        new Controle();
    }
    
    public Boolean demandeConnexion(String email, String password) {
        Utilisateur utilisateur = recupUtilisateur(email, password);
        //On a trouvé un utilisateur -> return true
        //Sinon, return false        
        return !(utilisateur.getEmail().isEmpty() && utilisateur.getPassword().isEmpty()); 
    }
    
    public String utilisateurCourant(String email, String password) {
        Utilisateur utilisateur = recupUtilisateur(email, password);
        return utilisateur.getPrenom() + " " + utilisateur.getNom();
    }
    
    public Utilisateur recupUtilisateur(String email, String password) {
        UtilisateurDAO uDAO = new UtilisateurDAO();
        return uDAO.find(email,password);
    }
    
    public ArrayList<Utilisateur> recupUtilisateurs() {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        return utilisateurDAO.find();
    }
    
    public ArrayList<Groupe> recupGroupes() {
        GroupeDAO gDAO = new GroupeDAO();
        return gDAO.find();
    }
    
    public Etudiant recupEtudiant(Utilisateur utilisateur) {
        EtudiantDAO eDAO = new EtudiantDAO();
        return eDAO.find(utilisateur.getId());
    }
    
    public Enseignant recupEnseignant(Utilisateur utilisateur) {
        EnseignantDAO eDAO = new EnseignantDAO();
        return eDAO.find(utilisateur.getId());
    }
    
    public Boolean admin(String email, String password) {
        Utilisateur u = recupUtilisateur(email, password);
        return u.getDroit() == 1;
    }
    
    public String recupInfo(String email, String password) {
        Utilisateur u = recupUtilisateur(email, password);
        String info = new String();
        
        //Si etudiant -> Promo
        if(u.getDroit() == 4) {
            Etudiant e = recupEtudiant(u);
            //Récupérer controle, puis utilisateur, groupe, puis promotion
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
     
    //Affichage Edt dans cours
    public void majSeancesEdt(int semaine, String prenom, String nom) {
        UtilisateurDAO uDAO = new UtilisateurDAO();
        Utilisateur u = uDAO.findByName(prenom, nom);
        
        //System.out.println("dans maj seances edt semaine prenom nom" + u.getPrenom() + " " + u.getNom());
        
        seancesEdt(semaine, u.getEmail(), u.getPassword());
    }
    
    //Affichage edt sur une semaine
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
    
    //Affichage edt d'une seule journee
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
    
    //Affichage Edt d'un groupe pour référent
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
    
    //Affichage de toutes les séances de l'année scolaire en cours
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
    
    public void updateRowHeights()
    {       
        int column = fenetre.getRecapCours().getSelectedColumn();
        int row = fenetre.getRecapCours().getSelectedRow();
        int rowHeight = fenetre.getRecapCours().getRowHeight();
        Component comp = fenetre.getRecapCours().prepareRenderer(fenetre.getRecapCours().getCellRenderer(row, column), row, column);
        rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
        fenetre.getRecapCours().setRowHeight(row, rowHeight);
    }

    public ArrayList<String> allUsersToStrings() {
        ArrayList<Utilisateur> utilisateurs = recupUtilisateurs();
        ArrayList<String> preNom = new ArrayList<>();
        
        for(int i=0;i<utilisateurs.size();i++)
            preNom.add(utilisateurs.get(i).getPrenom() + " " + utilisateurs.get(i).getNom());
         
        return preNom;
    }

    public ArrayList<String> allGroupsToStrings() {
        ArrayList<Groupe> groupes = recupGroupes();
        ArrayList<String> gp = new ArrayList<>();
        
        for(int i=0;i<groupes.size();i++)
            gp.add(groupes.get(i).getNom() + " " + groupes.get(i).getPromotion().getNom());
         
        return gp;
    }
    
    public ArrayList<TypeCours> recupAllTypes(){
        TypeCoursDAO tDAO =new TypeCoursDAO();
        return tDAO.findAllTypes();
    }
    
    public ArrayList<String> allTypeToStrings(){
        ArrayList<TypeCours> types = recupAllTypes();
        ArrayList<String> tp = new ArrayList<>();
        
        for (int i = 0 ; i <types.size(); i++)
            tp.add(types.get(i).getNom());
        return tp;
    }
    
    public ArrayList<Cours> recupAllCours(){
        CoursDAO DAO =new CoursDAO();
        return DAO.findAllCours();
    }
    
    public ArrayList<String> allCoursToStrings(){
        ArrayList<Cours> cours = recupAllCours();
        ArrayList<String> c = new ArrayList<>();
        
        for (int i = 0 ; i <cours.size(); i++)
            c.add(cours.get(i).getNom());
        return c;
    }
    
    public ArrayList<Salle> recupAllSalles(){
        SalleDAO DAO = new SalleDAO();
        return DAO.findAllSalles();
    }
    
    public ArrayList<String> allSallesToStrings(){
        ArrayList<Salle> salles = recupAllSalles();
        ArrayList<String> s = new ArrayList<>();
        
        for (int i = 0 ; i <salles.size(); i++)
            s.add(salles.get(i).getNom()+" "+ salles.get(i).getSite().getNom());
        return s;
    }
    
    public ArrayList<Enseignant> recupAllEnseignants(){
        EnseignantDAO eDAO = new EnseignantDAO();
        return eDAO.findAllTeacher();
    }
    
    public ArrayList<String> allEnseignantsToStrings(){
        ArrayList<Enseignant> ens = recupAllEnseignants();
        ArrayList<String> s = new ArrayList<>();
        
        for (int i = 0 ; i <ens.size(); i++)
            s.add(ens.get(i).getNom()+" "+ ens.get(i).getPrenom());
        return s;
    }
    
    public ArrayList<Seance> recupAllSeances(){
        SeanceDAO dao = new SeanceDAO();
        return dao.findAllSeances();
    }
    
    public ArrayList<String> allSeancesToStrings(){
        ArrayList<Seance> seances = recupAllSeances();
        ArrayList<String> string = new ArrayList<>();
        for (int i = 0 ; i < seances.size() ; i++)
        {
            ArrayList<String> temp = seances.get(i).toArrayListOfString();
            String msg = "Seance N°"+seances.get(i).getId();
            if(seances.get(i).getEtat() != 2) //si pas valide, car temp est de taille 6
                msg+=temp.get(0)+" "+temp.get(1)+" "+temp.get(2)+" "+temp.get(3)+" "+temp.get(4)+" "+temp.get(5);
            else //Si valide temp est de taille 5
                msg+= temp.get(0)+" "+temp.get(1)+" "+temp.get(2)+" "+temp.get(3)+" "+temp.get(4);
            
            string.add(msg);
        }
        
        return string;
    }
}