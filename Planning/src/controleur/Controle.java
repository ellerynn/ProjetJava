package controleur;

import vue.*;
import java.sql.*;

import modele.*;
import dao.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Controle {
    private Fenetre fenetre;
    
    //Constructeur
    public Controle() {
        fenetre = new Fenetre(this);
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
            *TYPECOURSDAO recuperation du nom
            */
            DAO<TypeCours> typecoursDAO = new TypeCoursDAO();
            TypeCours lol = typecoursDAO.find(5);
            System.out.println("le type de cours : "+ lol.getNom());
            System.out.println("***********************************1");
            /*
            *PromotionDAO recuperation du nom
            */
            DAO<Promotion> promotionDAO = new PromotionDAO();
            Promotion pro = promotionDAO.find(2);
            System.out.println("la promo : "+ pro.getNom());
            System.out.println("***********************************2");
            /*
            *SITEDAO recuperation du nom
            */
            DAO<Site> siteDAO = new SiteDAO();
            Site sit = siteDAO.find(2);
            System.out.println("le site : "+ sit.getNom());
            System.out.println("***********************************3");
            /*
            *COURSDAO recuperation du nom
            */
            DAO<Cours> coursDAO = new CoursDAO();
            Cours cou = coursDAO.find(2);
            System.out.println("le cours : "+ cou.getNom());
            System.out.println("***********************************4");
            /*
            *UTILISATEURDAO recuperation de toutes les données
            */
            //DAO<Utilisateur> utilisateurDAO = new UtilisateurDAO(con);
            //Utilisateur uti = utilisateurDAO.find(5);
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
            Utilisateur uti = utilisateurDAO.find("etudiant3@edu.ece.fr","etudiant");
            
            System.out.println("l'id : "+ uti.getId());
            System.out.println("le mail : "+ uti.getEmail());
            System.out.println("le password : "+ uti.getPassword());
            System.out.println("le nom : "+ uti.getNom());
            System.out.println("le prenom : "+ uti.getPrenom());
            System.out.println("le droit : "+ uti.getDroit());
            System.out.println("***********************************5");
            /*
            *GROUPEDAO recuperation de toutes les données
            */
            DAO<Groupe> groupeDAO = new GroupeDAO();
            Groupe gro = groupeDAO.find(4);
            System.out.println("l'id du groupe : "+ gro.getId());
            System.out.println("le nom du groupe : "+ gro.getNom());
            Promotion promo = gro.getPromotion();
            System.out.println("l'id de la promotion du groupe : "+ promo.getId());
            System.out.println("le nom de la promotion du groupe : "+ promo.getNom());
            System.out.println("***********************************6");
            /*
            *SALLEDAO recuperation de toutes les données
            */
            DAO<Salle> salleDAO = new SalleDAO();
            Salle sal = salleDAO.find(4);
            System.out.println("l'id de la salle : "+ sal.getId());
            System.out.println("le nom de la salle : "+ sal.getNom());
            Site site = sal.getSite();
            System.out.println("la capacite de la salle : "+ sal.getCapacite());
            System.out.println("le nom de la promotion du groupe : "+ site.getNom());
            System.out.println("***********************************7");
            /*
            *ENSEIGNANTDAO recuperation de toutes les données
            */
            
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
            /*
            for (int i = 0 ; i < ens.getSeances().size() ; i++){
                System.out.println("Id de la seance : " + ens.getSeances().get(i).getId()   );
            }*/
            
            System.out.println("***********************************8");
            /*
            *SEANCEDAO recuperation de toutes les données
            */
            
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
            /*
            *ETUDIANTDAO recuperation de toutes les données
            */
            DAO<Etudiant> etudiantDAO = new EtudiantDAO();
            Etudiant etu = etudiantDAO.find(4);
            System.out.println("l'id de l'etudiant : "+ etu.getId());
            System.out.println("le numero de l'etudiant : "+ etu.getNumero());
            System.out.println("l'ID du groupe de l'etudiant : "+ etu.getGroupe().getId());

            
            Groupe groupe = etu.getGroupe();
            System.out.println("l'id du groupe dans table groupe: "+ groupe.getId());
            System.out.println("***********************************10");

            /**************************UPDATE ***************/
            //Par exemple cours, en reprenant un cours déjà créer dans le main (et son dao aussi dj créer)
            cou.setNom("Maths");
            cou = coursDAO.update(cou); // Avant c't Analyse, mtn c'est Maths
            System.out.println("le cours : "+ cou.getNom());
            System.out.println("***********************************4UPDATE");
        
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
    
    public void demandeConnexion() {
        String email = new String();
        String password = new String();
        
        email = fenetre.getConnexion().getEmail();
        password = fenetre.getConnexion().getPassword();

        //UTILISATEURDAO recuperation de toutes les données
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        Utilisateur utilisateur = utilisateurDAO.find(email,password);
        
        //System.out.println("le mail : "+ email);
        //System.out.println("le password : "+ password);
        
        System.out.println("le mail : "+ utilisateur.getEmail());
        System.out.println("le password : "+ utilisateur.getPassword());
        
        //On a trouvé un utilisateur, on peut passer a la suite
    }
}
