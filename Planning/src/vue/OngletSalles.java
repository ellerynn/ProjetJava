package vue;

import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;
import javax.swing.table.*;

public class OngletSalles extends JTabbedPane {
    //Salles -> Emploi du temps
    private JPanel paneSalle;
    private JTextField rechercheBarreSalle;
    private JButton rechercheBoutonSalle;
    private JComboBox<String> rechercheSalle;
    private JScrollPane scrollPaneSalle;
    private JComboBox<String> semaineSalle;
    private JComboBox<String> vueSalle;
    private JTable tabSalle;
    //Salles -> Salles libres
    private JTable tabLibresSalle;
    private JScrollPane scrollPaneRecapSalle;
    
    public OngletSalles() {
        emploiDuTempsSalle();
        sallesLibres();
    }
    
    //Getters
    public JPanel getPaneSalle() {
        return paneSalle;
    }
    
    public JTextField getRechercheBarreSalle() {
        return rechercheBarreSalle;
    }
    
    public JButton getRechercheBoutonSalle() {
        return rechercheBoutonSalle;
    }
    
    public JComboBox<String> getRechercheSalle() {
        return rechercheSalle;
    }
    
    public JComboBox<String> getSemaineSalle() {
        return semaineSalle;
    }
    
    public JComboBox<String> getVueSalle() {
        return vueSalle;
    }
    
    public JTable getTabSalles() {
        return tabSalle;
    }
    
    public JScrollPane getScrollPaneRecapSalle() {
        return scrollPaneRecapSalle;
    }
    
    public JTable getTabLibresSalle() {
        return tabLibresSalle;
    }
    
    //Méthodes
    public void emploiDuTempsSalle() {
        paneSalle = new JPanel(); 
        vueSalle = new JComboBox<>(); 
        rechercheSalle = new JComboBox<>(); 
        rechercheBarreSalle = new JTextField(); 
        rechercheBoutonSalle = new JButton();
        semaineSalle = new JComboBox<>(); 
        scrollPaneSalle = new JScrollPane(); 
        tabSalle = new JTable(); 
        
        vueSalle.setModel(new DefaultComboBoxModel<>(new String[]{"en grille", "en liste"}));

        remplirComboBox(rechercheSalle, "[Bat. N°]", "[Bat. N°]");

        remplirComboBoxSemaine(semaineSalle);
        Calendar cal = Calendar.getInstance();  // date du jour
        semaineSalle.setSelectedItem(String.valueOf(cal.get(Calendar.WEEK_OF_YEAR))); //Valeur par défaut de ma JComboBox

        scrollPaneSalle.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        int semaine = cal.get(Calendar.WEEK_OF_YEAR);
        setTableauEdt(semaine); //Remplir le tableau Emploi du temps
        
        tabSalle.getTableHeader().setReorderingAllowed(false);
        
        scrollPaneSalle.setViewportView(tabSalle);
        
        rechercheBoutonSalle.setIcon(new ImageIcon("images\\icon_recherche.png"));
        rechercheBarreSalle.setVisible(false);
        rechercheBoutonSalle.setVisible(false);
        rechercheSalle.setVisible(false);
        
        GroupLayout c4 = new GroupLayout(paneSalle);
        paneSalle.setLayout(c4);
        c4.setHorizontalGroup(
                c4.createParallelGroup(Alignment.LEADING)
                        .addGroup(c4.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(c4.createParallelGroup(Alignment.LEADING)
                                        .addComponent(scrollPaneSalle)
                                        .addGroup(c4.createSequentialGroup()
                                                .addComponent(vueSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(rechercheSalle, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(rechercheBarreSalle, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(rechercheBoutonSalle, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                                                .addComponent(semaineSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        c4.setVerticalGroup(
                c4.createParallelGroup(Alignment.LEADING)
                        .addGroup(c4.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(c4.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(vueSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rechercheSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rechercheBarreSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rechercheBoutonSalle, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(semaineSalle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(scrollPaneSalle, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                                .addContainerGap())
        );

        this.addTab("Emploi du temps", paneSalle);
    }
    
    public void sallesLibres() {
        scrollPaneRecapSalle = new JScrollPane(); 
        tabLibresSalle = new JTable(); 
        
        scrollPaneRecapSalle.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tabLibresSalle.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Site", "Date", "Durée", "Capacité"}));
        scrollPaneRecapSalle.setViewportView(tabLibresSalle);

        this.addTab("Salles libres", scrollPaneRecapSalle);
    }
    
    public void remplirComboBox(JComboBox box, String intitule, Object objet) {
        box.setModel(new DefaultComboBoxModel<>(new String[]{intitule})); 
        for(int i = 1; i < 200; i++) {
            box.addItem(objet.toString());
        }
    }
    
    public void remplirComboBoxSemaine(JComboBox box) {
        box.setModel(new DefaultComboBoxModel<>(new String[]{"Semaine"})); 
        for(int i = 1; i < 54; i++) {
            box.addItem(String.valueOf(i));
        }
    }
    
    public void setTableauEdt(int semaine) {
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
        String[] dates = {"lun. ", "mar.", "mer.", "jeu.", "ven.", "sam."};
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
        
        tabSalle.setModel(new DefaultTableModel(new Object [][] { {"08h00"}, {"09h00"}, {"10h00"}, {"11h00"}, 
                                                                  {"12h00"}, {"13h00"}, {"14h00"}, {"15h00"}, 
                                                                  {"16h00"}, {"17h00"}, {"18h00"}, {"19h00"}, 
                                                                  {"20h00"}},
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
        
        tabSalle.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (tabSalle.getColumnModel().getColumnCount() > 0) {
            tabSalle.getColumnModel().getColumn(0).setPreferredWidth(20);
        }
        
        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
        custom.setHorizontalAlignment(JLabel.CENTER);
        tabSalle.getColumnModel().getColumn(0).setCellRenderer(custom);
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
