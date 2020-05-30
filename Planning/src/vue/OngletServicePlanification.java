package vue;

import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.text.Position;

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
        ongletGererCours.remplirComboBox(ongletGererCours.getSelectType(),"type", string);
        ongletGererCours.remplirComboBox(ongletGererCours.getSelectType2(),"type", string);
    }

    /**
     * rempli les JComboBox de l'onglet Gérer les cours avec les intitulés des cours
     * @param string
     */
    public void remplirComboCours(ArrayList<String> string) {
        ongletGererCours.remplirComboBox(ongletGererCours.getSelectCours(),"cours", string);
        ongletGererCours.remplirComboBox(ongletGererCours.getSelectCours2(),"cours", string);

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
     * Retourne les informations utiles saisies par l'user depuis l'onglet OngletGererCoursSP pour ajouter une séance
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
        else
            strings.add(null);
        //Groupes
        if(!ongletGererCours.getListeGroupes().getSelectedValuesList().isEmpty()) //Si liste groupes non vide
            strings.add(ongletGererCours.getListeGroupes().getSelectedValuesList()); //On add
        else
            strings.add(null);
        //Salles
        if(!ongletGererCours.getListeSalles().getSelectedValuesList().isEmpty())//Si liste salles non vide
            strings.add(ongletGererCours.getListeSalles().getSelectedValuesList());//On add
        else
            strings.add(null);
        return strings;
    }
    /**
     * permet de selectionner le contenu des Jcombox, JradioButton, JList 
     * de l'onglet OngletGererCoursSP dans la partie Modifier une séance en fonction d'une séance séléctionnée
     * @param forBeingSelectedByDefault 
     */
    public void dataToBeSelectedByDefault(ArrayList<Object> forBeingSelectedByDefault)
    {
        if (!forBeingSelectedByDefault.isEmpty())
        {
            for(int i = 0 ; i < forBeingSelectedByDefault.size() ; i++)
            {
                if(i==0)//Date et heure
                    ongletGererCours.setDate2((String)forBeingSelectedByDefault.get(i));
                if(i==1) //Etat
                {
                    if ((Integer)forBeingSelectedByDefault.get(i) == 1)
                        ongletGererCours.getEtatEC2().setSelected(true);
                    if((Integer)forBeingSelectedByDefault.get(i) == 2)
                        ongletGererCours.getEtatV2().setSelected(true);
                    if((Integer)forBeingSelectedByDefault.get(i) == 3)
                        ongletGererCours.getEtatA().setSelected(true);
                }
                if(i==2) //Cours
                    ongletGererCours.getSelectCours2().setSelectedItem((String)forBeingSelectedByDefault.get(i));
                if(i==3) //Type
                    ongletGererCours.getSelectType2().setSelectedItem((String)forBeingSelectedByDefault.get(i));
                
                if(i==4) //Enseignants
                {
                    int[] index = new int[((ArrayList<String>)forBeingSelectedByDefault.get(i)).size()];
                    for (int a = 0 ; a < ((ArrayList<String>)forBeingSelectedByDefault.get(i)).size(); a++)
                        index[a] =ongletGererCours.getListeEnseignants2().getNextMatch(((ArrayList<String>)forBeingSelectedByDefault.get(i)).get(a), 0, Position.Bias.Forward);
                    ongletGererCours.getListeEnseignants2().setSelectedIndices(index);
                }
                
                if(i==5) //Groupes 
                {
                    int[] index = new int[((ArrayList<String>)forBeingSelectedByDefault.get(i)).size()];
                    for (int a = 0 ; a < ((ArrayList<String>)forBeingSelectedByDefault.get(i)).size(); a++)
                        index[a] =ongletGererCours.getListeGroupes2().getNextMatch(((ArrayList<String>)forBeingSelectedByDefault.get(i)).get(a), 0, Position.Bias.Forward);
                    ongletGererCours.getListeGroupes2().setSelectedIndices(index);
                }
                if(i==6) //Salles
                {
                    int[] index = new int[((ArrayList<String>)forBeingSelectedByDefault.get(i)).size()];
                    for (int a = 0 ; a < ((ArrayList<String>)forBeingSelectedByDefault.get(i)).size(); a++)
                        index[a] =ongletGererCours.getListeSalles2().getNextMatch(((ArrayList<String>)forBeingSelectedByDefault.get(i)).get(a), 0, Position.Bias.Forward);
                    ongletGererCours.getListeSalles2().setSelectedIndices(index);
                }
            }
                
                    
        }//END OF IF
    }
    /**
     * Retourne les infos modifiés par l'user sur une séance donnée issues de l'onglet OngletGererCoursSP
     * @return 
     */
    public ArrayList<Object> getInfosModifSeance()
    {
        ArrayList<Object> strings = new ArrayList<>();
        //Semaine
        String date = ongletGererCours.getDate2().substring(9,19);
        Calendar cal = Calendar.getInstance();
        cal.set(Integer.parseInt(date.substring(0,4)), Integer.parseInt(date.substring(5,7))-1,Integer.parseInt(date.substring(8,10)));
        strings.add(String.valueOf(cal.get(Calendar.WEEK_OF_YEAR)));
        //Heure de debut
        strings.add(ongletGererCours.getDate2().substring(0,8));       
        //Date
        strings.add(date);                                                    
        //Etat
        if(ongletGererCours.getEtatEC2().isSelected())//Etat de la séance
        {
            strings.add("1"); //Si radio bouton EC cliqué, on stock "1" 
        }else if(ongletGererCours.getEtatV2().isSelected()){
            strings.add("2"); //Si radio bouton V cliqué, on stock "2"
        }else if(ongletGererCours.getEtatA().isSelected()){
            strings.add("3"); //Si radio annulé cliqué, on stock "3"
        }
        //Cours
        if(!ongletGererCours.getSelectCours2().getSelectedItem().toString().equals("cours")){
            strings.add(ongletGererCours.getSelectCours2().getSelectedItem().toString()); //Cours selectionné
        }
        //Type
        if(!ongletGererCours.getSelectType2().getSelectedItem().toString().equals("type")){
            strings.add(ongletGererCours.getSelectType2().getSelectedItem().toString()); //Type selectionné
        }
        //Enseignants
        if(!ongletGererCours.getListeEnseignants2().getSelectedValuesList().isEmpty()) //Si liste enseignants non vide
            strings.add(ongletGererCours.getListeEnseignants2().getSelectedValuesList()); //On add
        else
            strings.add(new ArrayList<>());
        //Groupes
        if(!ongletGererCours.getListeGroupes2().getSelectedValuesList().isEmpty()) //Si liste groupes non vide
            strings.add(ongletGererCours.getListeGroupes2().getSelectedValuesList()); //On add
        else
            strings.add(new ArrayList<>());
        //Salles
        if(!ongletGererCours.getListeSalles2().getSelectedValuesList().isEmpty())//Si liste salles non vide
            strings.add(ongletGererCours.getListeSalles2().getSelectedValuesList());//On add
        else
            strings.add(new ArrayList<>());
        return strings;
    }
}
