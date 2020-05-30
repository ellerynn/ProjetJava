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
import org.jfree.chart.ChartPanel;

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
     * @return le JSpinner contenant la date du jour dans l'onglet Home
     */
    public JSpinner getDateHome() {
        return ongletHome.getDateHome(); //Spinner date
    }
    
    /**
     * @return le JTable contenant l'edt sur une journée 
     */
    public JTable getEdtHome() {
        return ongletHome.getTabCoursHome(); //Emploi du temps 1 jour dans Home
    }
    
    /**
     * @return la barre de recherche dans l'onglet Cours
     */
    public JTextField getRechercheBarreCours() {
        return ongletCours.getRechercheBarre(); //Barre de recherche
    }
    
    /**
     * @return le bouton rechercher 
     */
    public JButton getRechercheBoutonCours() {
        return ongletCours.getRechercheBouton(); //Bouton de recherche
    }
    
    /**
     * @return la JComboBox de recherche de l'edt avec les utilisateurs de la BDD
     */
    public JComboBox getRechercheCours() {
        return ongletCours.getRecherche(); //Menu déroulant des utilisateurs
    }
    
    /**
     * @return bis mais cette fois avec les groupes et promo
     */
    public JComboBox getGroupesCours() {
        return ongletCours.getGroupes(); //Menu déroulant des groupes et promos
    }
    
    /**
     * @return la JComboBox contenant les semaines
     */
    public JComboBox getSemaineCours() {
        return ongletCours.getSemaine(); //Menu déroulant des semaines dans Cours
    }
    
    /**
     * @return le JTable contenant l'edt sur une semaine
     */
    public JTable getEdtCours() {
        return ongletCours.getEdt(); //Emploi du temps
    }
    
    /**
     * @return le JTable contenant le récapitulatif des cours
     */
    public JTable getRecapCours() {
        return ongletCours.getRecap(); //Récapitulatif des cours
    }
    
    /**
     * @return la JComboBox contenant les semaines dans l'onglet Salles
     */
    public JComboBox getSemaineSalles() {
        return ongletSalles.getSemaine(); //Menu déroulant des semaines dans Salles
    }
    
    /**
     * @return le bouton de déconnexion
     */
    public JButton getBoutonDeco() { //Pour passer au site lorsqu'on appuie sur le bouton connexion
        return ongletHome.getBoutonDeco();
    }
    
    /**
     * @return la liste de séances de la BDD de l'onglet SP
     */
    public JList getListeSeances() {//Jsp
        return ongletSP.getListeSeances();
    }

    /**
     * @return le premier bouton valider de l'onglet Gérer les cours
     */
    public JButton getBtnValider(){
        return ongletSP.getBtnValider();
    }

    /**
     * @return le second bouton valider de l'onglet Gérer les cours
     */
    public JButton getBtnValider2(){
        return ongletSP.getBtnValider2();
    }

    /**
     * @return le dernier bouton valider de l'onglet Gérer les cours
     */
    public JButton getBtnValider3(){
        return ongletSP.getBtnValider3();
    }
    
    /**
     * @return onglet SP
     */
    public JTabbedPane getOngletSP() {
        return ongletSP;
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
    /**
     * Retourne les informations utiles saisies par l'user dans l'onglet OngletServicePlanification pour ajouter une séance
     * @return 
     */
    public ArrayList<Object> getInfosAddSeance() {
        return ongletSP.getInfosAddSeance();
    }
    /**
     * Récupère et envoie les données d'une séance pour être séléctionnés dans OngletServicePlanification pour l'onglet OngletGererCoursSP
     * @param forBeingSelectedByDefault 
     */
    public void dataToBeSelectedByDefault(ArrayList<Object> forBeingSelectedByDefault) {
        ongletSP.dataToBeSelectedByDefault(forBeingSelectedByDefault);
    }
    /**
     * Retourne les données modifiés par l'user pour une séance donnée issues de OngletServicePlanification
     * @return 
     */
    public ArrayList<Object> getInfosModifSeance() {
        return ongletSP.getInfosModifSeance();
    }

    /**
     * ajout des graphes dans Home
     * @param c
     */
    public void ajouterGraphes(ArrayList<ChartPanel> c, ArrayList<ChartPanel> t) {
        ongletHome.ajouterGraphes(c, t);
    }
}
