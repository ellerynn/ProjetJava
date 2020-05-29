package vue;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * https://waytolearnx.com/2020/03/tester-si-une-annee-est-bissextile-en-java.html
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class OngletCours extends JTabbedPane {
    //Onglet Cours
    //Cours -> Emploi du temps
    private JComboBox<String> vueEdt;
    private JTextField rechercheBarre;
    private JButton rechercheBouton;
    private JComboBox<String> selectRecherche;
    private JComboBox<String> semaine;
    private JComboBox<String> groupes;
    private JTable tabEdt;
    private TableLabelRendererPanel p;
    //Cours -> Récapitulatifs des cours
    private JTable tabRecap;
    private  TableTreeRendererPanel p2;
    
    /**
     * constructeur
     */
    public OngletCours() { //Division en deux public void pour plus de clarté
        //Cours -> Emploi du temps
        vueEdt = new JComboBox<>();
        rechercheBarre = new JTextField();
        rechercheBouton = new JButton();
        selectRecherche = new JComboBox<>();
        semaine = new JComboBox<>();
        groupes = new JComboBox<>();
        tabEdt = new JTable();
        p = new TableLabelRendererPanel(tabEdt);
        //Cours -> Récapitulatifs des cours
        tabRecap = new JTable();
        p2 = new TableTreeRendererPanel(tabRecap);
        
        /*************************EMPLOI DU TEMPS*************************/
        JPanel cours = new JPanel();
        cours.setLayout(new GridBagLayout()); //Initialisation du container
        GridBagConstraints c = new GridBagConstraints(); //Contraintes d'ajout des composants
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.insets = new Insets(10,10,10,10);
        
        c.gridx = 0; c.gridy = 0; //Position
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_START;
        vueEdt.setModel(new DefaultComboBoxModel<>(new String[]{"en grille", "en liste"})); //Ajout des items au menu déroulant
        cours.add(vueEdt, c); //Ajout au conteneur     
        
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        cours.add(groupes, c);        
        
        c.gridx = 2;
        cours.add(selectRecherche, c);
        
        c.gridx = 3;
        rechercheBarre.setPreferredSize(new Dimension(250, 20));
        cours.add(rechercheBarre, c);
        
        c.gridx = 4; 
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_START;
        rechercheBouton.setIcon(new ImageIcon("images\\icon_recherche.png")); //Icone loupe dans bouton rechercher
        rechercheBouton.setPreferredSize(new Dimension(28, 28));
        cours.add(rechercheBouton, c);
        rechercheBarre.setVisible(false);
        rechercheBouton.setVisible(false);
        selectRecherche.setVisible(false);
        groupes.setVisible(false);
        
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx = 7;
        remplirComboBoxSemaine(semaine); //Remplir ma comboBox avec 52 valeurs
        Calendar cal = Calendar.getInstance();  //Date du jour  
        semaine.setSelectedItem(String.valueOf(cal.get(Calendar.WEEK_OF_YEAR))); //Valeur par défaut de ma JComboBox = semaine courante
        cours.add(semaine, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        setEdt(week); //Remplir le tableau Emploi du temps
        
        c.gridwidth = 8;   //2 columns wide
        c.gridx = 0; c.gridy = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        cours.add(p, c);
          
        this.add("Emploi du temps", cours);
        
        /*************************RECAPITULATIF DES COURS*************************/
        JPanel recapCours = new JPanel();
        recapCours.setLayout(new GridBagLayout()); //Initialisation du container
        GridBagConstraints t = new GridBagConstraints(); //Contraintes d'ajout des composants
        t.weightx = 1; t.weighty = 1;
        
        t.insets = new Insets(10,10,10,10);
        
        setRecap();
        
        t.gridwidth = 8;   //2 columns wide
        t.gridx = 0; t.gridy = 1;
        t.fill = GridBagConstraints.BOTH;
        recapCours.add(p2, t);
        
        this.add("Récapitulatif des cours", recapCours);
    }
    
    /**
     * retourne la barre de recherche
     * @return
     */
    public JTextField getRechercheBarre() {
        return this.rechercheBarre;
    }
    
    /**
     * retourne le bouton rechercher
     * @return
     */
    public JButton getRechercheBouton() {
        return this.rechercheBouton;
    }
    
    /**
     * retourne la JComboBox de recherche (utilisateurs de la BDD)
     * @return
     */
    public JComboBox getRecherche() {
        return this.selectRecherche;
    }
    
    /**
     * retourne la JComboBo de recherche (groupes)
     * @return
     */
    public JComboBox getGroupes() {
        return this.groupes;
    }
    
    /**
     * retourne la JComboBox des semaines 
     * @return
     */
    public JComboBox getSemaine() {
        return this.semaine;
    }
    
    /**
     * retourne le JTable contenant l'edt (cours) sur une semaine
     * @return
     */
    public JTable getEdt() {
        return this.tabEdt;
    }
    
    /**
     * retourne le JTable contenant le récapitulatif des séances de l'année scolaire en cours
     * @return
     */
    public JTable getRecap() {
        return this.tabRecap;
    }

    /**
     * remplo la JComboBox des semaines
     * @param box
     */
    public void remplirComboBoxSemaine(JComboBox box) {
        box.setModel(new DefaultComboBoxModel<>(new String[]{"Semaine"})); 
        for(int i = 1; i < 54; i++) {
            box.addItem(String.valueOf(i));
        }
    }
    
    /**
     * rempli une JComboBox avec l'ArrayList de string envoyé
     * @param box
     * @param string
     */
    public void remplirComboBox(JComboBox box, ArrayList<String> string) {
        box.setModel(new DefaultComboBoxModel<>()); 
        for(int i = 0; i < string.size(); i++) {
            box.addItem(string.get(i));
        }
    }
    
    /**
     * cf. méthode précédente, intitulé en plus
     * @param box
     * @param intitule
     * @param string
     */
    public void remplirComboBox(JComboBox box, String intitule, ArrayList<String> string) {
        box.setModel(new DefaultComboBoxModel<>(new String[]{intitule})); 
        for(int i = 0; i < string.size(); i++) {
            box.addItem(string.get(i));
        }
    }
    
    /**
     * mise à jour de l'edt en fonction de la semaine (entêtes et horaires)
     * @param semaine
     */
    public void setEdt(int semaine) {
        //A partir de la semaine en parametre, on veut récupérer les jours/mois de cette semaine
        Calendar cal = Calendar.getInstance(); //Date du jour
        int anneeScolaire = calculAnneeScolaire(); //2019
        
        cal.setWeekDate(cal.get(Calendar.YEAR), semaine, 2); //2 pour lundi ?
        
        if(cal.get(Calendar.MONTH)+1 >= 1 && cal.get(Calendar.MONTH)+1 <= 8 || semaine == 1)
            cal.setWeekDate(anneeScolaire+1, semaine, 2); //2 pour lundi ?
        
        else 
            cal.setWeekDate(anneeScolaire, semaine, 2); //2 pour lundi ?
                       
        //Maintenant, si j'affiche la date du jour, il me dira que nous sommes le lundi de la semaine (int semaine) de l'année courante
        int mois = cal.get(Calendar.MONTH)+1; //Mois courant = mois retourné + 1
        int jour = cal.get(Calendar.DAY_OF_MONTH); //Jour courant = jour retourné 
        String[] dates = {"lun.", "mar.", "mer.", "jeu.", "ven.", "sam."};
        String[] month = {" janvier", " février", " mars", " avril", " mai", " juin", 
                          " juillet", " août", " septembre", " octobre", " novembre", " décembre", " janvier"};
                
        String mois_nom = month[mois-1];
        
        for (int i=0;i<6;i++) {         
            //Blindage fin de mois !
            if(anneeBissextile(cal.get(Calendar.YEAR))) { //Si c'est une année bissextile
                if((jour > 29) && (cal.get(Calendar.MONTH) == 2)) {
                    jour = 1;
                    mois_nom = month[mois];
                }               
            }
            else {
                if((jour > 28) && (cal.get(Calendar.MONTH) == 2)) {
                    jour = 1;
                    mois_nom = month[mois];
                }
            }
                
            if((jour > 30) && (mois == 4 
                               || mois == 6 
                               || mois == 9 
                               || mois == 11)) {
                jour = 1;
                mois_nom = month[mois];
            }

            if(jour > 31) {
                jour = 1;
                mois_nom = month[mois];
            }  
            
            dates[i] = dates[i] + " " + jour++ + mois_nom;
        }
        
        tabEdt.setModel(new DefaultTableModel(new Object [][] { {"08h00"},{"08h15"},{"08h30"},{"08h45"}, 
                                                                {"09h00"},{"09h15"},{"09h30"},{"09h45"}, 
                                                                {"10h00"},{"10h15"},{"10h30"},{"10h45"}, 
                                                                {"11h00"},{"11h15"},{"11h30"},{"11h45"}, 
                                                                {"12h00"},{"12h15"},{"12h30"},{"12h45"}, 
                                                                {"13h00"},{"13h15"},{"13h30"},{"13h45"}, 
                                                                {"14h00"},{"14h15"},{"14h30"},{"14h45"}, 
                                                                {"15h00"},{"15h15"},{"15h30"},{"15h45"}, 
                                                                {"16h00"},{"16h15"},{"16h30"},{"16h45"}, 
                                                                {"17h00"},{"17h15"},{"17h30"},{"17h45"}, 
                                                                {"18h00"},{"18h15"},{"18h30"},{"18h45"}, 
                                                                {"19h00"},{"19h15"},{"19h30"},{"19h45"}, 
                                                                {"20h00"},{"20h15"},{"20h30"},{"20h45"}},
                                                new String[]{ "Horaires", dates[0], dates[1], dates[2], 
                                                              dates[3], dates[4],  dates[5]}) 
        { 
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        tabEdt.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (tabEdt.getColumnModel().getColumnCount() > 0) {
            tabEdt.getColumnModel().getColumn(0).setMaxWidth(55);
        }
        
        tabEdt.getTableHeader().setResizingAllowed(false); //On ne peut pas changer la taille des colonnes
        tabEdt.getTableHeader().setReorderingAllowed(false); //On ne peut pas échanger les colonnes de place
        tabEdt.setShowHorizontalLines(false); //On n'affiche pas les lignes horizontales
        
        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
        custom.setHorizontalAlignment(JLabel.CENTER);
        tabEdt.getColumnModel().getColumn(0).setCellRenderer(custom);
    }    
    
    /**
     * retourne true si l'annee est bissextile
     * @param annee
     * @return
     */
    public Boolean anneeBissextile(int annee) {
        if(annee%4 == 0) { 
            if(annee%100 == 0) { 
                if(annee%400 == 0)
                    return true;
                else 
                    return false;
            }
            else 
                return true;
        }
        return false;
    }
    
    /**
     * calcul de l'année scolaire en cours (retourne seulement le premier ex 2019/2020 -> 2019)
     * @return
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
     * misà jour du tableau contenant le récapitulatif (entêtes)
     */
    public void setRecap() {             
        tabRecap.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Matière", 
                                                                               "Première séance", 
                                                                               "Dernière séance", 
                                                                               "Durée", "Nb."}){ 
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        tabRecap.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (tabRecap.getColumnModel().getColumnCount() > 0) {
            tabRecap.getColumnModel().getColumn(3).setMaxWidth(55);
            tabRecap.getColumnModel().getColumn(4).setMaxWidth(30);
        }
        
        tabRecap.getTableHeader().setResizingAllowed(false); //On ne peut pas changer la taille des colonnes
        tabRecap.getTableHeader().setReorderingAllowed(false); //On ne peut pas échanger les colonnes de place
        
        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
        custom.setHorizontalAlignment(JLabel.CENTER);
        tabRecap.getColumnModel().getColumn(3).setCellRenderer(custom);
        tabRecap.getColumnModel().getColumn(4).setCellRenderer(custom);
        
    }
}

