package vue;

import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;

public class OngletServicePlanification extends JTabbedPane {
    private OngletGererCoursSP ongletGererCours;
    private OngletGererUtilisateursSP ongletGererUtilisateurs;
    private OngletGererSiteSP ongletGererSite;
    
    public OngletServicePlanification() {
        ongletGererCours = new OngletGererCoursSP();
        this.add("Gérer les cours", ongletGererCours);
        
        ongletGererUtilisateurs = new OngletGererUtilisateursSP();
        this.add("Gérer les utilisateurs", ongletGererUtilisateurs);
        
        ongletGererSite = new OngletGererSiteSP();
        this.add("Gérer le site", ongletGererSite);
    }
    
    public OngletGererCoursSP getOngletGererCours() {
        return ongletGererCours;
    }
    
    public OngletGererUtilisateursSP getOngletGererUtilisateurs() {
        return ongletGererUtilisateurs;
    }
    
    public OngletGererSiteSP getOngletGererSite() {
        return ongletGererSite;
    }
}
