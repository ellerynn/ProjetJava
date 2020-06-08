package vue;

/*SOURCES : 
* https://docs.oracle.com/javase/tutorial/uiswing/components/spinner.html
* https://www.tutorialspoint.com/java/util/calendar_setfield2.htm
*/

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.text.Position;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class OngletServicePlanification extends JSplitPane {
    //Ajouter une seance
    private JList<String> listeSeances;
    private JSpinner date;
    private JRadioButton etatEC;
    private JRadioButton etatV;
    private JComboBox<String> selectCours;
    private JComboBox<String> selectType;
    private JList<String> listeEnseignants;
    private JList<String> listeGroupes;
    private JList<String> listeSalles;
    private JButton valider;
    //Modifier une seance
    private JList<String> listeSeances2;
    private JSpinner date2;
    private JRadioButton etatEC2;
    private JRadioButton etatV2;
    private JRadioButton etatA;
    private JComboBox<String> selectCours2;
    private JComboBox<String> selectType2;
    private JList<String> listeEnseignants2;
    private JList<String> listeGroupes2;
    private JList<String> listeSalles2;
    private JButton valider2;
    //Ajouter un cours
    private JTextField intitule;
    private JButton valider3;
    
    /**
     * constructeur
     */
    public OngletServicePlanification() {
        listeSeances = new JList<>();
        date = new JSpinner();
        etatEC = new JRadioButton("En cours de validation");
        etatV = new JRadioButton("Validée");
        selectCours = new JComboBox<>();
        selectType = new JComboBox<>();
        listeEnseignants = new JList<>();
        listeGroupes = new JList<>();
        listeSalles = new JList<>();
        valider = new JButton("Valider");
        
        listeSeances2 = new JList<>();
        date2 = new JSpinner();
        etatEC2 = new JRadioButton("En cours de validation");
        etatV2 = new JRadioButton("Validée");
        etatA = new JRadioButton("Annulée");
        selectCours2 = new JComboBox<>();
        selectType2 = new JComboBox<>();
        listeEnseignants2 = new JList<>();
        listeGroupes2 = new JList<>();
        listeSalles2 = new JList<>();
        valider2 = new JButton("Valider");
        
        intitule = new JTextField();
        valider3 = new JButton("Valider");
                       
        //Liste des séances a droite
        JScrollPane container1 = new JScrollPane(listeSeances);
        remplirListe(listeSeances);
        this.setRightComponent(container1);
        
        //A gauche
        JPanel container2 = new JPanel();
        container2.setLayout(new GridBagLayout()); //Initialisation du container
        GridBagConstraints c = new GridBagConstraints(); //Contraintes d'ajout des composants
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 1;
        
        /***********************************AJOUTER UNE SEANCE***********************************/
        c.insets = new Insets(0,10,0,0);
        JLabel titre1 = new JLabel("Ajouter une seance"); //JLabel titre
        
        c.gridx = 0; c.gridy = 0; //Position
        container2.add(titre1, c); //Ajout au conteneur
        
        c.insets = new Insets(0,0,0,0);
        JLabel dateHeure = new JLabel("Date et heure :"); //Sous-titre
        c.gridx = 1; c.gridy = 1; //On décalle tout de 1
        container2.add(dateHeure, c);
        
        c.gridx = 2; //On décalle juste la position en x -> alignement avec sous-titre
        Calendar calendar = Calendar.getInstance();
        
        Date initDate = calendar.getTime();
        calendar.add(Calendar.YEAR, -1);
        calendar.set(calculAnneeScolaire(), 8, 1);
        Date earliestDate = calendar.getTime();
        calendar.set(calculAnneeScolaire() + 1, 7, 31);
        Date latestDate = calendar.getTime();
        date.setModel(new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.DAY_OF_MONTH));
        date.setEditor(new JSpinner.DateEditor(date, "dd/MM/yyyy HH:mm"));
        container2.add(date, c);
        
        JLabel etats = new JLabel("Etat :");
        c.gridy = 2; c.gridx  = 1;
        container2.add(etats, c);
        
        ButtonGroup groupeEtat = new ButtonGroup();
        groupeEtat.add(etatEC);
        groupeEtat.add(etatV);
        
        c.gridx  = 2;
        container2.add(etatEC, c);
        c.gridx = 3;
        container2.add(etatV, c);
        
        JLabel cours = new JLabel("Cours :");
        c.gridy = 3; c.gridx = 1;
        container2.add(cours, c);
        
        c.gridx = 2;
        remplirComboBox(selectCours, "Veuillez sélectionner", "Cours");
        container2.add(selectCours, c);
        
        JLabel type = new JLabel("Type du cours : ");
        c.gridy = 4; c.gridx = 1;
        container2.add(type, c);
        
        c.gridx = 2;
        container2.add(selectType, c);
        
        c.gridy = 5; c.gridx = 1;
        JLabel profs = new JLabel("Enseignants :                  ");
        container2.add(profs, c);
        
        c.gridx = 2;
        JLabel tds = new JLabel("Groupes : ");
        container2.add(tds, c);
        
        c.gridx = 3;
        JLabel salles = new JLabel("Salles : ");
        container2.add(salles, c);
        
        c.fill = GridBagConstraints.BOTH;
        JScrollPane container3 = new JScrollPane(listeEnseignants);
        listeEnseignants.setVisibleRowCount(4);
        remplirListe(listeEnseignants);
	c.insets = new Insets(5,0,10,10);  //padding
	c.gridx = 1;       //aligned with date
	c.gridwidth = 1;   //2 columns wide
        c.gridy = 6;       //third row
        container2.add(container3, c);
        
        JScrollPane container4 = new JScrollPane(listeGroupes);
        listeGroupes.setVisibleRowCount(4);
        remplirListe(listeGroupes);
	c.gridx = 2;       //aligned with date
        container2.add(container4, c);
        
        JScrollPane container5 = new JScrollPane(listeSalles);
        listeSalles.setVisibleRowCount(4);
        remplirListe(listeSalles);
        c.gridwidth = 2;
	c.gridx = 3;       //aligned with date
        container2.add(container5, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;  
        c.gridx = 7;
        c.gridy = 6;
        c.insets = new Insets(0,0,10,10);
        container2.add(valider, c);
        
        c.insets = new Insets(0,0,0,0);  //padding
        
        /***********************************MODIFIER UNE SEANCE***********************************/
        c.insets = new Insets(0,10,0,0);
        JLabel titre2 = new JLabel("Modifier une seance"); //JLabel titre

        c.gridx = 0; c.gridy = 7; //Position
        container2.add(titre2, c); //Ajout au conteneur
        
        c.insets = new Insets(0,0,0,0);
        JLabel dateHeure2 = new JLabel("Date et heure :"); //Sous-titre
        c.gridx = 1; c.gridy = 8; //On décalle tout de 1
        container2.add(dateHeure2, c);
        
        c.gridx = 2; //On décalle juste la position en x -> alignement avec sous-titre
        date2.setModel(new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.DAY_OF_MONTH));
        date2.setEditor(new JSpinner.DateEditor(date2, "dd/MM/yyyy HH:mm"));
        container2.add(date2, c);
        
        JLabel etats2 = new JLabel("Etat :");
        c.gridy = 9; c.gridx  = 1;
        container2.add(etats2, c);
        
        ButtonGroup groupeEtat2 = new ButtonGroup();
        groupeEtat2.add(etatEC2);
        groupeEtat2.add(etatV2);
        groupeEtat2.add(etatA);
        
        c.gridx  = 2;
        container2.add(etatEC2, c);
        c.gridx = 3;
        container2.add(etatV2, c);
        c.gridx = 4;
        container2.add(etatA, c);
        
        JLabel cours2 = new JLabel("Cours :");
        c.gridy = 10; c.gridx = 1;
        container2.add(cours2, c);
        
        c.gridx = 2;
        remplirComboBox(selectCours2, "Veuillez sélectionner", "Cours");
        container2.add(selectCours2, c);
        
        JLabel type2 = new JLabel("Type du cours : ");
        c.gridy = 11; c.gridx = 1;
        container2.add(type2, c);
        
        c.gridx = 2;
        remplirComboBoxType(selectType2);
        container2.add(selectType2, c);
        
        c.gridy = 12; c.gridx = 1;
        JLabel profs2 = new JLabel("Enseignants : ");
        container2.add(profs2, c);
        
        c.gridy = 12; c.gridx = 2;
        JLabel tds2 = new JLabel("Groupes : ");
        container2.add(tds2, c);
        
        c.gridy = 12; c.gridx = 3;
        JLabel salles2 = new JLabel("Salles : ");
        container2.add(salles2, c);
        
        c.fill = GridBagConstraints.BOTH;
        JScrollPane container6 = new JScrollPane(listeEnseignants2);
        listeEnseignants2.setVisibleRowCount(4);
        remplirListe(listeEnseignants2);
	c.insets = new Insets(5,0,10,10);  //padding
	c.gridx = 1;       //aligned with date
        c.gridy = 13;       //third row
        container2.add(container6, c);
        
        JScrollPane container7 = new JScrollPane(listeGroupes2);
        listeGroupes2.setVisibleRowCount(4);
        remplirListe(listeGroupes2);
	c.gridx = 2;       //aligned with date
        container2.add(container7, c);
        
        JScrollPane container8 = new JScrollPane(listeSalles2);
        listeSalles2.setVisibleRowCount(4);
        remplirListe(listeSalles2);
        c.gridwidth = 2;   //2 columns wide
	c.gridx = 3;       //aligned with date
        container2.add(container8, c);  
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 1;  
        c.gridx = 7;
        c.gridy = 13;
        c.insets = new Insets(0,0,10,10);
        container2.add(valider2, c);
        
        c.insets = new Insets(0,10,0,0);  //padding
        
        /***********************************AJOUTER UN COURS***********************************/
        JLabel titre3 = new JLabel("Ajouter un cours"); //JLabel titre

        c.gridx = 0; c.gridy = 14; //Position
        container2.add(titre3, c); //Ajout au conteneur
        
        c.insets = new Insets(0,0,0,0);
        JLabel cours3 = new JLabel("Intitulé du cours : "); //Sous-titre
        c.gridx = 1; c.gridy = 15;  //On décalle tout de 1
        container2.add(cours3, c);
        
        c.gridx = 2; c.gridwidth = 4;
        c.insets = new Insets(0,0,0,10);
        container2.add(intitule, c);
        
        c.gridwidth = 1;  
        c.gridx = 7;
        c.gridy = 15;
        c.insets = new Insets(0,0,0,10);
        container2.add(valider3, c);
                
        this.setLeftComponent(container2);
        
    }
    
    /**
     * retourne selectType
     * @return retourne selectType
     */
    public JComboBox getSelectType() {
        return this.selectType;
    }

    /**
     * retourne selectType2
     * @return retourne selectType2
     */
    public JComboBox getSelectType2() {
        return this.selectType2;
    }

    /**
     * retourne selectCours
     * @return retourne selectCours
     */
    public JComboBox getSelectCours() {
        return this.selectCours;
    }

    /**
     * retourne selectCours2
     * @return retourne selectCours2
     */
    public JComboBox getSelectCours2() {
        return this.selectCours2;
    }

    /**
     * retourne listeSalles
     * @return retourne listeSalles
     */
    public JList getListeSalles() {
        return this.listeSalles;
    }

    /**
     * retourne listeSalles2
     * @return retourne listeSalles2
     */
    public JList getListeSalles2() {
        return this.listeSalles2;
    }

    /**
     * retourne listeGroupes
     * @return retourne listeGroupes
     */
    public JList getListeGroupes(){
        return this.listeGroupes;
    }

    /**
     * retourne listeGroupes2
     * @return retourne listeGroupes2
     */
    public JList getListeGroupes2(){
        return this.listeGroupes2;
    }

    /**
     * retourne listeEnseignants
     * @return retourne listeEnseignants
     */
    public JList getListeEnseignants(){
        return this.listeEnseignants;
    }

    /**
     * retourne listeEnseignants2
     * @return retourne listeEnseignants2
     */
    public JList getListeEnseignants2(){
        return this.listeEnseignants2;
    }

    /**
     * retourne listeSeances
     * @return retourne listeSeances
     */
    public JList getListeSeances(){
        return this.listeSeances;
    }

    /**
     * retourne listeSeances2
     * @return retourne listeSeances2
     */
    public JList getListeSeances2(){
        return this.listeSeances2;
    }

    /**
     * retourne valider
     * @return retourne valider
     */
    public JButton getBtnValider(){
        return this.valider;
    }

    /**
     * retourne valider2
     * @return retourne valider2
     */
    public JButton getBtnValider2(){
        return this.valider2;
    }

    /**
     * retourne valider3
     * @return retourne valider3
     */
    public JButton getBtnValider3(){
        return this.valider3;
    }

    /**
     * retourne date sous forme de String
     * @return retourne date sous forme de String
     */
    public String getDate() {
        String temp = String.valueOf(date.getValue()).substring(11, 19); //On récup l'heure
        String jour=DateFormat.getDateInstance(3).format(date.getValue()).substring(0,2); //On recup le jour
        String mois=DateFormat.getDateInstance(3).format(date.getValue()).substring(3,5); //On recup le mois
        String formatAnnee = DateFormat.getDateInstance(2).format(date.getValue());
        int posAnnee = formatAnnee.lastIndexOf(" ");
        String annee = formatAnnee.substring(posAnnee+1); //On récup l'année en yyyy
        
        
        temp +=" "+annee+"-"+mois+"-"+jour; //Et on assemble
        return temp;
    }
    
    /**
     * Méthode qui remplit une JList par défaut
     * @param liste la JList en question
     */
    public void remplirListe(JList liste) {
        Vector<String> listData = new Vector();
        for(int i=1;i<101;i++)
            listData.add("Item liste "+i);
        liste.setListData(listData);
    }
    
    /**
     * rempli une JComboBox avec un objet
     * @param box la JComboBox en question
     * @param intitule l'intitulé de la JComboBox
     * @param objet les données à mettre dans la JComboBox
     */
    public void remplirComboBox(JComboBox box, String intitule, Object objet) {
        box.setModel(new DefaultComboBoxModel<>(new String[]{intitule})); 
        for(int i = 1; i < 200; i++) {
            box.addItem(objet.toString());
        }
    }
    
    /**
     * Méthode qui remplit une JComboBox par défaut
     * @param box la JcomboBox en question
     */
    public void remplirComboBoxType(JComboBox box) {
        box.setModel(new DefaultComboBoxModel<>(new String[] { "Veuillez sélectionner", 
                                                               "Magistral", 
                                                               "TP", 
                                                               "TD", 
                                                               "Interactif", 
                                                               "Projet", 
                                                               "Soutien", 
                                                               "DS", 
                                                               "Partiel", 
                                                               "Rattrapage" }));
    }
    
    /**
     * rempli une JComboBox avec un ArrayList de string 
     * @param box la JcomboBox en question
     * @param intitule l'intituté de la JcomboBox en question
     * @param string les données à mettre dans la JComboBox
    */
    public void remplirComboBox(JComboBox box, String intitule, ArrayList<String> string) {
        box.setModel(new DefaultComboBoxModel<>(new String[]{intitule})); 
        for(int i = 0; i < string.size(); i++) {
            box.addItem(string.get(i));
        }
    }

    /**
     * rempli une liste avec un ArrayList de String
     * @param liste la JList en question
     * @param string les données à mettre dans la JList
     */
    public void remplirListe(JList liste, ArrayList<String> string) {
        Vector<String> listData = new Vector();
        for(String s : string)
            listData.add(s);
        liste.setListData(listData);
    }

    /**
     * Retourne etatEC
     * @return Retourne etatEC
     */
    public JRadioButton getEtatEC()
    {
        return etatEC;
    }

    /**
     * Retourne etatV
     * @return Retourne etatV
     */
    public JRadioButton getEtatV()
    {
        return etatV;
    }
    /**
     * Retourne Date2 sous forme de String
     * @return Retourne Date2 sous forme de String 
     */
    public String getDate2()
    {
        String temp = String.valueOf(date2.getValue()).substring(11, 19); //On récup l'heure
        String jour=DateFormat.getDateInstance(3).format(date2.getValue()).substring(0,2); //On recup le jour
        String mois=DateFormat.getDateInstance(3).format(date2.getValue()).substring(3,5); //On recup le mois
        String formatAnnee = DateFormat.getDateInstance(2).format(date.getValue());
        int posAnnee = formatAnnee.lastIndexOf(" ");
        String annee = formatAnnee.substring(posAnnee+1); //On récup l'année en yyyy
        temp +=" "+annee+"-"+mois+"-"+jour; //Et on assemble
        return temp;
    }
    /**
     * Retourne etatEC2
     * @return Retourne etatEC2
     */
    public JRadioButton getEtatEC2()
    {
        return etatEC2;
    }
    /**
     * Retourne etatV2
     * @return Retourne etatV2
     */
    public JRadioButton getEtatV2()
    {
        return etatV2;
    }
    /**
     * Retourne etatA
     * @return Retourne etatA
     */
    public JRadioButton getEtatA()
    {
        return etatA;
    }
    /**
     * Set le Jspinner date2 (date et heure)
     * @param val la valeur voulu
     */
    public void setDate2(String val)
    {
        if(!val.equals(getDate2())) //Si les données ne sont pas pareil, on set sinon on fait rien (pour éviter de rollback)
        {
            try { 
                Date laDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(val);
                date2.setValue(laDate);
            } catch (ParseException ex) {
                Logger.getLogger(OngletServicePlanification.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * calcul de l'année scolaire en cours (retourne seulement le premier ex 2019/2020 = 2019)
     * @return retourne l'année scolaire en cours
     */
    public int calculAnneeScolaire() {
        Calendar cal = Calendar.getInstance();
        int annee;
        
        if(cal.get(Calendar.MONTH)+1 >= 9 && cal.get(Calendar.MONTH)+1 <= 12) { //Entre septembre et décembre
            annee = cal.get(Calendar.YEAR);
        }
        
        else
            annee = cal.get(Calendar.YEAR)-1;
        return annee;
    }
    
    /**
     * Retourne les informations utiles saisies par l'user pour ajouter une séance
     * @return Retourne les informations utiles saisies par l'user pour ajouter une séance
     */
    public ArrayList<Object> getInfosAddSeance()
    {
        ArrayList<Object> strings = new ArrayList<>();
        boolean isOk = true;
        //BLINDAGE Decalaration
        String donnees = this.getDate();
        String date = donnees.substring(9,19);
        String heure = donnees.substring(0,8);
        Calendar cal = Calendar.getInstance();
        
        cal.set(Integer.parseInt(date.substring(0,4)), Integer.parseInt(date.substring(5,7))-1,Integer.parseInt(date.substring(8,10)),Integer.parseInt(heure.substring(0,2)),Integer.parseInt(heure.substring(3,5)),0);
        String jour =cal.getTime().toString();
        //Dimanche Samedi
        if(jour.contains("Sat") || jour.contains("Sun"))
        {
            System.out.println("Impssible d'ajouter une séance a un samedi ou dimanche.");
            isOk = false;
        }
        //avant 8h, après 19h (fermture 20h30)
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
        try {
            Date dateActuelle = format.parse(donnees);
            Date ouverture = format.parse("08:00:00 "+ date);
            Date fermeture = format.parse("19:00:00 "+ date);
            if(dateActuelle.before(ouverture))
            {
                System.out.println("Impossible l'école n'est pas ouvert.");
                isOk = false;
            }
            if(dateActuelle.after(fermeture))
            {
                System.out.println("Impossible l'école va être fermer avant même d'avoir finit la séance.");
                isOk = false;
            }
        } catch (ParseException ex) {
            Logger.getLogger(OngletServicePlanification.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(isOk)
        {
            //Semaine
            strings.add(String.valueOf(cal.get(Calendar.WEEK_OF_YEAR)));
            //Heure de debut
            strings.add(heure);             
            //Date
            strings.add(date);                                                    
            //Etat
            if(this.getEtatEC().isSelected())//Etat de la séance
            {
                strings.add("1"); //Si radio bouton EC cliqué, on stock "1" 
            }else if(this.getEtatV().isSelected()){
                strings.add("2"); //Si radio bouton V cliqué, on stock "2"
            }
            //Cours
            if(!this.getSelectCours().getSelectedItem().toString().equals("cours")){
                strings.add(this.getSelectCours().getSelectedItem().toString()); //Cours selectionné
            }
            //Type
            if(!this.getSelectType().getSelectedItem().toString().equals("type")){
                strings.add(this.getSelectType().getSelectedItem().toString()); //Type selectionné
            }
            //Enseignants
            if(!this.getListeEnseignants().getSelectedValuesList().isEmpty()) //Si liste enseignants non vide
                strings.add(this.getListeEnseignants().getSelectedValuesList()); //On add
            else
                strings.add(null);
            //Groupes
            if(!this.getListeGroupes().getSelectedValuesList().isEmpty()) //Si liste groupes non vide
                strings.add(this.getListeGroupes().getSelectedValuesList()); //On add
            else
                strings.add(null);
            //Salles
            if(!this.getListeSalles().getSelectedValuesList().isEmpty())//Si liste salles non vide
                strings.add(this.getListeSalles().getSelectedValuesList());//On add
            else
                strings.add(null);
        }
        //Si tout est bien saisie, la taille de size > 0 sinon = 0
        return strings;
    }
    /**
     * permet de selectionner le contenu des Jcombox, JradioButton, JList 
     * de l'onglet OngletGererCoursSP dans la partie Modifier une séance en fonction d'une séance séléctionnée
     * @param forBeingSelectedByDefault les données à être selectionner par défaut dans les JcomboBox, JSpinner, JList
     */
    public void dataToBeSelectedByDefault(ArrayList<Object> forBeingSelectedByDefault)
    {
        if (!forBeingSelectedByDefault.isEmpty())
        {
            for(int i = 0 ; i < forBeingSelectedByDefault.size() ; i++)
            {
                if(i==0)//Date et heure
                    this.setDate2((String)forBeingSelectedByDefault.get(i));
                if(i==1) //Etat
                {
                    if ((Integer)forBeingSelectedByDefault.get(i) == 1)
                        this.getEtatEC2().setSelected(true);
                    if((Integer)forBeingSelectedByDefault.get(i) == 2)
                        this.getEtatV2().setSelected(true);
                    if((Integer)forBeingSelectedByDefault.get(i) == 3)
                        this.getEtatA().setSelected(true);
                }
                if(i==2) //Cours
                    this.getSelectCours2().setSelectedItem((String)forBeingSelectedByDefault.get(i));
                if(i==3) //Type
                    this.getSelectType2().setSelectedItem((String)forBeingSelectedByDefault.get(i));
                
                if(i==4) //Enseignants
                {
                    int[] index = new int[((ArrayList<String>)forBeingSelectedByDefault.get(i)).size()];
                    for (int a = 0 ; a < ((ArrayList<String>)forBeingSelectedByDefault.get(i)).size(); a++)
                        index[a] =this.getListeEnseignants2().getNextMatch(((ArrayList<String>)forBeingSelectedByDefault.get(i)).get(a), 0, Position.Bias.Forward);
                    this.getListeEnseignants2().setSelectedIndices(index);
                }
                
                if(i==5) //Groupes 
                {
                    int[] index = new int[((ArrayList<String>)forBeingSelectedByDefault.get(i)).size()];
                    for (int a = 0 ; a < ((ArrayList<String>)forBeingSelectedByDefault.get(i)).size(); a++)
                        index[a] =this.getListeGroupes2().getNextMatch(((ArrayList<String>)forBeingSelectedByDefault.get(i)).get(a), 0, Position.Bias.Forward);
                    this.getListeGroupes2().setSelectedIndices(index);
                }
                if(i==6) //Salles
                {
                    int[] index = new int[((ArrayList<String>)forBeingSelectedByDefault.get(i)).size()];
                    for (int a = 0 ; a < ((ArrayList<String>)forBeingSelectedByDefault.get(i)).size(); a++)
                        index[a] =this.getListeSalles2().getNextMatch(((ArrayList<String>)forBeingSelectedByDefault.get(i)).get(a), 0, Position.Bias.Forward);
                    this.getListeSalles2().setSelectedIndices(index);
                }
            }
                
                    
        }//END OF IF
    }
    /**
     * Retourne les infos modifiés par l'user sur une séance donnée
     * @return Retourne les infos modifiés par l'user sur une séance donnée
     */
    public ArrayList<Object> getInfosModifSeance()
    {
        ArrayList<Object> strings = new ArrayList<>();
        boolean isOk = true;
        //BLINDAGE Decalaration
        String donnees = this.getDate2(); //Données date issu du Jspinner
        String date = donnees.substring(9,19);
        String heure = donnees.substring(0,8);
        Calendar cal = Calendar.getInstance();
        
        cal.set(Integer.parseInt(date.substring(0,4)), Integer.parseInt(date.substring(5,7))-1,Integer.parseInt(date.substring(8,10)));
        String jour =cal.getTime().toString();
        //Dimanche Samedi
        if(jour.contains("Sat") || jour.contains("Sun"))
        {
            System.out.println("Impssible d'ajouter une séance a un samedi ou dimanche.");
            isOk = false;
        }
        //avant 8h, après 19h (fermture 20h30)
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
        try {
            Date dateActuelle = format.parse(donnees);
            Date ouverture = format.parse("08:00:00 "+ date);
            Date fermeture = format.parse("19:00:00 "+ date);
            if(dateActuelle.before(ouverture))
            {
                System.out.println("Impossible l'école n'est pas ouvert.");
                isOk = false;
            }
            if(dateActuelle.after(fermeture))
            {
                System.out.println("Impossible l'école va être fermer avant même d'avoir finit la séance.");
                isOk = false;
            }
        } catch (ParseException ex) {
            Logger.getLogger(OngletServicePlanification.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(isOk)
        {
            //Semaine
            strings.add(String.valueOf(cal.get(Calendar.WEEK_OF_YEAR)));
            //Heure de debut
            strings.add(heure);       
            //Date
            strings.add(date);
            //Etat
            if(this.getEtatEC2().isSelected())//Etat de la séance
            {
                strings.add("1"); //Si radio bouton EC cliqué, on stock "1" 
            }else if(this.getEtatV2().isSelected()){
                strings.add("2"); //Si radio bouton V cliqué, on stock "2"
            }else if(this.getEtatA().isSelected()){
                strings.add("3"); //Si radio annulé cliqué, on stock "3"
            }
            //Cours
            if(!this.getSelectCours2().getSelectedItem().toString().equals("cours")){
                strings.add(this.getSelectCours2().getSelectedItem().toString()); //Cours selectionné
            }
            //Type
            if(!this.getSelectType2().getSelectedItem().toString().equals("type")){
                strings.add(this.getSelectType2().getSelectedItem().toString()); //Type selectionné
            }
            //Enseignants
            if(!this.getListeEnseignants2().getSelectedValuesList().isEmpty()) //Si liste enseignants non vide
                strings.add(this.getListeEnseignants2().getSelectedValuesList()); //On add
            else
                strings.add(new ArrayList<>());
            //Groupes
            if(!this.getListeGroupes2().getSelectedValuesList().isEmpty()) //Si liste groupes non vide
                strings.add(this.getListeGroupes2().getSelectedValuesList()); //On add
            else
                strings.add(new ArrayList<>());
            //Salles
            if(!this.getListeSalles2().getSelectedValuesList().isEmpty())//Si liste salles non vide
                strings.add(this.getListeSalles2().getSelectedValuesList());//On add
            else
                strings.add(new ArrayList<>());
        }
        return strings;
    }    

    /**
     * rempli les JComboBox de l'onglet Gérer les cours avec les types de cours
     * @param string Les données a mettre dans les JComBox dédiés aux types de cours 
     */
    public void remplirComboTypes(ArrayList<String> string) {
        remplirComboBox(getSelectType(),"type", string);
        remplirComboBox(getSelectType2(),"type", string);
    }

    /**
     * rempli les JComboBox de l'onglet Gérer les cours avec les intitulés des cours
     * @param string Les données a mettre dans les JComBox dédiés aux intitulés des cours
     */
    public void remplirComboCours(ArrayList<String> string) {
        remplirComboBox(getSelectCours(),"cours", string);
        remplirComboBox(getSelectCours2(),"cours", string);

    }

    /**
     * rempli les listes de l'onglet Gérer les cours avec les salles
     * @param string Les données a mettre dans les JList dédiés aux salles
     */
    public void remplirListSalle(ArrayList<String> string) {
        remplirListe(getListeSalles(), string);
        remplirListe(getListeSalles2(), string);
    }

    /**
     * rempli les listes de l'onglet Gérer les cours avec les groupes
     * @param string Les données a mettre dans les JList dédiés aux groupes
     */
    public void remplirListGroupes(ArrayList<String> string) {
        remplirListe(getListeGroupes(), string);
        remplirListe(getListeGroupes2(), string);
    }

    /**
     * rempli les listes de l'onglet Gérer les cours avec les enseignants
     * @param string Les données a mettre dans les JList dédiés aux enseignants
     */
    public void remplirListEnseignants(ArrayList<String> string) {
        remplirListe(getListeEnseignants(), string);
        remplirListe(getListeEnseignants2(), string);
    }

    /**
     * rempli les liste de l'onglet Gérer les cours avec les séances
     * @param string Les données a mettre dans les JList dédiés aux séances
     */
    public void remplirListSeances(ArrayList<String> string) {
        remplirListe(getListeSeances(), string);
        remplirListe(getListeSeances2(), string);
    }
    /**
     * Retourne ce que l'utilisateur à saisie pour l'intitulé d'un cours
     * @return Retourne ce que l'utilisateur à saisie pour l'intitulé d'un cours
     */
    public JTextField getIntitule()
    {
        return intitule;
    }
}
