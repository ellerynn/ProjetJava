package vue;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

//La classe EmploiDuTemps correspond à l'application à proprement parlé
//Après connexion de l'utilisateur, on ouvre cette interface
public class EmploiDuTemps extends JTabbedPane {
    private OngletHome ongletHome;
    private OngletCours ongletCours;
    private OngletSalles ongletSalles;
    private OngletServicePlanification ongletSP; //Ajouté en dehors du constructeur lorsque droit utilisateur = 1 (admin)
    
    /*Constructeur*/
    public EmploiDuTemps() {
        ongletHome = new OngletHome(); //Onglet Home
        //On écoute le bouton du panneau Home, celui en haut a droite du sous panneau Emploi du temps
        ongletHome.getBouton().addActionListener((ActionEvent event) -> {
             this.setSelectedIndex(1); //Crée un lien entre l'emploi du temps du jours avec l'onglet Cours
        });
        
        this.add("Home", ongletHome); //Ajout de l'onglet Home a mon JTabbedPane principal (this)
        
        ongletCours = new OngletCours(); //Onglet Cours
        this.add("Cours", ongletCours);
        
        ongletSalles = new OngletSalles(); //Onglet Salles
        this.add("Salles", ongletSalles);
    }
    
    //Getters 
    public JSpinner getDateHome() {
        return ongletHome.getDateHome(); //Spinner date
    }
    
    public JTable getEdtHome() {
        return ongletHome.getTabCoursHome(); //Emploi du temps 1 jour dans Home
    }
    
    public JTextField getRechercheBarreCours() {
        return ongletCours.getRechercheBarre(); //Barre de recherche
    }
    
    public JButton getRechercheBoutonCours() {
        return ongletCours.getRechercheBouton(); //Bouton de recherche
    }
    
    public JComboBox getRechercheCours() {
        return ongletCours.getRecherche(); //Menu déroulant des utilisateurs
    }
    
    public JComboBox getGroupesCours() {
        return ongletCours.getGroupes(); //Menu déroulant des groupes et promos
    }
    
    public JComboBox getSemaineCours() {
        return ongletCours.getSemaine(); //Menu déroulant des semaines dans Cours
    }
    
    public JTable getEdtCours() {
        return ongletCours.getEdt(); //Emploi du temps
    }
    
    public JTable getRecapCours() {
        return ongletCours.getRecap(); //Récapitulatif des cours
    }
    
    public JComboBox getSemaineSalles() {
        return ongletSalles.getSemaine(); //Menu déroulant des semaines dans Salles
    }
    
    //Setters
    public void addOngletServicePlanification() {
        ongletSP = new OngletServicePlanification(); //Onglet Service planification
        this.add("Service planification", ongletSP);
    }
    
    public void setEdtHome() {
        ongletHome.setEdt();
    }
    
    public void setEdtCours(int semaine) {
        ongletCours.setEdt(semaine);
    }
    
    public void setRecapCours(int semaine) {
        ongletCours.setRecap();
    }
    
    public void setEdtSalles(int semaine) {
        ongletSalles.setEdt(semaine);
    }
    
    public void setRechercheCours(ArrayList<String> string) {
        ongletCours.remplirComboBox(ongletCours.getRecherche(), "Veuillez sélectionner", string);
    }
    
    public void setGroupesCours(ArrayList<String> string) {
        ongletCours.remplirComboBox(ongletCours.getGroupes(), "Groupes", string);
    }
}
