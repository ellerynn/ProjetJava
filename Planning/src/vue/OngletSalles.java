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
    
    public JTextField rechercheBarreSalle() {
        return rechercheBarreSalle;
    }
    
    public JButton rechercheBoutonSalle() {
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

        setTableauEdt();
        tabSalle.getTableHeader().setReorderingAllowed(false);
        
        scrollPaneSalle.setViewportView(tabSalle);
        
        rechercheBoutonSalle.setIcon(new ImageIcon("images\\icon_recherche.png"));
        
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
        for(int i = 1; i < 53; i++) {
            box.addItem(String.valueOf(i));
        }
    }
    
    public void setTableauEdt() {  
        Calendar cal = Calendar.getInstance(); //Date du jour
        int mois = cal.get(Calendar.MONTH)+1; //Mois courant = mois retourné + 1
        int jour = cal.get(Calendar.DAY_OF_MONTH); //Jour courant = jour retourné 
        int jour_semaine = cal.get(Calendar.DAY_OF_WEEK); //Jour courant = jour retourné (1 = dimanche)
        String[] semaine = {"lun. ", "mar.", "mer.", "jeu.", "ven.", "sam."};
        String mois_nom = new String();
        
        //Convertir les int mois en string -> 1 = janvier
        switch(mois) {
            case 1: //JANVIER
                mois_nom = " janvier";
                break;
            case 2: //FEVRIER
                mois_nom = " février";
                break;
            case 3: //MARS
                mois_nom = " mars";
                break;
            case 4: //AVRIL
                mois_nom = " avril";
                break;
            case 5: //MAI
                mois_nom = " mai";
                break;
            case 6: //JUIN
                mois_nom = " juin";
                break;
            case 7: //JUILLET
                mois_nom = " juillet";
                break;
            case 8: //AOUT
                mois_nom = " août";
                break;
            case 9: //SEPTEMBRE
                mois_nom = " septembre";
                break;
            case 10: //OCTOBRE
                mois_nom = " octobre";
                break;
            case 11: //NOVEMBRE
                mois_nom = " novembre";
                break;
            case 12: //DECEMBRE
                mois_nom = " décembre";
                break;
        }
        
        switch(jour_semaine) {
            //SI DIMANCHE OU LUNDI, ON NE FAIT RIEN
            case 3: //MARDI
                jour = jour - 1; 
                break;
            case 4: //MERCREDI
                jour = jour - 2;
                break;
            case 5: //JEUDI
                jour = jour - 3;
                break;
            case 6: //VENDREDI
                jour = jour - 4; 
                break;
            case 7: //SAMEDI
                jour = jour - 5; 
                break;
        }
        
        for (int i=0;i<6;i++) {
            semaine[i] = semaine[i] + " " + jour++ + mois_nom;
        }
        
        tabSalle.setModel(new DefaultTableModel(new Object [][] { {"08h00"}, {"09h00"}, {"10h00"}, {"11h00"}, 
                                                                  {"12h00"}, {"13h00"}, {"14h00"}, {"15h00"}, 
                                                                  {"16h00"}, {"17h00"}, {"18h00"}, {"19h00"}, 
                                                                  {"20h00"}},
                                                new String[]{ "Horaires", semaine[0], semaine[1], semaine[2], 
                                                              semaine[3], semaine[4],  semaine[5]}) 
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
            tabSalle.getColumnModel().getColumn(0).setResizable(false);
            tabSalle.getColumnModel().getColumn(0).setPreferredWidth(20);
        }
        
        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
        custom.setHorizontalAlignment(JLabel.CENTER);
        tabSalle.getColumnModel().getColumn(0).setCellRenderer(custom);
    }     
}
