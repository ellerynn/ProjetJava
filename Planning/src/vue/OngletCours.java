package vue;

import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

//La classe OngletCours correspond à l'onglet Cours de notre planning (classe EmploiDuTemps)
public class OngletCours extends JTabbedPane {
    //Onglet Cours
    //Cours -> Emploi du temps
    private JPanel paneCours;
    private JComboBox<String> vueEdt;
    private JTextField rechercheBarreCours;
    private JButton rechercheBoutonCours;
    private JComboBox<String> rechercheCours;
    private JScrollPane scrollPaneCours;
    private JComboBox<String> semaineCours;
    private JTable tabCours;
    //Cours -> Récapitulatifs des cours
    private JScrollPane scrollPaneRecapCours;
    private JTable tabRecapCours;
    
    public OngletCours() { //Division en deux public void pour plus de clarté
        emploiDuTempsCours();
        recapCours();
    }
    
    //Getters 
    public JPanel getPaneCours() {
        return paneCours;
    }
    
    public JComboBox<String> getVueEdt() {
        return vueEdt;
    }
    
    public JComboBox<String> getSemaineCours() {
        return semaineCours;
    }
    
    public JTextField rechercheBarreCours() {
        return rechercheBarreCours;
    }
    
    public JButton rechercheBoutonCours() {
        return rechercheBoutonCours;
    }
    
    public JComboBox<String> getRechercheCours() {
        return rechercheCours;
    }
    
    public JTable getTabCours() {
        return tabCours;
    }
    
    public JScrollPane getScrollPaneRecapCours() {
        return scrollPaneRecapCours;
    }
    
    public JTable getTabRecapCours() {
        return tabRecapCours;
    }
    
    //Méthodes
    public void emploiDuTempsCours() { //Construit l'onglet Emploi du temps
        paneCours = new JPanel();
        vueEdt = new JComboBox<>(); //Menu déroulant -> en grille ou en liste
        rechercheCours = new JComboBox<>(); //Pour sélectionner un élève -> A BLINDER
        rechercheBarreCours = new JTextField(); //Pour rechercher un élève avec son nom/prénom -> A BLINDER N'apparait que pour un référent ou admin
        rechercheBoutonCours = new JButton(); //Bouton rechercher -> A BLINDER
        semaineCours = new JComboBox<>(); //Pour sélectionner la semaine
        scrollPaneCours = new JScrollPane(); 
        tabCours = new JTable(); //Tableau affichage des groupes
        
        vueEdt.setModel(new DefaultComboBoxModel<>(new String[]{"en grille", "en liste"})); //Ajout des items au menu déroulant

        remplirComboBox(rechercheCours, "[NOM Prénom]", "[NOM Prénom]"); //Tous les étudiants de la BDD

        rechercheBoutonCours.setIcon(new ImageIcon("images\\icon_recherche.png")); //Icone loupe dans bouton rechercher
        
        remplirComboBoxSemaine(semaineCours); //Remplir ma comboBox avec 52 valeurs
        Calendar cal = Calendar.getInstance();  //Date du jour        
        semaineCours.setSelectedItem(String.valueOf(cal.get(Calendar.WEEK_OF_YEAR))); //Valeur par défaut de ma JComboBox = semaine courante
               
        scrollPaneCours.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); //Barre de scroll toujours la

        setTableauEdt(); //Remplir le tableau Emploi du temps
        
        tabCours.getTableHeader().setReorderingAllowed(false); //On ne peut pas échanger les colonnes de place
        
        scrollPaneCours.setViewportView(tabCours);
        
        GroupLayout c3 = new GroupLayout(paneCours); //Gérer l'affichage
        paneCours.setLayout(c3);
        c3.setHorizontalGroup(
                c3.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(c3.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(c3.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollPaneCours)
                                        .addGroup(c3.createSequentialGroup()
                                                .addComponent(vueEdt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rechercheCours, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rechercheBarreCours, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(rechercheBoutonCours, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                                                .addComponent(semaineCours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        c3.setVerticalGroup(
                c3.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(c3.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(c3.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(vueEdt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rechercheCours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rechercheBarreCours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(rechercheBoutonCours, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(semaineCours, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(scrollPaneCours, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                                .addContainerGap())
        );

        this.addTab("Emploi du temps", paneCours); //On ajoute l'emploi du temps à l'onglet Cours
    }
    
    public void recapCours() {
        scrollPaneRecapCours = new JScrollPane(); 
        tabRecapCours = new JTable(); 
        
        scrollPaneRecapCours.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tabRecapCours.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Matière", "Première séance", "Dernière séance", "Durée", "Nb."}));
        tabRecapCours.getTableHeader().setReorderingAllowed(false);
        scrollPaneRecapCours.setViewportView(tabRecapCours);

        this.addTab("Récapitulatif cours", scrollPaneRecapCours);
    }
    
    public void remplirComboBoxSemaine(JComboBox box) {
        box.setModel(new DefaultComboBoxModel<>(new String[]{"Semaine"})); 
        for(int i = 1; i < 53; i++) {
            box.addItem(String.valueOf(i));
        }
    }
    
    public void remplirComboBox(JComboBox box, String intitule, Object objet) {
        box.setModel(new DefaultComboBoxModel<>(new String[]{intitule})); 
        for(int i = 1; i < 200; i++) {
            box.addItem(objet.toString());
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
        
        tabCours.setModel(new DefaultTableModel(new Object [][] { {"08h00"}, {"09h00"}, {"10h00"}, {"11h00"}, 
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
        
        tabCours.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (tabCours.getColumnModel().getColumnCount() > 0) {
            tabCours.getColumnModel().getColumn(0).setPreferredWidth(20);
        }
        
        DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
        custom.setHorizontalAlignment(JLabel.CENTER);
        tabCours.getColumnModel().getColumn(0).setCellRenderer(custom);
    }    
}
