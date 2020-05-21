package vue;

import controleur.Controle;
import java.awt.event.*;
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
    public JSplitPane getOngletHome() {
        return ongletHome;
    }
    
    public JTabbedPane getOngletCours() {
        return ongletCours;
    }
    
    public JTextField getRechercheBarreCours() {
        return ongletCours.getRechercheBarreCours();
    }
    
    public JButton getRechercheBoutonCours() {
        return ongletCours.getRechercheBoutonCours();
    }
    
    public JComboBox getRechercheCours() {
        return ongletCours.getRechercheCours();
    }
    
    public JComboBox getSemaineCours() {
        return ongletCours.getSemaineCours();
    }
    
    public JTable getTabCours() {
        return ongletCours.getTabCours();
    }
    
    public JTabbedPane getOngletSalles() {
        return ongletSalles;
    }
    
    public JTextField getRechercheBarreSalle() {
        return ongletSalles.getRechercheBarreSalle();
    }
    
    public JButton getRechercheBoutonSalle() {
        return ongletSalles.getRechercheBoutonSalle();
    }
    
    public JComboBox getRechercheSalle() {
        return ongletSalles.getRechercheSalle();
    }
    
    public JTable getTabSalles() {
        return ongletSalles.getTabSalles();
    }
    
    public JComboBox getSemaineSalles() {
        return ongletSalles.getSemaineSalle();
    }
    
    public JTabbedPane getOngletServicePlanification() {
        return ongletSP;
    }
    
    //Setters
    public void addOngletServicePlanification() {
        ongletSP = new OngletServicePlanification(); //Onglet Service planification
        this.add("Service planification", ongletSP);
    }
    
    public void setTabCours(int semaine) {
        ongletCours.setTableauEdt(semaine);
    }
    
    public void setTabSalles(int semaine) {
        ongletSalles.setTableauEdt(semaine);
    }
}
