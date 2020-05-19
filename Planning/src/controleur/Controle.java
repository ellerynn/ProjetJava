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
        
        //Ouverture interface graphique
        Controle controle = new Controle();
        
        /*
        //TYPECOURSDAO recuperation du nom
        DAO<TypeCours> typecoursDAO = new TypeCoursDAO();
        TypeCours lol = typecoursDAO.find(5);
        System.out.println("le type de cours : "+ lol.getNom());
        System.out.println("***********************************1");
        
        //PromotionDAO recuperation du nom
        DAO<Promotion> promotionDAO = new PromotionDAO();
        Promotion pro = promotionDAO.find(2);
        System.out.println("la promo : "+ pro.getNom());
        System.out.println("***********************************2");
        
        //SITEDAO recuperation du nom
        DAO<Site> siteDAO = new SiteDAO();
        Site sit = siteDAO.find(2);
        System.out.println("le site : "+ sit.getNom());
        System.out.println("***********************************3");
        
        //COURSDAO recuperation du nom
        DAO<Cours> coursDAO = new CoursDAO();
        Cours cou = coursDAO.find(2);
        System.out.println("le cours : "+ cou.getNom());
        System.out.println("***********************************4");
        
        //UTILISATEURDAO recuperation de toutes les données
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        Utilisateur uti = utilisateurDAO.find("etudiant3@edu.ece.fr","etudiant");

        System.out.println("l'id : "+ uti.getId());
        System.out.println("le mail : "+ uti.getEmail());
        System.out.println("le password : "+ uti.getPassword());
        System.out.println("le nom : "+ uti.getNom());
        System.out.println("le prenom : "+ uti.getPrenom());
        System.out.println("le droit : "+ uti.getDroit());
        System.out.println("***********************************5");

        //GROUPEDAO recuperation de toutes les données
        DAO<Groupe> groupeDAO = new GroupeDAO();
        Groupe gro = groupeDAO.find(4);
        System.out.println("l'id du groupe : "+ gro.getId());
        System.out.println("le nom du groupe : "+ gro.getNom());
        Promotion promo = gro.getPromotion();
        System.out.println("l'id de la promotion du groupe : "+ promo.getId());
        System.out.println("le nom de la promotion du groupe : "+ promo.getNom());
        System.out.println("***********************************6");
        
        //SALLEDAO recuperation de toutes les données
        DAO<Salle> salleDAO = new SalleDAO();
        Salle sal = salleDAO.find(4);
        System.out.println("l'id de la salle : "+ sal.getId());
        System.out.println("le nom de la salle : "+ sal.getNom());
        Site site = sal.getSite();
        System.out.println("la capacite de la salle : "+ sal.getCapacite());
        System.out.println("le nom de la promotion du groupe : "+ site.getNom());
        System.out.println("***********************************7");
        
        //ENSEIGNANTDAO recuperation de toutes les données
        DAO<Enseignant> enseignantDAO = new EnseignantDAO();
        Enseignant ens = enseignantDAO.find(16);
        System.out.println("l'id de l'enseignant : "+ ens.getId());
        System.out.println("l'id : "+ ens.getId());
        System.out.println("le mail : "+ ens.getEmail());
        System.out.println("le password : "+ ens.getPassword());
        System.out.println("le nom : "+ ens.getNom());
        System.out.println("le prenom : "+ ens.getPrenom());
        System.out.println("le droit : "+ ens.getDroit());

        ArrayList<Cours> couurs = ens.getCours();
        for (int i = 0 ; i < couurs.size() ; i++){
            System.out.println("le cours qu'à le prof: "+ couurs.get(i).getNom());
        }
        System.out.println("***********************************8");
        
        //SEANCEDAO recuperation de toutes les données
        DAO<Seance> seanceDAO = new SeanceDAO();
        Seance sea = seanceDAO.find(1);

        System.out.println("l'id de la seance : "+ sea.getId());
        System.out.println("la semaine : "+ sea.getSemaine());
        System.out.println("l'heure de début : "+ sea.getHeureDebut());
        System.out.println("l'heure de fin : "+ sea.getHeureFin());
        System.out.println("l'etat : "+ sea.getEtat());

        System.out.println("l'id du cours : "+ (sea.getCours()).getId());
        System.out.println("l'id du type cours : "+ (sea.getTypeCours()).getId() );

        Cours cours2 = sea.getCours();
        TypeCours types2 = sea.getTypeCours();
        System.out.println("le cours: "+ cours2.getNom());
        System.out.println("le type de cours: "+ types2.getNom());

        System.out.println("le/les professeurs:");
        for (int i = 0 ; i < sea.getEnseignants().size(); i++){
            System.out.println(  (sea.getEnseignants().get(i)).getNom());
        }
        System.out.println("Le/les groupes:");
        for (int i = 0 ; i < sea.getGroupes().size(); i++){
            System.out.println(  (sea.getGroupes().get(i)).getNom());
        }
        System.out.println("Le/les salles (+site)");
        for (int i = 0 ; i < sea.getSalles().size(); i++){
            System.out.println("Salle: "+(sea.getSalles().get(i)).getNom());
            System.out.println("Localisé à : "+(sea.getSalles().get(i)).getSite().getNom());
        }

        System.out.println("***********************************9");
        
        //ETUDIANTDAO recuperation de toutes les données
        DAO<Etudiant> etudiantDAO = new EtudiantDAO();
        Etudiant etu = etudiantDAO.find(4);
        System.out.println("l'id de l'etudiant : "+ etu.getId());
        System.out.println("le numero de l'etudiant : "+ etu.getNumero());
        System.out.println("l'ID du groupe de l'etudiant : "+ etu.getGroupe().getId());


        Groupe groupe = etu.getGroupe();
        System.out.println("l'id du groupe dans table groupe: "+ groupe.getId());
        System.out.println("***********************************10");

        //*************************UPDATE ***************
        //Par exemple cours, en reprenant un cours déjà créer dans le main (et son dao aussi dj créer)
        cou.setNom("Maths");
        cou = coursDAO.update(cou); // Avant c't Analyse, mtn c'est Maths
        System.out.println("le cours : "+ cou.getNom());
        System.out.println("***********************************4UPDATE");

        //*************************RECHERCHE*************
        //**Un Lambda cherche Seance par semaine ******
        System.out.println("*********Recherche les séances pour un User et une semaine:*********"); //Marche pour prof et étudiant
        ArrayList<Seance> mesSeancesOnWeek = new ArrayList<>();
        SeanceDAO scDAO = new SeanceDAO();
        int semaine = 1;
        int userID = 11;
        mesSeancesOnWeek = scDAO.findSeancesByUserAndWeek(userID,semaine); //Si on connait le nom de l'user, on connait l'id de l'Utilisateur car sinon la personne n'existe pas
        System.out.println("EDT semaine : " + semaine + " de l'user "+ userID);
        System.out.println("Les seances sont rangés par date et heure: ");
        for (int i = 0 ; i < mesSeancesOnWeek.size() ; i++) //Pour chaque séance de cette semaine de ce prof :
        {
           System.out.println("----------------------------------:");
           System.out.println("Cours en cours de validation/valider/annuler : "+ mesSeancesOnWeek.get(i).getEtat()); 
           //Faudra trier en fct de getEtat(), si on veut afficher que ceux annuler
           System.out.println("la date : "+ mesSeancesOnWeek.get(i).getDate());
           System.out.println("Heure debut: "+  mesSeancesOnWeek.get(i).getHeureDebut());
           System.out.println("Heure fin: "+  mesSeancesOnWeek.get(i).getHeureFin());

           for (int a = 0 ; a < mesSeancesOnWeek.get(i).getSalles().size() ; a++) //Les salles
           {
               System.out.println("Salle :"+ mesSeancesOnWeek.get(i).getSalles().get(a).getNom());
               System.out.println("Le site de cette salle : "+ mesSeancesOnWeek.get(i).getSalles().get(a).getSite().getNom());
           }

           System.out.println("cours de : "+ mesSeancesOnWeek.get(i).getCours().getNom());
           System.out.println("Type de cours :"+ mesSeancesOnWeek.get(i).getTypeCours().getNom());

           for (int a = 0; a< mesSeancesOnWeek.get(i).getEnseignants().size(); a++) //Les profs
           {
               System.out.println("Prof qui anime : "+mesSeancesOnWeek.get(i).getEnseignants().get(a).getNom());
           }
           for (int a = 0; a< mesSeancesOnWeek.get(i).getGroupes().size(); a++) //Les groupes
           {
               System.out.println("TD présent : "+mesSeancesOnWeek.get(i).getGroupes().get(a).getNom());
               System.out.println("Appartient à promo :"+ mesSeancesOnWeek.get(i).getGroupes().get(a).getPromotion().getNom());
           }
        }

        System.out.println("\n*********Recherche les séances pour un Groupe et une semaine:*********"); //Si on connait le TD, on connait l'id du Groupe car sinon on connait pas groupe
        //SeanceDAO est dj créé dans une des simulations
        ArrayList<Seance> seancesByTD = new ArrayList<>();
        int semaine1 = 1;
        int groupeID = 1;
        seancesByTD = scDAO.findSeancesByGroupAndWeek(groupeID, semaine1);
        System.out.println("EDT semaine : " + semaine1 + " du groupe "+ groupeID);
        System.out.println(seancesByTD.size() +" a/ont été trouvé(s) [Pour extraire données, voir exemple de mesSeancesOnWeek]");
        System.out.println("Les seances sont rangés par date et heure: ");
        for (int i = 0 ; i < seancesByTD.size() ; i++)
        {
            System.out.println("Seance d'id : "+ seancesByTD.get(i).getId()+"\n");

        }

        System.out.println("\n*********Recherche les séances pour une promotion et une semaine:*********"); //Si on connait la promo, on connait l'id de la Promo car sinon on connait pas la promo
        ArrayList<Seance> seancesByPromo = new ArrayList<>();
        int semaine2 = 1;
        int promoID = 2;
        seancesByPromo = scDAO.findSeancesByPromoAndWeek(promoID, semaine2);
        System.out.println("EDT semaine : " + semaine2 + " de la promo "+ promoID);
        System.out.println(seancesByPromo.size() +" a/ont été trouvé(s) [Pour extraire données, voir exemple de mesSeancesOnWeek]");
        System.out.println("Les seances sont rangés par date et heure: ");
        for (int i = 0 ; i < seancesByPromo.size() ; i++)
        {
            System.out.println("Seance d'id : "+ seancesByPromo.get(i).getId());

        }
        System.out.println("\n*********Recherche edt d'une salle (occupés ou/et annulés) et une semaine:*********"); ///Il s'agit d'afficher les horaires occupés/annulés
        ArrayList<Seance> seancesBySalle = new ArrayList<>();
        int semaine3 = 1;
        int salleID = 3;
        seancesBySalle = scDAO.findSeancesBySalle(salleID, semaine3);
        System.out.println("Les seances sont rangés par date et heure: ");
        for (int i = 0 ; i < seancesBySalle.size() ; i++) //Pour chaque séance de cette semaine de ce prof :
        {
            //System.out.println("Occupée ou annulée (getEtat()) : "+ seancesBySalle.get(i).getEtat()); 
            System.out.println("Salle occopé par la seance d'id : "+ seancesBySalle.get(i).getId());
            System.out.println("Pour des exemples d'extraction d'autres données, se référer à la partie mesSeancesOnWeek");
        }

        UtilisateurDAO uDAO= new UtilisateurDAO();
        System.out.println("\n*********NBTotal*********\nLe nombre total de gens dans cette école : "+uDAO.nombreMax()+ " ouai c'pas bcp :/");
        
        /*Fenetre fenetre = new Fenetre();
            
        try {
            ConnexionBDD c = new ConnexionBDD("edt","root","");
            System.out.println("BDD identifiée");
        }
        catch(SQLException sql){
           System.out.println("exception");
        }
        catch(ClassNotFoundException tm) {
           System.out.println("class exception");
        }*/
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
