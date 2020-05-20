package controleur;

import vue.*;
import java.sql.*;

import modele.*;
import dao.*;
import java.awt.event.*;
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
        String email = new String();
        String password = new String();
        
        email = fenetre.getConnexion().getEmail();
        password = fenetre.getConnexion().getPassword();

        //UTILISATEURDAO recuperation de toutes les données
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        utilisateur = utilisateurDAO.find(email,password);
                
        System.out.println("le mail : "+ utilisateur.getEmail());
        System.out.println("le password : "+ utilisateur.getPassword());
        
        if(!(utilisateur.getEmail().isEmpty() && utilisateur.getPassword().isEmpty())) { //On a trouvé un utilisateur 
            if(utilisateur.getDroit() == 4) { //C'est un etudiant (droit = 4)
                etudiant.setId(utilisateur.getId());
                etudiant.setDroit(utilisateur.getDroit());
                etudiant.setEmail(utilisateur.getEmail());
                etudiant.setNom(utilisateur.getNom());
                etudiant.setPassword(utilisateur.getPassword());
                etudiant.setPrenom(utilisateur.getPrenom());

                DAO<Etudiant> etudiantDAO = new EtudiantDAO(); //Récupération des données de l'étudiant via DAO
                etudiant = etudiantDAO.find(etudiant.getId());
                System.out.println("le numero de l'etudiant : "+ etudiant.getNumero());
                System.out.println("l'ID du groupe de l'etudiant : "+ etudiant.getGroupe().getId());
                
                SeanceDAO seanceDAO = new SeanceDAO(); //Récupération des séances de l'étudiant selon son groupe
                ArrayList<Seance> seancesByTD = seanceDAO.findSeancesByGroupForStudent(etudiant.getGroupe().getId());
                
                for (int i = 0 ; i < seancesByTD.size() ; i++)
                {
                    System.out.println("Seance d'id : "+ seancesByTD.get(i).getId() + seancesByTD.get(i).getCours().getNom()+"\n");

                }
            }
            
            //C'est un enseignant (droit = 3) ou un référent pédagogique (droit = 2)
            if(utilisateur.getDroit() == 3 || utilisateur.getDroit() == 2) { 
                enseignant.setId(utilisateur.getId());
                enseignant.setDroit(utilisateur.getDroit());
                enseignant.setEmail(utilisateur.getEmail());
                enseignant.setNom(utilisateur.getNom());
                enseignant.setPassword(utilisateur.getPassword());
                enseignant.setPrenom(utilisateur.getPrenom());

                DAO<Enseignant> enseignantDAO = new EnseignantDAO(); //Récupération des données de l'étudiant via DAO
                enseignant = enseignantDAO.find(enseignant.getId());
                
                SeanceDAO seanceDAO = new SeanceDAO(); //Récupération des séances de l'enseignant selon son groupe
                ArrayList<Seance> seancesByClass = seanceDAO.findSeancesByClassForTeacher(enseignant.getId());
                
                for (int i = 0 ; i < seancesByClass.size() ; i++)
                {
                    System.out.println("Seance d'id : "+ seancesByClass.get(i).getId() + seancesByClass.get(i).getCours().getNom()+"\n");
                }
                
                CoursDAO coursDAO = new CoursDAO(); //Récupération des cours de l'enseignant
                ArrayList<Cours> cours = coursDAO.findCoursForTeacher(enseignant.getId());
                
                for (int i = 0 ; i < cours.size() ; i++)
                {
                    System.out.println("Cours d'id : "+ cours.get(i).getId() + cours.get(i).getNom()+"\n");
                }                
            }
            
            //C'est un administrateur = utilisateur (droit = 1)
            //On peut passer a la suite
            return true;
        }
              
        return false; //On n'a pas trouvé d'utilisateur correspondant
    }
}
