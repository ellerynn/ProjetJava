package controleur;

import controleur.ConnexionBDD;
import vue.*;
import java.sql.*;

import modele.*;
import dao.*;
import java.util.*;

public class Planning {
    public static void main(String[] args) {
            Connection con;
            String nameDatabase = "edt";
            String loginDatabase = "root";
            String passwordDatabase = "";
        try {
            
            //chargement du driver
            Class.forName("com.mysql.jdbc.Driver");
            //url de connexion
            String urlDatabase = "jdbc:mysql://localhost:3306/" + nameDatabase;
            //création connexion JDBC a la base
            con = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);
            
            /*
            *TYPECOURSDAO recuperation du nom
            */
            DAO<TypeCours> typecoursDAO = new TypeCoursDAO(con);
            TypeCours lol = typecoursDAO.find(5);
            System.out.println("le type de cours : "+ lol.getNom());
            /*
            *PromotionDAO recuperation du nom
            */
            DAO<Promotion> promotionDAO = new PromotionDAO(con);
            Promotion pro = promotionDAO.find(2);
            System.out.println("la promo : "+ pro.getNom());
            /*
            *SITEDAO recuperation du nom
            */
            DAO<Site> siteDAO = new SiteDAO(con);
            Site sit = siteDAO.find(2);
            System.out.println("le site : "+ sit.getNom());
            /*
            *COURSDAO recuperation du nom
            */
            DAO<Cours> coursDAO = new CoursDAO(con);
            Cours cou = coursDAO.find(2);
            System.out.println("le cours : "+ cou.getNom());
            /*
            *UTILISATEURDAO recuperation de toutes les données
            */
            //DAO<Utilisateur> utilisateurDAO = new UtilisateurDAO(con);
            //Utilisateur uti = utilisateurDAO.find(5);
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO(con);
            Utilisateur uti = utilisateurDAO.find("etudiant3@edu.ece.fr","etudiant");
            
            System.out.println("l'id : "+ uti.getId());
            System.out.println("le mail : "+ uti.getEmail());
            System.out.println("le password : "+ uti.getPassword());
            System.out.println("le nom : "+ uti.getNom());
            System.out.println("le prenom : "+ uti.getPrenom());
            System.out.println("le droit : "+ uti.getDroit());
            /*
            *GROUPEDAO recuperation de toutes les données
            */
            DAO<Groupe> groupeDAO = new GroupeDAO(con);
            Groupe gro = groupeDAO.find(4);
            System.out.println("l'id du groupe : "+ gro.getId());
            System.out.println("le nom du groupe : "+ gro.getNom());
            Promotion promo = gro.getPromotion();
            System.out.println("l'id de la promotion du groupe : "+ promo.getId());
            System.out.println("le nom de la promotion du groupe : "+ promo.getNom());
            /*
            *SALLEDAO recuperation de toutes les données
            */
            DAO<Salle> salleDAO = new SalleDAO(con);
            Salle sal = salleDAO.find(4);
            System.out.println("l'id de la salle : "+ sal.getId());
            System.out.println("le nom de la salle : "+ sal.getNom());
            Site site = sal.getSite();
            System.out.println("la capacite de la salle : "+ sal.getCapacite());
            System.out.println("le nom de la promotion du groupe : "+ site.getNom());

        }

        catch(SQLException sql){
           System.out.println("exception");
        }
        catch(ClassNotFoundException tm) {
           System.out.println("class exception");
        }
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
}
