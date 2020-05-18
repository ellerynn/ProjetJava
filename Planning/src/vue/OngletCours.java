package vue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OngletCours extends JTabbedPane {
    //Onglet Cours
    //private JTabbedPane tabbedPaneCours;
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
    
    public OngletCours() {
        emploiDuTempsCours();
        recapCours();
    }
    
    public void emploiDuTempsCours() {
        paneCours = new JPanel();
        vueEdt = new JComboBox<>(); //Menu déroulant
        rechercheCours = new JComboBox<>();
        rechercheBarreCours = new JTextField(); 
        rechercheBoutonCours = new JButton();
        semaineCours = new JComboBox<>(); 
        scrollPaneCours = new JScrollPane(); 
        tabCours = new JTable(); 
        
        vueEdt.setModel(new DefaultComboBoxModel<>(new String[]{"en grille", "en liste"})); //Ajout des items au menu déroulant

        remplirComboBox(rechercheCours, "[NOM Prénom]", "[NOM Prénom]");

        rechercheBoutonCours.setIcon(new ImageIcon("images\\icon_recherche.png"));
        
        remplirComboBoxSemaine(semaineCours);
        
        scrollPaneCours.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tabCours.setModel(new DefaultTableModel(new Object[][]{}, new String[]{ "Horaires", "[Date]", "[Date]", "[Date]", "[Date]", "[Date]", "[Date]"}));
        tabCours.getTableHeader().setReorderingAllowed(false); //A TESTER
        
        scrollPaneCours.setViewportView(tabCours);
        
        GroupLayout c3 = new GroupLayout(paneCours);
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

        this.addTab("Emploi du temps", paneCours);
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
}
