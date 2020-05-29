package vue;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTabbedPane;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class OngletServicePlanification extends JTabbedPane {
    private OngletGererCoursSP ongletGererCours;
    private OngletGererUtilisateursSP ongletGererUtilisateurs;
    private OngletGererSiteSP ongletGererSite;
    
    /**
     * constructeur
     */
    public OngletServicePlanification() {
        ongletGererCours = new OngletGererCoursSP();
        this.add("Gérer les cours", ongletGererCours);
        
        ongletGererUtilisateurs = new OngletGererUtilisateursSP();
        this.add("Gérer les utilisateurs", ongletGererUtilisateurs);
        
        ongletGererSite = new OngletGererSiteSP();
        this.add("Gérer le site", ongletGererSite);
    }
    
    /**
     * récupère l'onglet Gérer les cours de l'onglet SP
     * @return
     */
    public OngletGererCoursSP getOngletGererCours() {
        return ongletGererCours;
    }
    
    /**
     * récupère la liste des séances de l'onglet Gérer les cours
     * @return
     */
    public JList getListeSeances(){ 
        return ongletGererCours.getListeSeances();
    }

    /**
     * récupère le premier bouton valider de l'onglet Gérer les cours
     * @return
     */
    public JButton getBtnValider(){
        return ongletGererCours.getBtnValider();
    }

    /**
     * récupère le second bouton valider de l'onglet Gérer les cours
     * @return
     */
    public JButton getBtnValider2(){
        return ongletGererCours.getBtnValider2();
    }

    /**
     * récupère le dernier bouton valider de l'onglet Gérer les cours
     * @return
     */
    public JButton getBtnValider3(){
        return ongletGererCours.getBtnValider3();
    }
    
    /**
     * récupère l'onglet Gérer les utilisateurs de l'onglet SP
     * @return
     */
    public OngletGererUtilisateursSP getOngletGererUtilisateurs() {
        return ongletGererUtilisateurs;
    }
    
    /**
     * récupère l'onglet Gérer les sites de l'onglet SP
     * @return
     */
    public OngletGererSiteSP getOngletGererSite() {
        return ongletGererSite;
    }

    /**
     * rempli les JComboBox de l'onglet Gérer les cours avec les types de cours
     * @param string
     */
    public void remplirComboTypes(ArrayList<String> string) {
        ongletGererCours.remplirComboBoxType(ongletGererCours.getSelectType(),"type", string);
        ongletGererCours.remplirComboBoxType(ongletGererCours.getSelectType2(),"type", string);
    }

    /**
     * rempli les JComboBox de l'onglet Gérer les cours avec les intitulés des cours
     * @param string
     */
    public void remplirComboCours(ArrayList<String> string) {
        ongletGererCours.remplirComboBoxType(ongletGererCours.getSelectCours(),"cours", string);
        ongletGererCours.remplirComboBoxType(ongletGererCours.getSelectCours2(),"cours", string);

    }

    /**
     * rempli les listes de l'onglet Gérer les cours avec les salles
     * @param string
     */
    public void remplirListSalle(ArrayList<String> string) {
        ongletGererCours.remplirListe(ongletGererCours.getListeSalles(), string);
        ongletGererCours.remplirListe(ongletGererCours.getListeSalles2(), string);
    }

    /**
     * rempli les listes de l'onglet Gérer les cours avec les groupes
     * @param string
     */
    public void remplirListGroupes(ArrayList<String> string) {
        ongletGererCours.remplirListe(ongletGererCours.getListeGroupes(), string);
        ongletGererCours.remplirListe(ongletGererCours.getListeGroupes2(), string);
    }

    /**
     * rempli les listes de l'onglet Gérer les cours avec les enseignants
     * @param string
     */
    public void remplirListEnseignants(ArrayList<String> string) {
        ongletGererCours.remplirListe(ongletGererCours.getListeEnseignants(), string);
        ongletGererCours.remplirListe(ongletGererCours.getListeEnseignants2(), string);
    }

    /**
     * rempli les liste de l'onglet Gérer les cours avec les séances
     * @param string
     */
    public void remplirListSeances(ArrayList<String> string) {
        ongletGererCours.remplirListe(ongletGererCours.getListeSeances(), string);
        ongletGererCours.remplirListe(ongletGererCours.getListeSeances2(), string);
    }
}
