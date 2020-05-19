package vue;

import java.awt.event.*;
import javax.swing.*;

//La classe EmploiDuTemps correspond à l'application à proprement parlé
//Après connexion de l'utilisateur, on ouvre cette interface
public class EmploiDuTemps extends JTabbedPane {
    /*Constructeur*/
    public EmploiDuTemps() {
        OngletHome ongletHome = new OngletHome(); //Onglet Home
        //On écoute le bouton du panneau Home, celui en haut a droite du sous panneau Emploi du temps
        ongletHome.getBouton().addActionListener((ActionEvent event) -> {
             this.setSelectedIndex(1); //Crée un lien entre l'emploi du temps du jours avec l'onglet Cours
        });
        
        this.add("Home", ongletHome); //Ajout de l'onglet Home a mon JTabbedPane principal (this)
        
        OngletCours ongletCours = new OngletCours(); //Onglet Cours
        this.add("Cours", ongletCours);
        
        OngletSalles ongletSalles = new OngletSalles(); //Onglet Salles
        this.add("Salles", ongletSalles);
        
        //A BLINDER ! Ne doit apparaitre que quand droit utilisateur = 1
        OngletServicePlanification ongletSP = new OngletServicePlanification(); //Onglet Service planification
        this.add("Service planification", ongletSP);
    }
}
