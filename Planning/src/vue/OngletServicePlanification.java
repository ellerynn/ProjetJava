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
    public JButton getBtnValider(){
        return ongletGererCours.getBtnValider();
    }
    public JButton getBtnValider2(){
        return ongletGererCours.getBtnValider2();
    }
    public JButton getBtnValider3(){
        return ongletGererCours.getBtnValider3();
    }
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
    public void remplirListSeances(ArrayList<String> string) {
        ongletGererCours.remplirListe(ongletGererCours.getListeSeances(), string);
        ongletGererCours.remplirListe(ongletGererCours.getListeSeances2(), string);
    }
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
    /***Fin donnée SP*****/
}
