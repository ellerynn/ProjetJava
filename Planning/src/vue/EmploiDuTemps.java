package vue;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
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
     * retourne le JSpinner contenant la date du jour dans l'onglet Home
     * @return le JSpinner contenant la date du jour dans l'onglet Home
     */
    public JSpinner getDateHome() {
        return ongletHome.getDateHome(); //Spinner date
    }
    
    /**
     * retourne le JTable contenant l'edt sur une journée issu de l'ongletHome
     * @return le JTable contenant l'edt sur une journée issu de l'ongletHome
     */
    public JTable getEdtHome() {
        return ongletHome.getTabCoursHome(); //Emploi du temps 1 jour dans Home
    }
    
    /**
     * retourne le type de vue souhaité : en grille ou en liste issu de l'ongletCours
     * @return le type de vue souhaité : en grille ou en liste issu de l'ongletCours
     */
    public JComboBox getVueCours() {
        return this.ongletCours.getVue();
    }
    
    /**
     * retourne le type de vue souhaité : en grille ou en liste issu de l'ongletSalles
     * @return le type de vue souhaité : en grille ou en liste issu de l'ongletSalles
     */
    public JComboBox getVueSalles() {
        return this.ongletSalles.getVue();
    }
    
    /**
     * retourne le conteneur de l'edt en grille issu de l'ongletCours
     * @return le conteneur de l'edt en grille issu de l'ongletCours
     */
    public TableLabelRendererPanel getGrilleCours() {
        return this.ongletCours.getGrille();
    }
    
    /**
     * retourne le conteneur de l'edt des salles en grille issu de l'ongletSalles
     * @return le conteneur de l'edt des salles en grille issu de l'ongletSalles
     */
    public TableLabelRendererPanel getGrilleSalles() {
        return this.ongletSalles.getGrille();
    }
    
    /**
     * retourne le conteneur de l'edt home en grille issu de l'ongletHome
     * @return le conteneur de l'edt home en grille issu de l'ongletHome
     */
    public TableLabelRendererPanel getGrilleHome() {
        return this.ongletHome.getGrille();
    }
    
    /**
     * retourne le conteneur de l'edt en liste issu de l'ongletCours
     * @return le conteneur de l'edt en liste issu de l'ongletCours
     */
    public TableLabelRendererPanel getListeCours() {
        return this.ongletCours.getListe();
    }
    
    /**
     * retourne le conteneur de l'edt en liste issu de l'ongletSalles
     * @return le conteneur de l'edt en liste issu de l'ongletSalles
     */
    public TableLabelRendererPanel getListeSalles() {
        return this.ongletSalles.getListe();
    }
    
    /**
     * retourne la barre de recherche issu de l'onglet Cours
     * @return la barre de recherche issu de l'onglet Cours
     */
    public JTextField getRechercheBarreCours() {
        return ongletCours.getRechercheBarre(); //Barre de recherche
    }
    
    /**
     * retourne le bouton rechercher issu de l'ongletCours
     * @return le bouton rechercher  issu de l'ongletCours
     */
    public JButton getRechercheBoutonCours() {
        return ongletCours.getRechercheBouton(); //Bouton de recherche
    }
    
    /**
     * retourne la barre de recherche issu de l'onglet Salles
     * @return la barre de recherche issu de l'onglet Salles
     */
    public JTextField getRechercheBarreSalles() {
        return ongletSalles.getRechercheBarre(); //Barre de recherche
    }
    
    /**
     * retourne le bouton rechercher issu de l'ongletSalles
     * @return le bouton rechercher issu de l'ongletSalles
     */
    public JButton getRechercheBoutonSalles() {
        return ongletSalles.getRechercheBouton(); //Bouton de recherche
    }
    
    /**
     * retourne la JComboBox de recherche de l'edt avec les utilisateurs de la BDD issu de l'ongletCours
     * @return la JComboBox de recherche de l'edt avec les utilisateurs de la BDD issu de l'ongletCours
     */
    public JComboBox getRechercheCours() {
        return ongletCours.getRecherche(); //Menu déroulant des utilisateurs
    }
    
    /**
     * retourne la barre de recherche issu de l'onglet Cours
     * @return la barre de recherche issu de l'onglet Cours
     */
    public JTextField getRechercheBarreRecapCours() {
        return ongletCours.getRechercheBarre2(); //Barre de recherche
    }
    
    /**
     * retourne le bouton rechercher issu de l'ongletCours
     * @return le bouton rechercher issu de l'ongletCours
     */
    public JButton getRechercheBoutonRecapCours() {
        return ongletCours.getRechercheBouton2(); //Bouton de recherche
    }
    
    /**
     * retourne la JComboBox de recherche de l'edt avec les utilisateurs de la BDD issu de l'ongletCours
     * @return la JComboBox de recherche de l'edt avec les utilisateurs de la BDD issu de l'ongletCours
     */
    public JComboBox getRechercheRecapCours() {
        return ongletCours.getRecherche2(); //Menu déroulant des utilisateurs
    }
    
    /**
     * retourne la barre de recherche issu de l'onglet Cours
     * @return la barre de recherche issu de l'onglet Cours
     */
    public JTextField getRechercheBarreLibres() {
        return ongletSalles.getRechercheBarre2(); //Barre de recherche
    }
    
    /**
     * retourne le bouton rechercher issu de l'ongletSalles
     * @return le bouton rechercher issu de l'ongletSalles
     */
    public JButton getRechercheBoutonLibres() {
        return ongletSalles.getRechercheBouton2(); //Bouton de recherche
    }
    
    /**
     * retourne la JComboBox de recherche de l'edt avec les utilisateurs de la BDD issu de l'ongletSalles
     * @return la JComboBox de recherche de l'edt avec les utilisateurs de la BDD issu de l'ongletSalles
     */
    public JComboBox getRechercheLibres() {
        return ongletSalles.getRecherche2(); //Menu déroulant des utilisateurs
    }
    
    /**
     * retourne la JComboBox de recherche de l'edt avec les utilisateurs de la BDD issu de l'ongletSalles
     * @return la JComboBox de recherche de l'edt avec les utilisateurs de la BDD issu de l'ongletSalles
     */
    public JComboBox getRechercheSalles() {
        return ongletSalles.getRecherche(); //Menu déroulant des utilisateurs
    }
    
    /**
     * retourne Menu déroulant des groupes et promos de l'ongletCours issu de l'ongletCours
     * @return bis mais cette fois avec les groupes et promo issu de l'oongletCours
     */
    public JComboBox getGroupesCours() {
        return ongletCours.getGroupes(); //Menu déroulant des groupes et promos
    }
    
    /**
     * retourne la JComboBox contenant les semaines issu de l'ongletCours
     * @return la JComboBox contenant les semaines issu de l'ongletCours
     */
    public JComboBox getSemaineCours() {
        return ongletCours.getSemaine(); //Menu déroulant des semaines dans Cours
    }
    
    /**
     * retourne la JComboBox promo issu de l'ongletCours
     * @return la JComboBox promo issu de l'ongletCours
     */
    public JComboBox getRecherchePromo() {
        return ongletCours.getRecherchePromo();
    }
    
    /**
     * retourne le JTable contenant l'edt sur une semaine issu de l'ongletCours
     * @return le JTable contenant l'edt sur une semaine issu de l'ongletCours
     */
    public JTable getEdtCours() {
        return ongletCours.getEdt(); //Emploi du temps
    }
    
    /**
     * retourne le tableau contenant l'emploi du temps sur une semaine issu de l'onglet Salles
     * @return le tableau contenant l'emploi du temps sur une semaine issu de l'onglet Salles
     */
    public JTable getEdtSalles() {
        return ongletSalles.getEdt(); 
    }
    
    /**
     * retourne le JTable contenant l'edt sur une semaine issu de l'ongletCours
     * @return le JTable contenant l'edt sur une semaine issu de l'ongletCours
     */
    public JTable getJTListeCours() {
        return ongletCours.getJTListe(); //Emploi du temps
    }
        
    /**
     * retourne le JTable contenant l'edt sur une semaine issu de l'ongletSalles
     * @return le JTable contenant l'edt sur une semaine issu de l'ongletSalles
     */
    public JTable getJTListeSalles() {
        return ongletSalles.getJTListe(); //Emploi du temps
    }
     
    /**
     * retourne le JTable contenant le récapitulatif des cours issu de l'ongletSalles
     * @return le JTable contenant le récapitulatif des cours issu de l'ongletSalles
     */
    public JTable getLibres() {
        return ongletSalles.getLibres(); //Récapitulatif des cours
    }
    
    /**
     * retourne le JTable contenant le récapitulatif des cours issu de l'ongletCours
     * @return le JTable contenant le récapitulatif des cours issu de l'ongletCours
     */
    public JTable getRecapCours() {
        return ongletCours.getRecap(); //Récapitulatif des cours
    }
    
    /**
     * retourne la JComboBox contenant les semaines issu de l'onglet Salles
     * @return la JComboBox contenant les semaines issu de l'onglet Salles
     */
    public JComboBox getSemaineSalles() {
        return ongletSalles.getSemaine(); //Menu déroulant des semaines dans Salles
    }
    
    /**
     * retourne le bouton de déconnexion issu de l'ongletHome
     * @return le bouton de déconnexion issu de l'ongletHome
     */
    public JButton getBoutonDeco() { //Pour passer au site lorsqu'on appuie sur le bouton connexion
        return ongletHome.getBoutonDeco();
    }
    
    /**
     * retourne la liste de séances de la BDD issu de l'onglet SP
     * @return la liste de séances de la BDD issu de l'onglet SP
     */
    public JList getListeSeances() {
        return ongletSP.getListeSeances();
    }

    /**
     * retourne le premier bouton valider issu de l'ongletSP
     * @return le premier bouton valider issu de l'ongletSP
     */
    public JButton getBtnValider(){
        return ongletSP.getBtnValider();
    }

    /**
     * retourne le second bouton valider issu de l'ongletSP
     * @return le second bouton valider issu de l'ongletSP
     */
    public JButton getBtnValider2(){
        return ongletSP.getBtnValider2();
    }

    /**
     * retourne le dernier bouton valider issu de l'ongletSP
     * @return le dernier bouton valider issu de l'ongletSP
     */
    public JButton getBtnValider3(){
        return ongletSP.getBtnValider3();
    }
    
    /**
     * retourne l'onglet SP
     * @return onglet SP
     */
    public JSplitPane getOngletSP() {
        return ongletSP;
    }
    
    /**
     * retourne le JLabel contenant la periode issu de l'ongletCours
     * @return le JLabel contenant la periode issu de l'ongletCours
     */
    public JLabel getPeriode() {
        return ongletCours.getPeriode();
    }
    
    /**
     * retourne le JLabel contenant la periode issu de l'ongletSalles
     * @return le JLabel contenant la periode issu de l'ongletSalles
     */
    public JLabel getPeriode2() {
        return ongletSalles.getPeriode();
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
     * @param semaine la semaine en question
     */
    public void setEdtCours(int semaine) {
        ongletCours.setEdt(semaine);
    }
    
    /**
     * MAJ de l'edt en liste sur une semaine dans l'onglet Cours
     * @param semaine la semaine en question
     */
    public void setListeCours(int semaine) {
        ongletCours.setListeEdt(semaine);
    }
    
    /**
     * MAJ de l'edt en liste sur une semaine dans l'onglet Cours
     * @param semaine la semaine en question
     */
    public void setListeSalles(int semaine) {
        ongletSalles.setListeEdt(semaine);
    }
    
    /**
     * MAJ du récap de séances dans l'onglet Cours
     * @param semaine la semaine en question
     */
    public void setRecapCours(int semaine) {
        ongletCours.setRecap();
    }
    
    /**
     * MAJ de l'edt sur une semaine dans l'onglet Salles
     * @param semaine la semaine en question
     */
    public void setEdtSalles(int semaine) {
        ongletSalles.setEdt(semaine);
    }
    
    /**
     * rempli la JcomboBox de recherche utilisateurs dans l'onglet Cours
     * @param string les infos à remplir dans la JComboBox de recherche utilisateurs dans l'onglet Cours 
     */
    public void setRechercheCours(ArrayList<String> string) {
        ongletCours.remplirComboBox(ongletCours.getRecherche(), "Veuillez sélectionner", string);
    }
    
    /**
     * rempli la JcomboBox de recherche utilisateurs dans l'onglet Cours
     * @param string les infos à remplir dans la JcomboBox de recherche utilisateurs dans l'onglet Cours
     */
    public void setPromosCours(ArrayList<String> string) {
        ongletCours.remplirComboBox(ongletCours.getRecherchePromo(), "Promotions", string);
    }
    
    /**
     * rempli la JcomboBox de recherche utilisateurs dans l'onglet Cours
     * @param string les infos à remplir dans la JcomboBox de recherche utilisateurs dans l'onglet Cours
     */
    public void setRechercheRecapCours(ArrayList<String> string) {
        ongletCours.remplirComboBox(ongletCours.getRecherche2(), "Veuillez sélectionner", string);
    }
    
    /**
     * rempli la JcomboBox de recherche salles dans l'onglet Salles
     * @param string les infos à remplir dans la JcomboBox de recherche salles dans l'onglet Salles
     */
    public void setRechercheSalles(ArrayList<String> string) {
        ongletSalles.remplirComboBox(ongletSalles.getRecherche(), "Veuillez sélectionner", string);
    }
    
    /**
     * rempli la JcomboBox de recherche salles dans l'onglet Salles
     * @param string les infos à remplir dans la JcomboBox de recherche salles dans l'onglet Salles
     */
    public void setRechercheLibres(ArrayList<String> string) {
        ongletSalles.remplirComboBox(ongletSalles.getRecherche2(), "Veuillez sélectionner", string);
    }
    
    /**
     * rempli la JcomboBox de recherche groupes et promo dans l'onglet Cours
     * @param string les infos à remplir dans la JcomboBox de recherche groupes et promo dans l'onglet Cours
     */
    public void setGroupesCours(ArrayList<String> string) {
        ongletCours.remplirComboBox(ongletCours.getGroupes(), "Groupes", string);
    }
    
    /**
     * rempli la JComboBox de l'onglet SP avec les types de cours de la BDD
     * @param string les infos à remplir dans la JComboBox de l'onglet SP avec les types de cours de la BDD
     */
    public void setTypes(ArrayList<String> string) {
        ongletSP.remplirComboTypes(string);
    }

    /**
     * rempli la JComboBox de l'onglet SP avec les cours de la BDD
     * @param string les infos à remplir dans la JComboBox de l'onglet SP avec les cours de la BDD
     */
    public void setCours(ArrayList<String> string) {
        ongletSP.remplirComboCours(string);
    }

    /**
     * rempli la JList de l'onglet SP avec les salles de la BDD
     * @param string les infos à remplir dans la JList de l'onglet SP avec les salles de la BDD
     */
    public void setSalles(ArrayList<String> string){
        ongletSP.remplirListSalle(string);
    }

    /**
     * rempli la JList de l'onglet SP avec les groupes de la BDD
     * @param string les infos à remplir dans la JList de l'onglet SP avec les groupes de la BDD
     */
    public void setGroupes(ArrayList<String> string){ //Je ne sais pas s'il y a myn de fusioner avec setGroupesCours
        ongletSP.remplirListGroupes(string);
    }

    /**
     * rempli la JList de l'onglet SP avec les enseignants de la BDD
     * @param string les infos à remplir dans la JList de l'onglet SP avec les enseignants de la BDD
     */
    public void setEnseignants(ArrayList<String> string){
        ongletSP.remplirListEnseignants(string);
    }

    /**
     * rempli la JList de l'onglet SP avec les séances de la BDD
     * @param string les infos à remplir dans la JList de l'onglet SP avec les séances de la BDD 
     */
    public void setSeances(ArrayList<String> string){
        ongletSP.remplirListSeances(string);
    }
    
    /**
     * Retourne les informations utiles saisies par l'user dans l'onglet OngletServicePlanification pour ajouter une séance
     * @return Retourne les informations utiles saisies par l'user dans l'onglet OngletServicePlanification pour ajouter une séance
     */
    public ArrayList<Object> getInfosAddSeance() {
        return ongletSP.getInfosAddSeance();
    }
    
    /**
     * Récupère et envoie les données d'une séance pour être séléctionnés dans OngletServicePlanification
     * @param forBeingSelectedByDefault les données d'une séance pour être séléctionnés dans OngletServicePlanification
     */
    public void dataToBeSelectedByDefault(ArrayList<Object> forBeingSelectedByDefault) {
        ongletSP.dataToBeSelectedByDefault(forBeingSelectedByDefault);
    }
    
    /**
     * Retourne les données modifiés par l'user pour une séance donnée issues de OngletServicePlanification
     * @return Retourne les données modifiés par l'user pour une séance donnée issues de OngletServicePlanification
     */
    public ArrayList<Object> getInfosModifSeance() {
        return ongletSP.getInfosModifSeance();
    }

    /**
     * ajout des graphes dans Home
     * @param c les chartPanel de la première ligne
     * @param t les chartPanel de la deuxième ligne
     */
    public void ajouterGraphes(ArrayList<ChartPanel> c, ArrayList<ChartPanel> t) {
        ongletHome.ajouterGraphes(c, t);
    }
    /**
     * Retourne ce que l'utilisateur à saisie pour l'intitulé d'un cours issu du service de planification
     * @return Retourne ce que l'utilisateur à saisie pour l'intitulé d'un cours issu du service de planification
     */
    public JTextField getIntitule()
    {
        return ongletSP.getIntitule();
    }
}
