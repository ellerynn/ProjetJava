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
        //controle.ModifierSeanceCoursNom(3,7);
        //controle.ModifierSeanceCoursType(1,6);
        //controle.AjoutGroupeSeance(1,6);
        //controle.EnleverGroupeSeance(1, 5);
        //controle.EnleverEnseignantSeance(1, 16);
    }
    
    //MAJ (Mise a Jour)
    public void AjoutGroupeSeance(int id_seance, int id_groupe) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance = sDAO.find(id_seance);
        sDAO.find_seance_groupe(id_seance,id_groupe);

        if(sDAO.find_seance_groupe(id_seance,id_groupe)){
            
            System.out.println("Le groupe que vous essayez d'insérer existe déjà");
        }
        else{
            if(sDAO.find_seance_creneau(id_groupe, seance)){
                System.out.println("Impossible de rajouter ce groupe car un cours est deja attitre dans ce créneaux");
            }else{
                int capacite = 0;
                for (int i = 0 ; i < seance.getSalles().size() ; i ++){
                    capacite += seance.getSalles().get(i).getCapacite();
                }
                if(capacite >= sDAO.find_capacite_groupe_total(id_groupe, id_seance)){
                    sDAO.insertInJonction(id_seance, id_groupe, 2);
                    System.out.println("Le groupe a bien été ajouté à cette seance ! ");
                }else{System.out.println("Le nombre d'eleves dans le groupe depasse la capacité maximale de la seance");}
            }         
        }    
    }
    
    public void ModifierSeanceCoursNom(int id_seance, int id_cours) {
        CoursDAO cDAO = new CoursDAO();
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance2 = sDAO.find(id_seance);
        seance2.setCours(cDAO.find(id_cours));
        seance2 = sDAO.update(seance2);
    }
    public void ModifierSeanceCoursType(int id_seance, int id_type) {
        TypeCoursDAO tDAO = new TypeCoursDAO();
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance2 = sDAO.find(id_seance);
        seance2.setTypeCours(tDAO.find(id_type));
        seance2 = sDAO.update(seance2);
    }
    
    public void EnleverGroupeSeance(int id_seance, int id_groupe) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance2 = sDAO.find(id_seance);
        sDAO.find_seance_groupe_valider(id_seance);
        sDAO.find_seance_groupe_enlever_blindage(id_seance);
        if(sDAO.find_seance_groupe_valider(id_seance)){
            if(sDAO.find_seance_groupe_enlever_blindage(id_seance)>1){
                sDAO.DeleteInJonction(id_seance, id_groupe, 2);
                System.out.println("Le groupe a été enlevée, la séance est toujours disponible !");
            }
            if(sDAO.find_seance_groupe_enlever_blindage(id_seance)==1){
                sDAO.DeleteInJonction(id_seance, id_groupe, 2);
                seance2.setEtat(1);
                sDAO.update(seance2);
                System.out.println("Le groupe a été enlevée, la seéance est en cours de validation !");
            }      
            if(sDAO.find_seance_groupe_enlever_blindage(id_seance)==0)
                System.out.println("Il n'y a pas de groupe à enlever dans seance_groupes!");
                
        }  
    }
    
    public void EnleverEnseignantSeance(int id_seance, int id_enseignant) {
        SeanceDAO sDAO = new SeanceDAO();
        Seance seance2 = sDAO.find(id_seance);
        sDAO.find_seance_enseignant_valider(id_seance);
        sDAO.find_seance_enseignant_enlever_blindage(id_seance);
        if(sDAO.find_seance_enseignant_valider(id_seance)){
            if(sDAO.find_seance_enseignant_enlever_blindage(id_seance)>1){
                sDAO.DeleteInJonction(id_seance, id_enseignant, 1);
                System.out.println("L'enseignant a été enlevée, la séance est toujours disponible !");
            }
            if(sDAO.find_seance_enseignant_enlever_blindage(id_seance)==1){
                sDAO.DeleteInJonction(id_seance, id_groupe, 2);
                seance2.setEtat(1);
                sDAO.update(seance2);
                System.out.println("Le groupe a été enlevée, la seéance est en cours de validation !");
            }   
            if(sDAO.find_seance_enseignant_enlever_blindage(id_seance)==0)
                System.out.println("Il n'y a pas d'enseignant à enlever dans seance_enseignants!");
        }  
    }

    public void ValiderSeance (int id_seance)
    {
        SeanceDAO seanceDAO = new SeanceDAO();
        Seance sea = seanceDAO.find(id_seance);
        seanceDAO.find_seance_enseignant_valider(id_seance);
        seanceDAO.find_seance_groupe_valider(id_seance);
        if((seanceDAO.find_seance_enseignant_valider(id_seance)) && (seanceDAO.find_seance_groupe_valider(id_seance)))
        {
            sea.setEtat(2);
            sea = seanceDAO.update(sea);
            System.out.println("C'est fait votre séance a été validée");
        }
        else
        {
            System.out.println("On ne peux pas valider cette séance car il faut au minimum un enseignant et un groupe");
        }
        
    }
    
    public void AnnulerSeance (int id_seance)
    {
        DAO<Seance> seanceDAO = new SeanceDAO();
        Seance sea = seanceDAO.find(id_seance);
        sea.setEtat(3);
        sea = seanceDAO.update(sea);
    }
    
    
    
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

        //On ne récupère que les séances de la semaine courante
        SeanceDAO seanceDAO = new SeanceDAO(); //Récupération des données de l'enseignant via DAO
        enseignant.setSeances(seanceDAO.findSeancesByUserAndWeek(enseignant.getId(), semaine()));
    }
    
    public void recupEtudiant() {
        etudiant.setId(utilisateur.getId());
        etudiant.setNom(utilisateur.getNom());
        etudiant.setPrenom(utilisateur.getPrenom());
        etudiant.setEmail(utilisateur.getEmail());
        etudiant.setPassword(utilisateur.getPassword());
        etudiant.setDroit(utilisateur.getDroit());

        //On ne récupère que les séances de la semaine courante
        SeanceDAO seanceDAO = new SeanceDAO(); //Récupération des données de l'étudiant via DAO
        etudiant.setSeances(seanceDAO.findSeancesByUserAndWeek(etudiant.getId(), semaine()));
    }
    
    public int semaine() {
        Calendar cal = Calendar.getInstance(); //Date du jour        
        return cal.get(Calendar.WEEK_OF_YEAR); //Semaine courante
    }
    
    
}
