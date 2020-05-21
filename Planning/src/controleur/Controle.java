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
