package controleur;

import vue.*;
import java.sql.*;

import modele.*;
import dao.*;
import java.util.ArrayList;

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
        //exempleCreationSeance();
        //Ouverture interface graphique
        Controle controle = new Controle();
    }
    
    public static void exempleCreationSeance()
    {
        //Déclaration des DAOs:
        SeanceDAO sDAO = new SeanceDAO();
        TypeCoursDAO tDAO = new TypeCoursDAO();
        CoursDAO cDAO = new CoursDAO();
        EnseignantDAO eDAO = new EnseignantDAO();
        GroupeDAO gDAO = new GroupeDAO();
        SalleDAO salleDAO = new SalleDAO();
        //Variables issus des saisies de l'utilisateur dans l'interface graphique:
        int Semaine = 1;
        String HeureDebut = "18:00:00";
        String HeureFin = "19:00:00";
        String Date = "2020:05:20";
        int CoursID = 1; //Si l'user voit le nom d'une matière, ça veut dire qu'on l'avait récup de la BDD, donc ID connu avec getID()
        int TypeID = 5; //Si l'user voit le nom d'une matière, ça veut dire qu'on l'avait récup de la BDD, donc ID connu avec getID()
        int EnseignantID = 17; // Faudra bien vérifier si l'enseignant enseigne ce cours
        int GroupeID = 2; //
        int GroupeBIS = 1; //On peut avoir 2 groupes par exemple
        int SalleID = 1; //
        
        
        //Création de ma séance
        Seance seance = new Seance(Semaine,HeureDebut,HeureFin,Date,cDAO.find(CoursID),tDAO.find(TypeID));
        seance.ajouterEnseignant(eDAO.find(EnseignantID));
        seance.ajouterGroupe(gDAO.find(GroupeID));
        seance.ajouterGroupe(gDAO.find(GroupeBIS));
        seance.ajouterSalle(salleDAO.find(SalleID));
        //On le stock dans la BDD
        seance = sDAO.create(seance);
        //On regarde si on a tout
        /*
        System.out.println("ID de la matiere: "+ seance.getCours().getId());
        System.out.println("ID animateur(s) (j'en ai affiché qu'un: "+ seance.getEnseignants().get(0).getNom());
        System.out.println("ID des groupe présent (J'en ai qu'affiché qu'un) : "+ seance.getGroupes().get(0).getId());
        System.out.println("ID de la salle (J'en ai qu'affiché qu'un): "+ seance.getSalles().get(0).getId());
        */
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
                etudiant.setId(utilisateur.getId());
                etudiant.setNom(utilisateur.getNom());
                etudiant.setPrenom(utilisateur.getPrenom());
                etudiant.setEmail(utilisateur.getEmail());
                etudiant.setPassword(utilisateur.getPassword());
                etudiant.setDroit(utilisateur.getDroit());

                DAO<Etudiant> etudiantDAO = new EtudiantDAO(); //Récupération des données de l'étudiant via DAO
                etudiant = etudiantDAO.find(etudiant.getId());
            }
            
            if(utilisateur.getDroit() == 3) { //C'est un enseignant (droit = 3)   
                enseignant.setId(utilisateur.getId());
                enseignant.setNom(utilisateur.getNom());
                enseignant.setPrenom(utilisateur.getPrenom());
                enseignant.setEmail(utilisateur.getEmail());
                enseignant.setPassword(utilisateur.getPassword());
                enseignant.setDroit(utilisateur.getDroit());

                DAO<Enseignant> enseignantDAO = new EnseignantDAO(); //Récupération des données de l'enseignant via DAO
                enseignant = enseignantDAO.find(enseignant.getId());
                
                for (int j = 0 ; j < enseignant.getCours().size() ; j++) {
                    SeanceDAO seanceDAO = new SeanceDAO(); //Récupération des données de l'enseignant via DAO
                    enseignant.setSeances(seanceDAO.findSeanceByClassForTeacher(enseignant.getCours().get(j).getId()));
                }
            }
            
            if(utilisateur.getDroit() == 2 || utilisateur.getDroit() == 1) { //C'est un référent pédagogique (droit = 2) ou un administrateur
                //IL FAUT RECUPERER TOUTES LA BASE DE DONNEES
                enseignant.setId(utilisateur.getId());
                enseignant.setNom(utilisateur.getNom());
                enseignant.setPrenom(utilisateur.getPrenom());
                enseignant.setEmail(utilisateur.getEmail());
                enseignant.setPassword(utilisateur.getPassword());
                enseignant.setDroit(utilisateur.getDroit());

                DAO<Enseignant> enseignantDAO = new EnseignantDAO(); //Récupération des données de l'enseignant via DAO
                enseignant = enseignantDAO.find(enseignant.getId());
                
                for (int j = 0 ; j < enseignant.getCours().size() ; j++) {
                    SeanceDAO seanceDAO = new SeanceDAO(); //Récupération des données de l'enseignant via DAO
                    enseignant.setSeances(seanceDAO.findSeanceByClassForTeacher(enseignant.getCours().get(j).getId()));
                }
            }
            
            //On peut passer a la suite
            return true;
        }
              
        return false; //On n'a pas trouvé d'utilisateur correspondant
    }
}
