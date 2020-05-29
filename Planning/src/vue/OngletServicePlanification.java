package vue;

import java.util.ArrayList;
import java.util.Calendar;
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
    
    /**
     *
     * @return
     */
    public ArrayList<Object> getInfosAddSeance()
    {
        ArrayList<Object> strings = new ArrayList<>();
        //Semaine
        String date = ongletGererCours.getDate().substring(9,19);
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(date.substring(0,4)), Integer.parseInt(date.substring(5,7))-1,Integer.parseInt(date.substring(8,10)));
        strings.add(String.valueOf(cal.get(Calendar.WEEK_OF_YEAR)));
        //Heure de debut
        strings.add(ongletGererCours.getDate().substring(0,8));             
        //Date
        strings.add(date);                                                    
        //Etat
        if(ongletGererCours.getEtatEC().isSelected())//Etat de la séance
        {
            strings.add("1"); //Si radio bouton EC cliqué, on stock "1" 
        }else if(ongletGererCours.getEtatV().isSelected()){
            strings.add("2"); //Si radio bouton V cliqué, on stock "2"
        }
        //Cours
        if(!ongletGererCours.getSelectCours().getSelectedItem().toString().equals("cours")){
            strings.add(ongletGererCours.getSelectCours().getSelectedItem().toString()); //Cours selectionné
        }
        //Type
        if(!ongletGererCours.getSelectType().getSelectedItem().toString().equals("type")){
            strings.add(ongletGererCours.getSelectType().getSelectedItem().toString()); //Type selectionné
        }
        //Enseignants
        if(!ongletGererCours.getListeEnseignants().getSelectedValuesList().isEmpty()) //Si liste enseignants non vide
            strings.add(ongletGererCours.getListeEnseignants().getSelectedValuesList()); //On add
        //Groupes
        if(!ongletGererCours.getListeGroupes().getSelectedValuesList().isEmpty()) //Si liste groupes non vide
            strings.add(ongletGererCours.getListeGroupes().getSelectedValuesList()); //On add
        //Salles
        if(!ongletGererCours.getListeSalles().getSelectedValuesList().isEmpty())//Si liste salles non vide
            strings.add(ongletGererCours.getListeSalles().getSelectedValuesList());//On add
        return strings;
    }
}
