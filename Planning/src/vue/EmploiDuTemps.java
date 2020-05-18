package vue;

import java.awt.event.*;
import javax.swing.*;

public class EmploiDuTemps extends JTabbedPane implements ActionListener {
    /*Constructeur*/
    public EmploiDuTemps() {
        //Onglet Home
        OngletHome ongletHome = new OngletHome();
        ongletHome.getBouton().addActionListener(this);
        this.add("Home", ongletHome);
        
        //Onglet Cours
        OngletCours ongletCours = new OngletCours();
        this.add("Cours", ongletCours);
        
        //Onglet Salles
        OngletSalles ongletSalles = new OngletSalles();
        this.add("Salles", ongletSalles);
        
        //Onglet Service planification
        OngletServicePlanification ongletSP = new OngletServicePlanification();
        this.add("Service planification", ongletSP);
    }
    
    //Pour le lien du bouton linkCoursHome
    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.setSelectedIndex(1);
    }  
}
