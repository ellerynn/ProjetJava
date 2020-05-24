package controleur;

import vue.*;
import java.sql.*;

import modele.*;
import dao.*;
import java.util.*;

public class Controle {
    private Fenetre fenetre;
    private Utilisateur utilisateur;
    private Etudiant etudiant;
    private Enseignant enseignant;
    private static Connection connect;
    
    //Constructeur
    public Controle() {
        fenetre = new Fenetre(this);
        utilisateur = new Utilisateur();
        etudiant = new Etudiant();
        enseignant = new Enseignant();
    }
    
    public static void main(String[] args) {
        //Connexion BDD
        Connection con;
        String nameDatabase = "edt";
        String loginDatabase = "root";
        String passwordDatabase = "";    
       
        //Ouverture interface graphique
        Controle controle = new Controle();
        //controle.ValiderSeance(3);  
        //controle.AnnulerSeance(1);
        //controle.ModifierSeanceCoursNom(3,7);
        //controle.ModifierSeanceCoursType(1,6);
        //controle.AjoutGroupeSeance(1,6);
        //controle.EnleverGroupeSeance(1, 5);
        //controle.EnleverEnseignantSeance(1, 16);
        //controle.AffecterEnseignantSeance(1,18);
        //controle.AjouterEnseignantSeance(1,17);
        //controle.AffecterSalleSeance(13,2);
        //controle.AffecterGroupeSeance(4,5);
    }
    
    //MODULE MAJ(Mise a Jour)/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
    
   //FIN DU MODULE MAJ ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    //Getters
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    
    public Etudiant getEtudiant() {
        return etudiant;
    }
    
    public Enseignant getEnseignant() {
        return enseignant;
    }
    
    public Boolean demandeConnexion() {
        String email = fenetre.getConnexion().getEmail();
        String password = fenetre.getConnexion().getPassword();

        //UTILISATEURDAO recuperation de toutes les données
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        utilisateur = utilisateurDAO.find(email,password);
        
        if(!(utilisateur.getEmail().isEmpty() && utilisateur.getPassword().isEmpty())) { //On a trouvé un utilisateur 
            //CAS PARTICULIERS D'UTILISATEUR : enseignant, étudiant, référent (= enseignant)
            if(utilisateur.getDroit() == 4) { //C'est un etudiant (droit = 4)
                recupEtudiant();
            }
            
            if(utilisateur.getDroit() == 3) { //C'est un enseignant (droit = 3)   
                recupEnseignant();
            }
            
            if(utilisateur.getDroit() == 2 || utilisateur.getDroit() == 1) { //C'est un référent pédagogique (droit = 2) ou un administrateur
                
            }
            
            //On peut passer a la suite
            return true;
        }
              
        return false; //On n'a pas trouvé d'utilisateur correspondant
    }

    public void recupEnseignant() {
        enseignant.setId(utilisateur.getId());
        enseignant.setNom(utilisateur.getNom());
        enseignant.setPrenom(utilisateur.getPrenom());
        enseignant.setEmail(utilisateur.getEmail());
        enseignant.setPassword(utilisateur.getPassword());
        enseignant.setDroit(utilisateur.getDroit());

        DAO<Enseignant> enseignantDAO = new EnseignantDAO(); //Récupération des données de l'enseignant via DAO
        enseignant = enseignantDAO.find(enseignant.getId());
    }
    
    public void recupEtudiant() {
        etudiant.setId(utilisateur.getId());
        etudiant.setNom(utilisateur.getNom());
        etudiant.setPrenom(utilisateur.getPrenom());
        etudiant.setEmail(utilisateur.getEmail());
        etudiant.setPassword(utilisateur.getPassword());
        etudiant.setDroit(utilisateur.getDroit());
    }
    
    public void recupSeances(int semaine) {
        SeanceDAO seanceDAO = new SeanceDAO();
        ArrayList<Seance> seances = seanceDAO.findSeancesByUserAndWeek(etudiant.getId(), semaine);
        
        if(utilisateur.getDroit() == 3 || utilisateur.getDroit() == 2) {
            //On ne récupère que les séances de la semaine courante
            enseignant.setSeances(seanceDAO.findSeancesByUserAndWeek(enseignant.getId(), semaine));
                
        }
        
        if(utilisateur.getDroit() == 4) {
            //On ne récupère que les séances de la semaine courante
            etudiant.setSeances(seanceDAO.findSeancesByUserAndWeek(etudiant.getId(), semaine));
        }
    }
    
    public int semaine() {
        Calendar cal = Calendar.getInstance(); //Date du jour        
        return cal.get(Calendar.WEEK_OF_YEAR); //Semaine courante
    }
    
    
}
