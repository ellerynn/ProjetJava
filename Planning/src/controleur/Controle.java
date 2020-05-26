package controleur;

import vue.*;
import java.sql.*;

import modele.*;
import dao.*;
import java.util.*;

public class Controle {
    private Fenetre fenetre;
    
    //Constructeur
    public Controle() {
        fenetre = new Fenetre(this);
        fenetre.setVisible(true);
    }
    
    public static void main(String[] args) {
        //Connexion BDD
        Connection con;
        String nameDatabase = "edt";
        String loginDatabase = "root";
        String passwordDatabase = "";    
       
        //Ouverture interface graphique
        Controle controle = new Controle();
    }
    
    public Boolean demandeConnexion(String email, String password) {
        //UTILISATEURDAO recuperation de toutes les données
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        Utilisateur utilisateur = utilisateurDAO.find(email,password);
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
    
    public Etudiant recupEtudiant(Utilisateur utilisateur) {
        EtudiantDAO eDAO = new EtudiantDAO();
        return eDAO.find(utilisateur.getId());
    }
    
    public Enseignant recupEnseignant(Utilisateur utilisateur) {
        EnseignantDAO eDAO = new EnseignantDAO();
        return eDAO.find(utilisateur.getId());
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
        }
        
        return info;
    }
     
    public void majSeancesEdt(int semaine, String prenom, String nom) {
        UtilisateurDAO uDAO = new UtilisateurDAO();
        Utilisateur u = uDAO.findByName(prenom, nom);
        
        seancesEdt(semaine, u.getEmail(), u.getPassword());
    }
        
    public void seancesEdt(int semaine, String email, String password) {
        System.out.println("On veut afficher les seances de " + email);
        
        SeanceDAO sDAO = new SeanceDAO();
        Etudiant et = new Etudiant();
        Enseignant en = new Enseignant();
        ArrayList<Seance> seances = new ArrayList<>(); //Conteneur de seances
        ArrayList<String> strSeances; //Conteneur des string relative a une seance
        //On récupère l'utilisateur
        Utilisateur u = recupUtilisateur(email, password);
        
        //On récupère les données de l'utilisateurs selon son profil (étudiant, enseignant dont référent)
        if(u.getDroit() == 3 || u.getDroit() == 2) {
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
                
                if(jourEdt.equals(jourBDD)) { //Si l'heure correspond, récupérer la ligne
                    //System.out.println("Ces deux jour sont pareils : " + jourBDD + " et " + jourEdt);
                    colonne = i;
                }
            }
            
            //Un cours dure forcément 1h30 = 6 cases
            strSeances = seances.get(j).toArrayListOfString();
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
    
    public ArrayList<String> allUsersToStrings() {
        ArrayList<Utilisateur> utilisateurs = recupUtilisateurs();
        ArrayList<String> preNom = new ArrayList<>();
        
        for(int i=0;i<utilisateurs.size();i++)
            preNom.add(utilisateurs.get(i).getPrenom() + " " + utilisateurs.get(i).getNom());
         
        return preNom;
    }

    //MODULE MAJ(Mise a Jour)

/*
    * MODULE MAJ N°1 AFFECTER UN ENSEIGNANT A UNE SEANCE
*/
    public void AffecterEnseignantSeance(int id_seance, int id_enseignant) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance = sDAO.find(id_seance);
        
        sDAO.find_seance_enseignant(id_seance,id_enseignant);//METHODE N°5 DANS SEANCEDAO
        
        if(sDAO.find_seance_affecter_enseignant(id_seance)){//METHODE N°7 DANS SEANCEDAO
            
            System.out.println("L'enseignant que vous essayez d'affecter ne peut être affecter can un enseignant est deja affecte, veuillez AJOUTER un enseignant");
        }
        else{
            if(sDAO.find_seance_creneau_enseignant(id_enseignant, seance)){//METHODE N°14 DANS SEANCEDAO
                System.out.println("Impossible d'affecter cet enseignant à cette séance car il est deja attitre dans ce créneau");
            }else{
                    sDAO.insertInJonction(id_seance, id_enseignant, 1);//METHODE N°18 DANS SEANCEDAO
                    System.out.println("L'enseignant a bien été affecté à cette séance ! ");
            }         
        }    
    }
/*
    * MODULE MAJ N°2  AFFECTER UN GROUPE A UNE SEANCE
*/
    public void AffecterGroupeSeance(int id_seance, int id_groupe) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance = sDAO.find(id_seance);
        
        sDAO.find_seance_groupe(id_seance,id_groupe);//METHODE N°4 DANS SEANCEDAO

        if(sDAO.find_seance_affecter_groupe(id_seance)){//METHODE N°21 DANS SEANCEDAO
            
            System.out.println("La groupe que vous essayez d'affecter ne peut pas etre affecter.");
        }
        else{
            if(sDAO.find_seance_creneau_groupe(id_groupe, seance)){//METHODE N°13 DANS SEANCEDAO
                System.out.println("Impossible de rajouter ce groupe car un cours est deja attitre dans ce créneaux");
            }else{
                int capacite = 0;
                for (int i = 0 ; i < seance.getSalles().size() ; i ++){
                    capacite += seance.getSalles().get(i).getCapacite();
                }
                if(capacite >= sDAO.find_capacite_groupe_total(id_groupe, id_seance)){//METHODE N°16 DANS SEANCEDAO
                    sDAO.insertInJonction(id_seance, id_groupe, 2);//METHODE N°18 DANS SEANCEDAO
                    System.out.println("Le groupe a bien été ajouté à cette seance ! ");
                }else{System.out.println("Le nombre d'eleves dans le groupe depasse la capacité maximale de la seance");}
            }         
        }    
    }
/*
    * MODULE MAJ N°3 MODIFIER LE NOM DU COURS
*/
    public void ModifierSeanceCoursNom(int id_seance, int id_cours) {
        CoursDAO cDAO = new CoursDAO();
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance2 = sDAO.find(id_seance);
        seance2.setCours(cDAO.find(id_cours));
        seance2 = sDAO.update(seance2);
    }
/*
    * MODULE MAJ N°3 MODIFIER LE TYPE DU COURS
*/
    public void ModifierSeanceCoursType(int id_seance, int id_type) {
        TypeCoursDAO tDAO = new TypeCoursDAO();
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance2 = sDAO.find(id_seance);
        seance2.setTypeCours(tDAO.find(id_type));
        seance2 = sDAO.update(seance2);
    }
/*
    * MODULE MAJ N°4 AFFECTER UNE SALLE A UNE SEANCE
*/
    public void AffecterSalleSeance(int id_seance, int id_salle) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance = sDAO.find(id_seance);
        
        sDAO.find_seance_salle(id_seance,id_salle);//METHODE N°6 DANS SEANCEDAO

        if(sDAO.find_seance_affecter_salle(id_seance)){ //METHODE N°8 DANS SEANCEDAO
            System.out.println("La salle que vous essayez d'affecter ne peut pas etre affecter.");
        }
        else{
            if(sDAO.find_seance_creneau_salle(id_salle, seance)){ //METHODE N°15 DANS SEANCEDAO
                System.out.println("Impossible d'affecter cet salle à cette séance car il est deja attitre dans ce créneau");
            }else{

                SalleDAO salledao = new SalleDAO();
                Salle salle = salledao.find(id_salle);

                if(salle.getCapacite() >= sDAO.find_capacite_salle_total(id_seance)){ //METHODE N°17 DANS SEANCEDAO
                    sDAO.insertInJonction(id_seance, id_salle, 3); //METHODE N°18 DANS SEANCEDAO
                    System.out.println("La salle a bien été affecté à cette séance ! ");
                }else{System.out.println("Le nombre d'eleves pour cette seance depasse la capacité maximale de la seance, veuillez affecter une salle avec une capacité plus grande");}         
            }         
        }    
    }
/*
    * MODULE MAJ N°5 DEPLACER UNE SEANCE DE COURS
*/
    public void DeplacerSeance(int id_seance,int Semaine, String Date, String Heure_Debut, String Heure_Fin, int Etat) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance = sDAO.find(id_seance);
        seance.setSemaine(Semaine);
        seance.setDate(Date);
        seance.setHeureDebut(Heure_Debut);
        seance.setHeureFin(Heure_Fin);
        seance.setEtat(Etat);
        seance = sDAO.update(seance);
    }
/*
    * MODULE MAJ N°6 AJOUTER UNE SEANCE
*/
    public void AjouterSeance(int Semaine, int Date, String Heure_Debut, String Heure_Fin, int Etat, int ID_cours,int ID_type) {
        SeanceDAO sDAO = new SeanceDAO();
        
        sDAO.insertSeance(Semaine,Date, Heure_Debut, Heure_Fin,Etat, ID_cours,ID_type);//METHODE N°19 DANS SEANCEDAO
    }
/*
    * MODULE MAJ N°7 AJOUTER UN ENSEIGNANT A UNE SEANCE
*/
    public void AjouterEnseignantSeance(int id_seance, int id_enseignant) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance = sDAO.find(id_seance);
        
        sDAO.find_seance_enseignant(id_seance,id_enseignant);//METHODE N°5 DANS SEANCEDAO
        
        if(sDAO.find_seance_enseignant(id_seance, id_enseignant)){
            
            System.out.println("L'enseignant que vous essayez d'ajouter existe déjà");
        }
        else{
            if(sDAO.find_seance_creneau_enseignant(id_enseignant, seance)){//METHODE N°14 DANS SEANCEDAO
                System.out.println("Impossible d'ajouterz cet enseignant à cette séance car il est deja attitre dans ce créneau");
            }else{
                    sDAO.insertInJonction(id_seance, id_enseignant, 1);//METHODE N°18 DANS SEANCEDAO
                    System.out.println("L'enseignant a bien été ajouter à cette séance ! ");
            }         
        }    
    }
/*
    * MODULE MAJ N°8  AJOUT D'UN GROUPE A UNE SEANCE
*/
    public void AjoutGroupeSeance(int id_seance, int id_groupe) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance = sDAO.find(id_seance);
        
        sDAO.find_seance_groupe(id_seance,id_groupe);//METHODE N°4 DANS SEANCEDAO

        if(sDAO.find_seance_groupe(id_seance,id_groupe)){//METHODE N°4 DANS SEANCEDAO
            
            System.out.println("Le groupe que vous essayez d'insérer existe déjà");
        }
        else{
            if(sDAO.find_seance_creneau_groupe(id_groupe, seance)){//METHODE N°13 DANS SEANCEDAO
                System.out.println("Impossible de rajouter ce groupe car un cours est deja attitre dans ce créneaux");
            }else{
                int capacite = 0;
                for (int i = 0 ; i < seance.getSalles().size() ; i ++){
                    capacite += seance.getSalles().get(i).getCapacite();
                }
                if(capacite >= sDAO.find_capacite_groupe_total(id_groupe, id_seance)){//METHODE N°16 DANS SEANCEDAO
                    sDAO.insertInJonction(id_seance, id_groupe, 2);//METHODE N°18 DANS SEANCEDAO
                    System.out.println("Le groupe a bien été ajouté à cette seance ! ");
                }else{System.out.println("Le nombre d'eleves dans le groupe depasse la capacité maximale de la seance");}
            }         
        }    
    }
/*
    * MODULE MAJ N°9 ANNULER UNE SEANCE
*/
    public void AnnulerSeance (int id_seance)
    {
        DAO<Seance> seanceDAO = new SeanceDAO();
        Seance sea = seanceDAO.find(id_seance);
        sea.setEtat(3);
        sea = seanceDAO.update(sea);
    }
/*
    * MODULE MAJ N°10 VALIDER UNE SEANCE
*/
    public void ValiderSeance (int id_seance){
        SeanceDAO seanceDAO = new SeanceDAO();
        Seance sea = seanceDAO.find(id_seance);
        
        seanceDAO.find_seance_enseignant_valider(id_seance);// METHODE N°10  DANS SEANCEDAO
        seanceDAO.find_seance_groupe_valider(id_seance);// METHODE N°9  DANS SEANCEDAO
        //LES CONDITIONS
        if((seanceDAO.find_seance_enseignant_valider(id_seance)) && (seanceDAO.find_seance_groupe_valider(id_seance))){
            sea.setEtat(2);
            sea = seanceDAO.update(sea);//METHODE UPDATE DANS SEANCEDAO
            System.out.println("C'est fait votre séance a été validée");
        }
        else{System.out.println("On ne peux pas valider cette séance car il faut au minimum un enseignant et un groupe");} 
    }
/*
    * MODULE MAJ N°11 ENLEVER UN GROUPE D'UNE SEANCE
*/
    public void EnleverGroupeSeance(int id_seance, int id_groupe) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance2 = sDAO.find(id_seance);
        
        sDAO.find_seance_groupe_valider(id_seance);//METHODE N°9 DANS SEANCEDAO
        sDAO.find_seance_groupe_enlever_blindage(id_seance);//METHODE N°11 DANS SEANCEDAO
        //LES CONDITIONS
        if(sDAO.find_seance_groupe_valider(id_seance)){
            if(sDAO.find_seance_groupe_enlever_blindage(id_seance)>1){
                sDAO.DeleteInJonction(id_seance, id_groupe, 2);//METHODE N°12 DANS SEANCEDAO
                System.out.println("Le groupe a été enlevée, la séance est toujours disponible !");
            }
            if(sDAO.find_seance_groupe_enlever_blindage(id_seance)==1){
                sDAO.DeleteInJonction(id_seance, id_groupe, 2);//METHODE N°12 DANS SEANCEDAO
                seance2.setEtat(1);
                sDAO.update(seance2);
                System.out.println("Le groupe a été enlevée, la seéance est en cours de validation !");
            }      
            if(sDAO.find_seance_groupe_enlever_blindage(id_seance)==0)
                System.out.println("Il n'y a pas de groupe à enlever dans seance_groupes!");
                
        }  
    }
/*
    * MODULE MAJ N°11 ENLEVER UN ENSEIGNANT D'UNE SEANCE
*/
    public void EnleverEnseignantSeance(int id_seance, int id_enseignant) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance2 = sDAO.find(id_seance);
        
        sDAO.find_seance_enseignant_valider(id_seance);// METHODE N°10  DANS SEANCEDAO
        sDAO.find_seance_enseignant_enlever_blindage(id_seance);//METHODE N°12 DANS SEANCEDAO
        //LES CONDITIONS 
        if(sDAO.find_seance_enseignant_valider(id_seance)){
            if(sDAO.find_seance_enseignant_enlever_blindage(id_seance)>1){
                sDAO.DeleteInJonction(id_seance, id_enseignant, 1);//METHODE N°12 DANS SEANCEDAO
                System.out.println("L'enseignant a été enlevée, la séance est toujours disponible !");
            }
            if(sDAO.find_seance_enseignant_enlever_blindage(id_seance)==1){
                sDAO.DeleteInJonction(id_seance, id_enseignant, 1);//METHODE N°20 DANS SEANCEDAO
                seance2.setEtat(1);
                sDAO.update(seance2);
                System.out.println("Le groupe a été enlevée, la seéance est en cours de validation !");
            }   
            if(sDAO.find_seance_enseignant_enlever_blindage(id_seance)==0)
                System.out.println("Il n'y a pas d'enseignant à enlever dans seance_enseignants!");
        }  
    }
}