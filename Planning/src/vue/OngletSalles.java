package vue;

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
    private JScrollPane scrollPaneRecapSalle;
    private JScrollPane scrollPaneSalle;
    private JComboBox<String> semaineSalle;
    private JComboBox<String> vueSalle;
    private JTable tabSalle;
    //Salles -> Salles libres
    private JTable tabLibresSalle;
    
    public OngletSalles() {
        emploiDuTempsSalle();
        sallesLibres();
    }

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

        scrollPaneSalle.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tabSalle.setModel(new DefaultTableModel(new Object[][]{}, new String[]{"Horaires", "[Date]", "[Date]", "[Date]", "[Date]", "[Date]", "[Date]"}));
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
}
