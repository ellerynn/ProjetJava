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
    /***Données Service Planif****/
    public void remplirComboTypes(ArrayList<String> string) {
        ongletGererCours.remplirComboBoxType(ongletGererCours.getSelectType(),"type", string);
        ongletGererCours.remplirComboBoxType(ongletGererCours.getSelectType2(),"type", string);
    }
    public void remplirComboCours(ArrayList<String> string) {
        ongletGererCours.remplirComboBoxType(ongletGererCours.getSelectCours(),"cours", string);
        ongletGererCours.remplirComboBoxType(ongletGererCours.getSelectCours2(),"cours", string);

    }
    public void remplirListSalle(ArrayList<String> string) {
        ongletGererCours.remplirListe(ongletGererCours.getListeSalles(), string);
        ongletGererCours.remplirListe(ongletGererCours.getListeSalles2(), string);
    }
    public void remplirListGroupes(ArrayList<String> string) {
        ongletGererCours.remplirListe(ongletGererCours.getListeGroupes(), string);
        ongletGererCours.remplirListe(ongletGererCours.getListeGroupes2(), string);
    }
    public void remplirListEnseignants(ArrayList<String> string) {
        ongletGererCours.remplirListe(ongletGererCours.getListeEnseignants(), string);
        ongletGererCours.remplirListe(ongletGererCours.getListeEnseignants2(), string);
    }
    /***Fin donnée SP*****/
}
