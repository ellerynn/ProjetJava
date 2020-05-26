package vue;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class OngletSalles extends JTabbedPane {
    //Onglet Salles
    //Salles -> Emploi du temps
    private JComboBox<String> vueEdt;
    private JTextField rechercheBarre;
    private JButton rechercheBouton;
    private JComboBox<String> selectRecherche;
    private JComboBox<String> semaine;
    private JTable tabEdt;
    //Salles -> Salles libres
    private JTable tabLibres;
    
    public OngletSalles() {
        //Cours -> Emploi du temps
        vueEdt = new JComboBox<String>();
        rechercheBarre = new JTextField();
        rechercheBouton = new JButton();
        selectRecherche = new JComboBox<String>();
        semaine = new JComboBox<String>();
        tabEdt = new JTable();
        //Cours -> Récapitulatifs des cours
        tabLibres = new JTable();
        
        /*************************EMPLOI DU TEMPS*************************/
        JPanel cours = new JPanel();
        cours.setLayout(new GridBagLayout()); //Initialisation du container
        GridBagConstraints c = new GridBagConstraints(); //Contraintes d'ajout des composants
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        
        c.insets = new Insets(10,10,10,10);
        
        c.gridx = 0; c.gridy = 0; //Position
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_START;
        vueEdt.setModel(new DefaultComboBoxModel<>(new String[]{"en grille", "en liste"})); //Ajout des items au menu déroulant
        cours.add(vueEdt, c); //Ajout au conteneur     
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        remplirComboBox(selectRecherche, "[Salle]", "[Salle]"); //Tous les étudiants de la BDD
        cours.add(selectRecherche, c);
        
        c.gridx = 2;
        rechercheBarre.setPreferredSize(new Dimension(250, 20));
        cours.add(rechercheBarre, c);
        
        c.gridx = 3; 
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.LINE_START;
        rechercheBouton.setIcon(new ImageIcon("images\\icon_recherche.png")); //Icone loupe dans bouton rechercher
        rechercheBouton.setPreferredSize(new Dimension(28, 28));
        cours.add(rechercheBouton, c);
        
        c.anchor = GridBagConstraints.LINE_END;
        c.gridx = 7;
        remplirComboBoxSemaine(semaine); //Remplir ma comboBox avec 52 valeurs
        Calendar cal = Calendar.getInstance();  //Date du jour        
        semaine.setSelectedItem(String.valueOf(cal.get(Calendar.WEEK_OF_YEAR))); //Valeur par défaut de ma JComboBox = semaine courante
        cours.add(semaine, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        setEdt(week); //Remplir le tableau Emploi du temps
        
        tabEdt.getTableHeader().setReorderingAllowed(false); //On ne peut pas échanger les colonnes de place
        tabEdt.setRowHeight(100);
        c.gridwidth = 8;   //2 columns wide
        c.gridx = 0; c.gridy = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        cours.add(new JScrollPane(tabEdt), c);
        c.fill = GridBagConstraints.HORIZONTAL;
          
        this.add("Emploi du temps", cours);
        
        /*************************SALLES LIBRES*************************/
        JPanel libres = new JPanel();
        libres.setLayout(new GridBagLayout()); //Initialisation du container
        GridBagConstraints t = new GridBagConstraints(); //Contraintes d'ajout des composants
        t.weightx = 1;
        
        t.insets = new Insets(10,10,10,10);
        
        tabLibres.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Site", "Date", "Durée", "Capacité"}));
        tabLibres.getTableHeader().setReorderingAllowed(false); //On ne peut pas échanger les colonnes de place
        tabLibres.setRowHeight(100);
        
        t.gridwidth = 8;   //2 columns wide
        t.gridx = 0; c.gridy = 1;
        t.fill = GridBagConstraints.BOTH;
        libres.add(new JScrollPane(tabLibres), c);
        
        this.add("Salles libres", libres);
    }
    
    //Getters
    public JComboBox getSemaine() {
        return this.semaine;
    }
    
    public void remplirComboBoxSemaine(JComboBox box) {
        box.setModel(new DefaultComboBoxModel<>(new String[]{"Semaine"})); 
        for(int i = 1; i < 54; i++) {
            box.addItem(String.valueOf(i));
        }
    }
    
    public void remplirComboBox(JComboBox box, String intitule, Object objet) {
        box.setModel(new DefaultComboBoxModel<>(new String[]{intitule})); 
        for(int i = 1; i < 200; i++) {
            box.addItem(objet.toString());
        }
    }
    
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
            tabEdt.getColumnModel().getColumn(0).setPreferredWidth(20);
        }
        
        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
        custom.setHorizontalAlignment(JLabel.CENTER);
        tabEdt.getColumnModel().getColumn(0).setCellRenderer(custom);
    }    
    
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
}
