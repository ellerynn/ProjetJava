package vue;

import java.awt.Color;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Camille
 * @author Sutharsan
 * @author Emilie
 */
public class OngletSalles extends JTabbedPane {
    //Onglet Salles
    //Salles -> Emploi du temps
    private JComboBox<String> vueEdt;
    private JTextField rechercheBarre;
    private JButton rechercheBouton;
    private JComboBox<String> selectRecherche;
    private JComboBox<String> semaine;
    private JTable tabEdt;
    private JTable listeEdt;
    private TableLabelRendererPanel p;
    private TableLabelRendererPanel p1;
    //Salles -> Salles libres
    private JTable tabLibres;
    private JScrollPane p2;
    private JLabel periode;
    private JTextField rechercheBarre2;
    private JButton rechercheBouton2;
    private JComboBox<String> selectRecherche2;
    
    /**
     * constructeur
     */
    public OngletSalles() {
        //Salles -> Emploi du temps
        vueEdt = new JComboBox<>();
        rechercheBarre = new JTextField();
        rechercheBouton = new JButton();
        selectRecherche = new JComboBox<>();
        semaine = new JComboBox<>();
        tabEdt = new JTable();listeEdt = new JTable();
        p = new TableLabelRendererPanel(tabEdt);
        p1 = new TableLabelRendererPanel(listeEdt);
        //Salles -> Salles libres
        tabLibres = new JTable();
        p2 = new JScrollPane(tabLibres);
        periode = new JLabel("du X au X");
        rechercheBarre2 = new JTextField();
        rechercheBouton2 = new JButton();
        selectRecherche2 = new JComboBox<>();
        
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
               
        c.gridwidth = 8;   //2 columns wide
        c.gridx = 0; c.gridy = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        p.setVisible(false);
        cours.add(p, c);
        
        setListeEdt(week);
        p1.setBackground(Color.red);
        p1.setVisible(false);
        cours.add(p1, c);
          
        this.add("Emploi du temps", cours);
        
        /*************************SALLES LIBRES*************************/
        JPanel libres = new JPanel();
        libres.setLayout(new GridBagLayout()); //Initialisation du container
        GridBagConstraints t = new GridBagConstraints(); //Contraintes d'ajout des composants        
        t.insets = new Insets(10,10,10,10);
        
        t.gridx = 0; c.gridy = 0; //Position
        t.anchor = GridBagConstraints.LINE_START;
        t.fill = GridBagConstraints.HORIZONTAL;        
        libres.add(new JLabel("Salles libres : "), t);
        
        t.gridx = 1;
        libres.add(selectRecherche2, t);
                
        t.gridx = 3;
        rechercheBarre2.setPreferredSize(new Dimension(250, 20));
        libres.add(rechercheBarre2, t);
        
        t.gridx = 4; 
        t.fill = GridBagConstraints.NONE;
        t.anchor = GridBagConstraints.LINE_START;
        rechercheBouton2.setIcon(new ImageIcon("images\\icon_recherche.png")); //Icone loupe dans bouton rechercher
        rechercheBouton2.setPreferredSize(new Dimension(28, 28));
        libres.add(rechercheBouton2, t);
        
        t.gridx = 0; t.gridy = 1;
        periode.setVisible(false);
        libres.add(periode, t);
        
        t.weightx = 1; t.weighty = 1;
        setLibres();
        t.gridwidth = 8;   //2 columns wide
        t.gridx = 0; t.gridy = 2;
        t.fill = GridBagConstraints.BOTH;
        libres.add(p2, t);
        
        this.add("Salles libres", libres);
    }
    
    /**
     * retourne le JComboBox semaine
     * @return retourne le JComboBox semaine
     */
    public JComboBox getSemaine() {
        return this.semaine;
    }
    
    /**
     * retourne la barre de recherche
     * @return retourne la barre de recherche
     */
    public JTextField getRechercheBarre() {
        return this.rechercheBarre;
    }
    
    /**
     * retourne le bouton rechercher
     * @return retourne le bouton rechercher
     */
    public JButton getRechercheBouton() {
        return this.rechercheBouton;
    }
    
    /**
     * retourne le JTable contenant l'edt (salles) sur une semaine
     * @return retourne le JTable contenant l'edt (salles) sur une semaine
     */
    public JTable getEdt() {
        return this.tabEdt;
    }
    
    /**
     * retourne le JTable contenant l'edt (salles) sur une semaine
     * @return retourne le JTable contenant l'edt (salles) sur une semaine
     */
    public JTable getLibres() {
        return this.tabLibres;
    }
    
    /**
     * retourne la JComboBox de recherche (utilisateurs de la BDD)
     * @return retourne la JComboBox de recherche (utilisateurs de la BDD)
     */
    public JComboBox getRecherche() {
        return this.selectRecherche;
    }
    
     /**
     * retourne le JLabel contenant la periode
     * @return le JLabel contenant la periode
     */
    public JLabel getPeriode() {
        return this.periode;
    }
    
    /**
     * retourne la barre de recherche
     * @return retourne la barre de recherche
     */
    public JTextField getRechercheBarre2() {
        return this.rechercheBarre2;
    }
    
    /**
     * retourne le bouton rechercher
     * @return retourne le bouton rechercher
     */
    public JButton getRechercheBouton2() {
        return this.rechercheBouton2;
    }
    
    /**
     * retourne la JComboBox de recherche (utilisateurs de la BDD)
     * @return retourne la JComboBox de recherche (utilisateurs de la BDD)
     */
    public JComboBox getRecherche2() {
        return this.selectRecherche2;
    }
    
    /**
     * retourne le type de vue souhaité : en grille ou en liste
     * @return le type de vue souhaité : en grille ou en liste
     */
    public JComboBox getVue() {
        return this.vueEdt;
    }
    
    /**
     * retourne le conteneur de l'edt en grille
     * @return le conteneur de l'edt en grille
     */
    public TableLabelRendererPanel getGrille() {
        return this.p;
    }
    
    /**
     * retourne le conteneur de l'edt en liste
     * @return le conteneur de l'edt en liste
     */
    public TableLabelRendererPanel getListe() {
        return this.p1;
    }
    
    /**
     * retourne le JTable contenant l'edt (cours) sur une semaine
     * @return retourne le JTable contenant l'edt (cours) sur une semaine
     */
    public JTable getJTListe() {
        return this.listeEdt;
    }
    
    /**
     * rempli les JComboBox avec les numeros de semaines sur une année
     * @param box La JComboBox en question
     */
    public void remplirComboBoxSemaine(JComboBox box) {
        box.setModel(new DefaultComboBoxModel<>(new String[]{"Semaine"})); 
        for(int i = 1; i < 54; i++) {
            box.addItem(String.valueOf(i));
        }
    }
    
    /**
     * rempli un JComboBox avec un objet
     * @param box La JComboBox en question
     * @param intitule l'intitulé de la JComboBox
     * @param string les données à mettre dans la JComboBox
     */
    public void remplirComboBox(JComboBox box, String intitule, ArrayList<String> string) {
        box.setModel(new DefaultComboBoxModel<>(new String[]{intitule})); 
        for(int i = 0; i < string.size(); i++) {
            box.addItem(string.get(i));
        }
    }
    
    /**
     * mise à jour des entêtes de l'emploi du temps (dates) selon la semaine
     * @param semaine la semaine en question
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
        
        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
        custom.setHorizontalAlignment(JLabel.CENTER);
        tabEdt.getColumnModel().getColumn(0).setCellRenderer(custom);
        
        tabEdt.getTableHeader().setResizingAllowed(false); //On ne peut pas changer la taille des colonnes
        tabEdt.getTableHeader().setReorderingAllowed(false); //On ne peut pas échanger les colonnes de place
        tabEdt.setShowHorizontalLines(false); //On n'affiche pas les lignes horizontales    
    }    
    
    /**
     * Méthode qui permet de savoir si une année est bissextile
     * @param annee l'année en question
     * @return retourne true si une année est bissextile
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
     * calcul de l'année scolaire en cours
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
     * mise à jour de l'edt sous forme de liste en fonction de la semaine
     * @param semaine la semaine en question
     */
    public void setListeEdt(int semaine) {
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
        
        listeEdt.setModel(new DefaultTableModel() 
        { 
            boolean[] canEdit = new boolean [] {false};

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        ((DefaultTableModel) listeEdt.getModel()).setColumnCount(1);
        ((DefaultTableModel) listeEdt.getModel()).setRowCount(dates.length);
        System.out.println("lignes " + dates.length);
        
        for(int i=0;i<dates.length;i++)
            listeEdt.setValueAt(dates[i], i, 0);
        
        listeEdt.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        
        listeEdt.getTableHeader().setResizingAllowed(false); //On ne peut pas changer la taille des colonnes
        listeEdt.getTableHeader().setReorderingAllowed(false); //On ne peut pas échanger les colonnes de place
        listeEdt.setShowHorizontalLines(false); //On n'affiche pas les lignes horizontales
    }
    
    /**
     * misà jour du tableau contenant les salles libres (entêtes)
     */
    public void setLibres() {             
        tabLibres.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Jour", 
                                                                               "Horaires", 
                                                                               "Capacité"}){ 
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        tabLibres.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (tabLibres.getColumnModel().getColumnCount() > 0) {
            tabLibres.getColumnModel().getColumn(2).setMaxWidth(70);
        }
        
        tabLibres.getTableHeader().setResizingAllowed(false); //On ne peut pas changer la taille des colonnes
        tabLibres.getTableHeader().setReorderingAllowed(false); //On ne peut pas échanger les colonnes de place        
    }
}
