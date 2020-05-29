package vue;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class EmploiDuTemps extends JTabbedPane {
    private OngletHome ongletHome;
    private OngletCours ongletCours;
    private OngletSalles ongletSalles;
    private OngletServicePlanification ongletSP; //Ajouté en dehors du constructeur lorsque droit utilisateur = 1 (admin)
    
    /**
     * constructeur
     */
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
    
    /**
     * retourne le JSpinner contenant la date du jour dans l'onglet Home
     * @return
     */
    public JSpinner getDateHome() {
        return ongletHome.getDateHome(); //Spinner date
    }
    
    /**
     * retourne le JTable contenant l'edt sur une journée 
     * @return
     */
    public JTable getEdtHome() {
        return ongletHome.getTabCoursHome(); //Emploi du temps 1 jour dans Home
    }
    
    /**
     * retourne la barre de recherche dans l'onglet Cours
     * @return
     */
    public JTextField getRechercheBarreCours() {
        return ongletCours.getRechercheBarre(); //Barre de recherche
    }
    
    /**
     * retourne le bouton rechercher 
     * @return
     */
    public JButton getRechercheBoutonCours() {
        return ongletCours.getRechercheBouton(); //Bouton de recherche
    }
    
    /**
     * retourne la JComboBox de recherche de l'edt avec les utilisateurs de la BDD
     * @return
     */
    public JComboBox getRechercheCours() {
        return ongletCours.getRecherche(); //Menu déroulant des utilisateurs
    }
    
    /**
     * bis mais cette fois avec les groupes et promo
     * @return
     */
    public JComboBox getGroupesCours() {
        return ongletCours.getGroupes(); //Menu déroulant des groupes et promos
    }
    
    /**
     * retourne la JComboBox contenant les semaines
     * @return
     */
    public JComboBox getSemaineCours() {
        return ongletCours.getSemaine(); //Menu déroulant des semaines dans Cours
    }
    
    /**
     * retourne le JTable contenant l'edt sur une semaine
     * @return
     */
    public JTable getEdtCours() {
        return ongletCours.getEdt(); //Emploi du temps
    }
    
    /**
     * retourne le JTable contenant le récapitulatif des cours
     * @return
     */
    public JTable getRecapCours() {
        return ongletCours.getRecap(); //Récapitulatif des cours
    }
    
    /**
     * retourne la JComboBox contenant les semaines dans l'onglet Salles
     * @return
     */
    public JComboBox getSemaineSalles() {
        return ongletSalles.getSemaine(); //Menu déroulant des semaines dans Salles
    }
    
    /**
     * retourne le bouton de déconnexion
     * @return
     */
    public JButton getBoutonDeco() { //Pour passer au site lorsqu'on appuie sur le bouton connexion
        return ongletHome.getBoutonDeco();
    }
    
    /**
     * retourne la liste de séances de la BDD de l'onglet SP
     * @return
     */
    public JList getListeSeances() {//Jsp
        return ongletSP.getListeSeances();
    }

    /**
     * retourne le premier bouton valider de l'onglet Gérer les cours
     * @return
     */
    public JButton getBtnValider(){
        return ongletSP.getBtnValider();
    }

    /**
     * retourne le second bouton valider de l'onglet Gérer les cours
     * @return
     */
    public JButton getBtnValider2(){
        return ongletSP.getBtnValider2();
    }

    /**
     * retourne le dernier bouton valider de l'onglet Gérer les cours
     * @return
     */
    public JButton getBtnValider3(){
        return ongletSP.getBtnValider3();
    }
    
    /**
     * ajoute l'onglet Service planification si besoin
     */
    public void addOngletServicePlanification() {
        ongletSP = new OngletServicePlanification(); //Onglet Service planification
        this.add("Service planification", ongletSP);
    }
    
    /**
     * MAJ de l'edt sur un jour dans l'onglet Home
     */
    public void setEdtHome() {
        ongletHome.setEdt();
    }
    
    /**
     * MAJ de l'edt sur une semaine dans l'onglet Cours
     * @param semaine
     */
    public void setEdtCours(int semaine) {
        ongletCours.setEdt(semaine);
    }
    
    /**
     * MAJ du récap de séances dans l'onglet Cours
     * @param semaine
     */
    public void setRecapCours(int semaine) {
        ongletCours.setRecap();
    }
    
    /**
     * MAJ de l'edt sur une semaine dans l'onglet Salles
     * @param semaine
     */
    public void setEdtSalles(int semaine) {
        ongletSalles.setEdt(semaine);
    }
    
    /**
     * rempli la JcomboBox de recherche utilisateurs dans l'onglet Cours
     * @param string
     */
    public void setRechercheCours(ArrayList<String> string) {
        ongletCours.remplirComboBox(ongletCours.getRecherche(), "Veuillez sélectionner", string);
    }
    
    /**
     * rempli la JcomboBox de recherche groupes et promo dans l'onglet Cours
     * @param string
     */
    public void setGroupesCours(ArrayList<String> string) {
        ongletCours.remplirComboBox(ongletCours.getGroupes(), "Groupes", string);
    }
    
    /**
     * rempli la JComboBox de l'onglet SP avec les types de cours de la BDD
     * @param string
     */
    public void setTypes(ArrayList<String> string) {
        ongletSP.remplirComboTypes(string);
    }

    /**
     * rempli la JComboBox de l'onglet SP avec les cours de la BDD
     * @param string
     */
    public void setCours(ArrayList<String> string) {
        ongletSP.remplirComboCours(string);
    }

    /**
     * rempli la JList de l'onglet SP avec les salles de la BDD
     * @param string
     */
    public void setSalles(ArrayList<String> string){
        ongletSP.remplirListSalle(string);
    }

    /**
     * rempli la JList de l'onglet SP avec les groupes de la BDD
     * @param string
     */
    public void setGroupes(ArrayList<String> string){ //Je ne sais pas s'il y a myn de fusioner avec setGroupesCours
        ongletSP.remplirListGroupes(string);
    }

    /**
     * rempli la JList de l'onglet SP avec les enseignants de la BDD
     * @param string
     */
    public void setEnseignants(ArrayList<String> string){
        ongletSP.remplirListEnseignants(string);
    }

    /**
     * rempli la JList de l'onglet SP avec les séances de la BDD
     * @param string
     */
    public void setSeances(ArrayList<String> string){
        ongletSP.remplirListSeances(string);
    }
}
